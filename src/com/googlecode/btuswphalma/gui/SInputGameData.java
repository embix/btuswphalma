/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.base.LoginMessage;
import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;
import com.googlecode.btuswphalma.gameengine.ScoreList;
import com.googlecode.btuswphalma.gameengine.TerminateMessage;
import com.googlecode.btuswphalma.kiplayer.BestSingelStepKIAlgorithm;
import com.googlecode.btuswphalma.kiplayer.KIController;

/**
 * Controllerzustand nach Programmstart, zu diesem Zeitpunkt
 * sollen die Spieldaten eingegeben werden.
 * 
 * @see see com.googlecode.btuswphalma.gui.GuiController
 * @author embix
 * @author ASM
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
	// Auswahldialog: Master oder Client
	DialogMasterOrClient dialog = new DialogMasterOrClient(controller.getPresentation());
	if(dialog.ok()){
	    if(dialog.isMaster()){
		promptMasterGameData();
	    }else{
		promptClientGameData();
	    }
	    dataSend = true;	// TODO: ist dataSend wirklich notwendig?
	}
    }
    
    // Zeigt den Dialog für die Clientspielerdaten
    private void promptClientGameData() {
	DialogClientGameData dialog = new DialogClientGameData(controller.getPresentation(),false);
	
	if(dialog.ok()) {
	    ClientGameData cData = dialog.getClientGameData();
	    controller.getEngine().createManager(1, false, false);
	    controller.getEngine().createNetwork(cData.ip, cData.port, 0, false);
	    
	    controller.getMessageHandler().sendMessage(new LoginMessage(1,-1,cData.playerName));
	}
    }
    
    // Zeigt den Dialog für die Masterspielerdaten
    private void promptMasterGameData() {
	DialogMasterGameData dialog = new DialogMasterGameData(controller.getPresentation());
	
	if(dialog.ok()) {
	    MasterGameData mData = dialog.getMasterGameData();
	    controller.getEngine().createManager(mData.playerCount, true, mData.gmod == GameMode.HOT_SEAT);
	    
	    controller.getMessageHandler().sendMessage(new LoginMessage(1,-1,mData.playerName));
	    
	    if(mData.gmod == GameMode.HOT_SEAT ) {
		// Hotseat Modus -> Infos für die anderen Spieler abholen
		for(int i=1;i<mData.playerCount;i++) {
			DialogClientGameData mClientDlg = new DialogClientGameData(controller.getPresentation(),true);
			
			if(mClientDlg.ok()) {
			    ClientGameData mClientData = mClientDlg.getClientGameData();
			    controller.getMessageHandler().sendMessage(new LoginMessage(i+1,-1,mClientData.playerName));
			}
		    }
	    } else {
		// playerCount kann so uebergeben werden da ServerNetCom immer Anzahl Clients plus 1 haben will.
		controller.getEngine().createNetwork(null, mData.port, mData.playerCount, true);
		try {
		    System.err.println("AI?");
		    Thread.sleep(500);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		for (int i = 0; i < mData.aiCount;i++) {
		    //FIXME unterschiedliche KI benutzen
		    new KIController(new BestSingelStepKIAlgorithm(),"localhost",mData.port,"KI Spieler "+(i+1));
		}
	    }
	}
    }
    
    /**
     * Beendet das Spiel sofort durch Beenden der JVM.
     */
    void quitGame(){
	controller.mh.sendMessage(new TerminateMessage(1,-1));
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
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
	// wird ignoriert

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

    /**
     * Wird beim Zustandswechsel vom Controller aus auf dem neuen
     * Zustand aufgerufen.
     */
    public void run() {
	controller.plp.clear();
	promptGameData();
	controller.getEngine().start();	// starte Nachrichtenverteilung im Dispatcher
    }

    /**
     * Wird aufgerufen, wenn eine Spielerliste bekanntgegeben wurde
     * 
     * @param plrLst die Spielerliste
     */
    public void recvPlayerList(PlayerList plrLst) {
	controller.plp.process(plrLst);
    }

}
