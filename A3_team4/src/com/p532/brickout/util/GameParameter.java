/*
 * GameParameter : Stores and handles all the game parameters (lives, score etc)
 * 
 * Created By:  Zhenghao, Abhijit, Ankit, Jay and Nishant
 * 
 *  Last Edited By: Ankit, Mrunal, Rohith, Nishant and Yash
 */

package com.p532.brickout.util;

public class GameParameter {

	// Initial Values for some important variables
	private int score, lives, bricksLeft, level;

	// The game
	private Thread game;

	// Paused state
	private volatile boolean isPaused;

	// The mode of the game (PLAY, LOAD, REPLAY, UNDO)
	private Mode mode;

	// set and get functions for variables
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getBricksLeft() {
		return bricksLeft;
	}

	public void setBricksLeft(int bricksLeft) {
		this.bricksLeft = bricksLeft;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Thread getGame() {
		return game;
	}

	public void setGame(Thread game) {
		this.game = game;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	// reset functions for variables
	
	public void resetScore(){
		
		score = 0;
	}
	
	public void resetLives(){
		
		lives = Constants.MAX_LIVES;
	}
	
	public void resetBricksLeft(){
		
		bricksLeft = Constants.MAX_BRICKS;
	}
	
	public void resetLevel()	{
		
		level = 1;
	}

	// increment and decrement functions for variables

	public void incrementScore(int amt)	{
		
		score += amt;
	}
	
	public void decrementScore(int amt)	{
		
		score -= amt;
	}
	
	public void decrementBricks(int amt)	{
		
		bricksLeft -= amt;
	}
	
	public void incrementBricks(int amt){
		
		bricksLeft += amt;
	}
	
	public void incrementLives(int amt)	{
		lives += amt;
	}
	
	public void decrementLives(int amt)	{
		
		lives -= amt;
	}
	
	public void incrementLevel(int amt)	{
		level += amt;
	}
	
	public void decrementLevel(int amt)	{
		level -= amt;
	}
}
