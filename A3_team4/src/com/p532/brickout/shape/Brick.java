/*
 * Brick : Handles the initialization and updation of Brick
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.p532.brickout.util.Constants;

public class Brick extends Structure implements Constants,Serializable 
{
	// Variables
    private boolean destroyed;

    // Constructor to initialize Brick using variables
    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setDestroyed(false);

    }
    
    // Constructor to initialize Brick using exisiting brick
    public Brick(Brick brick) {
        super(brick);
        this.setDestroyed(brick.isDestroyed());
    }

    // Draws a brick
    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }


    // Detect if the brick has been hit on its bottom, top, left, or right sides
    public boolean hitBottom(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y + height)
                ) {
            return true;
        }
        return false;
    }
    
    public boolean hitTop(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y)
                ) {
            return true;
        }
        return false;
    }

    public boolean hitLeft(int ballX, int ballY) {
        if ((ballY >= y) && (ballY <= y + height) && (ballX == x)
                ) {
            return true;
        }
        return false;
    }

    public boolean hitRight(int ballX, int ballY) {
        if ((ballY >= y) && (ballY <= y + height) && (ballX == x + width)
                ) {
            return true;
        }
        return false;
    }

    // Sets and checks if the brick has been destroyed
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
