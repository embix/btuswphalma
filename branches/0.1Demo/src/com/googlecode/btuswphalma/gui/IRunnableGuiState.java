/**
 * 
 */
package com.googlecode.btuswphalma.gui;

/**
 * Dieses Interface erweitert IGuiState um eine run() Methode,
 * die bei einem Zustandswechsel fuer den neuen Zustand
 * ausgefuehrt wird
 * @author embix
 *
 */
interface IRunnableGuiState extends IGuiState{

    /**
     * Wird beim Zustandswechsel vom Controller aus auf dem neuen
     * Zustand aufgerufen.
     */
    void run();
}
