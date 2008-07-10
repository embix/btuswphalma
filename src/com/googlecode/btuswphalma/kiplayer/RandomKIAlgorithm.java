package com.googlecode.btuswphalma.kiplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMath;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;

/**
 * Mit diesem Algorithmus wird von einem zufaelligen Spieler ein zufaelliger,
 * korrekter Zug berechnet.
 * 
 * @author sebastian
 * 
 */
public class RandomKIAlgorithm implements IKIAlgorithm {

    /**
     * Zur Markierung eines erreichten Feldes
     */
    private static final byte USED_POSITION = -2;

    /**
     * Fuer einen zufaellig gewaehlten, noch nicht betrachteter Stein des
     * Spielers werden alle moeglichen Zuege berechnet und falls es Zuege gibt,
     * einer zufaellig ausgewaehlt. Falls nicht, wieder von vorne.
     * 
     * @see com.googlecode.btuswphalma.kiplayer.IKIAlgorithm#computeMove(com.googlecode.btuswphalma.gameengine.Board,
     *      com.googlecode.btuswphalma.gameengine.PlayerList, int)
     */
    public HalmaMove computeMove(Board board, PlayerList playerList,
	    int activePlayer) {
	// TODO fehlerhafte eingaben
	// 1. Spielsteinpositionen finden
	List<BoardPosition> playerPos = getPlayerPoses(board, activePlayer);
	List<ArrayList<BoardPosition>> playerMoves;
	Random random = new Random();
	BoardPosition workingPos;
	int randIndex;
	boolean found = false;

	System.err.println(playerPos);
	while (!playerPos.isEmpty() && !found) {
	    // 2. Position waehlen und aus Liste streichen
	    randIndex = random.nextInt(playerPos.size());
	    workingPos = playerPos.get(randIndex);
	    playerPos.remove(randIndex);
	    // 3. Zuege berechnen
	    playerMoves = findMoves(board, workingPos);
	    System.err.println(playerMoves);
	    // 4. Zug waehlen oder zurueck zu 2.
	    if (!playerMoves.isEmpty()) {
		randIndex = random.nextInt(playerMoves.size());
		return new HalmaMove(playerMoves.get(randIndex));
	    }
	}
	return null;
    }

    /**
     * Eine Liste mit den Positionen der Spielsteine eines Spielers wird
     * zurueckgegeben
     * 
     * @param board
     *                das Spielbrett
     * @param activePlayer
     *                der Spieler
     * @return die Liste mit den Positionen der Spielsteine des Spielers auf dem
     *         Brett
     */
    private List<BoardPosition> getPlayerPoses(Board board, int activePlayer) {

	ArrayList<BoardPosition> playerPos = new ArrayList<BoardPosition>(10);
	byte[][] brdArray = board.getBoardArryClone();
	// TODO Zahl auslagern
	// nur gueltige Spielernummern
	if (activePlayer < 1 || activePlayer > 6) {
	    return playerPos;
	}

	for (byte i = 0; i < brdArray.length; i++) {
	    for (byte j = 0; j < brdArray[i].length; j++) {
		if (activePlayer == brdArray[i][j]) {
		    playerPos.add(new BoardPosition(i, j));
		}
	    }
	}
	return playerPos;
    }

    /**
     * Fuer ein Spielbrett werden alle moeglichen Zuege von einer Position aus
     * zurueckgegeben. Dabei wird nicht kontrolliert, ob auf der Position ein
     * Spielstein steht.
     * 
     * @param board
     *                das Spielbrett
     * @param startPos
     *                die
     * @return Eine Liste von Zuegen, noch als ArrayListe gespeichert
     */
    private List<ArrayList<BoardPosition>> findMoves(Board board,
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
    private void computeNeutralMove(BoardPosition startPos,
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
    private void computePushes(BoardPosition startPos, byte[][] brdArray,
	    byte[][] pushDistances,
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
     * @param brdArray Darstellung des Spielbretts
     * @param jumpDistances die Sprungdistanzen
     * @param reachedPoses Durch Spruenge neu erreichte Positionen
     * @param moveMap Map mit den Spruengen fuer Ein- und Rueckgabe
     */
    private void computeSequelJumps(byte[][] brdArray, byte[][] jumpDistances,
	    Queue<BoardPosition> reachedPoses,
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
     * Fuer ein Startposition werden fuer alle Felder, die mit einem einzigen Teilsprung erreichber sind
     * dieser Teilsprung gespeichert.  
     * @param startPos die Startposition der Zuege
     * @param brdArray Darstellung des Spielbretts
     * @param jumpDistances die Sprungdistanzen
     * @param reachedPoses Durch Spruenge neu erreichte Positionen
     * @param moveMap Map mit den Spruengen fuer Ein- und Rueckgabe
     */
    private void computeStartJumps(BoardPosition startPos, byte[][] brdArray,
	    final byte[][] jumpDistances, Queue<BoardPosition> reachedPoses,
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

}
