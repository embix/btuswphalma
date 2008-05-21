/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.googlecode.btuswphalma.gameengine.ScoreList;
import com.googlecode.btuswphalma.gameengine.ScoreEntry;

/**
 * @author embix
 * 
 */
public class DialogGameScore extends JDialog implements ActionListener {

    private boolean ok;
    //private ScoreList scores;
    private JTable tableScores;

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JDialog (serializable) notwendig.
     */
    private static final long serialVersionUID = -418268005004781925L;

    /**
     * @param owner
     *                gibt an, zu welchem Container der Dialog verankert werden
     *                soll.
     * @param scores
     *                gibt die anzuzeigenden Spielergebnisse an
     */
    public DialogGameScore(JFrame owner, ScoreList scores) {
	super(owner, "Spielergebnisse", true);
	Container pane = getContentPane();
	BorderLayout bLay = new BorderLayout();
	pane.setLayout(bLay);

	// TODO: Tabelle anzeigen
	ScoreTableModel model = new ScoreTableModel(scores);
	tableScores = new JTable(model);
	pane.add(new JScrollPane(tableScores), BorderLayout.CENTER);
	
	/* allgemeiner Standard fuer Dialoge */
	// OK
	JButton ok = new JButton("OK");
	ok.addActionListener(this);
	pane.add(ok, BorderLayout.PAGE_END);
	// Packen und anzeigen
	setLocationRelativeTo(owner);
	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	pack();
	setResizable(false);
	setVisible(true);
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

    /**
     * @return gibt an, ob der Dialog erfolgreich angezeigt wurde
     */
    public boolean ok() {
	return ok;
    }

    /**
     * Innere Klasse zur Darstellung der Spielergebnisse in einer Tabelle.
     * Sofern diese Klasse von anderen benoetigt werden sollte, so ist ein
     * Refactoring dahingehend noetig, dass sie eine eigenstaendige Klasse wird.
     * 
     * @author embix
     * 
     */
    class ScoreTableModel extends AbstractTableModel {

	/**
	 * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
	 * AbstractTableModel (serializable) notwendig.
	 */
	private static final long serialVersionUID = -7473438376915051574L;

	private int cols = 3;// statisch, nicht enkoppelt
	private int rows;
	private String daten[][];

	/**
	 * @param scores gibt das Anzuzeigende Ergebnisobjekt
	 * an
	 */
	public ScoreTableModel(ScoreList scores) {
	    //Statisch auf 6 Spieler begrenzen
	    int rows = scores.getScoreList().size();
	    if(rows > 6){
		rows = 6;
	    }
	    daten = new String[rows][cols];
	    // Tabellenbeschriftung
	    daten[0][0] = "Rang";
	    daten[0][1] = "Name";
	    daten[0][2] = "Runden";
	    ScoreEntry entry;
	    for(int i = 1; i <= rows; i++){
		entry = scores.getEntry(i);
		daten[i][0] = String.valueOf(entry.getRanking());
		daten[i][1] = entry.getName();
		daten[i][2] = String.valueOf(entry.getRounds());
	    }
	   
	}

	/**
	 * @return gibt die Anzahl der Spalten zurueck
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
	    return cols; 
	}

	/**
	 * @return gibt die Anzahl der Zeilen zurueck
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
	    return rows;
	}

	/**
	 * @return Gibt das Objekt an der gewuenschten Stelle zurueck
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
	    // Einhaltung der Begrenzung pruefen
	    if ((row < rows) && (row >= 0)
		    && (col < cols) && (col >= 0)) {
		return daten[row][col];
	    }
	    return null;
	}
    }
}
