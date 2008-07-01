/**
 * 
 */
package com.googlecode.btuswphalma.base;

import com.googlecode.btuswphalma.gameengine.PlayerList;

/**
 * @author sebastian
 * 
 */
public class PlayerListMessage extends AbstractMessage {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = -1486418375693126054L;
    /**
     * die Spielerliste
     */
    private PlayerList playerList;

    /**
     * Erzeugt eine PlayerListMessage. Diese ist entsprechend adressiert und
     * enthaelt die PlayerList
     * 
     * @param source die Quelladresse
     * @param destination die Zieladresse
     * @param playerList die Spielerliste
     */
    public PlayerListMessage(int source, int destination, PlayerList playerList) {
	super(source, destination);
	this.playerList = playerList;
    }

    /**
     * Gibt die Spielerliste zurueck
     * @return die Spielerliste
     */
    public PlayerList getPlayerList() {
	return playerList;
    }

    /**
     * Identifiziert diese Nachricht als eine, die eine PlayerList enthaelt.
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	// TODO wirklich diesen Typ?
	return MessageType.MT_PLAYERDATA;
    }

}
