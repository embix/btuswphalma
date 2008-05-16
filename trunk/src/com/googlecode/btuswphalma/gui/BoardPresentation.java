/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

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
     * Der Radius der Spielfeldkreise, er dient
     * gleichzeitig als Skalierungsfaktor fuer das
     * sichtbare Spielfeld.
     */
    public static final int RADIUS = 12;  
    
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
	
	
	// Haeuser zeichen
	zeichneHausA(g2d, 14, 2, Color.RED); // oberes rotes Haus
	zeichneHausA(g2d, 5, 20, Color.BLUE); // unten links blaues Haus
	zeichneHausA(g2d, 23, 20, Color.GREEN); // unten rechts gruenes Haus
	
	// zeichne unteres rotes Haus
	// TODO: rotes Hintergrund Dreieck zeichen
	/*for (int i = 0; i <= 4; i++){
	    g2d.setPaint(Color.WHITE);
	    for (int j = 1; j <= i; j++){
		xpos = (int)((6 - (0.5 * i) + j ) * RADIUS);
		ypos = RADIUS * (17 - i);
		g2d.fillOval(xpos, ypos, RADIUS, RADIUS);
	    }
	}*/
	
	// zeichne links oben gruenes Haus
    }
    
    private void zeichneHausA(Graphics2D g2d, int xOffset, int yOffset, Color c){
	// farbiges Dreieck zeichnen
	Polygon triangle = new Polygon();
	// obere Ecke
	triangle.addPoint(RADIUS * xOffset, RADIUS * yOffset);
	 //unten links Ecke
	triangle.addPoint(RADIUS * (xOffset - 3), RADIUS * (yOffset + 6));
	// unten rechts Ecke
	triangle.addPoint(RADIUS * (xOffset + 3), RADIUS * (yOffset + 6));
	g2d.setPaint(c);
	g2d.fill(triangle);
	
	// die weissen Spielfelder zeichnen
	int xpos;
	int ypos;

	for (int i = 0; i <= 4; i++) {
	    for (int j = 0; j < i; j++){
		xpos = xOffset+1 - i + 2*j;
		ypos = yOffset-2 + 2*i ;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}
    }
    
    private void zeichneGrossenKreis(Graphics2D g2d, int x, int y, Color c){
	g2d.setPaint(c);
	// Skalierte Boardposition berechnen
	int xpos = RADIUS * x;
	int ypos = RADIUS * y;
	// Kreismittelpunkt entspricht (xStart-xEnd, yStart-yEnd)
	g2d.fillOval(xpos-RADIUS, ypos-RADIUS, 2*RADIUS, 2*RADIUS);
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
