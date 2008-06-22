package com.googlecode.btuswphalma.base;

import java.io.Serializable;

/**
 * Eine {@link NetMessage} dient dazu, dass eine {@link IMessage} ueber das Netzwerk verschickt werden kann.
 * Deshalb ist die Klasse {@link Serializable}. Eine {@link NetMessage} ist
 * also sozusagen ein Briefumschlag fuer eine {@link IMessage}
 * 
 * @author sebastian
 * 
 */
public class NetMessage implements Serializable {

    /**
     * von eclipse generierte serialVersionUID
     */
    private static final long serialVersionUID = 5262854577875735808L;
    /**
     * 
     */
    IMessage message;

    /**
     * Eine NetMessage ohne Nachricht wird erzeugt. Notwendig fuer
     * {@link Serializable}
     */
    public NetMessage() {
	message = null;
    }

    /**
     * Eine NetMessage mit Nachricht wird erzeugt
     * 
     * @param message
     *                die zu "verpackende" Nachricht
     */
    public NetMessage(IMessage message) {
	this.message = message;
    }

    /**
     * Die enthaltene Nachricht wird zurueckgegeben
     * 
     * @return die enthaltene Nachricht
     */
    public IMessage getMessage() {
	return message;
    }

}
