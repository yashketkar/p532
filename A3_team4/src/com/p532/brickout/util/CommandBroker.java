/*
 * CommandBroker : Takes the gameCommands and executes them
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.util;

import java.util.List;

import com.p532.brickout.intf.GameCommand;

public class CommandBroker implements GameCommand {

	// To store game commands used
	private List<GameCommand> gameCommands;
	
	// Constructor to initialize the list of game commands
	public CommandBroker(List<GameCommand> gameCommands) {
		super();
		this.gameCommands = gameCommands;
	}

	// executes the command
	@Override
	public void executeCommand() {
		
		for(GameCommand gameCommand : gameCommands)	{
			
			gameCommand.executeCommand();
		}
	}

	// Undo the commands
	@Override
	public void undo() {
		
		for(GameCommand gameCommand : gameCommands)	{
			
			gameCommand.undo();
		}
	}

}
