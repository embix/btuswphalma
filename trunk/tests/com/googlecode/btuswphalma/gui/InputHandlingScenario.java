/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * @author embix
 * <h2>Testklasse fuer die Zugeingabe</h2>
 * 
 * Zunaechst werden alle Klassen bereitgestellt, die zur
 * Zugeingabe benoetigt werden. Anschliessend kann man
 * zur Zugeingabe auffordern. Nach erfolgter Zugeingabe
 * ist eine Bestaetigung abzugeben, was zur Ausgabe der
 * Zugfolge fuehren soll. Schliesslich kann die Zugeingabe
 * wieder aktiviert werden.
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
    private HalmaMove move;

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
    
    @SuppressWarnings("deprecation")
    private void moveModeOn(){
	System.out.println("Alter Controllerzustand ist: " + controller.getState().getClass());
	controller.recvPlayerActivate();
	System.out.println("Neuer Controllerzustand ist: " + controller.getState().getClass());
    }
    
    @SuppressWarnings("deprecation")
    private void moveModeOff(){
	System.out.println("Alter Controllerzustand ist: " + controller.getState().getClass());
	move = inh.setMoveEntryModeOff();
	controller.recvHalmaMove(move);
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
