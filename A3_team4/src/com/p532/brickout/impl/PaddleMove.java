/*
 * PaddleMove : Handles paddle movement
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Paddle;

public class PaddleMove implements GameCommand 
{
	// Variable for paddle
	private Paddle paddle;
	
	// Constructor to initialize PaddleMove with an existing paddle
	public PaddleMove(Paddle paddle) {
		super();
		this.paddle = paddle;
	}

	// Overriden functions from GameCommand but not needed/used
	@Override
	public void executeCommand() {
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

}
