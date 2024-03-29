/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * Im Spectatorzustand ist der Spieler fertig, jedoch sind noch
 * andere Spieler aktiv, deren Zuege angezeigt werden.
 * 
 * @author embix
 */
public class SSpectator implements IRunnableGuiState {

    private GuiController controller;
    
    /**
     * Kontstruktor fuer den Controllerzustand
     * @param controller gibt den GuiController an, zu dem dieser
     * Zustand gehoeren soll.
     */
    public SSpectator(GuiController controller){
	this.controller = controller;
    }

    /**
     * Wenn ein Board Objekt angekommen ist, so wird die darin codierte
     * Aufstellung zur Anzeige gebracht.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvBoard(com.googlecode.btuswphalma.gameengine.Board)
     */
    public void recvBoard(Board b) {
	// evtl angezeigten Zug ausblenden
	controller.getBoardPres().hideMove();
	// Board anzeigen
	controller.getBoardPres().setBoard(b);
	controller.getBoardPres().showBoard();

    }

    /**
     * Wird aufgerufen, wenn durch die das Sessionende verkuendet wurde,
     * ohne die Session ordnungsgemaess zu Ende gespielt wurde.
     * Wechselt den Zustand des Controllers zum SessionEnd Zustand.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvGameEnd()
     */
    public void recvGameEnd() {
	// Zustandswechsel in den Spielendezustand
	// TODO: (GUI) Meldung ueber ploetzliches Spielende ausgeben
	controller.setState(controller.stateSessionEnd);
    }

    /**
     * Wenn ein neuer Zug uebertragen wurde, soll dieser zur Anzeige
     * gebracht werden.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvHalmaMove(com.googlecode.btuswphalma.gameengine.HalmaMove)
     */
    public void recvHalmaMove(HalmaMove tm) {
	controller.getBoardPres().setHalmaMove(tm);
	controller.getBoardPres().showMove();

    }

    /**
     * Wenn ein Spieler einen Fehler gemacht hat, soll die Fehlermeldung
     * in einem Dialog ausgegeben werden.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvMoveError(java.lang.String)
     */
    public void recvMoveError(String errStr) {
	// ggf in extra Thread starten, damit Controller weiterhin auf
	// Nachrichten reagieren kann
	DialogError dialog = new DialogError(controller.getPresentation(),errStr);
	if(!(dialog.ok())){
	    // sollte nicht passieren
	}
    }

    /**
     * Da der Spieler bereits fertig ist, wird nicht erwartet erneut
     * aktiviert zu werden.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerActivate()
     */
    public void recvPlayerActivate() {
	// wird ignoriert

    }

    /**
     * Da der Spieler bereits fertig ist, wird nicht erwartet erneut
     * aktiviert zu werden.
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerFinished()
     */
    public void recvPlayerFinished() {
	// wird ignoriert

    }

    /**
     * Wenn die ScoreList uebermittelt wird, ist das Spiel zu Ende und das
     * Spielergebnis wird angezeigt. 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvScores(com.googlecode.btuswphalma.gameengine.ScoreList)
     */
    public void recvScores(ScoreList s) {
	controller.stateSessionEnd.recvScores(s);
	controller.setState(controller.stateSessionEnd);
    }

    /**
     * Wird beim Zustandswechsel vom Controller aus auf dem neuen
     * Zustand aufgerufen.
     */
    public void run() {
	// TODO Auto-generated method stub
	
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
