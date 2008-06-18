/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * Controllerzustand bei Spielende
 * 
 * @author embix
 */
public class SSessionEnd implements IRunnableGuiState {

    private GuiController controller;
    
    /**
     * Kontstruktor fuer den Controllerzustand
     * @param controller gibt den GuiController an, zu dem dieser
     * Zustand gehoeren soll.
     */
    public SSessionEnd(GuiController controller){
	this.controller = controller;
    }
    
    /**
     * Da das Spiel beendet ist, wird nicht erwartet eine Spielaufstellung
     * zu erhalten.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvBoard(com.googlecode.btuswphalma.gameengine.Board)
     */
    public void recvBoard(Board b) {
	// wird ignoriert

    }

    /**
     * Das Spiel ist bereits beendet, es wird nicht mit einem erneuten
     * Spielende gerechnet.
     *  
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvGameEnd()
     */
    public void recvGameEnd() {
	// wird ignoriert

    }

    /**
     * Da das Spiel beendet ist, wird nicht mit einem weiteren
     * Spielzug gerechnet.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvHalmaMove(com.googlecode.btuswphalma.gameengine.HalmaMove)
     */
    public void recvHalmaMove(HalmaMove tm) {
	// wird ignoriert;

    }

    /**
     * Da das Spiel beendet ist wird nicht mit einem Spielfehler gerechnet.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvMoveError(java.lang.String)
     */
    public void recvMoveError(String errStr) {
	// wird ignoriert

    }

    /**
     * Das Spiel ist beendet, der Spieler kann nicht reaktiviert werden.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerActivate()
     */
    public void recvPlayerActivate() {
	// wird ignoriert

    }

    /**
     * Das Spiel ist beendet, der Spieler ist also schon fertig.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerFinished()
     */
    public void recvPlayerFinished() {
	// wird ignoriert

    }

    /**
     * Wenn das Spiel zuende ist wurde sollen die Spielergebnisse angezeigt werden.
     * Diese Methode wird in der Regel von den anderen Zustaenden aus aufgerufen.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvScores(com.googlecode.btuswphalma.gameengine.ScoreList)
     */
    public void recvScores(ScoreList s) {
	controller.setState(controller.stateSessionEnd);
	DialogGameScore dialog = new DialogGameScore(controller.getPresentation(), s);
	if(!(dialog.ok())){
	    // sollte nicht passieren
	}
	// Anschliessend neue Session starten, oder beenden
	controller.setState(controller.stateInputGameData);
	
	// (GUI) TODO: unsauber! ordentlich machen
	try {
	    SInputGameData state = (SInputGameData)controller.stateInputGameData;
	    state.promptGameData(); // neue Spieldateneingabe
	} catch (RuntimeException e) {
	    e.printStackTrace(); // Debugausgabe
	    System.exit(1); // Programmende
	}
    }

    public void run() {
	// TODO Auto-generated method stub
	
    }

}
