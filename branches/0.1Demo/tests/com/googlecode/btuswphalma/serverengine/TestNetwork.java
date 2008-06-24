/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

/**
 * Implementation des INetCom Interfaces fuer Testzwecke
 * 
 * @author ASM
 */
public class TestNetwork extends TestBaseObject implements INetCom {

    private java.util.concurrent.ArrayBlockingQueue<IMessage> netMsgQueue =
		new java.util.concurrent.ArrayBlockingQueue<IMessage>(8);
    private Object msgNotifyObject = new java.lang.Object();

    /**
     * @param n
     *                Anzahl der zu sendenden Nachrichten
     * @param thres
     *                Anzahl an Nachrichten die auf in der Warteschlage sein
     *                müssen bevor der Dispatcher informiert wird
     */
    public TestNetwork(int n) {
	super(MessageType.MT_LOGIN, n, 3, -1, "TestNetwork");
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#getMessage()
     */
    public IMessage getMessage() {
	return netMsgQueue.poll();
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#getNotifyObject()
     */
    public Object getNotifyObject() {
	return msgNotifyObject;
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#hasMessage()
     */
    public boolean hasMessage() {
	return netMsgQueue.size() != 0;
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#sendMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void sendMessage(IMessage msg) {
	trackMessage(msg);
    }

    protected void dispatchMessage(IMessage msg) {
	netMsgQueue.add(msg);

	synchronized (msgNotifyObject) {
	    msgNotifyObject.notifyAll();
	}
    }

}
