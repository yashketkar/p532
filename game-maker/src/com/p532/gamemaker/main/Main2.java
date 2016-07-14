package com.p532.gamemaker.main;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.p532.gamemaker.ui.GameMaker;
import com.p532.gamemaker.util.Constants;

public class Main2 implements Constants {

	// Variables
	private static JFrame frame;
	private static JComponent gamemaker;
	private static ImageIcon icon;

	public Main2() {
		URL iconURL = this.getClass().getResource("icon.png");
		// iconURL is null when not found
		icon = new ImageIcon(iconURL);

	}

	public static void main(String[] args) {
		// Set look and feel to that of OS
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Defining frame for the game
		frame = new JFrame("Game Maker 1.0");
		// frame.setIconImage(icon.getImage());
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamemaker = new GameMaker(WINDOW_WIDTH, WINDOW_HEIGHT);

		// Adding the main game maker user interface to the frame
		frame.getContentPane().add(gamemaker);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}