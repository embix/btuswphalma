/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import com.googlecode.btuswphalma.base.IMessage;

/**
 * Eine Implementierung des IManagers dient dazu, das Spiel auszüfuhren,
 * inklusive der zeitlichen Steuerung. Das Interface dient auch dazu, dass die
 * Implementierung von SpieleEngine und die Servergine nicht aufeinander warten
 * müssen
 * 
 * @author sebastian
 * 
 */
public interface IManager {

    /**
     * Es wird eine Nachricht übergeben. Dabei wird aber nicht gewährleistet,
     * wann die Nachricht verarbeitet wird. Typischerweise landet sie auf einer
     * Queue.
     * 
     * @param msg
     */
    public void acceptMessage(IMessage msg);

}
