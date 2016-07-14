/**
 * 
 */
package com.p532.brickout.impl;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.util.Constants;

/**
 * @author hp
 *
 */
public class LivesCheckerTest implements Constants {
	private EventChecker eventChecker;
	private Board board;

	@Before
	public void setUp() {
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		eventChecker = new LivesChecker(board);
	}

	@Test
	public void testReset() {
		board.getGameParameter().setBricksLeft(0);
		eventChecker.check();
		boolean flag = true;
		Brick[][] br = (Brick[][]) board.getBricks();
		for (Brick[] bricks : br) {
			for (Brick b : bricks) {
				if (!(b.getWidth() > 0 && b.getHeight() > 0 && b.getColor().equals(Color.BLUE))) {
					flag = false;
				}
			}
		}
		Assert.assertTrue(flag);
		Assert.assertTrue(board.getGameParameter().isPaused() == true);
	}
}
