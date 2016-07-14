/*
 * GamePause : Handles execution of pauseCommand
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;

public class GamePause implements GameCommand 
{
	// Variable board
	private Board board;	
	
	// Constructor for GamePause, initialized using board
	public GamePause(Board board) {
		super();
		this.board = board;
	}

	// Overriden inherited function to executeCommand
	// Function sets paused state to true
	@Override
	public void executeCommand() 
	{
		board.getGameParameter().setPaused(true);
	}

	// Overriden inherited function but not needed/used
	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}
}
