/*
 * Constants : Contains all the Constant values
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.util;

public interface Constants 
{
    // Window Size
    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 650;	

    // Lives
    public static final int MAX_LIVES = 5;
    public static final int MIN_LIVES = 0;

    // Ball
    public static final int BALL_WIDTH = 10;
    public static final int BALL_HEIGHT = 10;
    public static final int BALL_RIGHT_BOUND = 490;
    public static final int BALL_X_START = 320;
    public static final int BALL_Y_START = 320;

    // Paddle
    public static final int PADDLE_WIDTH = 70;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_RIGHT_BOUND = 430;
    public static final int PADDLE_X_START = 300;
    public static final int PADDLE_Y_START = 450;
    public static final int PADDLE_MIN = 35;
    public static final int PADDLE_MAX = 140;

    // Bricks
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 25;
    public static final int MAX_BRICKS = 50;
    public static final int NO_BRICKS = 0;
    public static final int BRICK_ROWS = 5;
    public static final int BRICK_COLUMNS = 10;
    
    //Directions
    String X_DIRECTION ="X";
    String Y_DIRECTION ="Y";

    // Clock (Also used to place Lives/Score/Level Labels)
    int CLOCK_LOCATION_X = 10, CLOCK_LOCATION_Y = WINDOW_HEIGHT / 2, TIME_STEP = 5;

    // code of action
	int GAME_END = -1, BRICK_COLLISION = -2, GAME_BEGIN = -3, GAME_LOST = -4, GAME_WIN = -5;
	
}