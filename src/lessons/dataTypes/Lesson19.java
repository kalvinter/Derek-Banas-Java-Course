package lessons.dataTypes;

import java.util.regex.*;

/* TOPIC: REGEX and Strings
 * 
 * */

public class Lesson19 {

	public static void main(String[] args) {
		String longString = " Derek Banas CA AK AKA 12345 PA (412)555-1212 johnsmith@hotmail.com 1-(412)555-1212 412-555-1234 412 555-1234";
		String strangeString = "  1Z aaa  **** *** {{{ {{ {";
		
		/* REGEX -> pattern matching.
		 * Define patterns to look for.
		 * [] -> what you search for is in quare brackets
		 * 	
		 * Examples:
		 * 	[A-Z] 		-> Every uppercase letter from A to Z
		 * 	[A-Za-z] 	-> Every uppercase and lowercase letter from A to z
		 * 	[0-9]		-> Every number
		 *  [^A-G]		-> ^ defines not. Everything that is not between A and G
		 *  \\s		-> Whitespace
		 *  \\S		-> Not whitespace
		 *  \\d			-> Digits
		 *  \\D			-> Not a digit
		 *  \\w			-> same as [A-Za-z0-9] - no whitespaces
		 *  \\W			-> not part of [A-Za-z0-9]
		 *  *			-> Characters that do not occur
		 * 	[]{n, m}	-> {min-length, max-length} math pattern that has this min- and max-length
		 * 	[]{n,}		-> {min-length, no max-length} math pattern that has this min- but there is no max-length
		 *  []{n}		-> {exact_length} match should have exactly this length
		 *  + 			-> Whatever proceeds must occur one or more times 
		 *  
		 *  The following signs must be backslashed -> . ^ * + ? {} [] () \ |
		 * */
		
		// Word that is 2 to 20 characters in length
		System.out.println("Word that is 2 to 20 characters in length");
		regexChecker("\\s[A-Za-z]{2,20}\\s", longString);
		/* WHy does it return Derek and not Banas? 
		 * It needs whitespace before and after the match
		 * The whitespace between Derek and Banas is counted to Derek
		 * Therefore Banas is missing a whitespace before it.
		 * */
		
		// Find ZIP-Code 5 digits long
		System.out.println("\nFind ZIP-Code 5 digits long");
		regexChecker("\\s\\d{5}\\s", longString);
		
		// States that begin with a C or A -> 2 Letter Codes
		System.out.println("\nStates that begin with a C or A");
		regexChecker("\\sA[A-Z]|C[A-Z]\\s", longString);

		System.out.println("\nOne or more times: {");
		regexChecker("(\\{+)", strangeString);
		
		// Warning this includes also spaces!
		System.out.println("\nAnything that is three or more times");
		regexChecker(".{3}", strangeString);
		
		// any email address
		System.out.println("\n Any Email Address");
		// any type of character @ any type of character . 2-4 characters for TLD
		regexChecker("[\\w._-]+@[\\w._-]+\\.[\\w]{2,4}", longString);
		
		
		// Get phone numbers with different formats
		// In String: (412)555-1212 1-(412)555-1212 412-555-1234 412 555-1234
		System.out.println("\nGet phone numbers with different formats");
		// ( |-)? -> it is whitespace or - OR might not even exist (?)
		// pattern? -> pattern might not exist
		regexChecker("([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4})", longString);
		
		regexReplace(longString);
		
	}
	
	public static void regexChecker(String theRegex, String str2Check) {
		// Transform regex-string to regex-pattern-object
		Pattern checkRegex = Pattern.compile(theRegex);
		
		// apply regex.pattern to string
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		
		// while regexMatcher finds matches (Generator)
		while (regexMatcher.find()) {
			// Make sure that there is information in the match being kicked out
			if (regexMatcher.group().length() != 0) {
				// .group() -> returns found patterns
				System.out.println(regexMatcher.group().trim());
			}
			
			System.out.println("Start index: " + regexMatcher.start());
			System.out.println("End index: " + regexMatcher.end());
		}
	}
	
	public static void regexReplace(String str2Replace) {
		// Goal: Replace all white spaces
		
		// First, create pattern
		Pattern replace = Pattern.compile("\\s+");
		
		/* Replace all characters, case-insensitive
		 * 		Pattern replace = Pattern.compile("\\[A-Z]+", Pattern.CASE_INSENSITIVE);
		 * // OR is equal to
		 * 		Pattern replace = Pattern.compile("\\[A-Za-z]+"); 
		*/
		
		// Second, apply pattern to Matcher - get all matches
		Matcher regexMatcher = replace.matcher(str2Replace.trim());
		
		// Third, execute replace with replaceAll
		System.out.println(regexMatcher.replaceAll(", "));
	}
	
}
