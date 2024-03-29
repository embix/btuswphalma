/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * Klasse zur Instanzierung der BoardPresentation und einfachen nicht
 * automatischen Tests.
 * <p>
 * Es sollen die Aufstellung der Spielsteine und der aktuelle Zug angezeigt
 * werden koennen, wobei das Spielfeld immer im Hintergrund ist.
 * </p>
 * 
 * @author embix
 * @see BoardPresentation
 */
public class BoardPresentationStarter
	extends JPanel
	implements ActionListener {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 720399813441767361L;

    private JFrame owner;
    private BoardPresentation pres;
    private int spieler = 4;
    private JButton spielerbutton;

    /*
     * Beschriftung der Buttons waere als enum wohl besser aufgehoben, dann
     * koennte man auch die Instanzierung der Buttons automatisieren (Schleife)
     */
    // private static final String POS = "positioniere Spielstein";
    private static final String BRETT = "zeige Spielbrett";
    private static final String ZUG = "zeige Spielzug";
    private static final String HIDEBRETT = "verstecke Brett";
    private static final String HIDEZUG = "verstecke Zug";

    /**
     * Konstruktor
     * 
     * @param owner
     *                gibt an, zu welchem Container der Dialog-Startbutton
     *                gehoeren soll.
     */
    public BoardPresentationStarter(JFrame owner) {
	this.owner = owner;
	this.setLayout(new BorderLayout());

	// Spielfelddarstellung hinzufuegen
	pres = new BoardPresentation();
	pres.setSize(new Dimension(640, 480));
	this.add(pres, BorderLayout.CENTER);

	// Buttonfeld
	JPanel p = new JPanel();

	// Button zum Positionieren eines Spielsteines
	spielerbutton = new JButton(String.valueOf(spieler));
	spielerbutton.addActionListener(this);
	p.add(spielerbutton);

	JButton b;
	// Button zum Setzen des Spielbretts mit Spielsteinen
	b = new JButton(BRETT);
	b.addActionListener(this);
	p.add(b);

	// Button zum Anzeigen eines Zuges
	b = new JButton(ZUG);
	b.addActionListener(this);
	p.add(b);

	// Button zum Verstecken der Aufstellung
	b = new JButton(HIDEBRETT);
	b.addActionListener(this);
	p.add(b);

	// Button zum Verstecken des Zuges
	b = new JButton(HIDEZUG);
	b.addActionListener(this);
	p.add(b);

	// Panel einbetten
	this.add(p, BorderLayout.PAGE_END);
	this.owner.add(this);
    }

    /**
     * Instanziert ein BoardPresentation Objekt zur optischen Begutachtung.
     * 
     * @param args
     */
    public static void main(String[] args) {
	JFrame frame = new JFrame("BoardPresentationStarter");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new BoardPresentationStarter(frame));
	frame.setSize(720, 500);
	frame.setVisible(true);
    }

    /**
     * Eventhandler fuer die Buttons zum Testen der BoardPresentation
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
	String cmd = event.getActionCommand();
	// besser: in innere Klassen auslagern
	if (cmd.equals(BRETT)) {
	    System.out.println("Button: " + BRETT);
	    // Brett mit 4 Spielern in Startpostion erzeugen
	    Board board = new Board(spieler);
	    // Spielbrett uebergeben und anzeigen
	    pres.setBoard(board);
	    pres.showBoard();
	    return;
	}
	if (cmd.equals(ZUG)) {
	    System.out.println("Button: " + ZUG);
	    // Spielzug erzeugen
	    HalmaMove move = new HalmaMove();
	    // (x)(y)-Folge der Einzelpositionen
	    // Startposition liegt bewusst ausserhalb des Spielfeldes!
	    // dann Sprung aus dem Haus, dann Schub
	    /* byte zugfolge[] = {12,0,7,3,6,5,7,6}; */
	    byte zugfolge[] = { 12, 0, 7, 3, 6, 5, 7, 6, 9, 6, 10, 4, 11, 6 };
	    // der Zug ist zwar ungueltig, aber ideal als Anzeigetest
	    for (int i = 0; i < zugfolge.length - 1; i += 2) {
		move.addBoardPosition(new BoardPosition(zugfolge[i],
			zugfolge[i + 1]));
	    }
	    // Spielzug uebergeben und anzeigen
	    pres.setHalmaMove(move);
	    pres.showMove();
	    return;
	}
	if (cmd.equals(HIDEBRETT)) {
	    System.out.println("Button: " + HIDEBRETT);
	    // Aufstellung verstecken
	    pres.hideBoard();
	    return;
	}
	if (cmd.equals(HIDEZUG)) {
	    System.out.println("Button: " + HIDEZUG);
	    // Zug verstecken
	    pres.hideMove();
	    return;
	}
	if (true) {
	    System.out.println("Button: " + spieler);
	    spieler = (spieler) % 6;
	    spieler++;
	    spielerbutton.setText(String.valueOf(spieler));
	    // schmutzig!
	    ActionEvent e = new ActionEvent(this, 1, BRETT);
	    this.actionPerformed(e);
	    return;
	}
	// hierhin eh kommt niemand
    }
}
