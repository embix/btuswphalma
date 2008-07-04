/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

/**
 * Klasse des Containers fuer die gesamte Darstellung, alle graphischen
 * Komponenten werden direkt oder inderekt mit der Presenation-Instanz
 * verbunden.
 * 
 * @author embix
 * @author ASM
 * 
 */
public class Presentation extends JFrame {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 8544934143314222146L;

    private GuiController controller;
    
    /**
     * Initialisierung
     */
    public Presentation() {
	this.setTitle("BTU SWP Halma");
	
	// TODO: (GUI) wenn mitten in Spielsession soll (modaler)
	// Abfragedialog erscheinen - idealerweise mit Speicheroption
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	
	// BoardPresentation waehlt Groesse aus RADIUS 
	//this.setSize(640, 480);
	
	
	// das Menue darstellen
	HalmaMenuBar menubar = new HalmaMenuBar();
	setJMenuBar(menubar);
	
	// mit dem Controller verbinden
	controller = GuiController.getInstance();
	controller.initialize(this);
	
	// das Spielfeld anzeigen
	BoardPresentation boardpres = controller.getBoardPres();
	boardpres.setBorder(new BevelBorder(BevelBorder.LOWERED));
	this.add(boardpres, BorderLayout.CENTER);
	
	this.pack();
	
	// erst nach Anbindung der graphischen Komponenten sichtbar machen
	this.setVisible(true);
    }
    
    /**
     * Wenn ein neues Spiel gestartet werden soll (Menue->Neu) wird
     * der Dialog zur Eingabe der Spieldaten aufgerufen.
     * 
     * @deprecated
     */
    /*private void startMaster(){
	DialogMasterGameData mDialog = new DialogMasterGameData(this);
	if(mDialog.ok()){
	 // TODO: (GUI)Spieldaten verarbeiten
	    MasterGameData mData = mDialog.getMasterGameData();
	    controller.getEngine().createManager(mData.playerCount, true);
	    
	    controller.getMessageHandler().sendMessage(new LoginMessage(mData.playerName,1,-1));
	   
	    for(int i=1;i<mData.playerCount;i++) {
		// Hotseat Pfad
		DialogClientGameData mClientDlg = new DialogClientGameData(this);
		
		if(mClientDlg.ok()) {
		    ClientGameData mClientData = mClientDlg.getClientGameData();
		    
		    controller.getMessageHandler().sendMessage(new LoginMessage(mClientData.playerName,i+1,-1));
		}
	    }
	}
    }*/
    
    
    /**
     * Den Massstab fuer die Spielfelddarstellung vergrossern
     */
    private void increaseScale(){
	// unsauber
	int radius = controller.getBoardPres().getRadius();
	System.out.print("radius="+radius+"\n");
	radius +=2;
	controller.getBoardPres().setRadius(radius);
	controller.plp.setRadius(radius);
	if(radius == controller.getBoardPres().getRadius()){
	    pack();
	}
    }
    
    /**
     * Den Massstab fuer die Spielfelddarstellung verkleinern
     */
    private void decreaseScale(){
	// unsauber
	int radius = controller.getBoardPres().getRadius();
	System.out.print("radius="+radius+"\n");
	radius -=2;
	controller.getBoardPres().setRadius(radius);
	controller.plp.setRadius(radius);
	if(radius == controller.getBoardPres().getRadius()){
	    pack();
	}
    }
    
    
    /*
     * Innere Klassen fuer Anzeigeelemente, ggf "freilassen" in
     * eigene Datei, dabei dann aber auf Kopplungsprobleme achten.
     */
    
    /**
     * Diese innere Klasse implementiert das Menue
     * @author embix
     */
    public class HalmaMenuBar extends JMenuBar{

	private static final long serialVersionUID = 1309789090746454939L;
	JMenu menu;
	MenuListener mlistener = new MenuListener();
	
	/**
	 * Konstruktor fuer den Menuebalken
	 */
	public HalmaMenuBar(){
	    // Menue Spiel
	    menu = new JMenu("Spiel");
	    
	    // Menuepunkt Neu
	    addItem("Neu", "neu", KeyEvent.VK_N,
		    "<html>Startet ein neue Spielsession</html>");
	    
	    menu.addSeparator();
	    // Menuepunkte Darstellung vergroessern/verkleinern
	    addItem("<html>Darstellung vergr&ouml;&szlig;ern</html>", "groesser", KeyEvent.VK_PLUS,
		    "<html>Vergr&ouml;&szlig;ert den Ma&szlig;stab f&uuml;r die<p> Darstellung des Spielfeldes</html>");
	    addItem("Darstellung verkleinern", "kleiner", KeyEvent.VK_MINUS,
		    "<html>Verkleinert den Ma&szlig;stab f&uuml;r die<p> Darstellung des Spielfeldes</html>");
	    
	    // Menuepunkt Beenden
	    addItem("Beenden", "ende", -1,
		    "<html>Beendet die aktive Session und schlie&szlig;t das Programm</html>");
	    
	    add(menu);
	}
	
	/**
	 * Fuegt dem Menuepunkt Unterpunkte hinzu
	 * 
	 * @param label Angezeigter Titel
	 * @param cmd Befehlsstring zu dem Titel
	 * @param code Belegung fuer Tastenkombination (keine = -1)
	 */
	private void addItem(String label, String cmd, int code, String toolTip){
	    JMenuItem mi = new JMenuItem(label);
	    mi.setActionCommand(cmd);
	    mi.setToolTipText(toolTip);
	    if(code != -1){
		mi.setAccelerator(KeyStroke.getKeyStroke(code, InputEvent.CTRL_MASK));
	    }
	    mi.addActionListener(mlistener);
	    menu.add(mi);
	}
	
    }
    
    /**
     * Klasse zur Behandlung der Menueereignisse
     * 
     * @author embix
     */
    public class MenuListener implements ActionListener{

	/**
	 * Methode zum reagieren auf die MenueEreignisse
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
	    Object obj = e.getSource();
	    String cmd = e.getActionCommand();
	    
	    if(obj instanceof JMenuItem){
		// TODO: (GUI) Menuehandling sauberer umsetzen
		System.out.println("Menue: " + cmd);// debug Ausgabe
		if(cmd.equals("ende")){
		    System.exit(0);
		}
		if(cmd.equals("neu")){
		    // startMaster();
		    controller.newGame();
		}
		if(cmd.equals("groesser")){
		    increaseScale();
		}
		if(cmd.equals("kleiner")){
		    decreaseScale();
		}
	    }
	}
    }
}
