/*
 * GameCommand : Interfact with functions to execute and undo commands
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.intf;

public interface GameCommand {

	void executeCommand();
	void undo();
}
