/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.ScoreList;
import com.googlecode.btuswphalma.serverengine.Dispatcher;

/**
 * Der GuiController ist ein Zustandsautomat, der die Interaktion
 * mit dem Benutzer und der Engine steuert. Die einzelnen Zustaende
 * werden durch extra Klassen implementiert.
 * Damit die externen Klassen nichts ueber den Aufbau des Controllers
 * wissen muessen, werden die durch das IGuiState-Interface geforderten
 * Methoden angeboten und fuer den Aufrufer transparent an die aktiven
 * Zustand weitergeleitet (bessere Kapselung).
 * 
 * @author embix
 *
 */
public class GuiController{

    private static GuiController controller;
    
    // der gegenwaertige Zustand des Controllers
    private IGuiState state;

    // alle moeglichen Zustaende des Controllers
    IGuiState stateInputGameData;
    IGuiState stateMakeMove;
    IGuiState stateShowMove;
    IGuiState stateSpectator;
    IGuiState stateSessionEnd;
    //IGuiState stateHighScoreShow; // Wunschkriterium, wird spaeter implementiert
    
   
    // Darstellung des Spielbrettes incl. der Aufstellung
    private BoardPresentation boardPres;
    
    // Hilfsklasse zu bauen der Spielzuege
    private BuilderHalmaMove builder;
    
    // Hilfsklasse zum Verarbeiten der Benutzereingaben
    private InputHandler inh;
    
    // die Poststelle / Schnittstelle zu Engine (und Netz)
    private MessageHandler mh;
    
    // das Hauptfenster
    private Presentation frame;
    
    /**
     * Der Konstruktor des Controllers instanziert seine
     * Zustandsobjekte und instanziert die Hilfsklassen
     * beziehungsweise verbindet sich mit ihnen.
     * 
     * Die Parameteruebergabe steht noch nicht fest, sie
     * haengt vom Bootprozess des Programms ab
     */
    private GuiController(){
	// Instanzierung der Zustaendsobjekte
	stateInputGameData = new SInputGameData(this);
	stateMakeMove = new SMakeMove(this);
	stateShowMove = new SShowMove(this);
	
	//stateSpectator = new SSpectator(this);
	/* Fuer den Hotseatmode wird nicht der normale SSpectator-Zustand
	 * verwendet, da sonst (nach fertig werden eines Spielers) nicht mehr
	 * auf playerActivate reagiert wird.
	 * Daher wird stateSpectator=stateShowMove gesetzt.
	 * TODO: (GUI)(TP3) Unterscheidung zwischen Hotseat und Netzwerkspiel
	 * betrachten und z.B. zwei Konstruktoren anbieten
	 */ 
	stateSpectator = stateShowMove;
	
	stateSessionEnd = new SSessionEnd(this);
	
	// TODO: (GUI) Instanzierung der GUI-Hilfsklassen
	this.boardPres = new BoardPresentation();
	this.builder = new BuilderHalmaMove();
	this.inh = new InputHandler();
	
	// FIXME: (ALLE) Instanzierung Engine (gameengine und serverengine)
	Dispatcher engine = new Dispatcher(); // hoffentlich reicht das...
	this.mh = MessageHandler.createMasterMessageHandler(engine, this);
	
	// Presentation traegt sich selbst ein, siehe initialize()
    }
    
    /**
     * Da es nur einen Controller fuer die Gui geben darf ist
     * die Instanzierung limitiert.
     * 
     * @return gibt den Controller zurueck
     */
    public static synchronized GuiController getInstance(){
	if(controller == null){
	    controller = new GuiController();
	}
	return controller;
    }
    
    
    /**
     * Gibt den aktuellen Zustand des Controllers zurueck, stattdessen
     * sollen die Methodenaufrufe direkt an den GuiController gerichtet
     * werden.
     * Sollte nicht mehr verwendet werden, da saemtliche Methoden des
     * Zustandes jetzt ueber den Controller angesprochen werden koennen.
     * 
     * @return der aktuelle Controllerzustand
     * @deprecated
     */
    @Deprecated
    public IGuiState getState(){
	return state;
    }
    
    /**
     * Fuehrt einen Zustandswechsel durch. Da dieser vom urspruenglichen
     * Zustand abhaengt, muss diese Methdode fuer den alten Zustand sichtbar
     * sein, somit ist die Methode fuer das gesamte Package sichtbar.
     * 
     * @param state der neue Zustand des Controllers
     */
    void setState(IGuiState state){
	// TODO: (GUI) wenn beim Zustandswechsel eine Aktion erforderlich ist,
	// soll diese ggf auch ausgefuehrt werden - umgeht unsaubere casts
	// Ansatz: void commitChange(); // oder run();
	this.state = state;
    }
    
    /**
     * Gibt die BoardPresentation zurueck, auf der das Spielgeschehen
     * dargestellt wird.
     * 
     * @return das BoardPresentation Objekt
     */
    public BoardPresentation getBoardPres(){
	return boardPres;
    }
    
    /**
     * Gibt den zustaendigen InputHandler zurueck
     * @return gibt den Eingabehandler zurueck
     */
    public InputHandler getInHandler(){
	return inh;
    }
    
    /**
     * Gibt das fuer die Zugkonstruktion zustaendige Objekt zurueck
     * @return gibt den Zugbauer zurueck
     */
    public BuilderHalmaMove getBuilder(){
	return builder;
    }
    
    /**
     * Gibt das fuer die Nachrichtenversendung zustaendige Objekt zurueck
     * @return gibt den MessageHandler zuruck
     */
    public MessageHandler getMessageHandler(){
	return mh;
    }
    
    /**
     * Gibt das fuer die Anzeige zustaendige Objekt zuruck
     * @return geibt das Hauptfenster zurueck
     */
    public Presentation getPresentation(){
	return frame;
    }

    /**
     * Mit dieser Methode werden die graphischen Komponenten mit dem
     * fuer die Ausgabe zustaendigen Frame verbunden.
     * 
     * @param frame gibt das fuer die Darstellung zustaendige Presentation
     */
    public void initialize(Presentation frame) {
	// TODO: (GUI) eigene Darstellungselemente in das Frame einbetten
	// ist momentan innerhalb Presentation realisiert
	this.frame = frame;
	// den Inputhandler auf die Boardpresaentation ansetzen
	boardPres.addMouseListener(inh);
    }

    
    
    /*
     * Hier kommen die Methoden, welche durch IGuiState implementiert
     * werden muessen. Dabei werden die Aufrufe direkt an den aktiven
     * Zustand weitergeleitet. 
     */
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * eine neue Spielaufstellung uebermittelt wurde.
     * 
     * @param b gibt das aktuelle Objekt fuer die Aufstellung an 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvBoard(com.googlecode.btuswphalma.gameengine.Board)
     */
    public void recvBoard(Board b) {
	this.state.recvBoard(b);	
    }

    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * das Ende der Session bekanntgegeben wurde.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvGameEnd()
     */
    public void recvGameEnd() {
	this.state.recvGameEnd();
    }
    
    /** 
     * Wird vom MessageHandler aufgerufen, wenn von der SpielEngine ein
     * Spielzug uebermittelt wurde. 
     * 
     * @param tm gibt den Spielzug an, der uebertragen werden soll
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvHalmaMove(com.googlecode.btuswphalma.gameengine.HalmaMove)
     */
    public void recvHalmaMove(HalmaMove tm) {
	this.state.recvHalmaMove(tm);	
    }

    /**
     * Wird vom MessageHandler aufgerufen, wenn von der SpielEngine
     * ein Fehler uebermittelt wurde.
     * 
     * @param errStr ist der die Fehlerausgabe enthaltene String
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvMoveError(java.lang.String)
     */
    public void recvMoveError(String errStr) {
	this.state.recvMoveError(errStr);
    }

    /**
     * Wird vom MessageHandler aufgerufen, wenn der Spieler von der
     * SpielEngine als naechster ausgewaehlt wurde.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerActivate()
     */
    public void recvPlayerActivate() {
	this.state.recvPlayerActivate();	
    }

    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * festgestellt wurde, dass der Spieler fertig ist.
     * 
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvPlayerFinished()
     */
    public void recvPlayerFinished() {
	this.state.recvPlayerFinished();
    }

    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * die Spielergebnisse bekanntgegeben wurden.
     * 
     * @param s gibt das Objekt fuer die Spielergebnisse an.
     * @see com.googlecode.btuswphalma.gui.IGuiState#recvScores(com.googlecode.btuswphalma.gameengine.ScoreList)
     */
    public void recvScores(ScoreList s) {
	this.state.recvScores(s);	
    }
}
