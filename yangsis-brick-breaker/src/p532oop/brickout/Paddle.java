package p532oop.brickout;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Structure {
    // Variables
    private int xSpeed;

    // Constructor
    public Paddle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    // Draws the paddle
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Places the paddle back in starting position at center of screen
    public void reset() {
        x = PADDLE_X_START;
        y = PADDLE_Y_START;
    }

    // Checks if the ball hit the paddle
    public boolean hitPaddle(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width)
                && ((ballY >= y) && (ballY <= y + height))) {
            return true;
        }
        return false;
    }

}