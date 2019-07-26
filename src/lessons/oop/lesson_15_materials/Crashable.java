package lessons.oop.lesson_15_materials;

public abstract class Crashable {

	// normal field - not final, can be changed by subclass
	boolean carDrivable = true;
	
	// abstract classes can contain normal methods that are inherited
	public void youCrashed() {
		this.carDrivable = false;
	}
	
	// Abstract methods have to have a full signature (public abstract)
	public abstract void setCarStrength(int carStrength);
	
	public abstract int getCarStrength();
	
}
