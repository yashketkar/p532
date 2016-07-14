/*
 * SerializeSave : Handles the save to file functionality
 * 
 * Created By:  Ankit, Mrunal, Rohith, Nishant and Yash
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.p532.brickout.gameui.Board;

public class SerializeSave 
{	
	// Variable fileName
	String fileName;
	
	// Constructor of SerializeSave, initializes fileName
	public SerializeSave(String name)
	{
		fileName = name;
	}
	
	// Function takes event array and stores it into fileName.a3 in saveFolder
	public <T> void saveIt(final T objectToSerialize)
	{
		if (objectToSerialize == null) {
			throw new IllegalArgumentException("Object to be serialized cannot be null.");
		}
		
		try 
		{
			// Saving to saveFolder/fileName.a3
			FileOutputStream fos = new FileOutputStream(Board.saveFolder + "/" + fileName + ".a3");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objectToSerialize);
			// To keep track of control flow, message is printed
			System.out.println("Serialization of Object " + objectToSerialize + " completed.");
		}
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		}
	}
}
