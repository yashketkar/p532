/*
 * Structure : Default layout of shape, used by all files in shape package
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

public abstract class Structure implements Constants,Serializable 
{
    // Variables
    protected int x, y, width, height;
    protected Color color;

    // Structure constructor to initialize all variables
    public Structure(int x, int y, int width, int height, Color color) 
    {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setColor(color);
    }
    
    // Structure constructor to initialize from existing structure 
    public Structure(Structure structure) 
    {
        this(structure.x, structure.y, structure.width, structure.height, structure.color);
    }
    
    // set method for this class
    public void set(Structure struc) {
        setX(struc.x);
        setY(struc.y);
        setWidth(struc.width);
        setHeight(struc.height);
        setColor(struc.color);
    }

    // Draw a structure
    public void draw(Graphics g) 
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // set and get methods for variables
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Accessor methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}