/**
 * 
 */
package com.googlecode.btuswphalma.gui;



import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 * @author embix
 *
 */
public class DialogMasterOrClient extends JDialog implements ActionListener {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = 2925998959825313126L;

    private boolean ok;
    private boolean master;
    private ButtonGroup radioMasterClient;
    private JRadioButton radioButtonMaster;
    private JRadioButton radioButtonClient;
    
    /**
     * Konstruktor fuer den Dialog
     * @param owner gibt das verantwortliche JFrame an
     */
    public DialogMasterOrClient(JFrame owner){
	super(owner, "Spieltyp", true);
	
	Container pane = getContentPane();
	GridBagLayout gridbag = new GridBagLayout();
	pane.setLayout(gridbag);
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(5, 5, 5, 3);
	
	// Bereich fuer die Spieltypauswahl
	radioButtonMaster = new JRadioButton("Als Spielleiter", true);
	radioButtonClient = new JRadioButton("Als Teilnehmer");
	radioMasterClient = new ButtonGroup();
	radioMasterClient.add(radioButtonMaster);
	radioMasterClient.add(radioButtonClient);
	add(radioButtonMaster);
	add(radioButtonClient);
	
	/* allgemeiner Standard fuer Dialoge */
	// OK
	JButton ok = new JButton("OK");
	ok.addActionListener(this);
	c.gridwidth = 1;
	gridbag.setConstraints(ok, c);
	pane.add(ok);
	// Abbrechen
	JButton abbr = new JButton("Abbrechen");
	abbr.addActionListener(this);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(abbr, c);
	pane.add(abbr);
	// Packen und anzeigen
	setLocationRelativeTo(owner);
	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	pack();
	setResizable(false);
	setVisible(true);
    }
    
    /**
     * @return gibt an, ob der Dialog erfolgreich ausgefuellt wurde
     */
    public boolean ok(){
	return ok;
    }
    
    /**
     * @return true, wenn als Master gestartet werden soll
     */
    public boolean isMaster(){
	return master;
    }
    
    /**
     * Eventhandler fuer die Buttons
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
	String cmd = event.getActionCommand();
	if (cmd.equals("OK")) {
	    // Checkboxen pruefen
	    if(radioButtonMaster.isSelected()){
		master = true;
	    }else{
		master = false;
	    }
	    ok = true; // alle Daten korrekt
	    dispose(); // Dialog beenden
	}
	if (cmd.equals("Abbrechen")) {
	    ok = false; // abgebrochen
	    dispose(); // Dialog beenden
	}
	// sonst war es ein Klick in die Checkboxen
    }

}
