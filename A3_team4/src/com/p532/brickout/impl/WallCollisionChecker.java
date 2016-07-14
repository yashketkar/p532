/*
 * WallCollisionChecker : Checks if the ball collides with the wall
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;

public class WallCollisionChecker implements EventChecker 
{
	// Variables
	private int x;
	private int y;
	private Board board;

	//Default constructor
	public WallCollisionChecker() {
		// TODO Auto-generated constructor stub
	}

	// Constructor for WallCollisionChecker, initialized using board 
	public WallCollisionChecker(Board board) {
		super();
		
		this.board = board;
	}

	// Check if collision happens
	@Override
	public void check() {

		if (x <= 0 || x >= board.getWidth() - board.getBall().getWidth()) {
            board.getBall().reverseXDir();
        }

        if (y <= 0) {
            board.getBall().reverseYDir();
        }

	}

	// set and get functions 
	
	@Override
	public void setX(int x) {
		
		this.x = x;
		
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	
}
