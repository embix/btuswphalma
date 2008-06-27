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
import javax.swing.JOptionPane;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Modaler Dialog zur Eingabe der Spielerdaten der Clients
 * 
 * @author embix
 * @author ASM
 * 
 */
public class DialogClientGameData extends JDialog implements ActionListener {

    private boolean ok;
    private JTextField fieldPlayerName;
    private JTextField fieldIpAddress;
    private JTextField fieldPort;
    private ClientGameData clientGameData;
    
    private boolean hotseat;
    
    private String defaultPort = "32334";	// TODO: Zentralisieren?

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JDialog (serializable) notwendig.
     */
    private static final long serialVersionUID = -6412444075603132149L;

    /**
     * @param owner
     *                gibt an, zu welchem Container der Dialog verankert werden
     *                soll.
     * @param hs 
     *			gibt an, ob dies ein Clientdialog für den Hotseatmodus sein soll
     */
    public DialogClientGameData(JFrame owner, boolean hs) {
	super(owner, "Eingabe der Clientspieldaten", true);
	
	hotseat = hs;

	Container pane = getContentPane();
	GridBagLayout gridbag = new GridBagLayout();
	pane.setLayout(gridbag);
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(5, 5, 5, 5);

	// Bereich fuer die Namenseingabe
	JLabel label = new JLabel("Name");
	c.gridwidth = 1;
	gridbag.setConstraints(label, c);
	pane.add(label);
	fieldPlayerName = new JTextField(10); // Evtl. bestimmte Groesse
						// erzwingen
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(fieldPlayerName, c);
	pane.add(fieldPlayerName);
	
	// Bereich fuer die IP-Adresse
	label = new JLabel("IP Adresse/Hostname");
	c.gridwidth = 1;
	gridbag.setConstraints(label, c);
	pane.add(label);
	fieldIpAddress = new JTextField(10);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(fieldIpAddress, c);
	pane.add(fieldIpAddress);
	
	if(hotseat) {
	    fieldIpAddress.setEnabled(false);
	}
	
	// Bereich fuer den Port
	label = new JLabel("Netzwerk Port");
	c.gridwidth = 1;
	gridbag.setConstraints(label, c);
	pane.add(label);
	fieldPort = new JTextField(10);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(fieldPort, c);
	pane.add(fieldPort);
	
	fieldPort.setText(defaultPort);
	if(hotseat) {
	    fieldPort.setEnabled(false);
	}

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
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
	String cmd = event.getActionCommand();
	if (cmd.equals("OK")) {
	    // Namen pruefen
	    if (getPlayerName().length() == 0) {
		fieldPlayerName.setText("Vorname");
		fieldPlayerName.requestFocus();
		return;
	    }
	    ok = true; // alle Daten korrekt
	    dispose(); // Dialog beenden
	}
	if (cmd.equals("Abbrechen")) {
	    ok = false; // abgebrochen
	    dispose(); // Dialog beenden
	}
	
	// Copy & Paste vom Masterdialog :/
	if(getPort() == 0 || (getPort() > 65535)) {
		fieldPort.setText(defaultPort);
		fieldPort.requestFocus();
		return;
	    }
    }

    /**
     * @return gibt ein die Spierlerdaten enthaltendes ClientGameData Objekt
     *         zurueck.
     * @see ClientGameData
     */
    public ClientGameData getClientGameData() {
	clientGameData = new ClientGameData();
	clientGameData.playerName = getPlayerName();
	
	if(!hotseat) {
        	clientGameData.ip = getIpAddress();
        	clientGameData.port = getPort();
	}
	return clientGameData;
    }

    /**
     * @return gibt an, ob der Dialog erfolgreich ausgefuellt wurde
     */
    public boolean ok() {
	return ok;
    }

    /**
     * @return gibt den eingegebenen Spielernamen zurueck
     */
    private String getPlayerName() {
	return fieldPlayerName.getText();
    }
    
    private InetAddress getIpAddress()
    {
	InetAddress a;
	
	try {
	    a = InetAddress.getByName(fieldIpAddress.getText());
	}
	catch(UnknownHostException e) {
	    JOptionPane.showMessageDialog(null,
		    "Fehler beim Verarbeiten der IP Adresse/des Hostnamens.\n"
		    + "Möglicherweise konnte der angegebene Hostname nicht aufgelöst werden." );
	    a = null;
	}
	return a;
    }
    
    /**
     * @return gibt den eingegebenen Netzwerkport zurueck
     */
    private int getPort() {
	int port;
	try {
	    port = Integer.parseInt(fieldPort.getText());
	} catch (java.lang.NumberFormatException ex) {
	    port = 0;
	}
	return port;
    }
}
