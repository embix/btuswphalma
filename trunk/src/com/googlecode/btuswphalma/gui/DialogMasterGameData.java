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
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Modaler Dialog zur Eingabe der Spielerdaten des Masters
 * 
 * @author embix
 *
 */
public class DialogMasterGameData
	extends JDialog
	implements ActionListener{

    private boolean ok;
    private MasterGameData masterGameData;
    private JTextField fieldPlayerName;
    private JTextField fieldPlayerCount;
    private ButtonGroup radioGameMode;
    private JRadioButton radioButtonHotseat;
    private JRadioButton radioButtonNetplay;
    private JTextField fieldAiPlayerCount;
       
    
    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung
     * von JDialog (serializable) notwendig.
     */
    private static final long serialVersionUID = -7986197728895489140L;

    /**
     * Konstruktor: erzeugt das Dialogfenster mit allen Eingabefeldern
     * und macht ihn anschliessend (modal) sichtbar
     * 
     * @param owner gibt an, zu welchem Container der
     * Dialog verkankert werden soll.
     */
    public DialogMasterGameData(JFrame owner) {
	super(owner, "Eingabe der Hostspieldaten" , true);
	
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
	
	// Bereich fuer die Spieleranzahleingabe
	label = new JLabel("Anzahl Spieler");
	c.gridwidth = 1;
	gridbag.setConstraints(label, c);
	pane.add(label);
	fieldPlayerCount = new JTextField(10); // Evtl. bestimmte Groesse erzwingen
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(fieldPlayerCount, c);
	pane.add(fieldPlayerCount);
	
	// Bereich fuer die Eingabe der Computerspieleranzahl
	label = new JLabel("davon KI");
	c.gridwidth = 1;
	gridbag.setConstraints(label, c);
	pane.add(label);
	fieldAiPlayerCount = new JTextField(10); // Evtl. bestimmte Groesse erzwingen
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(fieldAiPlayerCount, c);
	pane.add(fieldAiPlayerCount);
	
	// Bereich fuer die Auswahl des Mehrspielermodus
	radioButtonHotseat = new JRadioButton("Hotseat", true);
	radioButtonNetplay = new JRadioButton("Netzwerkspiel");
	radioButtonHotseat.addActionListener(this);
	radioButtonNetplay.addActionListener(this);
	radioGameMode = new ButtonGroup();
	radioGameMode.add(radioButtonHotseat);
	radioGameMode.add(radioButtonNetplay);
	radioButtonNetplay.setEnabled(false); // TODO: (TP3) spaeter loeschen
	add(radioButtonHotseat);
	add(radioButtonNetplay); 
	
	// Bereich fuer die Auswahl des Regelmodus
	// TODO: (WunschKriterium) Regelmodus (mit Veto / regelkonform)
	
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
     * @return gibt ein die Spielerdaten enthaltende MasterGameData
     * Objekt zurueck.
     * @see MasterGameData
     */
    public MasterGameData getMasterGameData(){
	masterGameData = new MasterGameData();
	masterGameData.playerName = getPlayerName();
	masterGameData.playerCount = getPlayerCount();
	masterGameData.aiCount = getAiPlayerCount();
	masterGameData.gmod = getGameMode();
	return masterGameData;
    }

    /**
     * Eventhandler fuer die Buttons
     * 
     *  (non-Javadoc)
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
	    // Anzahl Spieler pruefen
	    int player = getPlayerCount();
	    if(( player < 1)||(player > 6)){
		fieldPlayerCount.setText("2");
		fieldPlayerCount.requestFocus();
		return;
	    }
	    // Anzahl KI Spieler pruefen
	    // TODO: (TP4) 0 bis 2 KI Spieler erlauben
	    //             pruefen, ob mehr KI als Gesamtspieler
	    if(getAiPlayerCount() != 0){
		fieldAiPlayerCount.setText("0");
		fieldAiPlayerCount.requestFocus();
		return;
	    }
	    
	    ok = true; // alle Daten korrekt
	    dispose(); // Dialog beenden
	}
	if(cmd.equals("Abbrechen")){
	    ok = false; // abgebrochen
	    dispose(); // Dialog beenden
	}
	// sonst war es ein Klick in die Checkboxen	
    }

    /**
     * @return gibt den eingegebenen Spielernamen zurueck
     */
    private String getPlayerName() {
	return fieldPlayerName.getText();
    }

    /**
     * @return gibt die Anzahl der eingegebenen Spielerslots zurueck
     */
    private int getPlayerCount() {
	int player;
	try{
	player = Integer.parseInt(fieldPlayerCount.getText());
	}
	catch(java.lang.NumberFormatException ex){
	player = 0;
	}
	return player;
    }

    /**
     * @return gibt die Anzahl der eingegebenen Computerpieler zurueck
     */
    private int getAiPlayerCount() {
	int aiplayer;
	try{
	aiplayer = Integer.parseInt(fieldAiPlayerCount.getText());
	}
	catch(java.lang.NumberFormatException ex){
	aiplayer = 0;
	}
	return aiplayer;
    }
    
    /**
     * @return gibt den ausgewaehlten Mehspielermodus (Netz/Hotseat) zurueck
     */
    private GameMode getGameMode(){
	GameMode mode;
	if(radioButtonNetplay.isSelected()){
	    mode = GameMode.NET_PLAY;
	}else{
	    mode = GameMode.HOT_SEAT;   
	}
	return mode;
    }
}
