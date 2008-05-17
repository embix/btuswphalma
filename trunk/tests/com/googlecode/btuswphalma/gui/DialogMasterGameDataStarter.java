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
public class DialogMasterGameDataStarter
	extends JPanel
	implements ActionListener {
    
    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung
     * von JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = -5232324680081090145L;
    
    private JFrame owner;
    private DialogMasterGameData dialog;

    /**
     * Konstruktor
     * 
     * @param owner gibt an, zu welchem Container der Dialog-Startbutton
     * gehoeren soll.
     */
    public DialogMasterGameDataStarter(JFrame owner){
	this.owner = owner;
	JButton b = new JButton("Call DialogMasterGameData");
	b.addActionListener(this);
	add(b);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new DialogMasterGameDataStarter(frame));
	frame.setSize(640, 480);
	frame.setVisible(true);
    }

    /**
     * Eventhandler
     * 
     *  (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
	dialog = new DialogMasterGameData(owner);
	// hier gehts erst weiter, wenn Dialog geschlossen wurde
	if(dialog.ok()){
	    System.out.println("Dialog ok");
	    MasterGameData mgd = dialog.getMasterGameData();
	    System.out.println("Spielername:    " + mgd.playerName);
	    System.out.println("Anzahl Spieler: " + mgd.playerCount);
	    System.out.println("davon Ki:       " + mgd.aiCount);
	    System.out.println("Game Mode:      " + mgd.gmod);
	}else{
	    System.out.println("Dialog nicht ok");
	}
	// TODO: Rueckgabedaten ausgeben
    }
}
