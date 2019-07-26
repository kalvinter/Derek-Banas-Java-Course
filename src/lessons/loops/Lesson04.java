package lessons.loops;

import java.util.Scanner;

public class Lesson04 {
	
	static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		
		// ---- While loop: ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
		// generator is created outside of the loop  
		int i = 0;
		while (i < 20) {
			
			if ( i % 2 == 0 ) {
				i++;
				continue;  // skip rest, jump back to top of the loop
			}
			
			System.out.println(i);
			i++;
			
			if ( i > 10 ) {
				break;  // break out of while loop (end it).
			}
		}
		
		double myPi = 4.0;
		
		double j = 3.0;
		
		System.out.println(myPi);

		// Calculate Pi: 4 - 4/3 + 4/5 - 4/7 etc.
		while ( j < 1100000000 ) {
			myPi = myPi - (4/j) + (4/(j+2));
			j += 4;
		}
		System.out.println(myPi);
		System.out.println(Math.PI);
		
		String contYorN = "Y";
		int h = 1;
		
		// equalsIgnoreCase -> compares strings while ignoring cases (upper case, lower case) differences
		while (contYorN.equalsIgnoreCase("y")) {
			System.out.println(h);
			System.out.print("Continue? y or n: ");
			contYorN = userInput.nextLine();
			h++;
		}
		
		// ---- Do-While loop: ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

		int k = 11;  
		do {
			System.out.println(k);
			k++;
			
		} while (k < 10);
		// while loop executes at least ones, even though condition is false
		
		// ---- For-loop: ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
		// initialize counter ; if condition true execute ; statement run after each cycle
		for (int l = 0; l < 10; l++) {
			System.out.println(l);
		}
		
	}
	
}
