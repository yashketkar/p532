package p532oop.brickout;

public interface Subject {
	
	public void register(Observer o);
	public void removeRegister(Observer o);
	public void notifyObservers();
}
