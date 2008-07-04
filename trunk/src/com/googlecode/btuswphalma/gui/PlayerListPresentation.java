/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import com.googlecode.btuswphalma.gameengine.Player;
import com.googlecode.btuswphalma.gameengine.PlayerList;



/**
 * Praesentationsklasse fuer die Darstellung der Spielerliste
 * 
 * @author embix
 *
 */
public class PlayerListPresentation extends javax.swing.JPanel {

    /**
     * Compiler generierte UID fuer diese Klasse. Wird durch Vererbung von
     * JPanel (serializable) notwendig.
     */
    private static final long serialVersionUID = -5966004214961279016L;

    private List<JLabel> labels;
    
    private Font fnt;
    
    private int radius = 12;
    
    private int activePlr = 0;
    
    /**
     * Konstruktor
     */
    public PlayerListPresentation(){
	labels = new ArrayList<JLabel>();
	this.setLayout(new GridLayout(12,2));
	fnt = new Font("Helvetica", Font.BOLD, radius*2);
	
    }
    
    /**
     * Nimmt Spielerliste entgegen und stellt diese dar
     * 
     * @param pl die Spielerliste
     */
    public void process(PlayerList pl){
	clear();
	JLabel l;
	byte id;
	for(Player p : pl.getPlayers()){
	    l = new JLabel(p.getName());
	    l.setFont(fnt);
	    id = (byte) p.getID();
	    Color c = BoardPresentation.mapPlayerToColor(id);
	    l.setForeground(c);
	    labels.add(l);
	    this.add(l);
	}
	try {
	    l = labels.get(activePlr-1);
	    l.setBorder(new BevelBorder(BevelBorder.LOWERED));
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
	repaint();
    }

    /**
     * Loescht die Spielerliste
     */
    public void clear() {
	for(JLabel l : labels){
	    this.remove(l);
	}
	labels.clear();
    }
    
    /**
     * Setter fuer den Massstab der Zeichengrosse / Radius
     * @param radius
     */
    public void setRadius(int radius){
	if ((radius > 5) && (radius < 29)) {
	    this.radius = radius;
	}
	fnt = new Font("Helvetica", Font.BOLD, radius*2);
	for(JLabel l : labels){
	    l.setFont(fnt);
	}
	repaint();
	// repaint wird vom Container implizit durch pack() aufgerufen,
	// allerdings macht pack nichts mehr, wenn die Grosse einmal
	// die gleiche war...
    }
    
    /**
     * Getter fuer den Zeichenradius / Massstab
     * @return gibt den aktuell verwendeten Zeichenradius zuruck
     */
    public int getRadius(){
	return radius;
    }

    
    /**
     * Den aktiven Spieler in der Spielerliste kenntlich machen
     * @param plrNr
     */
    public void markActive(int plrNr) {
	// unsauber
	activePlr = plrNr;
    }
}
