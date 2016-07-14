/**
 * 
 */
package com.p532.brickout.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.util.Constants;

/**
 * @author hp
 *
 */
public class WallCollisionCheckerTest implements Constants {
	private EventChecker eventChecker;
	private Board board;

	@Before
	public void setUp() {
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		eventChecker = new WallCollisionChecker(board);
	}

	@Test
	public void testCheck() {

		int xdir = board.getBall().getXDir();
		eventChecker.setX(-10);
		eventChecker.setY(455);
		eventChecker.check();
		Assert.assertEquals(xdir, board.getBall().getXDir() * (-1));
	}
}
