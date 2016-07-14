package p532oop.brickout;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JPanel;

/*
 * Name:
 * 		Board.java
 * Function:
 * 		Board is the concrete subject class that updates its observers
 * 		once changes have been made. 
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public class Board extends JPanel implements Runnable, Constants, Subject {
    private final int STATUS_LOCATION_X = 10, TIME_STEP = 5;
    // Items on-screen
    private Paddle paddle;
    private Ball ball;
    private Clock clock;
    private Brick[][] brick = new Brick[BRICK_COLUMNS][BRICK_ROWS];
    private List<Observer> observers;

    // Initial Values for some important variables
    private int score = 0, lives = MAX_LIVES, bricksLeft = MAX_BRICKS,
             xSpeed, level = 1;

    // Player's name
    private String playerName;

    // The game
    private Thread game;

    // Paused state
    private volatile boolean isPaused = true;

    // Constructor
    public Board(int width, int height) {
        super.setSize(width, height);
        addKeyListener(new BoardListener());
        setFocusable(true);

        makeBricks();
        paddle = new Paddle(PADDLE_X_START, PADDLE_Y_START, PADDLE_WIDTH,
                PADDLE_HEIGHT, Color.BLACK);
        ball = new Ball(BALL_X_START, BALL_Y_START, BALL_WIDTH, BALL_HEIGHT,
                Color.BLACK);
        clock = new Clock(STATUS_LOCATION_X, getHeight() / 2, BALL_WIDTH, BALL_HEIGHT,
                Color.red);
        observers = new ArrayList<Observer>();
        register(ball);
        register(clock);
        game = new Thread(this);
        game.start();

    }

    // fills the array of bricks
    private void makeBricks() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                brick[i][j] = new Brick((i * BRICK_WIDTH),
                        ((j * BRICK_HEIGHT) + (BRICK_HEIGHT / 2)),
                        BRICK_WIDTH - 5, BRICK_HEIGHT - 5, Color.blue);
            }
        }
    }

    // starts the thread
    private void start() {
        isPaused = false;
    }

    // stops the thread
    private void stop() {
        isPaused = true;
    }

    // runs the game
    public void run() {
        xSpeed = 1;
        while (true) {
            while (!isPaused) {
                int x1 = ball.getX();
                int y1 = ball.getY();

                checkPaddle(x1, y1);
                checkWall(x1, y1);
                checkBricks(x1, y1);
                checkLives();
                checkIfOut(y1);
                notifyObservers();
                repaint();

                try {
                    Thread.sleep(TIME_STEP);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private void checkLives() {
        if (bricksLeft == NO_BRICKS) {
            ball.reset();
            bricksLeft = MAX_BRICKS;
            makeBricks();
            lives++;
            level++;
            score += 100;
            repaint();
            stop();
            //isPaused = true;
        }
        if (lives == MIN_LIVES) {
            repaint();
            stop();
            //isPaused = true;
        }
    }

    private void checkPaddle(int x1, int y1) {
        if (paddle.hitPaddle(x1, y1) && ball.getXDir() < 0) {
            ball.setYDir(-1);
            xSpeed = -1;
            ball.setXDir(xSpeed);
        }
        if (paddle.hitPaddle(x1, y1) && ball.getXDir() > 0) {
            ball.setYDir(-1);
            xSpeed = 1;
            ball.setXDir(xSpeed);
        }
        if (paddle.getX() <= 0) {
            paddle.setX(0);
        }
        if (paddle.getX() + paddle.getWidth() >= getWidth()) {
            paddle.setX(getWidth() - paddle.getWidth());
        }
    }

    private void checkWall(int x1, int y1) {
        if (x1 >= getWidth() - ball.getWidth()) {
            xSpeed = -Math.abs(xSpeed);
            ball.setXDir(xSpeed);
        }
        if (x1 <= 0) {
            xSpeed = Math.abs(xSpeed);
            ball.setXDir(xSpeed);
        }
        if (y1 <= 0) {
            ball.setYDir(1);
        }
        if (y1 >= getHeight()) {
            ball.setYDir(-1);
        }
    }

    private void checkBricks(int x1, int y1) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (brick[i][j].hitBottom(x1, y1)) {
                    ball.setYDir(1);
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
                if (brick[i][j].hitLeft(x1, y1)) {
                    xSpeed = -xSpeed;
                    ball.setXDir(xSpeed);
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
                if (brick[i][j].hitRight(x1, y1)) {
                    xSpeed = -xSpeed;
                    ball.setXDir(xSpeed);
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
                if (brick[i][j].hitTop(x1, y1)) {
                    ball.setYDir(-1);
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
            }
        }
    }

    private void checkIfOut(int y1) {
        if (y1 > PADDLE_Y_START + 10) {
            lives--;
            score -= 100;
            ball.reset();
            clock.reset(); //resets the clock when ball falls out
            repaint();
            stop();
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

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                brick[i][j].draw(g);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("Lives: " + lives, STATUS_LOCATION_X, getHeight() - (getHeight() / 10));
        g.drawString("Score: " + score, STATUS_LOCATION_X, getHeight()
                - (2 * (getHeight() / 10)) + 25);
        g.drawString("Level: " + level, STATUS_LOCATION_X, getHeight()
                - (3 * (getHeight() / 10)) + 50);

        if (lives == MIN_LIVES) {
            g.drawString("Press the Spacebar twice to play again.",
                    getWidth() / 5, getHeight() - 20);
        }
    }

    public void register(Observer observer) {
        observers.add(observer);
    }
    
    public void removeRegister(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(TIME_STEP);
        }
    }
    // Private class that handles gameplay and controls
    private class BoardListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                if (lives > MIN_LIVES) {
                    if (!isPaused) {
                        stop();
                    } else {
                        start();
                    }
                } else {
                    paddle.setWidth(getWidth() / 7);
                    lives = MAX_LIVES;
                    score = 0;
                    bricksLeft = MAX_BRICKS;
                    level = 1;
                    makeBricks();
                    isPaused = true;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 5; j++) {
                            brick[i][j].setDestroyed(false);
                        }
                    }
                }
            }
            if (key == KeyEvent.VK_LEFT) {
                paddle.setX(paddle.getX() - 50);
            }
            if (key == KeyEvent.VK_RIGHT) {
                paddle.setX(paddle.getX() + 50);
            }
        }
        
    }
    
}
