package lessons.oop;

import lessons.oop.lesson_14_materials.*;

/*TOPIC: Sub-classing & Polymorphism
 * */

public class Lesson14 {

	public static void main(String[] args) {
		Animal genericAnimal = new Animal();
		System.out.println(genericAnimal.getName());
		System.out.println(genericAnimal.getFavFood());
		
		Cat morris = new Cat("Morris", "Tuna", "Rubber Mous");
		System.out.println(morris.getName());
		System.out.println(morris.getToy());
		System.out.println(morris.getFavFood());
		System.out.println();
		
		/* Created new Animal object which is a cat
		 * 
		 * Why?
		 * That way acceptAnimal can be told it receives an Animal.
		 * It can operate on it like an Animal.
		 * Because Cat is a sub-class of Animal and has thus all 
		 * methods and fields of an Animal.
		 * I do not have to know if its a cat or any other sub-class.
		 * I know its an Animal and that is enough.
		 * 
		 * If I call an Animal-Method which is overridden in Cat
		 * -> the correct cat method will be resolved!
		 * 
		 * */
		Animal tabby = new Cat("Tabby", "Salmon", "Ball");
		acceptAnimal(tabby);
	}
	
	public static void acceptAnimal(Animal randAnimal) {
		System.out.println(randAnimal.getName());
		System.out.println(randAnimal.getFavFood());
		System.out.println();
		
		// If I call an Animal-Method which is overridden in Cat
		// -> the correct cat method will be resolved!
		// -> use cat method instead of Animal method
		randAnimal.walkAround();
		
		/* But the interpreter is not smart enough to find methods 
		 * which are only specified in Cat!
		 * The basis here is Animal-Class!
		 * If it does not exist in Animal-Class - it cannot look
		 * for it then in Cat class!!!
		 * 
		 * This will cause an error:
		 * randAnimal.getToy() -> exists only in Cat
		 * 
		 * What can I do?
		 * Cast it / Convert it to a Cat!
		 */
		Cat tempCat = (Cat) randAnimal;		 
		System.out.println(tempCat.getToy());  // Works! The Animal object was cast to a cat!

		// Alternative short method -> Cast-in-place
		System.out.println(((Cat)randAnimal).getToy());
		
		// You can also check if it is really a cat
		if (randAnimal instanceof Cat) {
			System.out.println(randAnimal.getName() + " is a cat");
			
			// However, you would still need to cast it to cat
			// for the Java interpreter. Otherwise, it still thinks its an Animal
			System.out.println(((Cat)randAnimal).getToy());
		}
		
		
	}
	
}
