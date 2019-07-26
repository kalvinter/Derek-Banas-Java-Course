package lessons.oop.lesson_16_materials;

/* extends needs to be before implements
 * Extends -> super class
 * Implements -> Interface(s)
 * 
 * Clonable -> interface to enable object cloning
 * */

public class Vehicle extends Crashable implements Drivable, Cloneable{
	
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
	
	public Vehicle() {
		
	}
	
	public void setCarStrength(int carStrength) {
		this.carStrength = carStrength;
	}
	
	public int getCarStrength() {
		return this.carStrength;
	}
	
	public String toString() {
		return "Num of wheels: " + this.numOfWheel;
	}
	
	public Object clone() {
		/* IMPORTANT: You have to catch this exception.
		 * It should never occur BUT Java demands this 
		 * try/catch construct!
		 * */ 
		Vehicle car;
		
		try {
			car = (Vehicle) super.clone();
			
		} catch (CloneNotSupportedException e) {
			return null;
			
		}
		
		return car;
	}
	
}
