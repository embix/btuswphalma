/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Modaler Dialog zur Eingabe der Spielerdaten der Clients
 * 
 * @author embix
 */
public class DialogAboutBox extends JDialog implements ActionListener{

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JDialog (serializable) notwendig.
     */
	private static final long serialVersionUID = -3284676259903526761L;
	
	private boolean ok;
	
	public DialogAboutBox(JFrame owner) {
		super(owner, "Informationen zum Produkt", true);
		ok = false;
		
		Container pane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		pane.setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		// den auszugebenden Text einbetten
		JTextArea aboutText = new JTextArea();
		aboutText.setFont( new Font(Font.SANS_SERIF, Font.BOLD, 14) );
		// der eigendliche Text
		aboutText.append("BTU SWP Halma\n");
		aboutText.append("\n");
		aboutText.append("Version 1.0\n");
		aboutText.append("\n");
		aboutText.append("(c) 2008\n");
		aboutText.append("\n");
		aboutText.append("Sebastian Becker,\n");
		aboutText.append("Michel Bretschneider,\n");
		aboutText.append("Christian Hilschenz,\n");
		aboutText.append("Christoph Kerth\n");
		aboutText.append("\n");
		aboutText.append("\n");
		aboutText.append("http://code.google.com/p/btuswphalma/\n");
		aboutText.append("\n");
		aboutText.setEditable(false);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(aboutText, c);
		pane.add(aboutText);

		/* allgemeiner Standard fuer Dialoge */
		// OK
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		c.gridwidth = 1;
		gridbag.setConstraints(ok, c);
		pane.add(ok);
		// Packen und anzeigen
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
	}

	public boolean ok() {
		return ok;
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		if (cmd.equals("OK")) {
			ok = true;
			dispose();
		}
	}
}
