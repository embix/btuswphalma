/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.googlecode.btuswphalma.base.BoardMessage;
import com.googlecode.btuswphalma.base.GameEndMessage;
import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.LoginMessage;
import com.googlecode.btuswphalma.base.MessageType;
import com.googlecode.btuswphalma.base.MoveErrorMessage;
import com.googlecode.btuswphalma.base.MoveMessage;
import com.googlecode.btuswphalma.base.PlayerActivateMessage;
import com.googlecode.btuswphalma.base.PlayerFinishedMessage;
import com.googlecode.btuswphalma.base.ScoreMessage;
import com.googlecode.btuswphalma.serverengine.IDispatcher;

/**
 * @author sebastian
 * 
 */
public class Manager implements IManager, Runnable {

    /**
     * Die Adresse des Servers. Diese ist immer 1
     */
    private static final int SERVER_ID = 1;
    /**
     * Diese Adresse signalisiert, dass eine Message an alle gehen soll
     */
    // TODO woanders speichern
    private static final int BROADCAST_ID = 0;
    /**
     * Spiel mit Regelpruefung, ohne Veto
     */
    private static final int WITHOUT_VETO = 0;
    /**
     * Spiel mit einfacher Zugpruefung, mit Veto
     */
    @SuppressWarnings("unused")
    private static final int WITH_VETO = 1;
    /**
     * Die Anzahl der Millisekunden, die der Spielzug angezeigt werden soll
     */
    public static final long DISPLAY_TIME = 10000;
    /**
     * Die Queue, in die von aussen Nachrichten geschrieben werden. Diese
     * Nachrichten werden dann von dem laufenden thread verarbeitet
     */
    private ConcurrentLinkedQueue<IMessage> msgQueue;
    /**
     * Der Dispatcher dient dem Manager dazu Nachrichten an die Aussenwelt
     * schicken zu können
     */
    private IDispatcher dispatcher;
    /**
     * Das eigentliche Spiel. Hier ist das Spielbrett und die Spielerliste
     * gespeichert.
     */
    private Game game;
    /**
     * Die Gespielte Variante des Spiels.
     */
    private int gameVariant;
    /**
     * Wurde das Spiel angehalten/abgebrochen
     */
    private boolean gameHalted;

    /**
     * Manager mit Spielvariante ohne Veto wird erzeugt. Die Spieleranzahl wird
     * nur fuer das Game benoetigt.
     * 
     * @param numberOfPlayers
     * @param dispatcher
     */
    public Manager(int numberOfPlayers, IDispatcher dispatcher) {

	msgQueue = new ConcurrentLinkedQueue<IMessage>();
	try {
	    game = new Game(numberOfPlayers);
	} catch (Exception e) {
	    System.err.println("Couldn't create onject of type Game");
	    e.printStackTrace();
	    System.exit(1);
	}
	gameVariant = WITHOUT_VETO;
	gameHalted = false;
	this.dispatcher = dispatcher;
    }

    /**
     * Die Nachricht wird auf die Queue gelegt, und diese "benachrichtigt"
     * 
     * @see com.googlecode.btuswphalma.gameengine.IManager#acceptMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void acceptMessage(IMessage msg) {
	synchronized (msgQueue) {
	    msgQueue.add(msg);
	    msgQueue.notify();
	}
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
	// die Spieler werden hinzugefuegt
	runAddPlayers();
	// das eigentliche Spiel laeuft
	runGame();
	// das Spiel beenden
	runEnd();
    }

    /**
     * Die Endphase des Servers und der Clients wird eingeleitet.
     */
    private void runEnd() {
	sendGameEndMessages();
    }

    /**
     * An alle wird eine GameEndMessage geschickt
     */
    private void sendGameEndMessages() {
	IMessage gameEndMessage = createGameEndMessage(BROADCAST_ID);
	dispatcher.acceptMessage(gameEndMessage);
    }

    /**
     * Eine GameEndMessage wird zurueckgegeben
     * 
     * @param destination
     *                Die Zieladresse
     * @return die GameEndMessage
     */
    private IMessage createGameEndMessage(int destination) {
	return new GameEndMessage(SERVER_ID, destination);
    }

    /**
     * Das Spiel wird gespielt. Hier aber nur Entscheidung ueber Spieltyp.
     */
    private void runGame() {
	if (gameVariant == WITHOUT_VETO) {
	    runWithoutVeto();
	} else if (gameVariant == WITH_VETO) {
	    // TODO runWithVeto();
	}
	// Wenn keine der beiden Moeglichkeiten zutrifft (was nicht passieren
	// sollte) wird das Spiel in runEnd beendet
    }

    /**
     * Das Spiel ohne Veto Variante wird gespielt. Solange das Spielt laeuft,
     * wird eine Nachricht abgeholt und dann verarbeitet.
     */
    private void runWithoutVeto() {
	boolean playing = true;
	IMessage msg;
	while (playing && !gameHalted) {
	    // Der Spieler wird aufgefordert zu ziehen
	    initiateMove();
	    // Methode ist blockierend
	    msg = fetchMessage();
	    if (msg.getType() == MessageType.MT_MOVE) {
		// Wenn der Zug fehlerhaft war
		if (!processMove((MoveMessage) msg)) {
		    // es wird wieder ein Zug erwartet
		    dispatcher
			    .acceptMessage(createMoveErrorMessage("Ihr Zug war entsprach nicht den Regeln des Spiels Halma"));
		    initiateMove();
		    continue;
		}
		// kein else da continue im if Block darueber
		// Zug anzeigen
		showMove();
		// Warten damit die Clients sich den Zug anschauen koennen
		waitForTimeout();
		// Der Zug wird ausgefuehrt, also Spielbrett und Spieler
		// umsetzten. die notwendigen Nachrichten werden versendet.
		// Falls das Spiel fertig ist, wird false zurueckgegeben.
		playing = performMove();
	    } else if (msg.getType() == MessageType.MT_SAVE) {
		// TODO Hier Speichern
	    } else {
		stopGameOnError();
	    }
	}

    }

    /**
     * Der Zug wird ausgefuehrt. Je nach Ergebnis des Zuges werden
     * unterschiedliche Aktionen ausgefuehrt.
     * 
     * @return laeuft das Spiel weiter
     */
    private boolean performMove() {
	try {
	    switch (game.executeMove()) {
	    case Game.EXECUTE_MOVE_NORMAL:
		performNormalPlayerChange();
		break;
	    case Game.EXECUTE_MOVE_PLAYER_FINISHED:
		performFinishingPlayerChange();
		break;
	    case Game.EXECUTE_MOVE_GAME_FINISHED:
		sendResults();
		return false;
	    default:
		stopGameOnError();
		return false;
	    }
	} catch (Exception e) {
	    // TODO Excpetions
	    //e.printStackTrace();
	    //Das Spiel wird Einfach beendet
	    stopGameOnError();
	    return false;
	}
	// wenn Antwortmoeglichkeit "false" wird schon oben gegeben.
	return true;
    }

    /**
     * An alle Clients wird die Scorelist geschickt.
     */
    private void sendResults() {
	IMessage scrMsg = createScoreMessage(BROADCAST_ID);
	dispatcher.acceptMessage(scrMsg);
    }

    /**
     * Eine ScoreMessage wird erzeugt und zurueckgegeben.
     * 
     * @param destination
     *                die Zieladresse
     * @return die ScoreMessage
     */
    private IMessage createScoreMessage(int destination) {
	ScoreList scrLst = createScoreList();
	return new ScoreMessage(BROADCAST_ID, destination, scrLst);
    }

    /**
     * Es wird eine ScoreList erstellt.
     * 
     * @return die Scorelist
     */
    private ScoreList createScoreList() {
	ScoreList scoreList = new ScoreList();
	List<Player> plyrLst = new ArrayList<Player>(game.getPlayerList()
		.getPlayers());
	// ein Comperator der Spieler nach der Anzahl der benoetigten Runden
	// vergleicht.
	Comparator<Player> playerRoundComperator = new Comparator<Player>() {

	    public int compare(Player player1, Player player2) {
		int roundsPlayer1 = player1.getRounds();
		int roundsPlayer2 = player2.getRounds();
		/*
		 * Kein einfaches Abziehen der Werte, da Ueberlauf (allgemein,
		 * hier eher nicht) moeglich
		 */
		if (roundsPlayer1 < roundsPlayer2) {
		    return -1;
		} else if (roundsPlayer1 == roundsPlayer2) {
		    return 0;
		} else {
		    return 1;
		}
	    }
	};

	Collections.sort(plyrLst, playerRoundComperator);
	for (int i = 0, j = 1, k = 0; i < plyrLst.size(); i++) {
	    scoreList.addEntry(new ScoreEntry(j, plyrLst.get(i).getName(),
		    plyrLst.get(i).getRounds()));
	    // wenn der letzte Eintrag eingefuegt wurde, kann (darf) kein
	    // weiters j bestimmt werden.
	    if (i == plyrLst.size() - 1) {
		// die for Schleife wird abbrechen.
		continue;
	    }
	    // Das j fuer den naechsten Spieler bestimmen. Wenn mehrere Spieler
	    // in einer Runde fertig wurden haben sie denselben Platz.
	    // Uebersprungene Plaetze werden nicht vergeben, sonder
	    // uebersprungen
	    if (plyrLst.get(i).getRounds() < plyrLst.get(i + 1).getRounds()) {
		j = j + k + 1;
		k = 0;
	    } else {
		k++;
	    }
	}
	return scoreList;
    }

    /**
     * Durchfuehren eines Spielerwechsels. Ein Spieler wurde fertig. Dieser wird
     * benachrichtigt, sonst normaler Wechsel
     */
    private void performFinishingPlayerChange() {
	IMessage plyrFnshdMsg;
	// Der noch aktive Spieler ist fertig geworden, also erhaelt er eine
	// entsprechende Nachricht
	plyrFnshdMsg = createPlayerFinishedMessage(game.getActivePlayer());
	dispatcher.acceptMessage(plyrFnshdMsg);
	// nachdem der Spieler benachrichtigt wurde, ist der weitere
	// Spielerwechsel normal
	performNormalPlayerChange();
    }

    /**
     * Eine PlayerFinisheddMessage wird erzeugt
     * 
     * @param activePlayer
     *                die Nachricht ist ueblicherweise nur fuer den aktiven
     *                Spieler gedacht
     * @return eine PlayerFinishedMessage
     */
    private IMessage createPlayerFinishedMessage(int activePlayer) {
	return new PlayerFinishedMessage(SERVER_ID, activePlayer);
    }

    /**
     * Durchfuehren eines Spielerwechsels. Kein Spieler wurde fertig.
     */
    private void performNormalPlayerChange() {
	try {
	    game.executePlayerChange();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	initiateMove();
    }

    /**
     * Ein Zug wird initiiert. Dazu wird an alle das aktuelle Spielbrett
     * geschickt und der Spieler der jetzt an der Reihe ist, bekommt eine
     * Aktivierungsnachricht
     */
    private void initiateMove() {
	IMessage boardMsg;
	IMessage plyrActvtMsg;
	boardMsg = createBoardMessage(BROADCAST_ID);
	plyrActvtMsg = createPlayerActivateMessage(game.getActivePlayer());
	dispatcher.acceptMessage(boardMsg);
	dispatcher.acceptMessage(plyrActvtMsg);
    }

    /**
     * Erzeugt eine Nachricht fuer die Aktivierung des nachsten Spielers
     * 
     * @param destination
     *                die Zieladresse des zu aktivierenden Spielers
     * @return die PlayerActivateMessage
     */
    private IMessage createPlayerActivateMessage(int destination) {
	return new PlayerActivateMessage(SERVER_ID, game.getActivePlayer());
    }

    /**
     * Es wird gewartet, bis das Timeout ablaeuft. Diese Funktion dient dazu,
     * den Spielern Zeit zu lassen, sich den Spielzug anzuschaun.
     */
    private void waitForTimeout() {
	try {
	    Thread.sleep(DISPLAY_TIME);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Es werden die Nachrichten, die die GUI zum anzeigen eines Zuges braucht
     * erstellt und verschickt
     */
    private void showMove() {
	IMessage boardMsg;
	IMessage moveMsg;
	boardMsg = createBoardMessage(BROADCAST_ID);
	moveMsg = createMoveMessage(BROADCAST_ID);
	dispatcher.acceptMessage(boardMsg);
	dispatcher.acceptMessage(moveMsg);

    }

    /**
     * Es wird eine Nachricht erstellt, die den gespeicherten Zug des Spiels
     * enthaelt
     * 
     * @param destination
     *                die Zieleadresse
     * @return die MoveMessage
     */
    private IMessage createMoveMessage(int destination) {
	return new MoveMessage(SERVER_ID, destination, game.getKeptMove());
    }

    /**
     * Es wird eine Nachricht erstellt, die das aktuelle Board enhaelt.
     * 
     * @param destination
     *                die Zieladresse
     * @return ein Message mit einem Board
     */
    private IMessage createBoardMessage(int destination) {
	return new BoardMessage(SERVER_ID, destination, game.getBoard());
    }

    /**
     * @param msg
     * @return ist der Zug gelungen
     */
    private boolean processMove(final MoveMessage msg) {
	boolean result = false;
	try {
	    result = game.checkAndKeepMove(msg.getMove(), msg.getDestination());
	} catch (Exception e) {
	    stopGameOnError();
	    return false;
	}
	return result;

    }

    /**
     * Die Nachrichten zum Hinzufügen der Spieler wird abgeholt und verarbeitet.
     */
    private void runAddPlayers() {
	/*
	 * Solange das Spiel noch neue Spieler erwartet, werden neu Spieler
	 * hinzugefuegt.
	 */
	boolean adding = true;
	IMessage msg = null;
	while (adding && !gameHalted) {
	    // diese Methode ist blockierend
	    msg = fetchMessage();
	    if (msg.getType() == MessageType.MT_LOGIN) {
		// wenn der letzte Spieler hinzugefuegt wurde, endet die
		// Schleife
		adding = addPlayer((LoginMessage) msg);
	    } else {
		// Ein falscher Nachrichtentyp fuehrt zum Abbruch
		stopGameOnError();
	    }
	}
    }

    /**
     * Eine Nachricht wird von der Warteschlange geholt. Diese Methode ist
     * blockierend.
     * 
     * @return eine von der Warteschlange geholte Message
     */
    private IMessage fetchMessage() {
	IMessage msg = null;
	/*
	 * Es wird auf die Queue synchronisiert, weil aus ihr etwas entnommen
	 * werden soll.
	 */
	synchronized (msgQueue) {
	    /*
	     * Die Schleife verhindert dass wir weitermachen, obwohl jemand
	     * anders die Queue wieder leer gemacht hat (hier ueberflüssig)
	     */
	    while((msg = msgQueue.poll()) == null ) {
		try {
		    msgQueue.wait();
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		// msg = msgQueue.remove();
	    }
	}
	return msg;
    }

    /**
     * Ein neuer Spieler wird zum Game hinzugefuegt.
     * 
     * @param msg
     *                die LoginMessage eines Spielers
     * @return sind noch weitere Spieler hinzufuegen
     */
    private boolean addPlayer(final LoginMessage msg) {
	boolean result = false;
	try {
	    result = game.addPlayer(msg.getSource(), msg.getName());
	} catch (Exception e) {
	    e.printStackTrace();
	    stopGameOnError();
	}
	return result;
    }

    /**
     * Das Spiel wird geordnet beendet
     */
    private void stopGameOnError() {
	// TODO Server geordnet beenden, Clients benachrichtigen
	// da bei Erstellung die geordnete Beendigung noch nicht zur Verfuegung
	// stand wird der Server abgeschossen, Clients werden ignoriert.
	//System.err.println("sebastian ist fuer dieses exit verantwortlich");
	//System.exit(1);
	gameHalted = true;
    }

    /**
     * Es wird eine MoveErrorMassage zurueckgegeben
     * 
     * @param string
     * @return die MoveErrorMassage
     */
    private IMessage createMoveErrorMessage(String string) {
	return new MoveErrorMessage(SERVER_ID, game.getActivePlayer(), string);
    }

}
