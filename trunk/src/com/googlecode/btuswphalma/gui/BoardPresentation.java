/**
 * TODO: (GUI) Entwurf abgleichen: private Methoden nachtragen
 */
package com.googlecode.btuswphalma.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * Darstellungsklasse fuer das Spielfeld und die Spielzuege
 * 
 * @author embix
 * 
 */
public class BoardPresentation extends JPanel {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 1622559316573152181L;

    /**
     * Der Radius der Spielfeldkreise, er dient gleichzeitig als
     * Skalierungsfaktor fuer das sichtbare Spielfeld.
     */
    private int radius = 12;

    private Board board;
    private HalmaMove move;
    private boolean boardShow; // soll die Belegung gezeichnet werden?
    private boolean moveShow; // soll der aktuelle Zug gezeichnet werden?

    /**
     * Konstruktor
     */
    public BoardPresentation() {
	// Anfordern der optimalen Groesse
	optimizeSize();
    }

    /**
     * uebergibt dem Container die optimale Groesse des Panels
     */
    private void optimizeSize() {
	Dimension preferredSize = new Dimension(28 * radius, 36 * radius);
	this.setPreferredSize(preferredSize);
    }

    /**
     * zeichnen der Darstellung, wird u.a. bei resize aufgerufen
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	// 2D Graphikobjekt extrahieren
	Graphics2D g2d = null;
	try {
	    g2d = (Graphics2D) g;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	zeichneLeeresBrett(g2d);

	// erst das alte Spielbrett zeigen
	if (boardShow) {
	    zeichneBoard(g2d);
	}
	// dann den aktuellen Zug darueberlegen
	if (moveShow) {
	    zeichneMove(g2d);
	}
    }

    /**
     * Zeichnet das gesamte Spielfeld, ohne Spielsteine auf dem Brett
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     */
    private void zeichneLeeresBrett(Graphics2D g2d) {
	// Haeuser zeichen
	zeichneHausA(g2d, 14, 2, Color.RED); // oberes rotes Haus
	zeichneHausA(g2d, 5, 20, Color.BLUE); // unten links blaues Haus
	zeichneHausA(g2d, 23, 20, Color.GREEN); // unten rechts gruenes Haus
	zeichneHausV(g2d, 5, 16, Color.GREEN); // oben links gruenes Haus
	zeichneHausV(g2d, 23, 16, Color.BLUE); // oben rechts blaues Haus
	zeichneHausV(g2d, 14, 34, Color.RED); // unteres rotes Haus

	zeichneHauptFeld(g2d); // Spielfeldmitte zeichnen
    }

    /**
     * Zeichne die Mitte des Spielfeldes
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     */
    private void zeichneHauptFeld(Graphics2D g2d) {
	// Offsets fuer x und y
	int xOffset = 10 - 1;
	int yOffset = 10 + 2;

	// die weissen Spielfelder zeichnen
	int xpos;
	int ypos;
	// Aufbau von oben nach unten
	for (int i = 0; i <= 4; i++) {
	    for (int j = 0; j < i + 5; j++) {
		xpos = xOffset + 1 - i + 2 * j;
		ypos = yOffset - 2 + 2 * i;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}

	// Offsets fuer x und y
	xOffset = 10 - 1;
	yOffset = 26 - 2;
	// Aufbau von unten nach oben
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < i + 5; j++) {
		xpos = xOffset + 1 - i + 2 * j;
		ypos = yOffset + 2 - 2 * i;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}
    }

    /**
     * Zeichne ein farbiges Haus in V-Form mit Bezugspunkt unten mitte
     * enstrechend den Rasterpunkten.
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param xOffset
     *                x-Wert des Bezugspunktes im Raster
     * @param yOffset
     *                y-Wert des Bezugspunktes im Raster
     * @param c
     *                gibt die zu verwendende Farbe an
     */
    private void zeichneHausV(Graphics2D g2d, int xOffset, int yOffset, Color c) {
	// farbiges Dreieck zeichnen
	Polygon triangle = new Polygon();
	// obere Ecke
	triangle.addPoint(radius * xOffset, radius * yOffset);
	// unten links Ecke
	triangle.addPoint(radius * (xOffset - 3), radius * (yOffset - 6));
	// unten rechts Ecke
	triangle.addPoint(radius * (xOffset + 3), radius * (yOffset - 6));
	g2d.setPaint(c);
	g2d.fill(triangle);

	// die weissen Spielfelder zeichnen
	int xpos;
	int ypos;
	// Aufbau von unten nach oben
	for (int i = 0; i <= 4; i++) {
	    for (int j = 0; j < i; j++) {
		xpos = xOffset + 1 - i + 2 * j;
		ypos = yOffset + 2 - 2 * i;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}
    }

    /**
     * Zeichne ein farbiges Haus in A-Form mit Bezugspunkt oben mitte
     * enstrechend den Rasterpunkten.
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param xOffset
     *                x-Wert des Bezugspunktes im Raster
     * @param yOffset
     *                y-Wert des Bezugspunktes im Raster
     * @param c
     *                gibt die zu verwendende Farbe an
     */
    private void zeichneHausA(Graphics2D g2d, int xOffset, int yOffset, Color c) {
	// farbiges Dreieck zeichnen
	Polygon triangle = new Polygon();
	// obere Ecke
	triangle.addPoint(radius * xOffset, radius * yOffset);
	// unten links Ecke
	triangle.addPoint(radius * (xOffset - 3), radius * (yOffset + 6));
	// unten rechts Ecke
	triangle.addPoint(radius * (xOffset + 3), radius * (yOffset + 6));
	g2d.setPaint(c);
	g2d.fill(triangle);

	// die weissen Spielfelder zeichnen
	int xpos;
	int ypos;
	// Aufbau von oben nach unten
	for (int i = 0; i <= 4; i++) {
	    for (int j = 0; j < i; j++) {
		xpos = xOffset + 1 - i + 2 * j;
		ypos = yOffset - 2 + 2 * i;
		zeichneGrossenKreis(g2d, xpos, ypos, Color.WHITE);
	    }
	}
    }

    /**
     * Zeichnen einen großen Kreises
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param x
     *                gibt den x Wert des Mittelpunktes im Raster an
     * @param y
     *                gibt den y Wert des Mittelpunktes im Raster an
     * @param c
     *                gibt die zu verwendende Farbe an
     */
    private void zeichneGrossenKreis(Graphics2D g2d, int x, int y, Color c) {
	g2d.setPaint(c);
	// Skalierte Boardposition berechnen
	int xpos = radius * x;
	int ypos = radius * y;
	// Kreismittelpunkt entspricht (xStart-xEnd, yStart-yEnd)
	g2d.fillOval(xpos - radius, ypos - radius, 2 * radius, 2 * radius);
    }

    /**
     * Mit dieser Methode wird der Darstellung die neue Spielsituation bekannt
     * gemacht.
     * 
     * @param b
     *                legt das aktuelle Spielfeld fest
     */
    public void setBoard(Board b) {
	this.board = b;
    }

    /**
     * Mit dieser Methode wird der Darstellung der aktuelle Zug bekannt gemacht.
     * 
     * @param hm
     *                legt den aktuellen Spielzug fest
     */
    public void setHalmaMove(HalmaMove hm) {
	this.move = hm;
    }

    /**
     * Zeige das aktuell gesetzte Board auf dem Bildschirm an.
     */
    public void showBoard() {
	boardShow = true;
	repaint();
    }

    /**
     * Anhand des Spielbrettes werden die Spielsteine in der Spielerfarbe zur
     * Anzeige gebracht.
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     */
    private void zeichneBoard(Graphics2D g2d) {
	if (board == null) {
	    return; // kein Brett heisst nichts zu zeichnen
	}
	// TODO: (GUI) unsauber, besser mit getPositionState arbeiten
	byte boardArray[][] = board.getBoardArryClone();

	// ungeprueftes Anzeigen!
	// zeilenweise durchgehen
	for (int i = 0; i <= 16; i++) {
	    for (int j = 0; j <= 12; j++) {
		if (boardArray[i][j] > 0 && boardArray[i][j] < 7) {
		    // in Board-Klasse:(y)(x)
		    // Aufruf jedoch (x)(y)
		    zeichneSpielstein(g2d, j, i,
			    mapPlayerToColor(boardArray[i][j]));
		}
	    }
	}
    }

    /**
     * Gibt zu einer Spielernummer die entsprechende Farbe zurueck. Falls diese
     * Funktion mehrfach benoetigt werden sollte, ist sie public zu machen und
     * ggf in eine allgemein zugaengliche GUI-Klasse auszulagern.
     * 
     * @param player
     *                Spielernummer
     * @return Farbe des Spielers
     */
    private Color mapPlayerToColor(byte player) {
	switch (player) {
	case 1:
	    return Color.RED;
	case 2:
	    return Color.GREEN;
	case 3:
	    return Color.BLUE;
	case 4:
	    return Color.YELLOW;
	case 5:
	    return Color.MAGENTA; // orange ist schwieriger zu erkennen
	case 6:
	    return Color.CYAN;
	default:
	    return Color.WHITE;
	}
    }

    /**
     * Der aktuelle Zug wird zur Anzeige gebracht.
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     */
    private void zeichneMove(Graphics2D g2d) {
	if (move == null) {
	    return; // kein Zug heisst nichts zu zeichnen
	}

	// Postion in Boardbasis
	BoardPosition vonPos;
	BoardPosition nachPos;

	// Zielwerte fuer die Rasterpunkte
	int xVon;
	int yVon;
	int xNach;
	int yNach;
	for (int i = 0; i < move.getNumberOfPartMoves(); i++) {
	    vonPos = move.getPartPosition(i);
	    nachPos = move.getPartPosition(i + 1); // wird immer-1 doppelt
						    // ausgelesen

	    // Umrechnung Boardbasis in Rasterpunkte
	    xVon = gibRasterX(vonPos.getXPos(), vonPos.getYPos());
	    yVon = gibRasterY(vonPos.getYPos()); // doppelt ausgelesen,
						    // Performance?
	    xNach = gibRasterX(nachPos.getXPos(), nachPos.getYPos());
	    yNach = gibRasterY(nachPos.getYPos()); // doppelt auch ausgelesen,
						    // Performance?

	    // ein Pfeil waere wohl schoener, kann man ja spaeter noch machen
	    zeichneLinie(g2d, xVon, yVon, xNach, yNach);
	}
    }

    /**
     * Zeichne einen farbigen Spielstein enstrechend der Position zur Basis des
     * Board Objektes. Diese entspricht nicht dem Graphikraster!
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param xPos
     *                x-Wert des Bezugspunktes in Boardbasis
     * @param yPos
     *                y-Wert des Bezugspunktes im Boardbasis
     * @param c
     *                gibt die zu verwendende Farbe an
     */
    private void zeichneSpielstein(Graphics2D g2d, int xPos, int yPos, Color c) {
	// Umrechnung von Board-Basis in Rasterpunkte
	int x = gibRasterX(xPos, yPos);
	int y = gibRasterY(yPos);

	// zeichnet Spielstein als kleinen Kreis
	// kann spaeter auch huebscher umgesetzt werden
	zeichneKleinenKreis(g2d, x, y, c);
    }

    /**
     * Rechnet Boardpunkte in Rasterkoordinate x um
     * 
     * @param xPos
     *                x-Position in Boardbasis
     * @param yPos
     *                y-Position in Boardbasis
     * @return x-Position im Raster
     */
    public static int gibRasterX(int xPos, int yPos) {
	int x;
	// Ungepruefte Umrechnung
	if (yPos % 2 == 0) {
	    x = 2 + 2 * xPos;
	} else {
	    x = 3 + 2 * xPos;
	}
	return x;
    }

    /**
     * Rechnet Boardkoordiante y in Rasterkoordinate y um
     * 
     * @param yPos
     *                y-Position in Boardbasis
     * @return y-Position im Raster
     */
    public static int gibRasterY(int yPos) {
	// Ungepruefte Umrechnung
	return (2 + 2 * yPos);
    }

    /**
     * Zeichnen einen kleinen Kreises
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param x
     *                gibt den x Wert des Mittelpunktes im Raster an
     * @param y
     *                gibt den y Wert des Mittelpunktes im Raster an
     * @param c
     *                gibt die zu verwendende Farbe an
     */
    private void zeichneKleinenKreis(Graphics2D g2d, int x, int y, Color c) {
	g2d.setPaint(c);
	// Skalierte Boardposition berechnen
	int xpos = radius * x;
	int ypos = radius * y;
	// Kreismittelpunkt entspricht (xStart-xEnd, yStart-yEnd)
	g2d.fillOval(xpos - radius / 2, ypos - radius / 2, radius, radius);
    }

    /**
     * Zeichnen einer schwarzen Linie
     * 
     * @param g2d
     *                gibt an, in welches Graphikobjekt gezeichnet werden soll
     * @param xVon
     *                gibt den x Wert des Startpunktes im Raster an
     * @param yVon
     *                gibt den y Wert des Startpunktes im Raster an
     * @param xNach
     *                gibt den x Wert des Zielpunktes im Raster an
     * @param yNach
     *                gibt den y Wert des Zielpunktes im Raster an
     */
    private void zeichneLinie(Graphics2D g2d, int xVon, int yVon, int xNach,
	    int yNach) {
	g2d.setPaint(Color.BLACK);
	// Skalierte Boardposition berechnen
	xVon *= radius;
	yVon *= radius;
	xNach *= radius;
	yNach *= radius;
	// Zeichnen der Linie
	g2d.drawLine(xVon, yVon, xNach, yNach);
    }

    /**
     * Zeige den aktuellen Zug ueber dem letzten Board auf dem Bildschirm an.
     */
    public void showMove() {
	moveShow = true;
	repaint();
    }

    /**
     * Wird aufgerufen, wenn der aktuelle Spielzug nicht mehr angezeigt werden
     * soll.
     * 
     */
    public void hideMove() {
	moveShow = false;
	repaint();
    }

    /**
     * Wird aufgerufen, wenn die Position der Spielsteine nicht mehr angezeigt
     * werden soll.
     * 
     */
    public void hideBoard() {
	boardShow = false;
	repaint();
    }
    
    /**
     * Setter fuer den Massstab der Zeichengrosse / Radius
     * @param radius
     */
    public void setRadius(int radius){
	if ((radius > 5) && (radius < 29)) {
	    this.radius = radius;
	}
	optimizeSize();
	repaint();
	// repaint wird vom Container implizit durch pack() aufgerufen,
	// allerdings macht pack nichts mehr, wenn die Grosse einmal
	// die gleiche war...
    }
    
    /**
     * Getter fuer den Zeichenradius / Massstab
     * @return gibt den aktuell verwendeten Zeichenradius zuruck
     */
    public int getRadius(){
	return radius;
    }
    
    /**
     * Rechnet aus der auf den BoardPres-Objekt bezogenen Mousekoordianten
     * 
     * @param mouseX
     * @param mouseY
     * @return
     */
    public int gibRasterFromMouseX(int mouseX, int mouseY){
	return 0;
    }
}
