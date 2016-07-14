/*
 * Ball : Handles the initialization and updation of Ball
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.p532.brickout.intf.Observer;

public class Ball extends Structure implements Observer,Serializable 
{
    // Variables
    public final int INITIAL_X_DIR = 1, INITIAL_Y_DIR = -1;
    private boolean onScreen;
    private int xDir = INITIAL_X_DIR, yDir = INITIAL_Y_DIR;

    // Constructor to initialize Ball using variables
    public Ball(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setOnScreen(true);
    }

    // Constructor to initialize Ball using existing ball
    public Ball(Ball ball) {
        super(ball);
        xDir = ball.xDir;
        yDir = ball.yDir;
    }

    // set method for ball
    public void set(Ball ball) {
        super.set(ball);
        xDir = ball.xDir;
        yDir = ball.yDir;
    }

    // Draw the ball
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    // Resets the ball to original position at center of screen
    public void reset() {
        x = BALL_X_START;
        y = BALL_Y_START;
        xDir = INITIAL_X_DIR;
        yDir = INITIAL_Y_DIR;
    }

    // set and get methods for Ball
    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public void setYDir(int yDir) {
        this.yDir = yDir;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    // Accessor methods
    public int getXDir() {
        return xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public boolean isOnScreen() {
        return onScreen;
    }

    // Updates the ball
    @Override
    public void update(int timeStep) {
        if (timeStep > 0) {
            x += xDir;
            y += yDir;
        } else if (timeStep < 0) {
            x -= xDir;
            y -= yDir;
        }

    }
    
    // Functions to reverse ball movement while Undo
    public void reverseMove() {
        reverseXDir();
        reverseYDir();
    }

    public void reverseXDir() {
        setXDir(-getXDir());
    }

    public void reverseYDir() {
        setYDir(-getYDir());
    }
}
