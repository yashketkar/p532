/*
 * Board : Main Game JPanel, magic happens here
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.gameui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.p532.brickout.impl.BrickCollisionChecker;
import com.p532.brickout.impl.DeserializeLoad;
import com.p532.brickout.impl.GamePause;
import com.p532.brickout.impl.GameReplay;
import com.p532.brickout.impl.GameReset;
import com.p532.brickout.impl.GameResume;
import com.p532.brickout.impl.GameStop;
import com.p532.brickout.impl.GameUndo;
import com.p532.brickout.impl.LivesChecker;
import com.p532.brickout.impl.PaddleCollisionChecker;
import com.p532.brickout.impl.PlayerOutChecker;
import com.p532.brickout.impl.SaveGame;
import com.p532.brickout.impl.SerializeSave;
import com.p532.brickout.impl.WallCollisionChecker;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.intf.Observer;
import com.p532.brickout.intf.Subject;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.shape.Paddle;
import com.p532.brickout.util.BoardListener;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

//Class definition
public class Board extends JPanel implements Runnable, Constants, Subject {

	// Items on-screen
	private Paddle paddle;
	private Ball ball;
	private Clock clock;
	private Brick[][] bricks;
	private List<Observer> observers;
	private static ArrayDeque<GameEvent> events;
	private ArrayDeque<GameEvent> tempevent;

	// Initial Values for some important variables

	// To store events temporarily
	public ArrayDeque<GameEvent> getTempevent() {
		return tempevent;
	}

	public void setTempevent(ArrayDeque<GameEvent> tempevent) {
		this.tempevent = tempevent;
	}
	
	// The game
	private Thread game;

	// Contains GameParameters
	private GameParameter gameParameter;

	// Game Commands
	private GameCommand resetCommand;
	private GameCommand pauseCommand;
	private GameCommand resumeCommand;
	private GameCommand undoCommand;
	private GameCommand replayCommand;
	private GameCommand stopCommand;
	private GameCommand ballMoveCommand;
	private GameCommand clockTickCommand;
	private GameCommand commandBroker;

	// Collision and other checker
	private EventChecker brickCollisionChecker;
	private EventChecker wallCollisionChecker;
	private EventChecker paddleCollisionChecker;
	private EventChecker livesChecker;
	private EventChecker playerOutChecker;

	// JButtons to let the user control game states
	private JButton startBtn, stopBtn, undoBtn, replayBtn, layoutBtn, saveBtn, loadBtn;
	// JPanels to adjust buttons layout
	private JComponent btnPanel, northPanel, southPanel;
	// Default folder in project space to store/load game state files
	public static File saveFolder;
	// To check which layout is current and needs to be changed
	private int layoutFlag;

	// Main Constructor
	public Board(int width, int height) {
		
		// Setting up the board
		super.setSize(width, height);
		addKeyListener(new BoardListener(this));
		super.setLayout(null);
		
		// Creating buttons with default values
		startBtn = new JButton("START");
		stopBtn = new JButton("STOP");
		undoBtn = new JButton("UNDO");
		replayBtn = new JButton("REPLAY");
		layoutBtn = new JButton("LAYOUT");
		saveBtn = new JButton("SAVE");
		loadBtn = new JButton("LOAD");

		// Creating a temporary event object 
		tempevent = new ArrayDeque<>();
		
		// Creating a panel to hold all the buttons
		btnPanel = new JPanel();
		btnPanel.setBounds(1, CLOCK_LOCATION_Y + 170, WINDOW_WIDTH, 110);
		// For Flow Layout (alignment)
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// layoutFlag: 0 for Flow, 1 for Border
		layoutFlag = 0;

		// Add button press listeners to all buttons
		startBtn.addActionListener(new BoardListener(this));
		stopBtn.addActionListener(new BoardListener(this));
		undoBtn.addActionListener(new BoardListener(this));
		replayBtn.addActionListener(new BoardListener(this));
		layoutBtn.addActionListener(new BoardListener(this));
		saveBtn.addActionListener(new BoardListener(this));
		loadBtn.addActionListener(new BoardListener(this));

		// For FlowLayout, simply adding all the buttons
		btnPanel.add(layoutBtn);
		btnPanel.add(startBtn);
		btnPanel.add(stopBtn);
		btnPanel.add(undoBtn);
		btnPanel.add(replayBtn);
		btnPanel.add(saveBtn);
		btnPanel.add(loadBtn);

		// Adding the button panel to the board frame (this)
		this.add(btnPanel);

		// Initializing game functionalities
		gameParameter = new GameParameter();
		pauseCommand = new GamePause(this);
		resetCommand = new GameReset(this);
		resumeCommand = new GameResume(this);
		undoCommand = new GameUndo(this);
		replayCommand = new GameReplay(this);
		stopCommand = new GameStop(this);

		// Initializing collisions
		wallCollisionChecker = new WallCollisionChecker(this);
		paddleCollisionChecker = new PaddleCollisionChecker(this);
		brickCollisionChecker = new BrickCollisionChecker(this);
		// Initializing game info like lives 
		livesChecker = new LivesChecker(this);
		playerOutChecker = new PlayerOutChecker(this);

		// Name of the folder is initialized to saveFolder
		saveFolder = new File("saveFolder");
		// If the directory isn't found in project space, it is created
		if (!saveFolder.isDirectory()) 
		{
			saveFolder.mkdir();
			//If the folder doesn't exist, load is disabled until a file is saved
			loadBtn.setEnabled(false);
		}

		// After everything is initialized, the board is reset
		resetCommand.executeCommand();

		setFocusable(true);
		// Starting the game thread
		game = new Thread(this);
		game.start();

	}

	// runs the game
	public void run() {
		while (true) {

			// In replay mode, re-apply stored events
			while (gameParameter.getMode().equals(Mode.REPLAY) && clock.getTime() == events.peekFirst().getTime()) {
				// popping out events
				GameEvent event = events.pollFirst();
				tempevent.add(event);
				if (event.getAction() == KeyEvent.VK_LEFT || event.getAction() == KeyEvent.VK_RIGHT) {
					paddle.move(event.getAction(), getWidth());
				} else if (event.getAction() == GAME_END) {
					stopCommand.executeCommand();
				}

			}

			// In LOAD mode, all events are loaded but not shown, last state is restored
			while (gameParameter.getMode().equals(Mode.LOAD)) {
				while (clock.getTime() == events.peekFirst().getTime()) {
					// popping out events
					GameEvent event = events.pollFirst();
					// storing events to a temporary array
					tempevent.add(event);
					// paddle movements are recalculated from events
					if (event.getAction() == KeyEvent.VK_LEFT || event.getAction() == KeyEvent.VK_RIGHT) {
						paddle.move(event.getAction(), getWidth());
					} else if (event.getAction() == GAME_END) {
						System.out.print("GAME END");
					}
				}
				
				// while running, ball's position is stored
				int x1 = ball.getX();
				int y1 = ball.getY();

				// checkPaddle(x1, y1);
				paddleCollisionChecker.setX(x1);
				paddleCollisionChecker.setY(y1);
				paddleCollisionChecker.check();

				// checkWall(x1, y1);
				wallCollisionChecker.setX(x1);
				wallCollisionChecker.setY(y1);
				wallCollisionChecker.check();

				// checkBricks(x1, y1);
				brickCollisionChecker.setX(x1);
				brickCollisionChecker.setY(y1);
				brickCollisionChecker.check();

				// checkLives();
				livesChecker.check();

				// checkIfOut(y1);
				playerOutChecker.setY(y1);
				playerOutChecker.check();
				if (!gameParameter.getMode().equals(Mode.UNDO)) {
					commandBroker.executeCommand();
				}
				
				// Added for load
				// Instead of end of array, if the mode is load, the game instead of ending, is set to PLAY mode
				if (events.size() == 1 && gameParameter.getMode().equals(Mode.LOAD)) {
					
					gameParameter.setMode(Mode.PLAY);
					events = tempevent;
					resumeCommand.executeCommand();
					startBtn.setText("RESUME");
					undoBtn.setEnabled(true);
					replayBtn.setEnabled(true);
					stopBtn.setEnabled(true);
				}
			}
			
			// run condition for gameParameter set to paused or Undo
			if (gameParameter.isPaused()
					|| gameParameter.getMode().equals(Mode.UNDO) && clock.getTime() <= events.peekLast().getTime()) {
				if (gameParameter.getMode().equals(Mode.UNDO)) {
					GameEvent event = events.pollLast();
					if (event.getAction() == KeyEvent.VK_RIGHT || event.getAction() == KeyEvent.VK_LEFT) {
						paddle.setX(paddle.getX() - (int) event.getEventObject());
					} else if (event.getAction() == BRICK_COLLISION) {
						((Brick) event.getEventObject()).setDestroyed(false);
						// checkBricks(ball.getX(), ball.getY());
						brickCollisionChecker.setX(ball.getX());
						brickCollisionChecker.setY(ball.getY());
						brickCollisionChecker.check();
						// moveCommand.undo();
						/*
						 * ballMoveCommand.undo(); clockTickCommand.undo();
						 */

						commandBroker.undo();

					}
					repaint();
					// mode = Mode.PLAY;
					gameParameter.setMode(Mode.PLAY);
					pauseCommand.executeCommand();
				}
				// asking the thread to wait
				synchronized (game) {
					try {
						game.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			// if state is not paused or undo, i.e, running state
			else {

				int x1 = ball.getX();
				int y1 = ball.getY();

				// checkPaddle(x1, y1);
				paddleCollisionChecker.setX(x1);
				paddleCollisionChecker.setY(y1);
				paddleCollisionChecker.check();

				// checkWall(x1, y1);
				wallCollisionChecker.setX(x1);
				wallCollisionChecker.setY(y1);
				wallCollisionChecker.check();

				// checkBricks(x1, y1);
				brickCollisionChecker.setX(x1);
				brickCollisionChecker.setY(y1);
				brickCollisionChecker.check();

				// checkLives();
				livesChecker.check();

				// checkIfOut(y1);
				playerOutChecker.setY(y1);
				playerOutChecker.check();
				if (!gameParameter.getMode().equals(Mode.UNDO)) {
					commandBroker.executeCommand();
				} else {
					commandBroker.undo();
				}
				repaint();

				try {
					Thread.sleep(TIME_STEP);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}

		}
	}

	// fills the board
	@Override
	public void paintComponent(Graphics g) {
		// Toolkit.getDefaultToolkit().sync();
		super.paintComponent(g);
		paddle.draw(g);
		ball.draw(g);
		clock.draw(g);

		for (int i = 0; i < BRICK_COLUMNS; i++) {
			for (int j = 0; j < BRICK_ROWS; j++) {
				bricks[i][j].draw(g);
			}
		}
		//setting text color
		g.setColor(Color.BLACK);
		// the lives/score/level is painted
		g.drawString("Lives: " + gameParameter.getLives(), CLOCK_LOCATION_X, CLOCK_LOCATION_Y + 20);
		g.drawString("Score: " + gameParameter.getScore(), CLOCK_LOCATION_X, CLOCK_LOCATION_Y + 70);
		g.drawString("Level: " + gameParameter.getLevel(), CLOCK_LOCATION_X, CLOCK_LOCATION_Y + 120);
	}

	// Observer definitions
	public void register(Observer observer) {
		observers.add(observer);
	}

	public void unregister(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers(int timeStep) {
		for (Observer observer : observers) {
			observer.update(timeStep);
		}
	}

	// set and get methods for everything
	
	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Brick[][] getBricks() {
		return bricks;
	}

	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public ArrayDeque<GameEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayDeque<GameEvent> events) {
		this.events = events;
	}

	public Thread getGame() {
		return game;
	}

	public void setGame(Thread game) {
		this.game = game;
	}

	public int getTIME_STEP() {
		return TIME_STEP;
	}

	//set and get for GameParameter
	
	public GameParameter getGameParameter() {
		return gameParameter;
	}

	public void setGameParameter(GameParameter gameParameter) {
		this.gameParameter = gameParameter;
	}
	
	// GameCommands, set and get
	
	public GameCommand getResetCommand() {
		return resetCommand;
	}

	public void setResetCommand(GameCommand resetCommand) {
		this.resetCommand = resetCommand;
	}

	public GameCommand getPauseCommand() {
		return pauseCommand;
	}

	public void setPauseCommand(GameCommand pauseCommand) {
		this.pauseCommand = pauseCommand;
	}

	public GameCommand getResumeCommand() {
		return resumeCommand;
	}

	public void setResumeCommand(GameCommand resumeCommand) {
		this.resumeCommand = resumeCommand;
	}

	public GameCommand getUndoCommand() {
		return undoCommand;
	}

	public void setUndoCommand(GameCommand undoCommand) {
		this.undoCommand = undoCommand;
	}

	public GameCommand getReplayCommand() {
		return replayCommand;
	}

	public void setReplayCommand(GameCommand replayCommand) {
		this.replayCommand = replayCommand;
	}

	public GameCommand getStopCommand() {
		return stopCommand;
	}

	public void setStopCommand(GameCommand stopCommand) {
		this.stopCommand = stopCommand;
	}

	// get and set for buttons
	
	public JButton getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(JButton startBtn) {
		this.startBtn = startBtn;
	}

	public JButton getStopBtn() {
		return stopBtn;
	}

	public void setStopBtn(JButton stopBtn) {
		this.stopBtn = stopBtn;
	}

	public JButton getUndoBtn() {
		return undoBtn;
	}

	public void setUndoBtn(JButton undoBtn) {
		this.undoBtn = undoBtn;
	}

	public JButton getReplayBtn() {
		return replayBtn;
	}

	public void setReplayBtn(JButton replayBtn) {
		this.replayBtn = replayBtn;
	}

	public JButton getLayoutBtn() {
		return layoutBtn;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public JButton getLoadBtn() {
		return loadBtn;
	}

	// components get/set sub-commands
	public GameCommand getBallMoveCommand() {
		return ballMoveCommand;
	}

	public void setBallMoveCommand(GameCommand ballMoveCommand) {
		this.ballMoveCommand = ballMoveCommand;
	}

	public GameCommand getClockTickCommand() {
		return clockTickCommand;
	}

	public void setClockTickCommand(GameCommand clockTickCommand) {
		this.clockTickCommand = clockTickCommand;
	}

	public GameCommand getCommandBroker() {
		return commandBroker;
	}

	public void setCommandBroker(GameCommand commandBroker) {
		this.commandBroker = commandBroker;
	}

	// Function to switch layout
	public void switchLayout() 
	{
		// Clearing the panel
		btnPanel.removeAll();

		// Switching to BorderLayout
		if (layoutFlag == 0) {
			// Switching to BorderLayout
			layoutFlag = 1;
			System.out.println("Border");

			// For BorderLayout, adds a panel on north and south positions
			btnPanel.setLayout(new BorderLayout());
			northPanel = new JPanel();
			southPanel = new JPanel();

			// buttons added to northPanel. northPanel added to btnPanel
			northPanel.add(startBtn);
			northPanel.add(stopBtn);
			btnPanel.add(northPanel, BorderLayout.NORTH);

			// aligning center row
			btnPanel.add(undoBtn, BorderLayout.WEST);
			btnPanel.add(layoutBtn, BorderLayout.CENTER);
			btnPanel.add(replayBtn, BorderLayout.EAST);

			// buttons added to southPanel. southPanel added to btnPanel
			southPanel.add(saveBtn);
			southPanel.add(loadBtn);
			btnPanel.add(southPanel, BorderLayout.SOUTH);

		} 
		
		// Switching to flow layout
		else if (layoutFlag == 1) 
		{
			// Switching to Flow Layout
			layoutFlag = 0;
			System.out.println("Flow");

			btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

			btnPanel.add(layoutBtn);
			btnPanel.add(startBtn);
			btnPanel.add(stopBtn);
			btnPanel.add(undoBtn);
			btnPanel.add(replayBtn);
			btnPanel.add(saveBtn);
			btnPanel.add(loadBtn);

		} 
		
		//Unused else, just to print Nothing to notify if control goes here
		else 
		{
			System.out.println("Nothing");
		}

		// btnPanel is revalidated to ensure everything is removed and added
		btnPanel.revalidate();
		// btnPanel is repainted to remove buttons for unused space
		btnPanel.repaint();
	}

	// saveFile() creates and calls SaveGame, which returns fileName
	// fileName is then used to create a file by that name
	public void saveFile() throws IOException 
	{
		// SaveGame object is created
		SaveGame saveGame = new SaveGame();
		// savePopUp() ask for user input for fileName, which is returned here
		String fileName = saveGame.savePopUp();
		
		try 
		{	
			if(fileName!=null)
			{
				// To keep track of control flow, message is printed
				System.out.println("saving file now!");
				// loadBtn is enabled for first save
				loadBtn.setEnabled(true);
				// current events array and fileName are sent to be stored through object of SerializeSave 
				SerializeSave save = new SerializeSave(fileName);
				save.saveIt(events);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// loadFile() takes the fileName, opens it and loads it to the event array
	public ArrayDeque<GameEvent> loadFile(String fileName) 
	{
		// Object of DeserializeLoad is created with the fileName file
		DeserializeLoad load = new DeserializeLoad(fileName);
		try
		{
			// events is set to the loaded events
			setEvents((ArrayDeque<GameEvent>) load.loadIt());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// The game is resumed
		getResumeCommand().executeCommand();
		
		return events;
	}


}
