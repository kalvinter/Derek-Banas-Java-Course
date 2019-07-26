package lessons.oop.lesson_16_materials;

public interface Drivable {

	// CAREFUL: All fields in an interface are FINAL!
	double PI = 3.14;
	
	/* 'public abstract' can be omitted.
	 * Methods in interfaces are always public abstract
	 * 
	 *  Every method that is implemented must be at least
	 *  have the same access level or higher. 
	 *  They can't be more restrictive.
	 *  Public abstract methods have to be implemented as public methods
	 * 
	 * */
	int getWheel();
	
	void setWheel(int numWheels);
	
	double getSpeed();
	
	void setSpeed(double speed);
	
}
