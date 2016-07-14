/*
 * GameReset : Resets the game board
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.intf.Observer;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.shape.Paddle;
import com.p532.brickout.util.CommandBroker;
import com.p532.brickout.util.CommonStructureUtility;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.Mode;

public class GameReset implements GameCommand 
{	
	// Variable board
	private Board board;

	// Constructor function for GameReset, initializes using board
	public GameReset(Board board) {
		super();
		this.board = board;
	}

	// get and set functions for Board
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	// Overriden inherited function
	// Executes reset over the board
	@Override
	public void executeCommand() 
	{
		// Resetting all the GameParameters
		board.getGameParameter().resetScore();
		board.getGameParameter().resetBricksLeft();
		board.getGameParameter().resetLevel();
		board.getGameParameter().resetLives();

		board.setBricks(new Brick[Constants.BRICK_COLUMNS][Constants.BRICK_ROWS]);
		// makeBricks();
		CommonStructureUtility.makeBricks(board.getBricks());
		
		// Resetting the paddle
		board.setPaddle(new Paddle(Constants.PADDLE_X_START,
				Constants.PADDLE_Y_START, Constants.PADDLE_WIDTH,
				Constants.PADDLE_HEIGHT, Color.BLACK));
		// Resetting the ball with a new Ball
		board.setBall(new Ball(Constants.BALL_X_START, Constants.BALL_Y_START,
				Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Color.BLACK));
		
		// Resetting the clock
		board.setClock(new Clock(Constants.CLOCK_LOCATION_X, Constants.CLOCK_LOCATION_Y, Constants.BALL_WIDTH,
				Constants.BALL_HEIGHT, Color.red));
		
		// resetting ballMove, clockTick and Command
		board.setBallMoveCommand(new BallMove(board.getBall()));
		board.setClockTickCommand(new ClockTick(board.getClock()));		
		board.setCommandBroker(new CommandBroker(Arrays.asList(board.getBallMoveCommand(),
                board.getClockTickCommand())));
		
		// Creating new GameEvent array
		board.setEvents(new ArrayDeque<GameEvent>());

		// Setting events as Game_Begin as initial entry
		board.getEvents()
				.add(new GameEvent(board.getClock().getTime(),
						Constants.GAME_BEGIN));

		// Resetting the ball on board
		board.getBall().reset();

		// Set state of board as paused in PLAY
		board.getGameParameter().setPaused(true);
		board.getGameParameter().setMode(Mode.PLAY);

		// Registering observers
		board.setObservers(new ArrayList<Observer>());
		board.register(board.getBall());
		board.register(board.getClock());
		
		// Reset StartBtn to start
		board.getStartBtn().setText("START");
		// Disabling other buttons
        board.getUndoBtn().setEnabled(false);
        board.getReplayBtn().setEnabled(false);
        board.getStopBtn().setEnabled(false);
	}

	// Overriden inherited function, but not needed or used
	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}
}
