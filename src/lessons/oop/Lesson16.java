package lessons.oop;

import lessons.oop.lesson_16_materials.*;

/*TOPIC: Object Class
 * 
 * All Objects in Java inherit from the Object Class
 * 
 * */

public class Lesson16 {
	
	public static void main(String[] args) {
		
		// Java will see superCar as Object - but call the Vehicle constructor
		Object superCar = new Vehicle();
		
		/* superCar.getSpeed() -> is not allowed. 
		 * Is looked upon as an Object.
		 * Only way to access vehicle methods / fields => cast 
		 * */
		System.out.println(((Vehicle)superCar).getSpeed());
		
		Vehicle superTruck = new Vehicle();

		/* Upon creation, every object gets a unique identifier -> a hashcode
		 * equals compares the hashcodes of both objects
		 * */
		System.out.println(superCar.equals(superTruck));
		System.out.println(superCar.hashCode());  // get superCar's unique HashCode
		
		// Print the object's class
		// returns Vehicle even though it was created as Object
		System.out.println(superCar.getClass());
		
		// Gets the class -> then gets the classes's getName-function (not the one specified in the class by us)
		System.out.println(superCar.getClass().getName());
		
		if (superCar.getClass() == superTruck.getClass()){
			System.out.println("The same");
		}
		
		// Get superclass name by accessing class first
		System.out.println(superCar.getClass().getSuperclass());  // will return Crashable
		
		// Usually default toString from object is not useful -> it is often overriden
		System.out.println(superCar.toString());
		
		int randNum = 100;
		System.out.println(Integer.toString(randNum));
		System.out.println();

		
		/* Cloning objects 
		 * 
		 * Making a copy that is identical but detached
		 * meaning changes in the original do not affect the clone.
		 * 
		 * Clonable objects must implement the interface Clonable
		 * 
		 * */
		
		superTruck.setWheel(6);
		// Why cast to a vehicle? Because clone() returns an Object
		Vehicle superTruck2 = (Vehicle) superTruck.clone();
		
		// Print wheels for both vehicles -> they are the same. 
		// Supertruck2 did not get the default wheel value
		System.out.println(superTruck.getWheel());
		System.out.println(superTruck2.getWheel());
		System.out.println();
		
		// Show that they are different objects -> compare hashCodes
		System.out.println(superTruck.hashCode());
		System.out.println(superTruck2.hashCode());
		
		/* CAREFUL: If you have an object inside of another object
		 * 
		 * E.g. Object A has SubObject B
		 * 		=> Only Object A is cloned and not SubObject B as well
		 * */ 
		
	}
}
