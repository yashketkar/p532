package com.p532.gamemaker.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.p532.gamemaker.util.Constants;

//Class definition
public class GameMaker extends JPanel implements Constants {

	// Main Constructor
	public GameMaker(int width, int height) {
	// Setting up the board
	super.setSize(width, height);
	this.setLayout(new BorderLayout());
	this.add(new EditorPanel(),BorderLayout.WEST);
	this.add(new ArenaPanel(),BorderLayout.CENTER);
	setFocusable(true);
}
	
}
