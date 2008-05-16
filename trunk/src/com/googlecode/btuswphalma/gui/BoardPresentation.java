/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @author embix
 *
 */
public class BoardPresentation extends JPanel {
 
    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung
     * von JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 1622559316573152181L;
    
    /**
     * Durchmesser der Spielfeldkreise
     */
    public static final int DIAMETER = 25;  
    
    /**
     * Konstruktor
     */
    public BoardPresentation(){
	
    }

    
    /**
     * 
     *  (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g){

	Graphics2D g2d = null;
	try{
		g2d = (Graphics2D)g;
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// Feldfarben definieren
	g2d.setPaint(Color.WHITE);
	
	// zeichne oberes rotes Haus
	for (int i = 0; i <= 4; i++) {
	    g2d.setPaint(Color.WHITE);
	    for (int j = 1; j <= i; j++){
		int xpos = (int)((6 - (0.5 * i) + j ) * DIAMETER);
		int ypos = DIAMETER * i;
		g2d.fillOval(xpos, ypos, DIAMETER, DIAMETER);
	    }
	    
	}
    }
    
    // public void setBoard(Board b)
    // TODO: implementieren, wenn Board verfuegbar
    
    // public void setHalmaMove(HalmaMove hm)
    // TODO: implementieren, wenn HalmaMove verfuegbar
    
    /**
     * Zeige das aktuell gesetzte Board auf dem Bildschirm an.
     */
    public void showBoard(){
	
    }
    
    /**
     * Zeige den aktuellen Zug ueber dem letzten Board auf dem
     * Bildschirm an.
     */
    public void showMove(){
	showBoard(); // erst das Board anzeigen
	// dann Zug darueberlegen
    }
}
