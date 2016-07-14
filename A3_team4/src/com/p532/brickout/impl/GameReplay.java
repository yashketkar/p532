/*
 * GameReplay : Class to execute Replay command
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import java.util.ArrayDeque;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.Mode;

public class GameReplay implements GameCommand 
{
	// variable board
	private Board board;
	
	// Constructor for GameReplay, initializes using board
	public GameReplay(Board board) {
		super();
		this.board = board;
	}

	// Overriden function to executeCommand
	// Stores the current events in tmp and Mode as REPLAY
	@Override
	public void executeCommand() 
	{	
		ArrayDeque<GameEvent> tmp = board.getEvents();
		// board is reset
		board.getResetCommand().executeCommand();
		// events = tmp
		board.setEvents(tmp);
		// Mode = REPLAY
		board.getGameParameter().setMode(Mode.REPLAY);
		// Game is resumed to replay all events
		board.getResumeCommand().executeCommand();
	}

	// Overriden inherited function, not needed/used
	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}
}
