/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * @author embix
 *
 */
public class SMakeMove implements IGuiState {

    private GuiController controller;
    
    /**
     * Kontstruktor fuer den Controllerzustand
     * @param controller gibt den GuiController an, zu dem dieser
     * Zustand gehoeren soll.
     */
    public SMakeMove(GuiController controller){
	this.controller = controller;
    }
    
    /**
     * Es wird nicht damit gerechnet, dass sich waehrend der Zugeingabe
     * die Aufstellung aendert.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvBoard(com.googlecode.btuswphalma.gameengine.Board)
     */
    public void recvBoard(Board b) {
	// wird ignoriert

    }

    /**
     * Wird aufgerufen, wenn das Spiel ploetzlich durch die Engine beendet wird.
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvGameEnd()
     */
    public void recvGameEnd() {
	// Zustandswechsel in den Spielendezustand
	// TODO: (GUI) Meldung ueber ploetzliches Spielende ausgeben
	controller.setState(controller.stateSessionEnd);

    }

    /**
     * Es wird nicht damit gerechnet, dass waehrend der Zugeingabe ein
     * eine Anzeige eines anderen Zuges erfolgen soll.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvHalmaMove(com.googlecode.btuswphalma.gameengine.HalmaMove)
     */
    public void recvHalmaMove(HalmaMove tm) {
	// wird ignoriert

    }

    /**
     * Sofern ein Spielfehler auftritt, wird dieser erst spaeter erwartet,
     * wenn sich der Controller im Zuganzeigezustand befindet.
     * Allerding kann es unter unguenstigen Umstaenden dazu kommen, dass
     * sich der Controller noch in diesem Zustand befindet.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvMoveError(java.lang.String)
     */
    public void recvMoveError(String errStr) {
	// wird ignoriert
    }

    /**
     * Der Spieler ist schon am Zug.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerActivate()
     */
    public void recvPlayerActivate() {
	// wird ignoriert

    }

    /**
     * Wenn der Spieler am Zug ist, kann er noch nicht fertig sein.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerFinished()
     */
    public void recvPlayerFinished() {
	// wird ignoriert

    }

    /**
     * Wenn der Spieler am Zug ist, wird nicht erwartet, die Spielergebnisse
     * zu bekommen.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvScores(com.googlecode.btuswphalma.gameengine.ScoreList)
     */
    public void recvScores(ScoreList s) {
	// wird ignoriert
    }
    
    /**
     * Sendet den Spielzug ueber den MessageHandler an die Engine
     * 
     * @param hm gibt den zu uebertragenden Zug an
     */
    void sendHalmaMove(HalmaMove hm){
	// TODO: (GUI) implementieren, wenn der MessageHandler fertig ist
	
	// nach dem Senden wird auf die Anzeige der Spielzuege gewartet
	controller.setState(controller.stateShowMove);
    }
    
    /**
     * gibt die Eingabe eines Zuges frei
     */
    void makeMove(){
	// TODO: (GUI) implementieren, wenn das Zugeingabehandling fertig ist.
	//HalmaMove hm, controller.getBuilder(). 
	//sendHalmaMove(hm)
    }

}