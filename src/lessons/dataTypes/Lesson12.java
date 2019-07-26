package lessons.dataTypes;

/* TOPIC: LinkedLists
 *  */

import java.util.Arrays;
import java.util.LinkedList;

public class Lesson12 {

	public static void main(String[] args) {
		/* LinkedList -> very good at adding and deleting items
		 * 			  -> Not good at accessing specific elements
		 * 
		 * Main difference -> 
		 * 		ArrayList has an array to organize elements. Access
		 * 			via index is possible. Adding/deleting means also adjusting this organizing array
		 *  
		 * 		LinkedLists, however, only add remove elements in RAM with pointers to the 
		 * 			predecessor and successor. There is no second organizing array which keeps track 
		 * 			of the individual items' position. Thus, Accessing the fourth element means going through
		 * 			all elements (through each successor pointer) until the fourth is reached.
		 * 
		 * 
		 * */
		
		// no restriction regarding the data type contained in the list -> discouraged
		LinkedList linkedListOne = new LinkedList();
		
		// Restriction of the data type in the list to String
		LinkedList<String> names = new LinkedList<String>();
		
		names.add("Ahmed Benanni");
		names.add("Ali Syed");
		
		names.addLast("Nathan Marting");  // Same as add
		names.addFirst("Joshua Smith");
		
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();
		
		// insert add specific position
		names.add(0, "Noah Peters");
		
		// Replace a value at a specific position
		names.set(2, "Paul Newman");
		
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();
		
		// Remove item by index or by value
		names.remove(4);
		names.remove("Joshua Smith");
		
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();
		
		// get specific value by index -> careful: throws NoSuchElement-Exception
		System.out.println(names.get(0));
		
		// get last value
		System.out.println(names.getLast());
		
		// Copy list by handing it to the linked-list constructor
		LinkedList<String> nameCopy = new LinkedList<String>(names);
		
		System.out.println("\nnameCopy: " + nameCopy.toString());
		
		if (names.contains("Noah Peters")) {
			System.out.println("Noah is here");
		} else {
			System.out.println("Noah is not here");
		}
		
		if (names.containsAll(nameCopy)) {
			System.out.println("Collections are the same!");
		}
		
		// Get index of value. Must be the exactly same value
		System.out.println("Ali Index at: " + names.indexOf("Ali Syed"));
		
		System.out.println("List Empty: " + names.isEmpty());
		
		System.out.println("How many: " + names.size());
		
		// Gets first element in LinkedList. Returns null if empty 
		// If you try to get() a value but nothing exists (empty list) an error is thrown
		System.out.println("Look without Error: " + names.peek());
		
		// poll() -> get first element, remove it from list and return it
		System.out.println("Remove first Element: " + nameCopy.poll());
		
		// pollLast() -> get last element, remove it from list and return it
		System.out.println("Remove first Element: " + nameCopy.pollLast());
		
		
		// Add value as first element
		nameCopy.push("Noah Peters");
		
		for (String name : nameCopy) {
			System.out.println(name);
		}
		System.out.println();
		
		// Remove first element
		nameCopy.pop();
		
		for (String name : nameCopy) {
			System.out.println(name);
		}
		System.out.println();
		
		Object[] nameArray = new Object[4];
		nameArray = names.toArray();
		
		System.out.println(Arrays.toString(nameArray));
	}
	
	
}
