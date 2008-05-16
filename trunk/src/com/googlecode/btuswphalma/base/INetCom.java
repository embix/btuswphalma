/**
 * Basisschnittstelle für das Netzwerksystem
 * 
 */

package com.googlecode.btuswphalma.base;

public interface INetCom {
	
	public void sendMessage( IMessage msg );
	
	public Object getNotifyObject();
	
	public boolean hasMessage();
	
	public IMessage getMessage();
	
}
