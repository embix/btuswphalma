/**
 * 
 */
package com.googlecode.btuswphalma.gui;

/**
 * @author embix
 * 
 */
public class PresentationStarter {

    /**
     * Instanziert ein Presentation Objekt zur optischen Begutachtung.
     * 
     * @param args
     */
    public static void main(String[] args) {
	Presentation pres = new Presentation();
	System.out.println("Breite: " + pres.getWidth());
	System.out.println("Hoehe: " + pres.getHeight());

    }

}
