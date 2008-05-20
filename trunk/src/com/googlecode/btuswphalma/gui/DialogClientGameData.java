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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Modaler Dialog zur Eingabe der Spielerdaten der Clients
 * 
 * @author embix
 *
 */
public class DialogClientGameData
	extends JDialog
	implements ActionListener{

    private boolean ok;
    private JTextField fieldPlayerName;
    private ClientGameData clientGameData;
    
    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung
     * von JDialog (serializable) notwendig.
     */
    private static final long serialVersionUID = -6412444075603132149L;

    /**
     * @param owner gibt an, zu welchem Container der Dialog
     * verankert werden soll.
     */
    public DialogClientGameData(JFrame owner){
	super(owner, "Eingabe der Clientspieldaten",true);
	
	Container pane = getContentPane();
	GridBagLayout gridbag = new GridBagLayout();
	pane.setLayout(gridbag);
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(5,5,5,5);
	
	// Bereich fuer die Namenseingabe
	JLabel label = new JLabel("Name");
	c.gridwidth = 1;
	gridbag.setConstraints(label, c);
	pane.add(label);
	fieldPlayerName = new JTextField(10); // Evtl. bestimmte Groesse erzwingen
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(fieldPlayerName, c);
	pane.add(fieldPlayerName);
	
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
     * Eventhandler fuer die Buttons 
     * 
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
	String cmd = event.getActionCommand();
	if(cmd.equals("OK")){
	    // Namen pruefen
	    if(getPlayerName().length() == 0){
		fieldPlayerName.setText("Vorname");
		fieldPlayerName.requestFocus();
		return;
	    }
	    ok = true; // alle Daten korrekt
	    dispose(); // Dialog beenden
	}
	if(cmd.equals("Abbrechen")){
	    ok = false; // abgebrochen
	    dispose(); // Dialog beenden
	}
    }
    
    /**
     * @return gibt ein die Spierlerdaten enthaltendes ClientGameData
     * Objekt zurueck.
     * @see ClientGameData
     */
    public ClientGameData getClientGameData(){
	clientGameData = new ClientGameData();
	clientGameData.playerName = getPlayerName();
	return clientGameData;
    }
    
    /**
     * @return gibt an, ob der Dialog erfolgreich ausgefuellt wurde
     */
    public boolean ok(){
	return ok;
    }
    
    /**
     * @return gibt den eingegebenen Spielernamen zurueck
     */
    private String getPlayerName() {
	return fieldPlayerName.getText();
    }
}
