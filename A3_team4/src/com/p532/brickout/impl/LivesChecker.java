/*
 * LivesChecker : Checks the status of lives
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.util.CommonStructureUtility;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class LivesChecker implements EventChecker 
{
	// Board variable
	private Board board;
	
	// Constructor for LivesChecker, initializing using board
	public LivesChecker(Board board) {
		super();
		this.board = board;
	}

	// Check function to check status of the game/lives
	@Override
	public void check() 
	{	
		GameParameter gameParameter = board.getGameParameter();
		
		// If no bricks is left, player wins
		if (gameParameter.getBricksLeft() == Constants.NO_BRICKS) 
		{
			if (gameParameter.getMode().equals(Mode.PLAY)) 
			{
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_WIN));
			}
			
			// Game is reset
			board.getEvents().peekLast().setEventObject(new Ball(board.getBall()));
			board.getBall().reset();
			
			//Game starts again
			if (gameParameter.getMode().equals(Mode.PLAY)) 
			{
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_BEGIN));
			}
			
			//set bricks to max
			gameParameter.setBricksLeft(Constants.MAX_BRICKS);
			//makeBricks();
			CommonStructureUtility.makeBricks(board.getBricks());
			//lives++;
			gameParameter.incrementLives(1);
			//level++;
			gameParameter.incrementLevel(1);
			//score += 100;
			gameParameter.incrementScore(100);
			board.repaint();
			//pause();
			board.getPauseCommand().executeCommand();
		}
		
		//if lives are at min (0)
		if (gameParameter.getLives() == Constants.MIN_LIVES) {
			board.repaint();
			board.getPauseCommand().executeCommand();
		}
	}

	// set and get inherited but not needed/used
	
	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

}
