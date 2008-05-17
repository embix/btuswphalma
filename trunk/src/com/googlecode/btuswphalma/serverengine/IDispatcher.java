package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

/**
 * Basisschnittstelle der Server-Engine fuer den Manager (Spielengine)
 * 
 * @author ASM
 */
public interface IDispatcher {
	
	/**
	 * Sendet eine Nachricht an den Dispatcher
	 * 
	 * @param msg	Zu sendende Nachricht
	 */
	public void acceptMessage( IMessage msg );
	
}
