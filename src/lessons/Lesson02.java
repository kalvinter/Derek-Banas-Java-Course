package lessons;  // has to be on the first line!

import java.util.Scanner;


public class Lesson02 {
	
	// create a new instance -> use the new keyword
	// Scanner constructor needs an input channel as argument. System.in is the keyboard
	static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		// println -> print String + \n
		// print -> print String and cursor stays in the same line
		System.out.print("Enter a number");
		
		/* hasNextInt() -> If the next thing typed into the keyword 
		 *  is an integer (no decimal places) -> execute code
		 *  This can be used to cause different responses based on the type of user-input
		 *  e.g. hasNextInt, hasNextDouble() etc. 
		 *  */
		if (userInput.hasNextInt()){
			// nextInt() -> take the input and try to interpret it as integer
			int numberEntered = userInput.nextInt();
			System.out.println("Yey! You entered: " + numberEntered);
			
			int numEnteredTimesTwo = numberEntered * 2;
			System.out.println(numberEntered + " + " + numberEntered + " = " + numEnteredTimesTwo);
			
			int numEnteredMinusTwo = numberEntered - 2;
			System.out.println(numberEntered + " - 2 = " + numEnteredMinusTwo);
			
			int numEnteredTimesSelf = numberEntered * numberEntered;
			System.out.println(numberEntered + " * " + numberEntered + " = " + numEnteredTimesSelf);
			
			int numEnteredRemainder = numberEntered % 2;
			System.out.println(numberEntered + " % 2 = " + numEnteredRemainder);
			
			// Shorthands for increments/decrements
			numberEntered += 2;
			numberEntered -= 2;
			
			numberEntered++;  // numberEntered = numberEntered + 1
			numberEntered--;  // numberEntered = numberEntered - 1 
			
			int numberABS = Math.abs(numberEntered);  // Absolute Value
			
			int whichIsBigger = Math.max(5,  7);  // returns the higher of the two arguments
			System.out.println(Math.max(5.78F, 8.82D));  // works with float, double
			System.out.println(Math.max(9, 8.82D));  // works with mixed types
			
			double numSqrt = Math.sqrt(5.23);  // Square-root
			
			int numCeiling = (int) Math.ceil(5.23);  // ceil -> round number up
			int numFloor = (int) Math.floor(5.23);  // floor -> round down
			int numRound = (int) Math.round(5.23);  // Standard rounding (<0.5 down, >05. up)
			int numRoundTwo = (int) Math.round(5.63);
			
			System.out.println(numCeiling + " " + numFloor + " " + numRound + " " + numRoundTwo);
			
			int randomNumber = (int) (Math.random() * 11);  
			// Math.random() -> get random number between .0 to .9999
			System.out.println(randomNumber);
			
		} else {
			System.out.println("Ney! That was not an Integer!");

		};
		
		
	};
	
}
