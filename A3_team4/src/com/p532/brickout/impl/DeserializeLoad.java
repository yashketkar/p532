/*
 * DeserializeLoad : Handles the file loading from given fileName
 * 
 * Created By:  Ankit, Mrunal, Rohith, Nishant and Yash
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.p532.brickout.gameui.Board;

public class DeserializeLoad 
{
	// Variable fileName
	String fileName;
	
	// Constructor to initialize DeserializeLoad with the given fileName
	public DeserializeLoad(String name)
	{
		fileName = name;
	}

	// load opens the fileName file and returns the object after deserialization
	public <T> T loadIt() 
	{
		if (fileName == null) {
			throw new IllegalArgumentException("Cannot deserialize from a null filename.");
		}
		
		T objectOut = null;
		try 
		{
			// Loading from saveFolder/fileName
			FileInputStream fis = new FileInputStream(Board.saveFolder + "/" + fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			objectOut = (T) ois.readObject();
			// To keep track of control flow, message is printed
			System.out.println("Deserialization of Object " + objectOut + " is completed.");
		} 
		catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return objectOut;
	}
}
