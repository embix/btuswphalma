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
public class DialogErrorStarter 
	extends JPanel
	implements ActionListener {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung
     * von JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 5712744064685650405L;

    private JFrame owner;
    private DialogError dialog;
    
    /**
     * Konstruktor
     * 
     * @param owner gibt an, zu welchem Container der Dialog-Startbutton
     * gehoeren soll.
     */
    public DialogErrorStarter(JFrame owner){
	this.owner = owner;
	JButton b = new JButton("Call DialogError");
	b.addActionListener(this);
	add(b);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args){
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new DialogErrorStarter(frame));
	frame.setSize(640, 480);
	frame.setVisible(true);
    }
    
    /**
     * Eventhandler
     * 
     *  (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */   
    public void actionPerformed(ActionEvent event){
	String errStr = "Der von Ihnen gemachte Zug ist nicht regelkonform und somit ungueltig.";
	dialog = new DialogError(owner,errStr);
	// hier gehts erst weiter, wenn Dialog geschlossen wurde
	if(dialog.ok()){
	    System.out.println("Dialog ok");
	}else{
	    System.out.println("Dialog nicht ok");
	}
    }
}
