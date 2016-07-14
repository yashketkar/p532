/*
 * GameUndo : Handles undo function from board
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class GameUndo implements GameCommand 
{
	// Variable board
	private Board board;	
	
	// Constructor to initialize GameUndo with board
	public GameUndo(Board board) {
		super();
		this.board = board;
	}

	// Overriden function to undo
	@Override
	public void executeCommand() 
	{
		GameParameter gameParameter = board.getGameParameter();
		
		// Mode is set to undo
		gameParameter.setMode(Mode.UNDO);
		// Game is paused
		board.getPauseCommand().executeCommand();
		
		// Last event is loaded
		GameEvent event = board.getEvents().peekLast();
		// Special cases for game win or lost
		if (event.getAction() == Constants.GAME_WIN || event.getAction() == Constants.GAME_LOST) {
			if (event.getAction() == Constants.GAME_WIN) {
				gameParameter.setScore(gameParameter.getScore() - 100);
				gameParameter.setLives(gameParameter.getLives() - 1);
			} 
			else 
			{
				gameParameter.setScore(gameParameter.getScore() + 50);
				gameParameter.setLives(gameParameter.getLives() + 1);	
			}

			board.getBall().set((Ball) event.getEventObject());

			board.getEvents().pollLast();
			event = board.getEvents().peekLast();
		}
		board.getResumeCommand().executeCommand();		
	}

	// Overriden function inherited but not needed/used
	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

}
