package lessons.dataTypes;

/* TOPIC: ArrayList
 *  */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class Lesson11 {

	public static void main(String[] args) {
		
		// You have to specify the list type <int>, <String> etc.
		ArrayList<String> names;
		
		names = new ArrayList<String>();
		
		// Add values to the end of the list
		names.add("John Smith");
		names.add("Mohamed Alami");
		names.add("Oliver Miller");
		
		// Insert a value at the index 2
		names.add(2, "Jack Ryan");
		
		// ArrayList has size() 
		// -> not length, since it is dynamically expanded/shortened
		for (int i = 0; i < names.size(); i++) {
			// Get value at index i and
			System.out.println(names.get(i));
		}
		
		// Replace value -> set(index_for_replacement, new_value)
		names.set(0,  "John Adams");
		
		// remove value -> remove(index_to_be_removed)
		names.remove(3);
		
		// Can be normally printed -> will be with square brackets []
		System.out.println(names);
		
		for (String name : names) {
			System.out.println(name);
		}
		
		// ---- Using Iterators ---- ---- ----
		/* Iterator is an object generated from a list/Array
		 * It has two methods -> hasNext() and next()
		 * Iterator enable programm to loop through list one item at a time.
		 * 
		 * */
		Iterator indivItems = names.iterator();
		
		while (indivItems.hasNext()) {
			System.out.println(indivItems.next());
		}
		
		ArrayList<String> nameCopy = new ArrayList<String>();
		ArrayList<String> nameBackup = new ArrayList<String>();
		
		// Add all items from names to nameCopy
		nameCopy.addAll(names);
		
		
		String paulYoung = "Paul Young";
		names.add(paulYoung);
		if (names.contains(paulYoung)) {
			System.out.println("Paul is here");
		}
		
		// containsAll -> checks if all elments in nameCopy exist in names 
		// CAREFUL: names has one extra element compared to nameCopy.
		// Therefore, nameCopy.containsAll(names) returns False.
		if (names.containsAll(nameCopy)) {
			System.out.println("Everything in nameCopy is in names.");
		}
		
		if(names.isEmpty()) {
			System.out.println("Arraylist is empty");
		} else {
			System.out.println("Arraylist is not empty");
		}
		
		
		// with toArray() an ArrayList can be turned into a normal Array
		Object[] moreNames = new Object[4];
		moreNames = nameCopy.toArray();
		
		System.out.println(moreNames.toString());
	}
	
}
