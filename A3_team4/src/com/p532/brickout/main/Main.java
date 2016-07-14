/*
 * Main : Main method for the game, draws the frame where game is played
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

//import com.p532.breakout.gameui.Board;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.util.Constants;

public class Main implements Constants 
{
    // Variables
    private static JFrame frame;
    private static Board board;
    private static Dimension dim;

    // Build and run the game
    public static void main(String[] args) 
    {
        // Set look and feel to that of OS
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Defining frame for the game
        frame = new JFrame("Brick Breaker 4.0");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates a new board
        board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // Adding the board to the game frame
        frame.getContentPane().add(board);
        
        // Place frame in the middle of the screen
        try 
        {
        	// Setting the dimension and frame location according to screen size
    		dim = Toolkit.getDefaultToolkit().getScreenSize();
    		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
}