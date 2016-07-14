/*
 * GameStop : Stops the game and resets it
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;

public class GameStop implements GameCommand 
{
	// Variable board
	private Board board;
	
	// Constructor for GameStop, initialized using board
	public GameStop(Board board) {
		super();
		this.board = board;
	}

	// Overriden function to execute the command
	@Override
	public void executeCommand() 
	{
		board.getResetCommand().executeCommand();
		board.repaint();
	}

	// Overriden function but not needed/used
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
}
