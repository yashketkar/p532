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
public class GamePauseTest implements Constants {
	private GameCommand gameCommand;
	private Board board;

	@Before
	public void setUp() {
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameCommand = new GamePause(board);
	}

	@Test
	public void testReset() {
		gameCommand.executeCommand();
		Assert.assertTrue(board.getGameParameter().isPaused() == true);
	}
}
