/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * Diese Klasse uebernimmt das Zusammenbauen der Teilzuege
 * zu einem Spielzug.
 * 
 * @author embix
 *
 */
public class BuilderHalmaMove {

    private HalmaMove move;
    
    /**
     * Konstruktor des Zugbauers
     */
    public BuilderHalmaMove(){
	move = new HalmaMove();
    }
    
    /**
     * Eine Position hinzufuegen
     * @param x x-Koordinate auf BoardBasis
     * @param y y-Koordinate auf BoardBasis
     */
    public void addPosition(byte x, byte y){
	move.addBoardPosition(new BoardPosition(x, y));
    }
    
    /**
     * Eine Position hinzufuegen
     * @param pos die BoardPosition
     */
    public void addPosition(BoardPosition pos){
	move.addBoardPosition(pos);
    }
    
    /**
     * Den bisherigen Zug zurueckgeben
     * @return gib den Zug zurueck
     */
    public HalmaMove getMove() {
	return move;
    }
}
