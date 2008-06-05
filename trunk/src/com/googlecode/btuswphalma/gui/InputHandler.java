/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Hilfsklasse zu Verarbeitung der Benutzereingaben, zB fuer
 * die Eingabe der Spielzuege.
 * 
 * @author embix
 *
 */
public class InputHandler implements MouseListener{

    
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
	// Debugausgabe
	System.out.println("Mausklick an (" + event.getX() + "," + event.getY()
		+ ")");
	// Beginn eines Teil-Zuges?
    }

    /**
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent event) {
	// Ende eines TeilZuges?
	System.out.println("Mausklick aus (" + event.getX() + "," + event.getY()
		+ ")");
    }
}
