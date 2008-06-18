/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author sebastian
 * 
 */
public class HalmaMathTest {
    
    // Per Hand eingegeben, die korrekten isOnBoard Antworten
    private boolean[][] correctBoardAnswers ={
	    {false, false, false, false, false, false,  true, false, false, false, false, false, false},
	    {false, false, false, false, false,  true,  true, false, false, false, false, false, false},
	    {false, false, false, false, false,  true,  true,  true, false, false, false, false, false},
	    {false, false, false, false,  true,  true,  true,  true, false, false, false, false, false},
	    { true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true},
	    { true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false},
	    {false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false},
	    {false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false, false},
	    {false, false,  true,  true,  true,  true,  true,  true,  true,  true,  true, false, false},
	    {false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false, false},
	    {false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false},
	    { true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false},
	    { true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true},
	    {false, false, false, false,  true,  true,  true,  true, false, false, false, false, false},
	    {false, false, false, false, false,  true,  true,  true, false, false, false, false, false},
	    {false, false, false, false, false,  true,  true, false, false, false, false, false, false},
	    {false, false, false, false, false, false,  true, false, false, false, false, false, false}
    };
     
     

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.HalmaMath#isOnBoard(com.googlecode.btuswphalma.gameengine.BoardPosition)}.
     */
    @Test
    public void testIsOnBoardBoardPosition() {
	for (int x = 0; x < correctBoardAnswers.length; x++) {
	    for (int y = 0; y < correctBoardAnswers[1].length; y++) {
		Assert.assertEquals(correctBoardAnswers[x][y],HalmaMath.isOnBoard((byte)x, (byte)y));
	    }
	}
	for (byte x = -128; x < 127; x++) {
	    for (byte y = -128; y < 127; y++) {
		if(x<0||x>16||y<0||y>12) {
		    Assert.assertEquals(false, HalmaMath.isOnBoard(x, y));
		}
	    }
	}
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.HalmaMath#isOnBoard(byte, byte)}.
     */
    @Test
    @Ignore
    public void testIsOnBoardByteByte() {
	
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.HalmaMath#isPushDistance(com.googlecode.btuswphalma.gameengine.BoardPosition, com.googlecode.btuswphalma.gameengine.BoardPosition)}.
     */
    @Test
    public void testIsPushDistance() {
	// Fuer jede potentielle Spielbrettposition (innerhalb des 17*13 Gebiets) werden alle moeglichen
	// Endpositionen geprueft
	BoardPosition startPos = null;
	BoardPosition endPos = null;
	boolean res;
	for (byte x = -128; x < 127; x++) {
	    for (byte y = -128; y < 127; y++) {
		endPos = new BoardPosition(x,y);
		for (byte xs = 0; xs <= 16; xs++) {
		    for (byte ys = 0; ys <= 12; ys++) {
			startPos = new BoardPosition(xs,ys);
			res =        ((x == xs-1 || x == xs+1) && (xs%2==0) && (y == ys-1 || y == ys));
			res = res || ((x == xs-1 || x == xs+1) && (xs%2==1) && (y == ys || y == ys+1));
			res = res || (x==xs &&(y==ys-1||y==ys+1));
			Assert.assertEquals(res, HalmaMath.isPushDistance(startPos, endPos));
			Assert.assertEquals(false, HalmaMath.isPushDistance(startPos, null));
			Assert.assertEquals(false, HalmaMath.isPushDistance(null, endPos));
			Assert.assertEquals(false, HalmaMath.isPushDistance(null, null));
		    }
		}
	    }
	}
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.HalmaMath#isJumpDistance(com.googlecode.btuswphalma.gameengine.BoardPosition, com.googlecode.btuswphalma.gameengine.BoardPosition)}.
     */
    @Test
    public void testIsJumpDistance() {
	// Fuer jede potentielle Spielbrettposition (innerhalb des 17*13 Gebiets) werden alle moeglichen
	// Endpositionen geprueft
	BoardPosition startPos = null;
	BoardPosition endPos = null;
	boolean res;
	for (byte x = -128; x < 127; x++) {
	    for (byte y = -128; y < 127; y++) {
		endPos = new BoardPosition(x,y);
		for (byte xs = 0; xs <= 16; xs++) {
		    for (byte ys = 0; ys <= 12; ys++) {
			startPos = new BoardPosition(xs,ys);
			res =        ((x == xs-2 || x == xs+2) && (y == ys-1 || y == ys+1));
			res = res || (x==xs &&(y==ys-2||y==ys+2));
			Assert.assertEquals(res, HalmaMath.isJumpDistance(startPos, endPos));
			Assert.assertEquals(false, HalmaMath.isJumpDistance(startPos, null));
			Assert.assertEquals(false, HalmaMath.isJumpDistance(null, endPos));
			Assert.assertEquals(false, HalmaMath.isJumpDistance(null, null));
		    }
		}
	    }
	}
    }

}
