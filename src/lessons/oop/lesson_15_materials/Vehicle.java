package lessons.oop.lesson_15_materials;

// extends needs to be before implements
// Extends -> super class
// Implements -> Interface(s)
public class Vehicle extends Crashable implements Drivable{
	
	int numOfWheel = 2;
	double theSpeed = 0;
	int carStrength = 0;
	
	public int getWheel() {
		return this.numOfWheel;
	}
	
	public void setWheel(int numWheels) {
		this.numOfWheel = numWheels;
	}
	
	public double getSpeed() {
		return this.theSpeed;
	}
	
	public void setSpeed(double speed) {
		this.theSpeed = speed;
	}
	
	public Vehicle(int wheels, double speed) {
		this.numOfWheel = wheels;
		this.theSpeed = speed;
	}
	
	public void setCarStrength(int carStrength) {
		this.carStrength = carStrength;
	}
	
	public int getCarStrength() {
		return this.carStrength;
	}
	
}
