/*
 * BoardListener : Handles all the key and button press events
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.impl.LoadChooser;

public class BoardListener extends KeyAdapter implements ActionListener 
{
	// Creating an instance of Board to call the commands
	private Board board;

	// BoardListener constructor to add the board
	public BoardListener(Board board) 
	{
		super();
		this.board = board;
	}

	// Function to catch and use keyEvents
	@Override
	public void keyPressed(KeyEvent ke) 
	{
		
		int key = ke.getKeyCode();
		// If the game is in Play Mode and user presses right or left arrow keys, the paddle is moved
		// This event is also stored as a GameEvent
		if (board.getGameParameter().getMode().equals(Mode.PLAY) && !board.getGameParameter().isPaused() && key == KeyEvent.VK_LEFT
                || key == KeyEvent.VK_RIGHT) 
		{
            board.getEvents().add(new GameEvent(board.getClock().getTime(), key));
            board.getEvents().peekLast().setEventObject(board.getPaddle().move(key, board.getWidth()));

        }
	}

	// Function to catch and use buttons pressed
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		GameParameter gameParameter = board.getGameParameter();

		// If start/pause/resume button is pressed
		if (ae.getSource() == board.getStartBtn()) 
		{
			if (gameParameter.getLives() > Constants.MIN_LIVES) 
			{
				// Pauses the game
				if (!gameParameter.isPaused()) 
				{
					board.getPauseCommand().executeCommand();
				
					// To bring the focus back to the JPanel default
					board.requestFocus();
					board.getStartBtn().setText("RESUME");
				} 
				// Resumes the game
				else 
				{
					
					board.getResumeCommand().executeCommand();
					board.getStartBtn().setText("PAUSE");
					
					// To bring the focus back to the JPanel default
					board.requestFocus();

					// If statement removed, PFTAE-5 fixed

					board.getUndoBtn().setEnabled(true);
					board.getReplayBtn().setEnabled(true);
					board.getStopBtn().setEnabled(true);
				}
			}
			// Starts the game
			else 
			{
				board.getPaddle().setWidth(board.getWidth() / 7);
				// lives = MAX_LIVES;
				gameParameter.resetLives();
				// score = 0;
				gameParameter.resetScore();
				// bricksLeft = MAX_BRICKS;
				gameParameter.resetBricksLeft();
				// level = 1;
				gameParameter.resetLevel();
				// makeBricks();
				CommonStructureUtility.makeBricks(board.getBricks());

				gameParameter.setPaused(true);
				
				// Creating bricks
				for (int i = 0; i < Constants.BRICK_COLUMNS; i++) 
				{
					for (int j = 0; j < Constants.BRICK_ROWS; j++) 
					{
						board.getBricks()[i][j].setDestroyed(false);
					}
				}
			}
		} 
		
		// If stop button is pressed
		else if (ae.getSource() == board.getStopBtn()) 
		{
			// To bring the focus back to the JPanel default
			board.requestFocus();
			board.getStopCommand().executeCommand();
		}
		
		// If undo button is pressed
		else if (ae.getSource() == board.getUndoBtn() && gameParameter.getMode().equals(Mode.PLAY)) 
		{
			// Undo can only be executed till events array gets emptied
			if (!board.getEvents().isEmpty()) {
				// To bring the focus back to the JPanel default
				board.requestFocus();
				board.getStartBtn().setText("RESUME");

				board.getUndoCommand().executeCommand();
			} 
			// Once events array is emptied, replay and undo are set to false
			else 
			{
				board.getUndoBtn().setEnabled(false);
				board.getReplayBtn().setEnabled(false);
			}
		} 
		
		// If replay button is pressed
		else if (ae.getSource() == board.getReplayBtn() && gameParameter.getMode().equals(Mode.PLAY)) 
		{
			// Adding a GAME_END to flag where replay should stop
			board.getEvents().add(new GameEvent(board.getClock().getTime(),	Constants.GAME_END));
			
			// To bring the focus back to the JPanel default
			board.requestFocus();
			board.getReplayCommand().executeCommand();
		}
		
		// If Layout button is pressed
		else if (ae.getSource() == board.getLayoutBtn())
		{
			// To bring the focus back to the JPanel default
			board.requestFocus();
			// Switches layout
			board.switchLayout();
		}
		
		// If Save button is pressed 
		else if (ae.getSource() == board.getSaveBtn())
		{
			// To bring the focus back to the JPanel default
			board.requestFocus();
			// Game is paused while user tries to save
			board.getPauseCommand().executeCommand();
			
			// If the game is not going on, Start still says Start, otherwise, changed to Resume
			if(board.getEvents().size()>1)
			{
				board.getStartBtn().setText("RESUME");
			}
				
			try 
			{
				board.saveFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		// If Load button is pressed
		else if (ae.getSource() == board.getLoadBtn())
		{
			// To bring the focus back to the JPanel default
			board.requestFocus();
			if (!board.getGameParameter().isPaused()) {
				board.getPauseCommand().executeCommand();
				// If the game is not going on, Start still says Start, otherwise, changed to Resume
				if(board.getEvents().size()>1)
				{
					board.getStartBtn().setText("RESUME");
				}
			}
			
			LoadChooser loadChoose = new LoadChooser();

			String fileToDeserialize = loadChoose.displayFiles();
			
			System.out.println("CHECK : " + fileToDeserialize);
			if(!fileToDeserialize.equals("cancel"))
			{
				// the board is reset
				board.getResetCommand().executeCommand();
				board.setEvents(board.loadFile(fileToDeserialize));
				// A new GameEvent is added to stop the load at the right moment
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_END));
				// Mode is set to LOAD
				gameParameter.setMode(Mode.LOAD);

				//The game is resumed
				board.getResumeCommand().executeCommand();
			}
			
		}
	}

}
