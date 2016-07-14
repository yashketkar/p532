/*
 * PaddleCollisionChecker : Checks and handles Paddle collision
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.util.Mode;

public class PaddleCollisionChecker implements EventChecker 
{
	// Variables
	private int x;
	private int y;
	private Board board;

	// Constructor for PaddleColiisionChecker, initializes using existing board
	public PaddleCollisionChecker(Board board) {
		super();
		this.board = board;
	}
	
	// Checks for paddle collision
	@Override
	public void check() 
	{		
		if (board.getPaddle().hitPaddle(x, y)) 
		{
            if (board.getGameParameter().getMode().equals(Mode.UNDO)) 
            {
                board.getBall().setYDir(-board.getBall().INITIAL_Y_DIR);
            } 
            else 
            {
                board.getBall().setYDir(board.getBall().INITIAL_Y_DIR);
            }
        }
	}

	// set and get functions 
	
	@Override
	public void setX(int x) {
		
		this.x = x;
		
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
