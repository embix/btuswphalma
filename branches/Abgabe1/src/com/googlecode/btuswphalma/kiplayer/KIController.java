/**
 * 
 */
package com.googlecode.btuswphalma.kiplayer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.googlecode.btuswphalma.base.BoardMessage;
import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.LoginMessage;
import com.googlecode.btuswphalma.base.MessageAddresses;
import com.googlecode.btuswphalma.base.MoveMessage;
import com.googlecode.btuswphalma.base.PlayerListMessage;
import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;
import com.googlecode.btuswphalma.gameengine.RulesHalmaMoveChecker;
import com.googlecode.btuswphalma.network.ClientNetCom;

/**
 * Der KIController empfaengt und versendet Nachrichten. Zuege werden mit einem
 * 
 * @author sebastian
 * 
 */

public class KIController extends Thread {

    /**
     * Ein Objekt, welches einen Algorithmus kapselt
     */
    private IKIAlgorithm algorithm;

    /**
     * Die aktuelle Version des Bretts
     */
    private Board board;

    /**
     * die aktuelle Version der Spielerliste
     */
    private PlayerList playerList;

    /**
     * Der gerade aktive Spieler
     */
    private int activePlayer;

    /**
     * Die Verbindung zum Server
     */
    private ClientNetCom netCom;

    /**
     * Objekt zum Anzeigen einer neuen Nachricht
     */
    private Object notifyObject;

    /**
     * Regelpruefer fuer den Modus mit Veto
     */
    @SuppressWarnings("unused")
    private RulesHalmaMoveChecker rulesChecker;

    /**
     * Zeigt an, ob das Spiel noch laeuft
     */
    private boolean running;

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
     * Die Gespielte Variante des Spiels.
     */
    private int gameVariant;

    /**
     * Mit diesem Konstruktor wird eine KI fuer ein Spiel ohne Veto erzeugt.
     * 
     * @param algorithm
     *                der KI Algorithmus
     * @param host
     *                Domainame, oder IP-Adresse des Servers
     * @param port
     *                die TCP-Portnummer des Servers
     * @param name
     *                der Spielername
     */
    public KIController(IKIAlgorithm algorithm, String host, int port,
	    String name) {
	if (algorithm == null || host == null || name == null) {
	    // TODO Fehler anzeigen?
	    return;
	}

	this.algorithm = algorithm;
	running = true;
	try {
	    netCom = new ClientNetCom(InetAddress.getByName(host), port);
	    notifyObject = netCom.getNotifyObject();
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    running = false;
	    e.printStackTrace();
	    return;
	}
	// erst zum empfangen klar machen, dann senden
	// bei Fehlern wird nicht gestartet, siehe oben
	this.start();
	netCom.sendMessage(new LoginMessage(1, 1, name));
    }

    /**
     * Der Controller empfaengt, verarbeitet und versendet Nachrichten. Am
     * Schluss beendet er sich sauber.
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
	runGame();
	runEnd();
    }

    /**
     * Solange das Spiel laeuft, werden Nachrichten abgeholt und verarbeitet
     */
    private void runGame() {
	IMessage msg;
	while (running) {
	    while ((msg = netCom.getMessage()) == null) {
		try {
		    synchronized (notifyObject) {
			notifyObject.wait();
		    }
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	    processMessage(msg);
	}
    }

    /**
     * Der Controller wird sauber beendet
     */
    private void runEnd() {
	netCom.interrupt();
    }

    /**
     * Eine Nachricht wird entsprechend ihres Typs verarbeitet
     * 
     * @param msg
     *                die zu verarbeitende Nachricht.
     */
    private void processMessage(IMessage msg) {

	switch (msg.getType()) {
	case MT_MOVE:
	    processMove((MoveMessage) msg);
	    break;
	case MT_BOARD:
	    processBoard((BoardMessage) msg);
	    break;
	case MT_GAMEEND:
	    // TODO was dann?
	    running = false;
	    break;
	case MT_MOVEERROR:
	    // TODO Darf nicht passieren, beenden?
	    running = false;
	    break;
	case MT_PLAYERACTIVATE:
	    makeMove();
	    break;
	case MT_PLAYERDATA:
	    processPlayerData((PlayerListMessage) msg);
	    break;
	case MT_PLAYERFINISHED:
	    // TODO noch weitermachen?
	    break;
	case MT_SCORES:
	    // kann ignoriert werden.
	    break;
	case MT_TERMINATE:
	    // Kann der Client diesen Typ bekommen?
	    running = false;
	    break;
	default:
	    // Fehler
	    break;

	}

    }

    /**
     * Eine erhaltene Zugnachricht wird gegebenenfalls ueberprueft
     * 
     * @param msg
     *                eine Zugnachricht
     */
    private void processMove(MoveMessage msg) {
	if (gameVariant == WITHOUT_VETO) {
	    return;
	} else if (gameVariant == WITH_VETO) {
	    // TODO testAndVetoMove(msg.getMove());
	}
    }

    /**
     * Das neu Brett und der aktive Spieler werden gespeichert
     * 
     * @param msg
     *                die Brettnachricht
     */
    private void processBoard(BoardMessage msg) {
	board = msg.getBoard();
	activePlayer = msg.getActivePlayer();
    }

    /**
     * Anhand der gespeicherten Daten wird ein Zug berechnet und gesendet.
     */
    private void makeMove() {
	final HalmaMove move = algorithm.computeMove(board, playerList,
		activePlayer);
	netCom.sendMessage(new MoveMessage(activePlayer,
		MessageAddresses.MANAGER_ADDRESS, move));
	System.err.println("sent: " + move);
    }

    /**
     * Die aktuelle Spielerliste werden gespeichert
     * 
     * @param msg
     *                Nachricht mit der Spielerliste
     */
    private void processPlayerData(PlayerListMessage msg) {
	playerList = msg.getPlayerList();
    }

}
