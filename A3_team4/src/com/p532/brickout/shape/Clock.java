/*
 * Clock : Defines and handles creation and updation of clock
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import com.p532.brickout.intf.Observer;

public class Clock  extends Structure implements Observer,Serializable 
{
	// Variables
    private static final int MILLISEC_TO_SEC = 1000;
    private int time;
    
    // Constructor to initialize clock from variables (inherited from Structure)
    public Clock(int x, int y, int width, int height, Color color) 
    {
        super(x, y, width, height, color);
        time = 0;
    }
    
    // Constructor to initialize clock from exisiting clock
    public Clock(Clock clock) 
    {
        super(clock);
        time = clock.time;
    }
    
    // Draws the clock
    @Override
    public void draw(Graphics g) 
    {
        g.setColor(color);
  
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g.setFont(newFont);
        
        // Calculating for minutes and seconds
        int mm = time / MILLISEC_TO_SEC / 60 % 60;
        int ss = time / MILLISEC_TO_SEC % 60;
        
        g.drawString(String.format("%02d:%02d", mm, ss), x, y-30);
        g.setFont(currentFont);
    }

    // Updates the clock
    @Override
    public void update(int timeStep) 
    {
        time = (time + timeStep) % (3600 * MILLISEC_TO_SEC);
    }
    
    public int getTime() {
        return time;
    }
}
