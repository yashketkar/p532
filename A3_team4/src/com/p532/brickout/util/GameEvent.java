
/*
 * GameEvent : User class used to store objects in ArrayDeque<events>
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.util;

import java.io.Serializable;

public class GameEvent implements Serializable
{
	// Basic components
	private int time, action;

	//Using Object class because it's easier to typecast
	Object eventObject;

	// set and get functions for the components of the class
	
	public Object getEventObject() 
	{
		return eventObject;
	}
	public void setEventObject(Object eventObject) 
	{
		this.eventObject = eventObject;
	}
	public int getTime() 
	{
		return time;
	}

	public void setTime(int time) 
	{
		this.time = time;
	}

	public int getAction() 
	{
		return action;
	}

	public void setAction(int action) 
	{
		this.action = action;
	}

	public GameEvent(int time, int action) 
	{
		this.time = time;
		this.action = action;
	}
	
}
