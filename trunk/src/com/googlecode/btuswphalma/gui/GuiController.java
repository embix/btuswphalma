/**
 * 
 */
package com.googlecode.btuswphalma.gui;

/**
 * Der GuiController ist ein Zustandsautomat, der die Interaktion
 * mit dem Benutzer und der Engine steuert. Die einzelnen Zustaende
 * werden durch extra Klassen implementiert.
 * 
 * @author embix
 *
 */
public class GuiController {

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
	// TODO: (GUI) Instanzierung der Zustaendsobjekte
	// TODO: (GUI) Instanzierung der GUI-Hilfsklassen
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
     * gibt den aktuellen Zustand des Controllers zurueck
     * 
     * @return der aktuelle Controllerzustand
     */
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
}