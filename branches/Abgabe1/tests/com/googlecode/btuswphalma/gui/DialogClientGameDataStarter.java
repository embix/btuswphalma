/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author embix
 * 
 */
public class DialogClientGameDataStarter extends JPanel implements
	ActionListener {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 3052272139345268106L;

    private JFrame owner;
    private DialogClientGameData dialog;

    /**
     * Konstruktor
     * 
     * @param owner
     *                gibt an, zu welchem Container der Dialog-Startbutton
     *                gehoeren soll.
     */
    public DialogClientGameDataStarter(JFrame owner) {
	this.owner = owner;
	JButton b = new JButton("Call DialogClientGameData");
	b.addActionListener(this);
	add(b);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new DialogClientGameDataStarter(frame));
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
	dialog = new DialogClientGameData(owner,true);
	// hier gehts erst weiter, wenn Dialog geschlossen wurde
	if (dialog.ok()) {
	    System.out.println("Dialog ok");
	    ClientGameData cgd = dialog.getClientGameData();
	    System.out.println("Spielername:    " + cgd.playerName);
	} else {
	    System.out.println("Dialog nicht ok");
	}
    }
}
