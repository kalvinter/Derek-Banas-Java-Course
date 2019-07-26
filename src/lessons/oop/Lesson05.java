package lessons.oop;

import java.util.Scanner;

public class Lesson05 {
	
	static int randomNumber;
	
	static Scanner userInput = new Scanner(System.in);
	
	static double myPi = 3.14159;  // static =  class variable
	
	
	public static void main(String[] args) {
		printPi(1, 2);
		System.out.println("Global: " + myPi);
		
		System.out.println(addThem(1, 2));
		
		int d = 5;
		tryToChange(d);
		System.out.println("Global d = " + d);  
		// the value of global d has not changed
		// global d was passed as a value - not the variable reference - to tryToChange()
		// In tryToChange a new local variable d was created and changed.
		
		System.out.println(getRandomNum());
		int guessResult = 1;
		int randomGuess = 0;
		
		while (guessResult != -1) {
			System.out.print("Guess a number between 0 and 50");
			randomGuess = userInput.nextInt();
			guessResult = checkGuess(randomGuess);
		}
		
		System.out.println("Yes the random number is " + randomGuess);
	}
	
	// Basic method syntax:
	// accessModifier returnType methodName Parameters
	public static int printPi(int a, int b) {
		double smallPi = 3.0;  // local variable. Only accessible inside of this function
		
		// local variable precedes over global variable with same name inside of the function
		double myPi = 3.0;  
		
		System.out.println("Local: " + myPi);
		return 1;
	}
	
	public static int addThem(int a, int b) {
		int c = a + b;
		return c;
	}
	
	public static void tryToChange(int d) {
		d = d + 1;
		System.out.println("tryTochange d = " + d);
	}
	
	public static int getRandomNum() {
		randomNumber = (int) (Math.random() * 51);
		return randomNumber;
	}
	
	public static int checkGuess(int guess) {
		if (guess == randomNumber) {
			return -1;
		} else {
			return randomNumber;
		}
	}

}
