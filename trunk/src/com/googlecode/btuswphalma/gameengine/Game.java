package com.googlecode.btuswphalma.gameengine;

/**
 * Diese Klasse realisiert das eigentliche Halmaspiel. Nachdem alle Spieler
 * hinzugefügt wurden, beginnt das eigentliche Spiel. Züge werden übergeben und
 * geprüft. Wenn ein Zug die Prüfung übersteht, muss er noch bestetigt werden
 * und dann der Spielerwechselvolzogen werden.
 * 
 * @author sebastian
 * 
 */
public class Game {
    
    /**
     * Zustand in dem Spieler hinzugefügt werden
     */
    private static final int ADDING_PLAYERS = 0;
    /**
     * Zustand in dem auf die Eingabe eines Zuges gewartet wird
     */
    private static final int WAITING_MOVE = 1;
    /**
     * Zustand, in dem auf Bestätigung oder Verwerfen des Zuges von aussen gewartet wird
     */
    private static final int KEEPING_MOVE = 2;
    /**
     * Der Zug ist ausgefuehrt, der Spieler noch nicht gewechselt
     */
    private static final int HOLDING_PLAYER = 3;
    /**
     * Das Spiel ist abgeschlossen
     */
    private static final int FINISHED = 4;
    /**
     * Der Zug wurde ohne besondere Vorkommnisse durchgefuehrt
     */
    public static final int EXECUTE_MOVE_NORMAL = 0;
    /**
     * Ein Spieler wurde mit diesem Zug fertig
     */
    public static final int EXECUTE_MOVE_PLAYER_FINISHED = 1;
    /**
     * Das Spiel wurde mit diesem Zug beendet
     */
    public static final int EXECUTE_MOVE_GAME_FINISHED = 2;

    /**
     * Rundenzähler
     */
    private int round;
    /**
     * Die Nummer des gerade aktiven Spielers
     */
    private int activePlayer;
    /**
     * Zug der noch auf die Auführung wartet
     */
    private HalmaMove keptMove;
    /**
     * Die Anzahl der Spieler, die an dem Spiel teilnimmt. (Alle Spieler, aktive
     * und Beobachter)
     */
    private int numberOfPlayers;
    /**
     * Das Spielbrett
     */
    private Board board;
    /**
     * Die Liste der Spieler
     */
    private PlayerList playerList;
    /**
     * Der Zugprüfer
     */
    private IHalmaMoveChecker moveChecker;
    /**
     * Der Endeprüfer
     */
    private FinishChecker finishChecker;
    /**
     * Der Zustand in dem sich das Spielobjekt gerade befindet
     */
    private int gameState;

    /**
     * Es wird ein Spiel für die Anzahl an Spielern gesartet.
     * 
     * @param numberOfPlayers die gewuenschte Spielerzahl
     * @throws Exception 
     */
    public Game(int numberOfPlayers) throws Exception {
	if (numberOfPlayers < 2 || numberOfPlayers == 5 || numberOfPlayers > 6) {
	    //falsche anzahl an Spielern
	    throw new Exception("wrong number of players");
	}
	round = 0;
	activePlayer = 0;
	keptMove = null;
	this.numberOfPlayers = numberOfPlayers;
	board = new Board(numberOfPlayers);
	playerList = new PlayerList(numberOfPlayers);
	moveChecker = new RulesHalmaMoveChecker();
	finishChecker = new FinishChecker();
	gameState = ADDING_PLAYERS;
    }
    
    /**
     * Wenn das Spiel noch neue Spieler werwartet kann ein Spieler hinzugefügt werden
     * 
     * @param id die Nummer des Spielers (von 1-6)
     * @param name der Name des Spielers
     * @return Koennen noch weitere Spieler hunzugefuegt werden
     * @throws Exception Methode wird im falschen Zustand aufgerufen
     */
    public boolean addPlayer(int id, String name) throws Exception {
	if (gameState != ADDING_PLAYERS) {
	    throw new Exception("game is in wrong state for this method");//TODO Exception Klasse
	}
	//Der maximale Integerwert als initiale Rundenzahl, bei der gewonnen wurde.
	playerList.addPlayer(new Player(id,name,false,Integer.MAX_VALUE));
	if (playerList.getSize() == numberOfPlayers) {
	    activePlayer = 1;
	    round = 1;
	    gameState = WAITING_MOVE;
	    //kein Spieler kann mehr hinzgefuegt werden
	    return false;
	}
	//es koennen noch Spieler hinzugefuegt werden
	return true;
    }
    
    

    /**
     * Es wird ein HalmaMove geprüft und gegebenfalls gespeichert. Nur wenn die
     * Prüfung des Zugs diesen als korrekt verifiziert hat, wird der Zug
     * gespeichert. Zurückgegebn wird das Ergebnis der Prüfung.
     * 
     * @param move
     *                Der Spielzug
     * @param player
     *                Der Spieler der ihn durchführt
     * @return Ergebnis der Zugprüfung
     * @throws Exception 
     */
    public boolean checkAndKeepMove(HalmaMove move, int player) throws Exception {
	
	boolean result = false; //Das Ergebnis, ist es ein korrekter Zug
	if(gameState != WAITING_MOVE) {
	    throw new Exception("game is in wrong state for this method");//TODO Exception Klasse
	}
	//nur der aktive Spieler darf einen Zug machen
	if (player != activePlayer) {
	    result = false;
	    return result;
	}
	//der Zug wird geprueft
	result = moveChecker.checkMove(board, move, activePlayer);
	if (result) {
	    keptMove = move;
	    gameState=KEEPING_MOVE;
	}

	return result;
    }

    /**
     * Der Zug wird ausgeführt, also das Spielbrett umgesetzt. Der aktive
     * Spieler wird nicht geändert.
     * 
     * @return Normaler Zug oder ein Spieler ist fertig oder das Spiel ist zu
     *         Ende.
     * @throws Exception 
     */
    public int executeMove() throws Exception {
	Player player;
	int result = EXECUTE_MOVE_NORMAL;
	if(gameState != KEEPING_MOVE) {
	    throw new Exception("game is in wrong state for this method");//TODO Exception Klasse
	}
	board.exchangePositionState(keptMove.getStartPosition(), keptMove.getEndPosition());
	gameState = HOLDING_PLAYER;
	if(finishChecker.checkPlayerCompleted(board, activePlayer, numberOfPlayers)) {
	    /* 
	     * Wenn Spieler fertig , dann Ergebnis fuer Spieler eintragen und pruefen, ob
	     * Spiel fertig ist
	     */
	    player = playerList.getPlayer(activePlayer);
	    player.setRound(round);
	    player.setSpec(true);
	    if (finishChecker.checkGameFinished(playerList)) {
		result = EXECUTE_MOVE_GAME_FINISHED;
		gameState = FINISHED;
	    } else {
		result = EXECUTE_MOVE_PLAYER_FINISHED;
	    }
	}
	return result;
    }

    /**
     * Der gespeicherte Zug wird zurückgenommen. Wenn das Spiel nicht beendet
     * ist, soll danach wieder ein neuer Zug angenommen werden können.
     * @throws Exception 
     */
    public void discardMove() throws Exception {
	if(gameState != KEEPING_MOVE) {
	    throw new Exception("game is in wrong state for this method");//TODO Exception Klasse
	}
	keptMove = null;
	gameState = WAITING_MOVE;

    }

    /**
     * Der aktive Spieler wird auf den nächsten Spieler gesetzt, der an der
     * Reihe ist.
     * @throws Exception 
     */
    public void executePlayerChange() throws Exception {
	int oldActivePlayer = activePlayer;
	int nextPlayerId;
	Player player;
	if(gameState != HOLDING_PLAYER) {
	    throw new Exception("game is in wrong state for this method");//TODO Exception Klasse
	}
	
	for (int i = 0; i < numberOfPlayers - 1; i++) {
	    /*
	     * Spielernummern von 1 bis numberOfPlayers, beginnend bei dem Spieler nach dem aktiven
	     * pruefen, ob ie noch im Spiel sind
	     */
	    nextPlayerId = ((activePlayer + i) % numberOfPlayers) + 1;
	    player = playerList.getPlayer(nextPlayerId);
	    if(!player.getSpectator()) {
		activePlayer = nextPlayerId;
		//Schleife kann/muss abgebrochen werden
		break;
	    }
	}
	if(activePlayer < oldActivePlayer) {
	    //die naechste Runde wurde begonnen
	    round++;
	} else if (activePlayer == oldActivePlayer) {
	    //Fehlzustand, das sollte nicht passieren
	    throw new Exception("found no next player"); //TODO Exception Klasse
	}

    }

    /**
     * Die Runde, in der wir uns gerade befinden
     * 
     * @return die aktuelle Runde
     */
    public int getRound() {
	return round;
    }

    /**
     * Die Nummer des gerade aktiven Spielers
     * 
     * @return aktiver Spieler
     */
    public int getActivePlayer() {
	return activePlayer;
    }

    /**
     * Gibt den gespeicherten HalmaMove zurück
     * 
     * @return der gespeicherte HalmaMove
     */
    public HalmaMove getKeptMove() {
	return keptMove;
    }

    /**
     * Gibt das Board zurück
     * 
     * @return das Board
     */
    public Board getBoard() {
	return board;
    }

    /**
     * gibt die Spielerliste zurück
     * 
     * @return the playerList
     */
    public PlayerList getPlayerList() {
	return playerList;
    }

}
