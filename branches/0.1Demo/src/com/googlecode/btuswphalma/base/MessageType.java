package com.googlecode.btuswphalma.base;

/**
 * Nachrichtentypen
 * 
 * @author ASM
 */
public enum MessageType {
    /**
     * Spielzug
     */
    MT_MOVE,
    /**
     * Spielerdaten
     */
    MT_PLAYERDATA,
    /**
     * Spielabbruch
     */
    MT_TERMINATE,
    /**
     * Vetonachricht
     */
    MT_VETO,
    /**
     * Spielbrett
     */
    MT_BOARD,
    /**
     * Spieleplatzierungen
     */
    MT_SCORES,
    /**
     * Zugfehler
     */
    MT_MOVEERROR,
    /**
     * Spieleraktivierung
     */
    MT_PLAYERACTIVATE,
    /**
     * Anmeldung am Server
     */
    MT_LOGIN,
    /**
     * Spielende
     */
    MT_GAMEEND,
    /**
     * Spielstand speichern
     */
    MT_SAVE,
    /**
     * Spieler ist fertig (hat alle Steine im Haus)
     */
    MT_PLAYERFINISHED
}
