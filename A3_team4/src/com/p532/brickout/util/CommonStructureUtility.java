/*
 * BoardListener : Handles all the key and button press events
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.util;

import java.awt.Color;

import com.p532.brickout.shape.Brick;

public class CommonStructureUtility 
{
	// Function makes bricks from the given size
	public static void makeBricks(Brick bricks[][])	
	{	
		for (int i = 0; i < Constants.BRICK_COLUMNS; i++) 
		{
			for (int j = 0; j < Constants.BRICK_ROWS; j++) 
			{
				bricks[i][j] = new Brick((i * Constants.BRICK_WIDTH + 80), 
						((j * Constants.BRICK_HEIGHT) + (Constants.BRICK_HEIGHT / 2)),
						Constants.BRICK_WIDTH - 5, Constants.BRICK_HEIGHT - 5, Color.blue);
			}
		}
	}
	
	// Function copies bricks from the existing bricks[][] size
	public static Brick[][] copyBricks(Brick[][] bricks) 
	{
		Brick[][] copy = new Brick[bricks.length][bricks[0].length];
		
		for (int i = 0; i < bricks.length; i++) 
		{
			for (int j = 0; j < bricks[0].length; j++) 
			{
				copy[i][j] = new Brick(bricks[i][j]);
			}
		}
		return copy;
	}
}
