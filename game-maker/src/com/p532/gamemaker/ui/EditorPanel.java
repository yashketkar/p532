package com.p532.gamemaker.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.p532.gamemaker.util.Constants;

public class EditorPanel extends JPanel implements Constants {
	JTextField[] fields;

	public EditorPanel() {

		super.setLayout(new BorderLayout());
		JPanel labelPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		this.add(labelPanel, BorderLayout.WEST);
		this.add(fieldPanel, BorderLayout.CENTER);
		
		JTextField name = new JTextField();
		name.setColumns(15);
		JLabel lab = new JLabel("Name", JLabel.RIGHT);
		lab.setLabelFor(name);
		labelPanel.add(lab);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(name);
		fieldPanel.add(p);
		
	}

}
