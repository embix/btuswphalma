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
	
	zeichneHausV(g2d, 5, 16, Color.GREEN); // oben links gruenes Haus
	zeichneHausV(g2d, 23, 16, Color.BLUE); // oben rechts blaues Haus
	zeichneHausV(g2d, 14, 34, Color.RED); // unteres rotes Haus
    }
    
    /**
     * Zeichne ein farbiges Haus in V-Form mit Bezugspunkt
     * unten mitte enstrechend den Rasterpunkten.
     * 
     * @param g2d gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param xOffset x-Wert des Bezugspunktes im Raster 
     * @param yOffset y-Wert des Bezugspunktes im Raster
     * @param c gibt die zu verwendende Farbe an
     */
    private void zeichneHausV(Graphics2D g2d, int xOffset, int yOffset, Color c){
	// farbiges Dreieck zeichnen
	Polygon triangle = new Polygon();
	// obere Ecke
	triangle.addPoint(RADIUS * xOffset, RADIUS * yOffset);
	 //unten links Ecke
	triangle.addPoint(RADIUS * (xOffset - 3), RADIUS * (yOffset - 6));
	// unten rechts Ecke
	triangle.addPoint(RADIUS * (xOffset + 3), RADIUS * (yOffset - 6));
	g2d.setPaint(c);
	g2d.fill(triangle);
	
	// die weissen Spielfelder zeichnen
	int xpos;
	int ypos;
	// Aufbau von unten nach oben
	for (int i = 0; i <= 4; i++) {
	    for (int j = 0; j < i; j++){
		xpos = xOffset+1 - i + 2*j;
		ypos = yOffset+2 - 2*i ;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}
    }
    
    /**
     * Zeichne ein farbiges Haus in A-Form mit Bezugspunkt
     * oben mitte enstrechend den Rasterpunkten.
     * 
     * @param g2d gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param xOffset x-Wert des Bezugspunktes im Raster 
     * @param yOffset y-Wert des Bezugspunktes im Raster
     * @param c gibt die zu verwendende Farbe an
     */
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
	// Aufbau von oben nach unten
	for (int i = 0; i <= 4; i++) {
	    for (int j = 0; j < i; j++){
		xpos = xOffset+1 - i + 2*j;
		ypos = yOffset-2 + 2*i ;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}
    }
    
    /**
     * Zeichnet einen großen Kreis
     * 
     * @param g2d gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param x gibt den x Wert des Mittelpunktes im Raster an
     * @param y gibt den y Wert des Mittelpunktes im Raster an
     * @param c gibt die zu verwendende Farbe an
     */
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
	// TODO: implementieren
    }
    
    /**
     * Zeige den aktuellen Zug ueber dem letzten Board auf dem
     * Bildschirm an.
     */
    public void showMove(){
	showBoard(); // erst das Board anzeigen
	// dann Zug darueberlegen
	// TODO: implementieren
    }
}
