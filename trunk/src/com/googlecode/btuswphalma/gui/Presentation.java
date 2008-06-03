/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author embix
 * 
 */
public class Presentation extends JFrame {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 8544934143314222146L;

    private GuiController controller;
    
    /**
     * Initialisierung
     */
    public Presentation() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	
	this.setSize(640, 480);
	this.setVisible(true);
	
	controller = GuiController.getInstance();
	controller.initialize(this);
    }

}
