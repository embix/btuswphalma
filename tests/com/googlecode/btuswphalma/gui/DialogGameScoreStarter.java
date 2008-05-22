/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.googlecode.btuswphalma.gameengine.ScoreEntry;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * @author embix
 * 
 */
public class DialogGameScoreStarter extends JPanel implements ActionListener {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 6890783016079003342L;

    private JFrame owner;
    private DialogGameScore dialog;

    /**
     * Konstruktor
     * 
     * @param owner
     *                gibt an, zu welchem Container der Dialog-Startbutton
     *                gehoeren soll.
     * @param scores
     *                gibt das anzuzeigende Spielergebnisobjekt an
     */
    public DialogGameScoreStarter(JFrame owner) {
	this.owner = owner;
	JButton b = new JButton("Call DialogGameScore");
	b.addActionListener(this);
	add(b);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new DialogGameScoreStarter(frame));
	frame.setSize(640, 480);
	frame.setVisible(true);
    }

    /**
     * Der Eventhandler erzeugt nach Betaetigung des Buttons den zu testenden
     * Dialog und gibt seine Rueckgabewerte aus.
     * 
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
	// TODO: Spielergebnisliste erzeugen und übergeben
	ScoreList scores = new ScoreList();
	String names[] = { "Alfons", "Bertram", "Calligula", "Doro", "Eric",
		"Fritz" };
	ScoreEntry score;
	int i = 1;
	for (String name : names) {
	    score = new ScoreEntry(i,name,23+i*i);
	    scores.addEntryToEnd(score);
	    i++;
	}
	dialog = new DialogGameScore(owner, scores);
	// hier gehts erst weiter, wenn Dialog geschlossen wurde
	if (dialog.ok()) {
	    System.out.println("Dialog ok");
	} else {
	    System.out.println("Dialog nicht ok");
	}
    }
}
