/*
 * Subject : Observer interface  to define the functions to be inherited
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.intf;

public interface Subject 
{	
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObservers(int timeStep);
}
