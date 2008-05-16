/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

/**
 * Ein MoveChecker ist eigentlich eine Klasse die nur einen Algorithmus zu
 * verfügung stellt. Die Planung sieht zwei Implementierungen vor, eine die alle
 * Halmaregeln prüft, eine andere, die nur einige Mindestanforderungen an einen
 * Zug prüft.
 * 
 * @author sebastian
 * 
 */
public interface IHalmaMoveChecker {

    /**
     * Ein Zug auf einem Brett von einem Spieler wird geprüft. Wie genau diese
     * Prüfung aussieht ist nicht festgelegt.
     * 
     * @param board
     *                das Spielbrett auf dem gezogen wird
     * @param Move
     *                der Zug
     * @param player
     *                der Spieler der Zieht
     * @return konnte der Zugprüfer keinen Fehler finden (wahr: Zug OK)
     */
    
    // TODO: Sebastian - cool bleiben, bis das Board existiert
    //public boolean checkMove(Board board, HalmaMove move, int player);

}
