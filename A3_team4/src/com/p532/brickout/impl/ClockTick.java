/*
 * ClockTick : Handles executeCommand and undo for Clock
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 * Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.impl;

import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.util.Constants;

public class ClockTick implements GameCommand 
{
	// Variable of clock
	private Clock clock;
	
	// Constructor for ClockTick initializes using existing clock
	public ClockTick(Clock clock) {
		super();
		this.clock = clock;
	}

	// Overriden inherited function to executeCommand
	// Function to update the clock using TIME_STEP
	@Override
	public void executeCommand() {
		 clock.update(Constants.TIME_STEP);
	}

	// Overriden inherited function to undo
	// Function reverses the clock using update and negative value of TIME_STEP
	@Override
	public void undo() {
		 clock.update(-Constants.TIME_STEP);
	}
}
