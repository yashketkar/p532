/*
 * PlayerOutChecker : Checks if the player is out
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class PlayerOutChecker implements EventChecker 
{
	private Board board;
	private int y;
	
	// Constructor to initialize PlayerOutChecker with the board
	public PlayerOutChecker(Board board) {
		super();
		this.board = board;
	}

	// Checks if the player is out
	@Override
	public void check() 
	{	
		GameParameter gameParameter = board.getGameParameter();
		
		if (y > Constants.PADDLE_Y_START + 10 && !gameParameter.getMode().equals(Mode.UNDO)) 
		{
			//lives--;
			gameParameter.decrementLives(1);
			//score -= 50;
			gameParameter.decrementScore(50);
			
			if (gameParameter.getMode().equals(Mode.PLAY)) {
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_LOST));
			}
			
			board.getEvents().peekLast().setEventObject(new Ball(board.getBall()));
			board.getBall().reset();
			
			if (gameParameter.getMode().equals(Mode.PLAY)) {
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_BEGIN));
			}
			board.repaint();
		}
	}

	// set and get functions 
	
	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		this.y = y;

	}

}
