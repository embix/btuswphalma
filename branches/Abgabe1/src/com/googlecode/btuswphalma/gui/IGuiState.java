/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;
import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * IGuiState ist ein Interface zur Kapselung der Zustaende des GuiControllers.
 * 
 * Da sich einige Methoden vom Verhalten her aehnlich sind, ist es
 * moeglicherweise sinnvoll ein Refactoring dahingehend anzustreben,
 * dass von einer IGuiState implementierenden Klasse aehnliche Zustaende
 * abgeleitet werden.
 * 
 * @author embix
 *
 */
public interface IGuiState {

    /**
     * Wird vom MessageHandler aufgerufen, wenn von der SpielEngine
     * ein Fehler uebermittelt wurde.
     * 
     * @param errStr ist der die Fehlerausgabe enthaltene String
     */
    public void recvMoveError(String errStr);
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn von der SpielEngine ein
     * Spielzug uebermittelt wurde. 
     * 
     * @param tm gibt den Spielzug an, der uebertragen werden soll
     */
    public void recvHalmaMove(HalmaMove tm);
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn der Spieler von der
     * SpielEngine als naechster ausgewaehlt wurde.
     */
    public void recvPlayerActivate();
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * eine neue Spielaufstellung uebermittelt wurde.
     * 
     * @param b gibt das aktuelle Objekt fuer die Aufstellung an
     */
    public void recvBoard(Board b);
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * die Spielergebnisse bekanntgegeben wurden.
     * 
     * @param s gibt das Objekt fuer die Spielergebnisse an.
     */
    public void recvScores(ScoreList s);
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * festgestellt wurde, dass der Spieler fertig ist.
     */
    public void recvPlayerFinished();
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * das Ende der Session bekanntgegeben wurde.
     */
    public void recvGameEnd();
    
    /**
     * Wird vom MessageHandler aufgerufen, wenn durch die SpielEngine
     * eine Spielerliste proklamiert wurde.
     * 
     * @param plrLst die Spielerliste
     */
    public void recvPlayerList(PlayerList plrLst);
}
