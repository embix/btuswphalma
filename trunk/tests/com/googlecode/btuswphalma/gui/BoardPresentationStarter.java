/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import javax.swing.JFrame;

/**
 * @author embix
 *
 */
public class BoardPresentationStarter {

    /**
     * Instanziert ein BoardPresentation Objekt zur optischen
     * Begutachtung.
     * 
     * @param args
     */
    public static void main(String[] args) {
	BoardPresentation bp = new BoardPresentation();
	JFrame frame = new JFrame();
	frame.getContentPane().add(bp);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(640, 480);
	frame.setVisible(true);

    }

}
