package com.googlecode.btuswphalma.network;

import java.io.Serializable;
import com.googlecode.btuswphalma.base.IMessage;

/**
 * die Klasse fuer das NetMessage-Objekt, die eine 
 * IMessage enthaelt
 * @author Christoph
 */
public class NetMessage implements Serializable {
	
	/**	die UID - autogeneriert */ 
	private static final long serialVersionUID = 5162642829861881918L;
	
	/** die verpackte IMessage */
	private IMessage msg;
	
	/**
	 * leerer Konstruktor
	 */
	public NetMessage() {}
	
	public NetMessage(IMessage msg) {
	this.msg = msg;
	}
	
	/**
	 * fuegt die zu verpackende IMessage ein
	 * @param msg IMessage
	 */
	public void setMessage(IMessage msg) {
	this.msg = msg;
	}
	
	/**
	 * gibt die verpackte IMessage zurueck
	 * @return IMessage
	 */
	public IMessage getMessage() {
	return (this.msg);
	}
	
}
