package com.googlecode.btuswphalma.kiplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMath;
import com.googlecode.btuswphalma.gameengine.PlayerList;

/**
 * @author sebastian
 * 
 */
public class StaticKIMethods {

    /**
     * Zur Markierung eines erreichten Feldes
     */
    private static final byte USED_POSITION = -2;

    /**
     * Fuer jede "Spielrichtung" gibt es eine Matrix die die Positionen bewertet
     */
    private static final int[][][] corridorRating = {
	    { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 12, 12, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 23, 23, 23, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 33, 33, 33, 33, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 42, 42, 42, 42, 42, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 50, 50, 50, 50, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 57, 57, 57, 57, 57, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 63, 63, 63, 63, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 68, 68, 68, 68, 68, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 72, 72, 72, 72, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 75, 75, 75, 75, 75, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 77, 77, 77, 77, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 78, 78, 78, 78, 78, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 100, 100, 100, 100, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 410, 410, 410, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 1640, 1640, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 4920, 0, 0, 0, 0, 0, 0 } },

	    { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 42, 33, 23, 12, 0 },
		    { 0, 0, 0, 0, 0, 0, 57, 50, 42, 33, 23, 12, 0 },
		    { 0, 0, 0, 0, 0, 68, 63, 57, 50, 42, 33, 23, 0 },
		    { 0, 0, 0, 75, 72, 68, 63, 57, 50, 42, 33, 0, 0 },
		    { 0, 0, 78, 77, 75, 72, 68, 63, 57, 50, 42, 0, 0 },
		    { 0, 100, 78, 77, 75, 72, 68, 63, 57, 0, 0, 0, 0 },
		    { 0, 410, 100, 78, 77, 75, 72, 68, 0, 0, 0, 0, 0 },
		    { 1640, 410, 100, 78, 77, 75, 0, 0, 0, 0, 0, 0, 0 },
		    { 4920, 1640, 410, 100, 78, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

	    { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 4920, 1640, 410, 100, 78, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 1640, 410, 100, 78, 77, 75, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 410, 100, 78, 77, 75, 72, 68, 0, 0, 0, 0, 0 },
		    { 0, 100, 78, 77, 75, 72, 68, 63, 57, 0, 0, 0, 0 },
		    { 0, 0, 78, 77, 75, 72, 68, 63, 57, 50, 42, 0, 0 },
		    { 0, 0, 0, 75, 72, 68, 63, 57, 50, 42, 33, 0, 0 },
		    { 0, 0, 0, 0, 0, 68, 63, 57, 50, 42, 33, 23, 0 },
		    { 0, 0, 0, 0, 0, 0, 57, 50, 42, 33, 23, 12, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 42, 33, 23, 12, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

	    { { 0, 0, 0, 0, 0, 0, 4920, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 1640, 1640, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 410, 410, 410, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 100, 100, 100, 100, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 78, 78, 78, 78, 78, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 77, 77, 77, 77, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 75, 75, 75, 75, 75, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 72, 72, 72, 72, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 68, 68, 68, 68, 68, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 63, 63, 63, 63, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 57, 57, 57, 57, 57, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 50, 50, 50, 50, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 42, 42, 42, 42, 42, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 33, 33, 33, 33, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 23, 23, 23, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 12, 12, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

	    { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 78, 100, 410, 1640, 4920 },
		    { 0, 0, 0, 0, 0, 0, 75, 77, 78, 100, 410, 1640, 0 },
		    { 0, 0, 0, 0, 0, 68, 72, 75, 77, 78, 100, 410, 0 },
		    { 0, 0, 0, 57, 63, 68, 72, 75, 77, 78, 100, 0, 0 },
		    { 0, 0, 42, 50, 57, 63, 68, 72, 75, 77, 78, 0, 0 },
		    { 0, 33, 42, 50, 57, 63, 68, 72, 75, 0, 0, 0, 0 },
		    { 0, 23, 33, 42, 50, 57, 63, 68, 0, 0, 0, 0, 0 },
		    { 12, 23, 33, 42, 50, 57, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 12, 23, 33, 42, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

	    { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 12, 23, 33, 42, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 12, 23, 33, 42, 50, 57, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 23, 33, 42, 50, 57, 63, 68, 0, 0, 0, 0, 0 },
		    { 0, 33, 42, 50, 57, 63, 68, 72, 75, 0, 0, 0, 0 },
		    { 0, 0, 42, 50, 57, 63, 68, 72, 75, 77, 78, 0, 0 },
		    { 0, 0, 0, 57, 63, 68, 72, 75, 77, 78, 100, 0, 0 },
		    { 0, 0, 0, 0, 0, 68, 72, 75, 77, 78, 100, 410, 0 },
		    { 0, 0, 0, 0, 0, 0, 75, 77, 78, 100, 410, 1640, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 78, 100, 410, 1640, 4920 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    /**
     * Wichtungsfaktor fuer die naechsten Zuege
     */
    private static final int NEXT_MOVES_DIVISOR = 10;

    /**
     * Eine Spielsituation wird fuer einen bestimmten Spieler bewertet,
     * 
     * @param board
     *                Das zu bewertende Spielbrett
     * @param playerList
     *                Die Spielerliste
     * @param player
     *                der Spieler
     * @return Bewertung der Spielsituation
     */
    public static int evaluateGameSituation(Board board, PlayerList playerList,
	    int player) {

	int value = 0;
	List<ArrayList<BoardPosition>> nextMoves;

	if (board == null || playerList == null || player < 1
		|| player > playerList.getSize()) {
	    // TODO exception?
	    return Integer.MIN_VALUE;
	}
	// Auswerten der Spielerliste / Rangfolge
	// TODO
	// Bewerten der Steinpositionen
	value += evaluatePositions(board, player, playerList.getSize());
	// Zuege nach vorne finden
	nextMoves = findNextMoves(board, player);
	// Zuege nach vorne Bewerten (Haelfte des Ziels?)
	value += evaluateNextMoves(nextMoves, player, playerList.getSize())
		/ NEXT_MOVES_DIVISOR;
	return value;
    }

    /**
     * Die naechsten Zuege werden heuristisch bewertet
     * 
     * @param nextMoves
     *                die naechsten Zuege
     * @param player
     *                der Spieler
     * @param plyrNum
     *                die Anzahl der Spieler
     * @return Die Bewertung
     */
    private static int evaluateNextMoves(
	    List<ArrayList<BoardPosition>> nextMoves, int player, int plyrNum) {
	// TODO Auto-generated method stub
	int corridorIndex = getCorridorIndex(player, plyrNum);
	int[][] values = corridorRating[corridorIndex];
	int value = 0;
	int helpValue;

	for (ArrayList<BoardPosition> move : nextMoves) {

	    helpValue = values[move.get(move.size() - 1).getXPos()][move.get(
		    move.size() - 1).getYPos()];
	    helpValue -= values[move.get(0).getXPos()][move.get(0).getYPos()];
	    if (helpValue > 0) {
		value += helpValue;
	    }

	}

	return value;
    }

    /**
     * Findet die naechsten moeglichen Zeuge finden
     * 
     * @param board
     *                Das Spielbrett
     * @param player
     *                Der Spieler
     * @return Die moeglichen naechsten Zuege
     */
    private static List<ArrayList<BoardPosition>> findNextMoves(Board board,
	    int player) {

	List<BoardPosition> playerPos = getPlayerPoses(board, player);
	List<ArrayList<BoardPosition>> playerMoves = new LinkedList<ArrayList<BoardPosition>>();

	for (BoardPosition workingPos : playerPos) {
	    playerMoves.addAll(StaticKIMethods.findMoves(board, workingPos));
	}
	return playerMoves;
    }

    /**
     * Liefert die Positionen zurueck, auf denen auf denen Spielsteine des
     * Spielers stehen.
     * 
     * @param board
     *                Das Spielbrett
     * @param player
     *                Der Spieler
     * @return Liste mit Positionen
     */
    private static List<BoardPosition> getPlayerPoses(Board board, int player) {

	ArrayList<BoardPosition> playerPos = new ArrayList<BoardPosition>(10);
	byte[][] brdArray = board.getBoardArryClone();
	// TODO Zahl auslagern
	// nur gueltige Spielernummern
	if (player < 1 || player > 6) {
	    return playerPos;
	}

	for (byte i = 0; i < brdArray.length; i++) {
	    for (byte j = 0; j < brdArray[i].length; j++) {
		if (player == brdArray[i][j]) {
		    playerPos.add(new BoardPosition(i, j));
		}
	    }
	}
	return playerPos;
    }

    /**
     * Die Stellung auf dem Spielbrett wird fuer einen Spieler bewertet.
     * 
     * @param board
     *                Das Spielbrett
     * @param player
     *                Der Spieler
     * @param plyrNum
     *                Die Anzahl der Spieler
     * @return Bewertung der Stellung
     */
    private static int evaluatePositions(Board board, int player, int plyrNum) {

	byte[][] brdArray = board.getBoardArryClone();
	int corridorIndex = getCorridorIndex(player, plyrNum);
	int[][] values = corridorRating[corridorIndex];
	int value = 0;

	for (int i = 0; i < brdArray.length; i++) {
	    for (int j = 0; j < brdArray[i].length; j++) {
		if (brdArray[i][j] == player) {
		    value += values[i][j];
		}
	    }
	}
	return value;
    }

    /**
     * Der Index der Bewertungsmatrix wird zurueckgegeben.
     * 
     * @param player
     *                Die Spielernummer
     * @param plyrNum
     *                Die Anzahl der Spieler
     * @return Index der Bewertungsmatrix
     */
    private static int getCorridorIndex(int player, int plyrNum) {
	if (player < 1 || player > plyrNum) {
	    return -1;
	}
	switch (plyrNum) {
	case 2:
	    return (player - 1) * 3;
	case 3:
	    return (player - 1) * 2;
	case 4:
	    return player + player / 3;
	case 6:
	    return player - 1;
	default:
	    return -1;
	}
    }

    /**
     * Fuer ein Spielbrett werden alle moeglichen Zuege von einer Position aus
     * zurueckgegeben. Dabei wird nicht kontrolliert, ob auf der Position ein
     * Spielstein steht.
     * 
     * @param board
     *                das Spielbrett
     * @param startPos
     *                die Startposition
     * @return Eine Liste von Zuegen, noch als ArrayListe gespeichert
     */
    public static List<ArrayList<BoardPosition>> findMoves(Board board,
	    BoardPosition startPos) {
	// Darstellung des Spielbretts als Array
	byte[][] brdArray = board.getBoardArryClone();
	// moegliche Abstaende fuer Spruenge
	byte[][] jumpDistances = { { 2, -1 }, { 2, 1 }, { 0, -2 }, { 0, 2 },
		{ -2, -1 }, { -2, 1 } };
	// abhaengig von der Teilbarkeit durch 2, "schiefer" Schub
	byte shiftedY = (byte) (startPos.getXPos() % 2 == 0 ? -1 : 1);
	// moegliche Abstaende fuer Schuebe
	byte[][] pushDistances = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 },
		{ 1, shiftedY }, { -1, shiftedY } };
	// Durch Spruenge neu erreichte Positionen
	Queue<BoardPosition> reachedPoses = new LinkedList<BoardPosition>();
	// Eine Map, in der die Zuege gespeicherten sind, mit denen man zu der
	// Position kommt, die Key ist
	Map<BoardPosition, ArrayList<BoardPosition>> moveMap = new HashMap<BoardPosition, ArrayList<BoardPosition>>(
		20);

	// Die Initialen Zuege berechnen
	computeStartJumps(startPos, brdArray, jumpDistances, reachedPoses,
		moveMap);
	// Einen neutralen Sprung berechnen, falls vorhanden
	computeNeutralMove(startPos, moveMap);
	// laengere Spruenge berechnen
	computeSequelJumps(brdArray, jumpDistances, reachedPoses, moveMap);
	// Schuebe berechnen
	computePushes(startPos, brdArray, pushDistances, moveMap);

	return new ArrayList<ArrayList<BoardPosition>>(moveMap.values());
    }

    /**
     * Fuer ein Startposition werden fuer alle Felder, die mit einem einzigen
     * Teilsprung erreichber sind dieser Teilsprung gespeichert.
     * 
     * @param startPos
     *                die Startposition der Zuege
     * @param brdArray
     *                Darstellung des Spielbretts
     * @param jumpDistances
     *                die Sprungdistanzen
     * @param reachedPoses
     *                Durch Spruenge neu erreichte Positionen
     * @param moveMap
     *                Map mit den Spruengen fuer Ein- und Rueckgabe
     */
    private static void computeStartJumps(BoardPosition startPos,
	    byte[][] brdArray, final byte[][] jumpDistances,
	    Queue<BoardPosition> reachedPoses,
	    Map<BoardPosition, ArrayList<BoardPosition>> moveMap) {
	ArrayList<BoardPosition> newMove;
	BoardPosition workingPos;
	BoardPosition helpPos;
	byte newX;
	byte newY;
	for (int i = 0; i < jumpDistances.length; i++) {
	    newX = (byte) (startPos.getXPos() + jumpDistances[i][0]);
	    newY = (byte) (startPos.getYPos() + jumpDistances[i][1]);
	    // Feld leer und nicht erreicht
	    if (HalmaMath.isOnBoard(newX, newY) && brdArray[newX][newY] == 0) {
		workingPos = new BoardPosition(newX, newY);
		helpPos = HalmaMath.getOverleaptPos(startPos, workingPos);
		// Ein Sprung ist moeglich
		if (brdArray[helpPos.getXPos()][helpPos.getYPos()] > 0) {
		    newMove = new ArrayList<BoardPosition>();
		    newMove.add(startPos);
		    newMove.add(workingPos);
		    moveMap.put(workingPos, newMove);
		    reachedPoses.add(workingPos);
		    // Position als erreicht markieren
		    brdArray[newX][newY] = USED_POSITION;
		}
	    }
	}
    }

    /**
     * <p>
     * Fuer alle Felder, die mit einer Folge von Teilspruengen aus erreicht
     * werden koennen wird ein Zug berechnet Dazu werden jeweils fuer alle schon
     * erreichten Felder die Spruenge hinzugenommen, mit denen man unerreichte
     * Felder erreicht, bis keine neuen hinzukommen, und ein Fixpunkt erreicht
     * ist.
     * </p>
     * <p>
     * Damit diese Prozedur korrekt funktioniert, muss computeStartJumps davor
     * ausgefuehrt werden
     * </p>
     * 
     * @param brdArray
     *                Darstellung des Spielbretts
     * @param jumpDistances
     *                die Sprungdistanzen
     * @param reachedPoses
     *                Durch Spruenge neu erreichte Positionen
     * @param moveMap
     *                Map mit den Spruengen fuer Ein- und Rueckgabe
     */
    private static void computeSequelJumps(byte[][] brdArray,
	    byte[][] jumpDistances, Queue<BoardPosition> reachedPoses,
	    Map<BoardPosition, ArrayList<BoardPosition>> moveMap) {

	ArrayList<BoardPosition> newMove;
	BoardPosition hopPos;
	BoardPosition workingPos;
	BoardPosition helpPos;
	byte newX;
	byte newY;

	while (!reachedPoses.isEmpty()) {
	    hopPos = reachedPoses.poll();
	    for (int i = 0; i < jumpDistances.length; i++) {
		newX = (byte) (hopPos.getXPos() + jumpDistances[i][0]);
		newY = (byte) (hopPos.getYPos() + jumpDistances[i][1]);
		// Feld leer und nicht erreicht
		if (HalmaMath.isOnBoard(newX, newY)
			&& brdArray[newX][newY] == 0) {
		    workingPos = new BoardPosition(newX, newY);
		    helpPos = HalmaMath.getOverleaptPos(hopPos, workingPos);
		    // Ein Sprung ist moeglich
		    if (brdArray[helpPos.getXPos()][helpPos.getYPos()] > 0) {
			newMove = new ArrayList<BoardPosition>(moveMap
				.get(hopPos));
			newMove.add(workingPos);
			moveMap.put(workingPos, newMove);
			reachedPoses.add(workingPos);
			// Position als erreicht markieren
			brdArray[newX][newY] = USED_POSITION;
		    }
		}
	    }
	}
    }

    /**
     * <p>
     * Aus einem "minimalen Sprung" wird ein Sprung erzeugt, der wieder zum
     * Ausgangspunkt zurueckfuehrt, berechnet
     * </p>
     * <p>
     * Damit diese Prozedur korrekt funktioniert, darf nur und muss
     * computeStartJumps davor ausgefuehrt werden
     * </p>
     * 
     * @param startPos
     *                die Startposition der Zuege
     * @param moveMap
     *                Map mit den Spruengen fuer Ein- und Rueckgabe
     */
    private static void computeNeutralMove(BoardPosition startPos,
	    Map<BoardPosition, ArrayList<BoardPosition>> moveMap) {
	ArrayList<BoardPosition> newMove;
	if (!moveMap.isEmpty()) {
	    // newMove einfach benutzen?
	    newMove = new ArrayList<BoardPosition>(moveMap.values().iterator()
		    .next());
	    newMove.add(startPos);
	    moveMap.put(startPos, newMove);
	}
    }

    /**
     * Alle moeglichen Zuge werden ueberprueft und gegebenenfalls gespeichert
     * 
     * @param startPos
     *                die Startposition der Zuege
     * @param brdArray
     *                Darstellung des Spielbretts
     * @param pushDistances
     *                die Schubdistanzen
     * @param moveMap
     *                moveMap Map mit den Spruengen fuer Rueckgabe
     */
    private static void computePushes(BoardPosition startPos,
	    byte[][] brdArray, byte[][] pushDistances,
	    Map<BoardPosition, ArrayList<BoardPosition>> moveMap) {
	ArrayList<BoardPosition> newMove;
	BoardPosition workingPos;
	byte newX;
	byte newY;
	for (int i = 0; i < pushDistances.length; i++) {
	    newX = (byte) (startPos.getXPos() + pushDistances[i][0]);
	    newY = (byte) (startPos.getYPos() + pushDistances[i][1]);
	    if (HalmaMath.isOnBoard(newX, newY) && brdArray[newX][newY] == 0) {
		workingPos = new BoardPosition(newX, newY);
		newMove = new ArrayList<BoardPosition>();
		newMove.add(startPos);
		newMove.add(workingPos);
		moveMap.put(workingPos, newMove);
	    }
	}
    }

}
