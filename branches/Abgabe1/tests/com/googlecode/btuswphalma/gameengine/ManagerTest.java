package com.googlecode.btuswphalma.gameengine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Ignore;
import org.junit.Test;

import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.LoginMessage;
import com.googlecode.btuswphalma.base.MessageAddresses;
import com.googlecode.btuswphalma.base.MessageType;
import com.googlecode.btuswphalma.base.MoveMessage;
import com.googlecode.btuswphalma.serverengine.IDispatcher;

/**
 * Test fuer den Manager, implementiert IDispatcher, um als Dispatcher fuer den
 * Manager auftreten zu koennen.
 * 
 * @author sebastian
 * 
 */
public class ManagerTest implements IDispatcher {

    /**
     * Der zu testende Manager
     */
    private Manager manager;
    /**
     * Warteschlange, um die Antworten des Server ueberpruefen zu koennen
     */
    private ConcurrentLinkedQueue<IMessage> answers = new ConcurrentLinkedQueue<IMessage>();
    /**
     * Zeit, die man schlaeft, um den Manager arbeiten zu lassen
     */
    private final static int SLEEP_TIME = 10;

    /**
     * Testet, ob der Manager korrekte Schueber erkennt
     * 
     * @throws InterruptedException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void pushTest() throws InterruptedException {
	String player1 = "Player1";
	String player2 = "Player2";
	IMessage msg;

	manager = new Manager(2, this);
	Thread managerThread = new Thread(manager);
	managerThread.start();
	manager.acceptMessage(new LoginMessage(1, -1, player1));
	manager.acceptMessage(new LoginMessage(2, -1, player2));

	Thread.sleep(SLEEP_TIME);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_PLAYERACTIVATE);
	assertTrue(msg.getDestination() == 1);
	assertTrue(answers.isEmpty());

	int[][] mov = { { 3, 5 }, { 4, 5 } };
	testMove(mov, 1);
	
	//der soll sicher nicht mehr laufen evtl vorher schoener machen
	managerThread.stop();

    }

    /**
     * Testet das selbe Spiel wie GameTest, kann lange dauern
     * 
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testAcceptMessage() throws InterruptedException {
	String player1 = "Player1";
	String player2 = "Player2";
	IMessage msg;
	IMessage toSendMessage;

	manager = new Manager(2, this);
	Thread managerThread = new Thread(manager);
	managerThread.start();
	manager.acceptMessage(new LoginMessage(1, -1, player1));
	manager.acceptMessage(new LoginMessage(2, -1, player2));

	Thread.sleep(SLEEP_TIME);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_PLAYERACTIVATE);
	assertTrue(msg.getDestination() == 1);
	assertTrue(answers.isEmpty());

	int[][] mov = { { 3, 7 }, { 4, 7 } };
	testMove(mov, 1);

	mov = new int[][] { { 13, 4 }, { 12, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 4, 7 }, { 5, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 12, 5 }, { 11, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 2, 7 }, { 4, 6 }, { 6, 7 } };
	testMove(mov, 1);

	mov = new int[][] { { 14, 7 }, { 12, 6 }, { 10, 5 } };
	toSendMessage = new MoveMessage(2, -1, createHalmaMove(mov));
	manager.acceptMessage(toSendMessage);
	Thread.sleep(SLEEP_TIME);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_MOVE);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertTrue(answers.isEmpty());

	Thread.sleep(Manager.DISPLAY_TIME + SLEEP_TIME);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_PLAYERACTIVATE);
	assertTrue(msg.getDestination() == 1);
	assertTrue(answers.isEmpty());

	mov = new int[][] { { 6, 7 }, { 7, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 10, 5 }, { 9, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 0, 6 }, { 2, 7 }, { 4, 6 }, { 6, 7 }, { 8, 6 },
		{ 10, 5 }, { 12, 6 }, { 14, 7 } };
	testMove(mov, 1);

	mov = new int[][] { { 14, 5 }, { 12, 6 }, { 10, 5 }, { 8, 6 },
		{ 6, 7 }, { 4, 6 }, { 2, 7 }, { 0, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 2, 5 }, { 4, 6 }, { 6, 7 }, { 8, 6 }, { 10, 5 },
		{ 12, 6 }, { 14, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 15, 5 }, { 13, 4 } };
	testMove(mov, 2);

	mov = new int[][] { { 3, 6 }, { 4, 7 } };
	testMove(mov, 1);

	mov = new int[][] { { 14, 6 }, { 12, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 1, 5 }, { 3, 6 }, { 5, 7 }, { 5, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 16, 6 }, { 15, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 2, 6 }, { 4, 5 }, { 6, 6 }, { 8, 7 } };
	testMove(mov, 1);

	mov = new int[][] { { 15, 6 }, { 14, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 1, 6 }, { 2, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 12, 7 }, { 11, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 2, 6 }, { 4, 5 }, { 6, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 14, 6 }, { 12, 7 }, { 10, 6 }, { 8, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 8, 7 }, { 9, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 15, 5 }, { 14, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 6, 6 }, { 8, 7 }, { 10, 6 }, { 12, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 14, 6 }, { 12, 7 }, { 10, 6 }, { 8, 7 },
		{ 6, 6 }, { 4, 5 }, { 2, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 4, 7 }, { 6, 6 }, { 8, 7 }, { 10, 6 }, { 12, 7 },
		{ 14, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 13, 5 }, { 12, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 3, 4 }, { 4, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 12, 6 }, { 10, 5 }, { 8, 6 }, { 6, 7 }, { 4, 6 },
		{ 2, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 4, 5 }, { 6, 6 }, { 8, 7 }, { 10, 6 }, { 12, 7 } };
	testMove(mov, 1);

	mov = new int[][] { { 13, 6 }, { 11, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 3, 5 }, { 4, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 11, 6 }, { 10, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 4, 6 }, { 6, 7 }, { 8, 6 }, { 10, 5 }, { 12, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 13, 7 }, { 11, 6 }, { 9, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 5, 6 }, { 6, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 11, 5 }, { 10, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 6, 6 }, { 8, 7 }, { 10, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 13, 4 }, { 11, 5 }, { 9, 4 }, { 7, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 5, 5 }, { 6, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 10, 5 }, { 8, 6 }, { 6, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 6, 6 }, { 8, 7 }, { 10, 8 } };
	testMove(mov, 1);

	mov = new int[][] { { 10, 7 }, { 8, 6 }, { 6, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 7, 6 }, { 8, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 9, 5 }, { 7, 4 }, { 5, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 8, 6 }, { 10, 7 }, { 12, 8 } };
	testMove(mov, 1);

	mov = new int[][] { { 8, 5 }, { 6, 6 }, { 4, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 9, 6 }, { 11, 5 }, { 13, 6 }, { 15, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 7, 5 }, { 5, 4 }, { 3, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 14, 5 }, { 16, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 11, 7 }, { 10, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 10, 8 }, { 11, 8 } };
	testMove(mov, 1);

	mov = new int[][] { { 10, 7 }, { 8, 8 } };
	testMove(mov, 2);

	mov = new int[][] { { 11, 8 }, { 13, 7 }, { 15, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 8, 8 }, { 8, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 10, 6 }, { 11, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 9, 7 }, { 7, 6 }, { 5, 7 } };
	testMove(mov, 2);

	mov = new int[][] { { 11, 5 }, { 13, 4 } };
	testMove(mov, 1);

	mov = new int[][] { { 8, 7 }, { 7, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 12, 5 }, { 13, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 3, 5 }, { 1, 6 } };
	testMove(mov, 2);

	mov = new int[][] { { 12, 6 }, { 14, 5 } };
	testMove(mov, 1);

	mov = new int[][] { { 7, 6 }, { 7, 5 } };
	testMove(mov, 2);

	mov = new int[][] { { 12, 7 }, { 13, 6 } };
	testMove(mov, 1);

	mov = new int[][] { { 6, 7 }, { 4, 8 } };
	testMove(mov, 2);

	mov = new int[][] { { 12, 8 }, { 13, 7 } };
	toSendMessage = new MoveMessage(1, -1, createHalmaMove(mov));
	manager.acceptMessage(toSendMessage);
	Thread.sleep(SLEEP_TIME);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_MOVE);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertTrue(answers.isEmpty());

	Thread.sleep(Manager.DISPLAY_TIME + SLEEP_TIME);
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_SCORES);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_GAMEEND);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);

	Thread.sleep(SLEEP_TIME);
	assertFalse(managerThread.isAlive());

    }

    /**
     * Sendet einen Zug fuer einen Spieler, und testet, ob der Zug "normal" (kein Spielerwechsel, nicht zu Ende) abgelaufen ist
     * @param mov Der Zug als Array
     * @param player Der Spieler, der den Zug machen soll
     * @throws InterruptedException
     */
    private void testMove(int[][] mov, int player) throws InterruptedException {
	IMessage toSendMessage = new MoveMessage(player, -1,
		createHalmaMove(mov));
	manager.acceptMessage(toSendMessage);
	Thread.sleep(SLEEP_TIME);
	assertFalse(answers.isEmpty());
	IMessage msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_MOVE);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertTrue(answers.isEmpty());

	Thread.sleep(Manager.DISPLAY_TIME + SLEEP_TIME);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_BOARD);
	assertTrue(msg.getDestination() == MessageAddresses.BROADCAST_ADDRESS);
	assertFalse(answers.isEmpty());
	msg = answers.poll();
	assertTrue(msg.getType() == MessageType.MT_PLAYERACTIVATE);
	assertTrue(msg.getDestination() == 1 + player % 2);
	assertTrue(answers.isEmpty());
    }

    /**
     * Erzeugt aus einem Array einen HalmaMove (optimistisch fuer Format des Array)
     * @param moveArray Kodierung des Spielzugs
     * @return Der HalmaMove fuer den Spielzug
     */
    private HalmaMove createHalmaMove(int[][] moveArray) {
	ArrayList<BoardPosition> posList = new ArrayList<BoardPosition>(
		moveArray.length);
	for (int i = 0; i < moveArray.length; i++) {
	    posList.add(new BoardPosition((byte) moveArray[i][0],
		    (byte) moveArray[i][1]));
	}
	return new HalmaMove(posList);
    }

    /**
     * Die Nachricht wird nur auf der Warteschlange gespeichert
     * @see com.googlecode.btuswphalma.serverengine.IDispatcher#acceptMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void acceptMessage(IMessage msg) {
	answers.add(msg);
    }

}
