/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.googlecode.btuswphalma.gameengine.ScoreList;
import com.googlecode.btuswphalma.gameengine.ScoreEntry;

/**
 * @author embix
 * 
 */
public class DialogGameScore
	extends JDialog
	implements ActionListener,
		TableModelListener,
		ListSelectionListener{

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
	tableScores.setPreferredScrollableViewportSize(new Dimension(300,80));
	tableScores.getSelectionModel().addListSelectionListener(this);
	tableScores.setFillsViewportHeight(true);
	pane.add(tableScores.getTableHeader(), BorderLayout.PAGE_START);
	pane.add(tableScores, BorderLayout.CENTER);
	
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
	private String data[][];
	private String[] namen = {"Rang", "Name", "Runden"};

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
	    data = new String[rows+1][cols];
	    // Tabellenbeschriftung
	    ScoreEntry entry;
	    for(int i = 0; i < rows; i++){
		entry = scores.getEntry(i+1);
		data[i][0] = String.valueOf(entry.getRanking());
		data[i][1] = entry.getName();
		data[i][2] = String.valueOf(entry.getRounds());
		System.out.println(data[i][0] + " " + data[i][1]+ " " + data[i][2]);
	    }
	    for(int i = 0; i < rows; i++){
		for(int j = 0; j < cols; j++){
		    System.out.print(getValueAt(i,j) + " "); // geht nicht - Warum?!
		}
		System.out.println();
	    }
	   
	}

	/**
	 * @return gibt die Klasse der Daten der Spalte zurueck
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int col){
	    return String.class;
	}
	
	/**
	 * @return gibt den Namen der ensprechenden Spalte zurueck
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int col){
	    if((col < cols) && (col >=0)){
		return namen[col];
	    }
	    return null;
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
		return data[row][col];
	    }else{
		return null;
	    }
	}
    }

    /**
     * Wird aufgerufen, wenn sich die Tabelle geaendert hat
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent event) {
	
    }

    /**
     * Wird aufgerufen, wenn sich Werte in der Tabelle geaendert haben
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent event) {
	
    }
}
