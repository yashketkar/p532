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
public class PaddleCollisionCheckerTest implements Constants {
	private EventChecker eventChecker;
	private Board board;

	@Before
	public void setUp() {
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		eventChecker = new PaddleCollisionChecker(board);
	}

	@Test
	public void testCheck() {

		int ydir = board.getBall().INITIAL_Y_DIR;
		eventChecker.setX(230);
		eventChecker.setY(455);
		eventChecker.check();
		Assert.assertEquals(ydir, board.getBall().INITIAL_Y_DIR);
	}
}
