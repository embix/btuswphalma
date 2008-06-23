/**
 * Testprotokoll fuer die Zugeingabe
 * 
 * <p>
 * Tester:
 * <br>
 * Datum:
 * <br>
 * Reposversion:
 * <br>
 * Buildumgebung/Compiler und Betriebssystem:
 * <br>
 * Laufzeitumgebung und Betriebssystem:
 * </p>
 * 
 * 
 * <p>
 * Aktion:
 * 	Testklasse ausfuehren
 * <br>
 * erwartetes Ergebnis:
 * 	Das leere Spielfeld ist zu sehen, ebenso zwei
 * 	Schaltflaechen fuer die Zugeingabe und die
 * 	Zugbestaetigung. 
 * <br>
 * Beobachutung (sofern abweichend von Erwartung):
 * Testpunkt bestanden:
 * </p>
 * 
 * 
 * <p>
 * Aktion:
 * 	nach einander auf mehrere gueltige und ungueltige
 * 	Spielfelder klicken
 * <br>
 * erwartetes Ergebnis:
 * 	Es sollte sich nichts veraendern.
 * <br>
 * Beobachutung (sofern abweichend von Erwartung):
 * Testpunkt bestanden:
 * </p>
 * 
 * 
 * <p>
 * Aktion:
 * 	die Schaltflaeche fuer die Zugeingabe betaetigen und
 * 	anschliessend einen Spielzug vom Typ Schub eingeben
 * <br>
 * erwartetes Ergebnis:
 * 	Bei Betaetigung der Schaltflaeche erfolgt die
 * 	Konsolenausgabe, dass sich der Controllerzustand
 * 	von SShowMove nach SMakeMove geaendert hat.
 * 	Bei Eingabe des Zuges soll dieser auf dem Spielfeld
 * 	dargestellt und die Koordinaten ausgegeben werden.
 * <br>
 * Beobachutung (sofern abweichend von Erwartung):
 * Testpunkt bestanden:
 * </p>
 * 
 * 
 * <p>
 * Aktion:
 * 	die Schaltflaeche fuer die Zugbestaetigung betaetigen,
 * 	danach auf gueltige und ungueltige Spielfelder klicken
 * <br>
 * erwartetes Ergebnis:
 * 	Ein Wechsel des Controllerzustandes von SMakeMove nach
 * 	SShowMove sollte auf der Konsole ausgegeben werden.
 * 	Weiterhin sollte der Zug geloescht/ nicht mehr dargestellt
 * 	werden. Nach einem Klick auf das Spielbrett passiert
 * 	nichts.
 * <br>
 * Beobachutung (sofern abweichend von Erwartung):
 * Testpunkt bestanden:
 * </p>
 * 
 * 
 * <p>
 * Aktion:
 * 	die Schaltflaeche fuer die Zugeingabe betaetigen und
 * 	anschliessend einen Spielzug vom Typ Sprung eingeben
 * <br>
 * erwartetes Ergebnis:
 * 	Bei Betaetigung der Schaltflaeche erfolgt die
 * 	Konsolenausgabe, dass sich der Controllerzustand
 * 	von SShowMove nach SMakeMove geaendert hat.
 * 	Bei Eingabe des Zuges soll dieser auf dem Spielfeld
 * 	dargestellt und die Koordinaten ausgegeben werden.
 * <br>
 * Beobachutung (sofern abweichend von Erwartung):
 * Testpunkt bestanden:
 * </p>
 * 
 * 
 * <p>
 * Aktion:
 * 	die Schaltflaeche fuer die Zugbestaetigung betaetigen,
 * 	danach auf gueltige und ungueltige Spielfelder klicken
 * <br>
 * erwartetes Ergebnis:
 * 	Ein Wechsel des Controllerzustandes von SMakeMove nach
 * 	SShowMove sollte auf der Konsole ausgegeben werden.
 * 	Weiterhin sollte der Zug geloescht/ nicht mehr dargestellt
 * 	werden. Nach einem Klick auf das Spielbrett passiert
 * 	nichts.
 * <br>
 * Beobachutung (sofern abweichend von Erwartung):
 * Testpunkt bestanden:
 * </p>
 * 
 * 
 * <p>
 * Test bestanden:
 * <br>
 * Was muss noch getan werden:
 * <br>
 * Was sollte noch getan werden:
 * </p>
 */
package com.googlecode.btuswphalma.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Testklasse fuer die Zugeingabe
 * <p>
 * Zunaechst werden alle Klassen bereitgestellt, die zur
 * Zugeingabe benoetigt werden. Anschliessend kann man
 * zur Zugeingabe auffordern. Nach erfolgter Zugeingabe
 * ist eine Bestaetigung abzugeben, was zur Ausgabe der
 * Zugfolge fuehren soll. Schliesslich kann die Zugeingabe
 * wieder aktiviert werden.
 * </p>
 * @author embix
 */
public class InputHandlingScenario
	extends JPanel{

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 5377579732183480693L;
    
    private JFrame owner;
    private BoardPresentation pres;
    private InputHandler inh;
    private GuiController controller;

    /**
     * Konstruktor fuer die Zugeingabetestklasse
     * @param owner
     */
    public InputHandlingScenario(JFrame owner){
	this.owner = owner;
	this.controller = GuiController.getInstance();
	
	pres = controller.getBoardPres();
	this.add(pres);
	inh = controller.getInHandler();
	
	// unsauber, da initialize() nur fuer Presentation definiert
	//controller.initialize(owner);
	pres.addMouseListener(inh);
	
	// Controller per Hand in den gewuenschten Zustand bringen
	controller.setState(controller.stateShowMove);
		
	this.setVisible(true);
	
	JButton butt = new JButton("Neuen Zug eingeben");
	butt.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent e){
	       moveModeOn();
	   }
	});
	this.add(butt);
	
	butt = new JButton("<html>Zug best&auml;tigen</html>");
	butt.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent e){
	       moveModeOff();
	   }
	});
	this.add(butt);
	
	this.owner.add(this);	
    }
    
    /**
     * Zugeingabe beginnen
     */
    @SuppressWarnings("deprecation")
    private void moveModeOn(){
	System.out.println("Alter Controllerzustand ist: " + controller.getState().getClass());
	controller.recvPlayerActivate();
	System.out.println("Neuer Controllerzustand ist: " + controller.getState().getClass());
    }
    
    /**
     * Zugeingabe abschliessen
     */
    @SuppressWarnings("deprecation")
    private void moveModeOff(){
	System.out.println("Alter Controllerzustand ist: " + controller.getState().getClass());
	// unsauber
	try {
	    SMakeMove movstate = (SMakeMove) controller.getState();
	    movstate.sendHalmaMove();
	} catch (RuntimeException e) {
	    e.printStackTrace();
	    System.err.println("Nicht erwarteter Zustand, senden Fehlgeschlagen");
	}
	
	System.out.println("Neuer Controllerzustand ist: " + controller.getState().getClass());
    }
    
    /**
     * Instanziert die zur Zugeingabe noetigen Klassen zum 
     * manuellen Testen.
     * 
     * @param args
     */
    public static void main(String[] args) {
	JFrame frame = new JFrame("InputHandlingScenario");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new InputHandlingScenario(frame));
	frame.setSize(720, 500);
	frame.setVisible(true);
    }

}
