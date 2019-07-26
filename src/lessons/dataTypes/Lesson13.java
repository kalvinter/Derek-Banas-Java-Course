package lessons.dataTypes;

import java.util.Arrays;

/* TOPIC: Strings, StringBuilder
 *  */


public class Lesson13 {

	public static void main(String[] args) {
		// QUOTES! apostrophes are for characters (char)
		String randomString = "I'm just a randomString";
		
		// Add quotes by escaping them with \
		String gotToQuote = "He said: \"I'm here\"";
		
		System.out.println(randomString + " " + gotToQuote);
		
		int numTwo = 2;
		
		// primitives are automatically transformed to String in print-statement
		System.out.println(randomString + " " + numTwo);
		
		String uppercaseStr = "BIG";
		String lowercaseStr = "big";
		
		if ( uppercaseStr.equals(lowercaseStr)) {
			System.out.println("equals(): They are equal");
		}
		
		// now they are equal -> case is ignored
		if ( uppercaseStr.equalsIgnoreCase(lowercaseStr)) {
			System.out.println("equalsIgnoreCase(): They are equal");
		}
		
		String letters = "abcde";
		String moreLetters = "fghijk";
		
		// Get letter at index
		System.out.println("2nd Char: " + letters.charAt(1));
		
		// if both the strings are equal lexicographically => returns 0
		// If letters > moreLetters => returns positive
		// If letters < moreLetters => returns negative number
		System.out.println(letters.compareTo(moreLetters));
		
		// Returns bool -> checks if string contains substring
		System.out.println(letters.contains("abc"));
		
		// Returns bool -> checks if string ends with substring
		System.out.println(letters.endsWith("de"));
		
		// Return index where substring starts -> returns -1 if nothing is found
		System.out.println(letters.indexOf("bc"));
		
		// Return index where substring starts. 
		//The provided int is the index where it should start looking for the sub string
		System.out.println(letters.indexOf("bc", 3));
		
		// lastIndexOf() returns index but starts searching from the end of the string
		System.out.println("abjkba".indexOf("b"));  // returns 1
		System.out.println("abjkba".lastIndexOf("b"));  // returns 4
		
		// Get String length via length()-function
		System.out.println("Length of letter: " + letters.length());
		
		// Replace substring by another substring
		System.out.println("Replace: " + letters.replace("bc", "cb"));
		
		// Split string into array -> provide at which character string should be split
		String[] letterArray = "Herman;Schuhman;Park".split(";");
		System.out.println(Arrays.toString(letterArray));
		
		// Split string into array -> one slot per character
		char[] charArray = letters.toCharArray();
		
		System.out.println(Arrays.toString(charArray));
		
		// Get substring out of string by specifying start and end-index
		System.out.println(letters.substring(1, 4));
		
		System.out.println(letters.toUpperCase());
		System.out.println(letters.toLowerCase());
		
		String randString = "       abfglasd   ";
		
		// trim() -> Remove all whitespace in string
		System.out.println(randString.trim());
		
		
		/* Strings are immutable:
		 * 		this means, that after every edit, replace, concat a new String is created at 
		 * 		a new place in Memory. This is expensive
		 * 
		 * 		=> If you have a lot of edits and changes, use a StringBuilder instead.
		 * 		StringBuilder takes a larger block of memory and builds strings in there.
		 * 		Building is faster since it does not need to rebuild the strings from scratch
		 * 		after every edit.
		 * */
		
		StringBuilder randSB = new StringBuilder("A random Value");
		
		System.out.println(randSB);
		System.out.println(randSB.append(" again "));
		System.out.println(randSB.delete(15, 21));
		
		// Show pre-allocated size of StringBuilder memory in Characters
		System.out.println(randSB.capacity());
		
		// Increase the pre-allocated size
		randSB.ensureCapacity(60);
		
		// Show current size of string
		System.out.println(randSB.length());
		
		// Trim size of capacity down to actual size of string
		randSB.trimToSize();
		System.out.println(randSB.capacity());
		
		// Insert a substring at a specific index
		System.out.println(randSB.insert(1, "another"));
		
		// Transform it to a normal String
		String oldSB = randSB.toString();
		
		
	}
	
}
