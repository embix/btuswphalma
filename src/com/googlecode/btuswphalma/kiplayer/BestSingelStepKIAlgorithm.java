package com.googlecode.btuswphalma.kiplayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;

/**
 * Mit diesem Algorithmus wird von einem zufaelligen Spieler ein zufaelliger,
 * korrekter Zug berechnet.
 * 
 * @author sebastian
 * 
 */
public class BestSingelStepKIAlgorithm implements IKIAlgorithm {

    /**
     * Fuer alle Steine des Spielers werden alle Zuege berechnet und ein bester Zug ausgewaehlt. 
     * 
     * @see com.googlecode.btuswphalma.kiplayer.IKIAlgorithm#computeMove(com.googlecode.btuswphalma.gameengine.Board,
     *      com.googlecode.btuswphalma.gameengine.PlayerList, int)
     */
    public HalmaMove computeMove(Board board, PlayerList playerList,
	    int activePlayer) {
	// TODO fehlerhafte eingaben
	// 1. Spielsteinpositionen finden
	List<BoardPosition> playerPos = getPlayerPoses(board, activePlayer);
	List<ArrayList<BoardPosition>> playerMoves = new LinkedList<ArrayList<BoardPosition>>();
	Random random = new Random();
	HalmaMove move;

	for (BoardPosition workingPos : playerPos) {
	    playerMoves.addAll(StaticKIMethods.findMoves(board, workingPos));
	}

	if (!playerMoves.isEmpty()) {
	    move = chooseGoodMove(board, playerList, activePlayer, playerMoves,
		    random);
	    return move;
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
     * Die Zuege werden bewertet und aus den besten wird zufaellig eine
     * ausgewaehlt.
     * 
     * @param board
     *                das Spielbrett zur Bewertung
     * @param playerList
     *                die Spielerliste zur Bewertung
     * @param activePlayer
     *                Spieler fuer den bewertet wird
     * @param playerMoves
     *                Die Liste aus der ein Zug gewaehlt wird
     * @param random
     *                Zufallszahlengenerator
     * @return Einer der besten Zuege
     */
    private HalmaMove chooseGoodMove(Board board, PlayerList playerList,
	    int activePlayer, List<ArrayList<BoardPosition>> playerMoves,
	    Random random) {

	ArrayList<BoardPosition> chosenMove = new ArrayList<BoardPosition>();
	LinkedList<ArrayList<BoardPosition>> goodMoves = new LinkedList<ArrayList<BoardPosition>>();
	int value = 0;
	int maxValue = Integer.MIN_VALUE;
	// Alle Zuege werden bewertet, die besten werden gespeichert
	for (ArrayList<BoardPosition> moveList : playerMoves) {
	    // Brett nach dem Zug erzeugen
	    board.exchangePositionState(moveList.get(0), moveList.get(moveList
		    .size() - 1));
	    value = StaticKIMethods.evaluateGameSituation(board, playerList,
		    activePlayer);
	    if (maxValue < value) {
		goodMoves.clear();
		goodMoves.add(moveList);
		maxValue = value;
	    } else if (maxValue == value) {
		goodMoves.add(moveList);
	    }
	    // Brett wieder herstellen
	    board.exchangePositionState(moveList.get(moveList.size() - 1),
		    moveList.get(0));
	}

	chosenMove = goodMoves.get(random.nextInt(goodMoves.size()));
	return new HalmaMove(chosenMove);
    }

}
