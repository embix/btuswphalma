/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * Controllerzustand nach Programmstart, zu diesem Zeitpunkt
 * sollen die Spieldaten eingegeben werden.
 * 
 * @see see com.googlecode.btuswphalma.gui.GuiController
 * @author embix
 */
public class SInputGameData implements IRunnableGuiState {
    
    private GuiController controller;
    private boolean dataSend;

    /**
     * Kontstruktor fuer den Controllerzustand
     * @param controller gibt den GuiController an, zu dem dieser
     * Zustand gehoeren soll.
     */
    public SInputGameData(GuiController controller){
	this.controller = controller;
    }
    
    void sendGameData(MasterGameData gameData){
	// TODO: (GUI) senden der Spieldaten ueber den MessageHandler
	
	dataSend = true;
    }
    
    void sendGameData(ClientGameData gameData){
	// TODO: (GUI) senden der Spieldaten ueber den MessageHandler
	
	dataSend = true;
    }
    
    void promptGameData(){
	// TODO: (GUI) Auswahldialog: Master oder Client
	boolean master = true;// entsprechend der Fallunterscheidung
	
	if(master){
	   DialogMasterGameData dialog = new DialogMasterGameData(controller.getPresentation());
	   sendGameData(dialog.getMasterGameData()); 
	}else{
	    DialogClientGameData dialog = new DialogClientGameData(controller.getPresentation());
	    sendGameData(dialog.getClientGameData());
	}
    }
    
    /**
     * Beendet das Spiel sofort durch Beenden der JVM.
     */
    void quitGame(){
	System.exit(0);
    }
    
    /** 
     * Sofern die Spieldaten an die Engine geschickt wurden, wird auf
     * den Start des Spiels gewartet. Wenn das Spiel beginnt, wird die
     * Startaufstellung mittels Board Objekt versendet.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvBoard(com.googlecode.btuswphalma.gameengine.Board)
     */
    public void recvBoard(Board b) {
	// Spieldaten schon abgeschickt?
	if(dataSend){
	    // Board anzeigen lassen
	    controller.getBoardPres().setBoard(b);
	    controller.getBoardPres().showBoard();
	    
	    // Ueberganng zu ZugAnzeigen gem StateChart
	    controller.setState(controller.stateShowMove);
	}
    }

    /**
     * In diesem Zustand hat das Spiel noch nicht begonnen, kann also
     * noch nicht zu Ende sein.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvGameEnd()
     */
    public void recvGameEnd() {
	// wird ignoriert

    }

    /**
     * In diesem Zustand hat das Spiel noch nicht begonnen, wird nicht
     * weiter behandelt. Zunaechst sollte das Board kommen.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvHalmaMove(com.googlecode.btuswphalma.gameengine.HalmaMove)
     */
    public void recvHalmaMove(HalmaMove tm) {
	// wird ignoriert, da bei Spielbeginn zunaechst die Startaufstellung
	// als Board Objekt gesendet wird
    }

    /**
     * Der Spieler hat noch keinen Zug gemacht, kann also auch keinen Fehler
     * gemacht haben. Wird nicht behandelt.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvMoveError(java.lang.String)
     */
    public void recvMoveError(String errStr) {
	// wird ignoriert
    }

    /**
     * In diesem Zustand hat das Spiel noch nicht begonnen, wird nicht
     * weiter behandelt.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerActivate()
     */
    public void recvPlayerActivate() {
	// TODO: (GUI) Wechsel zu am Zug - Zustand, sofern

    }

    /** 
     * Spiel hat noch nicht begonnen - wird nicht behandelt.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerFinished()
     */
    public void recvPlayerFinished() {
	// wird ignoriert

    }

    /**
     * Spiel ist noch nicht zu Ende, kann folglich auch keine Spielergebnisse
     * empgangen.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvScores(com.googlecode.btuswphalma.gameengine.ScoreList)
     */
    public void recvScores(ScoreList s) {
	// wird ignoriert

    }

    public void run() {
	// TODO Auto-generated method stub
	
    }

}
