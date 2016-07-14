/*
 * GameResume : executes resume but setting pause state false and notifying the game thread
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;

public class GameResume implements GameCommand 
{
	// Variable board
	private Board board;
	
	// Constructor function for GameResume, initializes using board
	public GameResume(Board board) {
		super();
		this.board = board;
	}
	
	// Overriden function to execute Resume
	@Override
	public void executeCommand() 
	{
		// Paused is set to false
		board.getGameParameter().setPaused(false);
		// game thread is notified
		synchronized (board.getGame()) {
			board.getGame().notifyAll();
		}
	}

	// Overriden function inherited but not needed/used
	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}
}
