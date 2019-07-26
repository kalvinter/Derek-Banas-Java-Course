package lessons.oop;

/*TOPIC: Interfaces (Abstract Classes below)
 * 
 * In Java sub-classes can only inherit from ONE super-class.
 * Otherwise, Java would not know how to resolve super-relations
 * 
 * Thats why interfaces exist!
 * 
 * Interfaces basically, just specify a set of variables and 
 * methods that have to be implemented.
 * Interfaces add additional functionality to a class.
 * They are used to ensure that certain methods exist!
 * 
 * Basically: 
 * 		Classes are Nouns
 * 		Interfaces are Adjectives
 * 
 * Example: 
 * 		Class: Car
 * 		Interface: Drivable
 * 		=> The car has all methods of drivable -> it is drivable
 * 
 * In an interface
 * 		All fields are FINAL
 * 		All methods are abstract (have to be implemented)
 * 
 * */

/*TOPIC: Abstract Classes
 * 
 * Difference between abstract classes and interfaces?
 * 		=> In abstract class not all methods have to be implemented
 * 
 * 
 * */

import lessons.oop.lesson_15_materials.*;

public class lesson15 {

	public static void main(String[] args) {
		Vehicle car = new Vehicle(4, 100.00);
		
		System.out.println("Cars num wheels: " + car.getWheel());
		System.out.println("Cars max speed: " + car.getSpeed());
		
		car.setCarStrength(10);
		System.out.println("Strength " + car.getCarStrength());
	}
	
}
