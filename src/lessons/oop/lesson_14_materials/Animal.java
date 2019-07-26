package lessons.oop.lesson_14_materials;

public class Animal {
	
	// private -> only this class can access it
	private String name = "Animal";
	
	protected String favFood = "Food";
	
	// protected -> only class and sub-class can access it
	// final -> no sub-class can overwrite this method
	protected final void changeName(String newName) {
		this.name = newName;
	}
	
	// public -> every class from every package can access it
	public final String getName() {
		return this.name;
	}
	
	public final String getFavFood() {
		return this.favFood;
	}
	
	public void eatStuff() {
		System.out.println("Yum " + favFood);
	}
	
	public void walkAround() {
		System.out.println(this.name + " walks around.");
	}
	
	public Animal() {
		
	}
	
	public Animal(String name, String favFood) {
		this.changeName(name);
		this.favFood = favFood;
	}
	
	
	
}
