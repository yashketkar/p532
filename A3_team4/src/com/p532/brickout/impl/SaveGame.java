/*
 * SaveGame : Creates a pop up for user input of fileName  and returns fileName for saving file
 * 
 * Created By:  Ankit, Mrunal, Rohith, Nishant and Yash
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class SaveGame 
{
	public String savePopUp() 
	{
		// Creating current data and time as default text in inputDialog
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String defaultText = (String) dateFormat.format(date);
		
		Object response = JOptionPane.showInputDialog(null, "Enter the saveFile name", "Save File",
				JOptionPane.YES_NO_OPTION, null, null, defaultText);
		// Printing fileName to make sure what it's saved as
		System.out.println("Response: " + response);

		return (String) response;
	}
	
}
