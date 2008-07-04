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
    
    private Font f;
    
    /**
     * Konstruktor
     */
    public PlayerListPresentation(){
	labels = new ArrayList<JLabel>();
	this.setLayout(new GridLayout(12,2));
	f = new Font("Helvetica", Font.BOLD, 14);
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
	    l.setFont(f);
	    id = (byte) p.getID();
	    Color c = BoardPresentation.mapPlayerToColor(id);
	    l.setForeground(c);
	    labels.add(l);
	    this.add(l);
	}
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

}
