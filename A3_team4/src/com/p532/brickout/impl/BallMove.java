/*
 * BallMove : Handles executeCommand call for BallMove
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.util.Constants;

public class BallMove implements GameCommand 
{
	// variable ball
	private Ball ball;
	
	// Constructor function for BallMove using existing ball
	public BallMove(Ball ball) {
		super();
		this.ball = ball;
	}

	// Overriden inherited function to executeCommand
	// Function updates the ball by TIME_STEP
	@Override
	public void executeCommand() {
		ball.update(Constants.TIME_STEP);
	}

	// Overriden inherited function to undo
	// Function updates the ball by negative of TIME_STEP
	@Override
	public void undo() {
		ball.update(-Constants.TIME_STEP);
	}
}
