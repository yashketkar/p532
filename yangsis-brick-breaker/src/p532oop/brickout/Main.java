package p532oop.brickout;

import javax.swing.*;
import java.awt.*;

//Class definition
public class Main implements Constants {
    // Variables
    private static JFrame frame;
    private static Board board;
    private static Dimension dim;
    // Build and run the game
    public static void main(String[] args) {
        // Set look and feel to that of OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new JFrame("Brick Breaker 1.0");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);

        frame.getContentPane().add(board);
        
        // Place frame in the middle of the screen
        try {
    		dim = Toolkit.getDefaultToolkit().getScreenSize();
    		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        } catch (Exception e) {
            e.printStackTrace();
        
        }

        frame.setVisible(true);
    }
}