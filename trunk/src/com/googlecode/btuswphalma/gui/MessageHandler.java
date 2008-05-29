/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.base.BoardMessage;
import com.googlecode.btuswphalma.base.IGuiCom;
import com.googlecode.btuswphalma.base.IGuiListener;
import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.MessageType;
import com.googlecode.btuswphalma.base.MoveErrorMessage;
import com.googlecode.btuswphalma.base.ScoreMessage;
import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * Die Klasse MessageHandler stellt die wesentliche Schnittstelle nach
 * aussen (zur Spielengine, evtl. ueber das Netzwerk) dar und dient
 * dem Austausch der Nachrichten.
 * 
 * @author embix
 *
 */
public class MessageHandler
	implements IGuiListener{
    
    //private IGuiCom engine;
    private GuiController controller;
    
    
    /**
     * Konstruktor des MessageHandlers
     * 
     * @param engine gibt das Objekt an, dem die Nachrichten zugestellt
     * werden sollen.
     * @param controller gibt das Objekt an, welches die Gui steuert.
     */
    MessageHandler(IGuiCom engine, GuiController controller){
	// TODO: MessageHandler Messages an Engine senden lassen
	// this.engine = engine;
	this.controller = controller;
    }
    
    /**
     * Wird von aussen aufgerufen, wenn eine neue Nachricht ankommt.
     * Fuer jeden (bekannten) Nachrichtentyp wird die entsprechende
     * Behandlungsroutine aufgerufen.
     * 
     * @see com.googlecode.btuswphalma.base.IMessage
     * @see com.googlecode.btuswphalme.base.MessageType
     * @see com.googlecode.btuswphalma.base.IGuiListener#recvdMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void recvdMessage(IMessage msg) {
	MessageType type = msg.getType();
	// unschoen (feste Kopplung), geht sicher besser
	switch(type){
	case MT_MOVE:
	    recvMsgMove(msg);
	case MT_PLAYERDATA:
	    recvMsgPlayerData(msg);
	case MT_TERMINATE:
	    recvMsgTerminate(msg);
	case MT_SCORES:
	    recvMsgScores(msg);
	case MT_VETO:
	    recvMsgVeto(msg);
	case MT_BOARD:
	    recvMsgBoard(msg);
	case MT_MOVEERROR:
	    recvMsgMoveError(msg);
	case MT_PLAYERACTIVATE:
	    recvMsgPlayerActivate(msg);
	case MT_LOGIN:
	    recvMsgLogin(msg);
	case MT_GAMEEND:
	    recvMsgGameEnd(msg);
	case MT_SAVE:
	    recvMsgSave(msg);
	case MT_PLAYERFINISHED:
	    recvMsgPlayerFinished(msg);
	    
	default:
	    // unbekannter Nachrichtentyp: wird nur geloggt
	    System.out.println("(GUI)MessageHandler: unkown MessageType '" + type.toString() + "'");
	}
	
    }

    /**
     * Die Behandlungsroutine fuer Nachrichten des Typs
     * MT_PLAYERFINISHED ruft die entsprechende Behandlung
     * durch den Controller auf.
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgPlayerFinished(IMessage msg) {
	controller.getState().recvPlayerFinished();	
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs
     * MT_SAVE
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgSave(IMessage msg) {
	// TODO Auto-generated method stub
	
    }

    /**
     * Die Behandlungsroutine fuer Nachrichten des Typs
     * MT_GAMEEND ruft die fuer die Behandlung zustaendige
     * Methode des Controllers auf.
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgGameEnd(IMessage msg) {
	controller.getState().recvPlayerFinished();	
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs 
     * MT_LOGIN
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgLogin(IMessage msg) {
	// TODO Auto-generated method stub
	
    }

    /**
     * Die Behandlungsroutine fuer Nachrichten des Typs
     * MT_PLAYERACTIVATE ruft enstprechende Methode des
     * Contrllers auf.
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgPlayerActivate(IMessage msg) {
	controller.getState().recvPlayerActivate();
    }

    /**
     * Die Behandlungsroutine fuer Nachrichten des Typs
     * MT_MOVEERROR holt die Fehlermeldung aus der
     * Nachricht und uebergibt sie dem Controller zur
     * Anzeige.
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgMoveError(IMessage msg) {
	// unsauber	
	try {
	    MoveErrorMessage movErrMsg = (MoveErrorMessage) msg;
	    String errStr = movErrMsg.getErrorString();
	    controller.getState().recvMoveError(errStr);
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Die Behandlungsroutine fuer Nachrichten des Typs
     * MT_BOARD extrahiert das Board und uebergibt es
     * dem Controller.
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgBoard(IMessage msg) {
	// unsauber
	try {
	    BoardMessage boardMessage = (BoardMessage) msg;
	    Board board = boardMessage.getBoard();
	    controller.getState().recvBoard(board);
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs
     * MT_VETO
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgVeto(IMessage msg) {
	// TODO Auto-generated method stub
	
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs
     * MT_SCORES
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgScores(IMessage msg) {
	// unsauber
	try {
	    ScoreMessage scoreMsg = (ScoreMessage) msg;
	    ScoreList scores = scoreMsg.getScoreList();
	    controller.getState().recvScores(scores);
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
	
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs
     * MT_TERMINATE
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgTerminate(IMessage msg) {
	// TODO Auto-generated method stub
	
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs
     * MT_PLAYERDATA
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgPlayerData(IMessage msg) {
	// TODO Auto-generated method stub
	
    }

    /**
     * Behandlungsroutine fuer Nachrichten des Typs
     * MT_MOVE
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgMove(IMessage msg) {
	// TODO Auto-generated method stub
	
    }

    
    /**
     * Wird von innerhalb des Gui-Packages aus aufgerufen, wenn Nachrichten
     * an die Engine zu verschicken sind.
     * 
     * @param msg die zu sendende Nachricht
     */
    public void sendMessage(IMessage msg){
	
    }
    
    // TODO: (GUI) Message Handling implementieren
}
