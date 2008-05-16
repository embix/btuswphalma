/**
 * Basisschnittstelle der Server-Engine für GUIs
 * 
 */

package com.googlecode.btuswphalma.base;

public interface IGuiCom {
	
	public void registerGuiListener( IGuiListener lst );
	
	public void unregisterGuiListener( IGuiListener lst );
	
	public void recvMessage( IMessage msg );
	
}
