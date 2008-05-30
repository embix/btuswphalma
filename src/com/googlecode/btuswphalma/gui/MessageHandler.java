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
import com.googlecode.btuswphalma.base.MoveMessage;
import com.googlecode.btuswphalma.base.ScoreMessage;
import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * Die Klasse MessageHandler stellt die wesentliche Schnittstelle nach
 * aussen (zur Spielengine, evtl. ueber das Netzwerk) dar und dient
 * dem Austausch der Nachrichten.
 * Fuer die Hotseatvariante werden mehrere MessageHandler gleichzeitig
 * verwendet (ist einebessere Architektur moeglich?)
 * 
 * @author embix
 *
 */
public class MessageHandler
	implements IGuiListener{
    
    
    /**
     * Die Empfaenger ID entspricht immer der, der SpielEngine
     * also (-1)
     */
    public static final int DESTINATION_ID = -1;
    
    /**
     * Die Empfaenger ID des Messagehandlers wird erst festgelegt
     * 
     */
    private int source;
    
    private IGuiCom engine;
    private GuiController controller;
    
    
    /**
     * Konstruktor des MessageHandlers
     * 
     * @param engine gibt das Objekt an, dem die Nachrichten zugestellt
     * werden sollen.
     * @param controller gibt das Objekt an, welches die Gui steuert.
     */
    private MessageHandler(IGuiCom engine, GuiController controller){
	this.engine = engine;
	// sich bei der Engine anmelden, damit Nachrichten empfangen werden koennen
	this.engine.registerGuiListener(this);
	this.controller = controller;
    }
    
    /**
     * Erstellung des Messagehandlers fuer den Master
     * sollte auf eins limitiert werden
     * @param engine 
     * @param controller 
     * @return 
     */
    public static MessageHandler createMasterMessageHandler(IGuiCom engine, GuiController controller){
	MessageHandler mh = new MessageHandler(engine, controller);
	// TODO: (GUI) MessageHandler Instanzierung  fuer Master
	return mh;
    }
    
    // TODO: (GUI) Erstellung des MessageHandlers fuer (Hotseat)Clients 
    
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
	    // unbekannter Nachrichtentyp: wird nur fuer debugging geloggt
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
     * Die Behandlungsroutine fuer Nachrichten des Typs 
     * MT_LOGIN tut nichts, da die LoginMessages nur in
     * Richtung Engine versendet werden, folglich wird
     * eine solche auch nicht erwartet.
     * 
     * @param msg gibt die zu behandelnde Nachricht an
     */
    private void recvMsgLogin(IMessage msg) {
	// wird ignoriert
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
     * Die Behandlungsroutine fuer Nachrichten des Typs
     * MT_SCORES uebergibt den Nachritchteninhalt an den
     * Controller.
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
     * Fuer diesen Nachrichtentyp gibt es noch keine entsprechende
     * Nachrichtenklasse, so dass zunaechst nichts passiert.
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
	// unsauber
	try {
	    MoveMessage moveMsg = (MoveMessage) msg;
	    HalmaMove move = moveMsg.getMove();
	    controller.getState().recvHalmaMove(move);
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
	
    }

    
    /**
     * Wird von innerhalb des Gui-Packages aus aufgerufen, wenn Nachrichten
     * an die Engine zu verschicken sind.
     * 
     * @param msg die zu sendende Nachricht
     */
    public void sendMessage(IMessage msg){
	// TODO: (GUI) Message Handling implementieren
	engine.recvMessage(msg);
    }
    
    /**
     * Sendet einen (eingegebenen) Zug an die Engine
     * @param move gibt den zu versenden Zug an
     */
    public void sendMove(HalmaMove move){
	// Zug in Nachrichten verpacken
	MoveMessage moveMessage = new MoveMessage(source, DESTINATION_ID, move);
	// und weiterleiten
	sendMessage(moveMessage);
    }
    
}
