/*
 * BrickCollisionChecker : Handles Ball collisions with the Bricks
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class BrickCollisionChecker implements EventChecker 
{
	// Variables
	private int x;
	private int y;
	private Board board;
	
	// Constructor for BrickCollisionChecker, initializes using board
	public BrickCollisionChecker(Board board) {
		super();
		this.board = board;
	}

	//Overriden inherited function to check for collision
	@Override
	public void check() 
	{	
		// Game Parameters are loaded
		GameParameter gameParameter = board.getGameParameter();
		for (int i = 0; i < Constants.BRICK_COLUMNS; i++) 
		{
			for (int j = 0; j < Constants.BRICK_ROWS; j++) 
			{
				if (board.getBricks()[i][j].isDestroyed()) 
				{
					continue;
				}
				if (board.getBricks()[i][j].hitBottom(x, y)
						|| board.getBricks()[i][j].hitTop(x, y)) 
				{
					board.getBall().setYDir(-board.getBall().getYDir());
				} 
				else if (board.getBricks()[i][j].hitLeft(x, y)
						|| board.getBricks()[i][j].hitRight(x, y)) 
				{
					board.getBall().reverseXDir();
				} 
				else 
				{
					continue;
				}

				// If Undo is called
				if (!gameParameter.getMode().equals(Mode.UNDO)) 
				{
					board.getBricks()[i][j].setDestroyed(true);
					//bricksLeft--;
					gameParameter.decrementBricks(1);
					//score += 50;
					gameParameter.incrementScore(50);
					if (gameParameter.getMode().equals(Mode.PLAY)) 
					{
						board.getEvents().add(new GameEvent(board.getClock().getTime(),
								Constants.BRICK_COLLISION));
						board.getEvents().peekLast().setEventObject(board.getBricks()[i][j]);
					}
					
				} 
				else 
				{
					//bricksLeft++;
					gameParameter.incrementBricks(1);
					//score -= 50;
					gameParameter.decrementScore(50);
				}
			}
		}
	}

	// set and get methods for x
	@Override
	public void setX(int x) {
		
		this.x = x;

	}
	@Override
	public void setY(int y) {
		this.y = y;

	}

	// Functions to check where the bricks were hit (top, bottom, left, right)
	
	public boolean hitBottom(int ballX, int ballY, Brick brick) {
        if ((ballX >= x) && (ballX <= x + brick.getWidth() + 1) && (ballY == y + brick.getHeight())
                ) {
            return true;
        }
        return false;
    }

    public boolean hitTop(int ballX, int ballY, Brick brick) {
        if ((ballX >= x) && (ballX <= x + brick.getWidth() + 1) && (ballY == y)
                ) {
            return true;
        }
        return false;
    }

    public boolean hitLeft(int ballX, int ballY, Brick brick) {
        if ((ballY >= y) && (ballY <= y + brick.getHeight()) && (ballX == x)
                ) {
            return true;
        }
        return false;
    }

    public boolean hitRight(int ballX, int ballY, Brick brick) {
        if ((ballY >= y) && (ballY <= y + brick.getHeight()) && (ballX == x + brick.getWidth())
                ) {
            return true;
        }
        return false;
    }
}
