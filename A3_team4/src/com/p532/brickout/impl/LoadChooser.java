/*
 * LoadChooser : Shows the files available for load, and lets user choose in a pop up
 * 
 * Created By:  Ankit, Mrunal, Rohith, Nishant and Yash
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

import com.p532.brickout.gameui.Board;

public class LoadChooser 
{
	// Variable to fileName chosen
	public String filetoDeserialize;

	public String displayFiles() 
	{
		// Creating a panel and ButtonGroup to display Files
		JPanel radioPanel = new JPanel();
		ButtonGroup bg = new ButtonGroup();

		// Array to retrieve all files in given directory
		ArrayList<File> listOfFiles = new ArrayList<File>(Arrays.asList(Board.saveFolder.listFiles()));

		// Array to store all radioButtons
		ArrayList<JRadioButton> radioBtn = new ArrayList<JRadioButton>();
		
		// for loop goes through all the files and filters them based on user-defined extension
		for (File file : listOfFiles) {
			if (file.getName().toLowerCase().endsWith("a3")) {
				// All files with this extension are displayed in console
				System.out.println("file name is: " + file.getName());
				// Radio Button is created for each file(s) found with this extension
				radioBtn.add(new JRadioButton(file.getName()));
			}
		}

		// All radio buttons are added to the panel and button group
		for (JRadioButton rb : radioBtn) {
			radioPanel.add(rb);
			bg.add(rb);
		}

		// Creating a optionDialog
		String option[] = {"Load", "Cancel"};
		int selectedBtn = JOptionPane.showOptionDialog(null, radioPanel, "Choose the file you want..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
		
		if(selectedBtn == JOptionPane.YES_OPTION)
		{
			System.out.println("Loading..");
			// Check which button is selected and set the filetoDeserialize accordingly
			for (JRadioButton rb : radioBtn) {
				if (rb.isSelected()) {
					filetoDeserialize = rb.getText();
				}
			}
			// Displaying the chosen file
			System.out.println("file selected is " + filetoDeserialize);
		}
		// Setting the filetoDeserialize as cancel to handle it in Board
		else
		{			
			System.out.print("CancelMe");
		    filetoDeserialize="cancel";
		}
		return filetoDeserialize;
	}
}
