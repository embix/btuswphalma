/**
 * Basisschnittstelle für Engine-Abhör Objekte für GUIs
 * 
 */

package com.googlecode.btuswphalma.base;

public interface IGuiListener {
	
	public void recvdMessage( IMessage msg );
	
}
