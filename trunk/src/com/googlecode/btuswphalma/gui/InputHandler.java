/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * Hilfsklasse zu Verarbeitung der Benutzereingaben, zB fuer
 * die Eingabe der Spielzuege.
 * 
 * @author embix
 *
 */
public class InputHandler implements MouseListener {

    // befindet sich der Inputhandler im Zugeingabemodus?
    private boolean moveEntry;
    private BuilderHalmaMove builder;

    /**
     * einfacher Konstruktor
     */
    public InputHandler() {
	moveEntry = false;
	moveEntry = true;
    }

    /**
     * Setzt den Inputhandler in den Zugeingabemodus.
     */
    public void setMoveEntryModeOn() {
	builder = new BuilderHalmaMove();
	moveEntry = true;
    }

    /**
     * Beendet die Zugeingabe und gibt einen Spielzug zurueck
     * @return der erzeugte Zug wird zurueckgegeben
     */
    public HalmaMove setMoveEntryModeOff() {
	moveEntry = false;
	return builder.getMove();
    }

    // TODO: (GUI) Eingabehandling implementieren

    /* private void gotHalmaMove(){
    
     }*/

    /*
     * Methoden, die durch MouseListener gefordert werden 
     */

    /**
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent event) {
	// wird ueber pressed und released realisiert
    }

    /**
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public void mouseEntered(MouseEvent event) {
	// ist egal
    }

    /**
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public void mouseExited(MouseEvent event) {
	// ist egal
    }

    /**
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent event) {
	if (moveEntry) {
	    // Debugausgabe
	    System.out.println("Mausklick an (" + event.getX() + ","
		    + event.getY() + ")");
	    // entspricht der Rasterposition
	    // unsauber
	    try {
		BoardPresentation board = (BoardPresentation) event
			.getComponent();
		
		int x = board.gibRasterXFromMouse(event.getX(), event.getY());
		int y = board.gibRasterYFromMouse(event.getX(), event.getY());
		System.out.println("Raster: (" + x + "," + y + ")");
		BoardPosition pos;
		pos = board.gibBoardAusRaster(x, y);
		
		board.showPosition(pos);
		
		builder.addPosition(pos);
		// Debugausgabe
		byte xPos = pos.getXPos();
		byte yPos = pos.getYPos();
		System.out.println("Board:  (" + xPos + "," + yPos + ")");
	    } catch (RuntimeException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent event) {
	// Ende eines TeilZuges?
	System.out.println("Mausklick aus (" + event.getX() + ","
		+ event.getY() + ")");
    }
}
