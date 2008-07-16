/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

/**
 * Implementiert eine Laufzeitumgebung fuer die Server Engine zu Testzwecken
 * 
 * @author ASM
 */
public class TestServerEngine {

    /**
     * @see Java SDK
     * @param args
     */
    public static void main(String[] args) {
	Dispatcher dp = new Dispatcher();
	TestGui gui = new TestGui(dp, 4);
	TestManager tm = new TestManager(dp, 5, 6);
	TestNetwork tn = new TestNetwork(0);

	dp.setManager(tm);
	// dp.setNetwork(tn);
	dp.registerGuiListener(gui);

	System.out.println("Starte Test...");

	dp.start();

	// Starte Nachrichtproduzenten
	gui.start();
	tm.start();
	tn.start();

	try {
	    gui.join();
	    tm.join();
	    tn.join();
	} catch (InterruptedException e) {
	    // :p
	}

	dp.terminate();

	gui.printStats();
	tm.printStats();
	tn.printStats();
    }

}
