/**
 * 
 */
package com.p532.brickout.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.util.Constants;

/**
 * @author hp
 *
 */
public class GameResetTest implements Constants {
	private GameCommand gameCommand;
	private Board board;

	@Before
	public void setUp() {
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameCommand = new GameReset(board);
	}

	@Test
	public void testInit() {
		Assert.assertTrue(board.isFocusable());
	}

	@Test
	public void testReset() {
		gameCommand.executeCommand();
		Assert.assertTrue(board.getGameParameter().isPaused() == true);
		Assert.assertTrue("PLAY".equals(board.getGameParameter().getMode().toString()));
		Assert.assertTrue(board.getGameParameter().getLives() == 5);
	}
}
