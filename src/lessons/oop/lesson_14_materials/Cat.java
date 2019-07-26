package lessons.oop.lesson_14_materials;

public class Cat extends Animal {
	// Cat extends Animal -> inherits all fields and methods from Animal
	
	// Cat can add new attributes and methods
	public String favToy = "Yarn";
	
	public void playWith() {
		System.out.println("Yeah " + favToy);
	}
	
	// Cat can override existing methods of Animal IF they are not final
	public void walkAround() {
		System.out.println(this.getName() + " stalks around.");
	}
	
	public String getToy() {
		return this.favToy;
	}
	
	public Cat(String name, String favFood, String favToy) {
		// Call Animal constructor -> handles attributes already specified
		// in Animal
		super(name, favFood);
		
		// Just take care of new fields specified for Cat
		this.favToy = favToy;
	}
	
	
}
