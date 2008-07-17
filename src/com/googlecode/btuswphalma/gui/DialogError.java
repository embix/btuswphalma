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

/**
 * modaler Dialog zur Ausgabe von Spielfehlermeldungen
 * 
 * @author embix
 * 
 */
public class DialogError extends JDialog implements ActionListener {

	private boolean ok;
	private String labelErrorString;

	/**
	 * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
	 * JDialog (serializable) notwendig.
	 */
	private static final long serialVersionUID = 289147078094246751L;

	/**
	 * @param owner
	 *                gibt an, zu welchem Container der Dialog gehoeren soll.
	 * @param errStr
	 *                gibt an, welche Fehlermeldung ausgegeben werden soll.
	 */
	public DialogError(JFrame owner, String errStr) {
		super(owner, "Fehler", true);
		labelErrorString = errStr;
		Container pane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		pane.setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		// Fehlermeldung einbetten
		JLabel label = new JLabel(labelErrorString);
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(label, c);
		pane.add(label);

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

	/**
	 * 
	 * @return gibt an, ob der Dialog erfolgreich angezeigt wurde
	 */
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
