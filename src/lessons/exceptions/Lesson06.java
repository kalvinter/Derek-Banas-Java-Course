package lessons.exceptions;

/* Most Common Exceptions: 
 *  import java.lang.RuntimeException;  // runtime exception
 *  import java.lang.Exception;  // Compiler checks for these base-exceptions
 * 
 * import java.lang.ClassNotFoundException;
 * import java.lang.IndexOutOfBoundsException;
 * import java.lang.ArithmeticException;
 * import java.lang.IllegalArgumentException;
 * 
 * import java.util.InputMismatchException;
 * 
 * import java.io.FileNotFoundException;
 * import java.io.IOException;
 * */

import java.lang.ArithmeticException;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Lesson06 {

	static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		divideByZero(2);
		
		System.out.print("How old are you? ");
		int age = checkValidAge();
		
		if (age != 0) {
			System.out.println("You are " + age + " years old");
		}
		
		getAFile("./NonExistingFile.txt");
		
		try {
			getAFileThrowingException("./NonExistingFile.txt");
			
		} catch (IOException | InputMismatchException e) {
			// Side note: InputMismatchException makes no sense here 
			// -> just here to demonstrate how to catch two in one block
			// Handle multiple exceptions in one block -> separate them by a |
			System.out.println("ERROR: An IO-Exception occurred!");
			
		}
	}
	
	public static void divideByZero(int a) {
		try {
			int b = a / 0;
			
		} catch (ArithmeticException e) {
			System.out.println("ERROR: Division by Zero!");
			
			// Event e has several methods with details on the exception
			System.out.println(e.getMessage());
			// e.printStackTrace();
			System.out.println(e.toString());  // toString prints the normal complete message

		} 
		
	}
	
	public static int checkValidAge() {
		try {
			return userInput.nextInt();
		} catch (InputMismatchException e) {
			
			userInput.next(); // flushes Input.
			System.out.println("This isn't a whole number!");
			return 0;
		}
	}
	
	public static void getAFile(String fileName) {
		try {
			FileInputStream file = new FileInputStream(fileName);	
			
		} catch (FileNotFoundException  e) {
			System.out.println(e.getMessage());
			System.out.println("Sry, could not find that File");
			
		} catch (IOException e) {  // A less specific error. Always catch the most specific exceptions first
			System.out.println(e.getMessage());
			System.out.println("Sry, unknown IO-Error occurred");
			
		} catch (Exception e) {  // catches every single error.
			System.out.println("Some error occurred");
			
		} finally {
			// This block is always run, whether an exception was catched or not
			// useful for closing files or database connections
			System.out.println("Final-Block ran.");
		}
		
	}
	
	// If an exception should be handled in the function calling this function
	// add "throws" with the exception names to the method definiion
	// If these two exceptions occur, they are thrown to the method above
	// If they are then not dealt with in the method above, the exceptions will crash the programme
	public static void getAFileThrowingException(String fileName) throws IOException, FileNotFoundException {
		
		FileInputStream file = new FileInputStream(fileName);	
				
	}
	
}
