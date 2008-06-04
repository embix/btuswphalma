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

/**
 * Klasse des Containers fuer die gesamte Darstellung, alle graphischen
 * Komponenten werden direkt oder inderekt mit der Presenation-Instanz
 * verbunden.
 * 
 * @author embix
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
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	
	// anpassen, da Boardpres mom auch auf 640x480 laeuft 
	this.setSize(640, 480);
	
	
	// das Menue darstellen
	HalmaMenuBar menubar = new HalmaMenuBar();
	setJMenuBar(menubar);
	
	// mit dem Controller verbinden
	controller = GuiController.getInstance();
	controller.initialize(this);
	
	// das Spielfeld anzeigen
	BoardPresentation boardpres = controller.getBoardPres();
	this.add(boardpres, BorderLayout.CENTER);
	
	// erst nach Anbindung der graphischen Komponenten sichtbar machen
	this.setVisible(true);
    }
    
    /**
     * Wenn ein neues Spiel gestartet werden soll (Menue->Neu) wird
     * der Dialog zur Eingabe der Spieldaten aufgerufen.
     */
    private void startMaster(){
	DialogMasterGameData mDialog = new DialogMasterGameData(this);
	MasterGameData mData = mDialog.getMasterGameData();
	// TODO: (GUI)Spieldaten verarbeiten
	if(mData.playerCount > 1){
	    // Namen der anderen Hotseatspieler erfragen
	}
    }
    
    
    /*
     * Innere Klassen fuer Anzeigeelemente, ggf "freilassen" in
     * eigene Datei, dabei dann aber auf Kopplungsprobleme achten.
     */
    
    /**
     * Diese Klasse implementiert das Menue
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
	    addItem("Neu", "neu", KeyEvent.VK_N,
		    "<html>Startet ein neue Spielsession</html>");
	    menu.addSeparator();
	    addItem("Beenden", "ende", -1,
		    "<html>Beendet die aktive Session und schliesst das Programm</html>");
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
		// TODO: (GUI) Menuehandling sauber umsetzen
		System.out.println("Menue: " + cmd);// debug Ausgabe
		if(cmd.equals("ende")){
		    System.exit(0);
		}
		if(cmd.equals("neu")){
		    startMaster();
		}
	    }
	}
    }
}
