package com.p532.gamemaker.ui;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DragPanel extends JPanel {
	
	private int panelPos;
	public DragPanel() {
		super();

	}

	public int getPanelPos() {
		return panelPos;
	}

	public void setPanelPos(int panelPos) {
		this.panelPos = panelPos;
	}

	public JLabel draggingLabel;
}