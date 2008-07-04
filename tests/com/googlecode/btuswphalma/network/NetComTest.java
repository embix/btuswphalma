/**
 * 
 */
package com.googlecode.btuswphalma.network;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.googlecode.btuswphalma.base.BoardMessage;
import com.googlecode.btuswphalma.base.LoginMessage;
import com.googlecode.btuswphalma.base.MoveMessage;
import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.BoardPosition;
import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * @author sebastian
 *
 */
public class NetComTest {

    ServerNetCom server;
    ClientNetCom client1;
    ClientNetCom client2;
    static final int PORT = 61001;
    static final byte[] rwAddr = {127,0,0,1};
    static InetAddress address;
    
    /**
     * @throws UnknownHostException
     */
    @BeforeClass
    public static void setUpBeforeClass() throws UnknownHostException {
	address = InetAddress.getByAddress(rwAddr);
    }
    
    /**
     * 
     */
    @Test
    public void networkTest() {
	int i = 0;
	System.out.println("ZZZ"+i++);
	server = new ServerNetCom(PORT,3);
	System.out.println("ZZZ"+i++);
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("ZZZ"+i++);
	client1 = new ClientNetCom(address,PORT);
	System.out.println("ZZZ"+i++);
	client1.sendMessage(new LoginMessage("Client1",0,1));
	System.out.println("ZZZ"+i++);
	client2 = new ClientNetCom(address,PORT);
	System.out.println("ZZZ"+i++);
	client2.sendMessage(new LoginMessage("Client2",0,1));
	System.out.println("ZZZ"+i++);
	ArrayList<BoardPosition> poses= new ArrayList<BoardPosition>();
	poses.add(new BoardPosition((byte)1,(byte)2));
	poses.add(new BoardPosition((byte)1,(byte)2));
	client1.sendMessage(new MoveMessage(1,1,new HalmaMove(poses)));
	
	server.sendMessage(new BoardMessage(-1,0,new Board(2),1));
	
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	if (server.hasMessage()) {
	    System.out.println(server.getMessage().getSource());
	    System.out.println(server.getMessage().getSource());
	}
	if (client1.hasMessage()) {
	    System.out.println(client1.getMessage().getSource());
	}
	
	client1.interrupt();
	client2.interrupt();
	server.stopServer();
    }

}
