/**
 * 
 */
package com.googlecode.btuswphalma.network;

//import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.INetCom;

/**
 * @author sebastian
 *
 */
public class ServerNetCom extends Thread implements INetCom {
    
    int playerNum;
    HashMap<Integer, Connection> connectionMap;
    ConcurrentLinkedQueue<IMessage> messageQueue;
    Object notifyObject;
    ServerSocket serverSocket;
    
    /**
     * @param port
     * @param playerNum
     */
    public ServerNetCom(int port, int playerNum) {
	this.playerNum = playerNum;
	connectionMap = new HashMap<Integer, Connection>(playerNum);
	messageQueue = new ConcurrentLinkedQueue<IMessage>();
	notifyObject = new Object();
    }

    /*
     * @see com.googlecode.btuswphalma.base.INetCom#getMessage()
     */
    public IMessage getMessage() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see com.googlecode.btuswphalma.base.INetCom#getNotifyObject()
     */
    public Object getNotifyObject() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see com.googlecode.btuswphalma.base.INetCom#hasMessage()
     */
    public boolean hasMessage() {
	// TODO Auto-generated method stub
	return false;
    }

    /* (non-Javadoc)
     * @see com.googlecode.btuswphalma.base.INetCom#sendMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void sendMessage(IMessage msg) {
	// TODO Auto-generated method stub

    }
    
    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
	//TODO lass laufen
    }

}
