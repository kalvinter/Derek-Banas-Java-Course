package lessons.conditionals;

public class Lesson03 {

	public static void main(String[] args) {
		int randomNumber = (int) (Math.random() * 50);  
		// Get a number between 0 and 49
		// * X -> is not inclusive. * 50 means 0 to 49
		
		if (randomNumber < 25) {
			System.out.println("The random number is less than 25");
			
		} else if (randomNumber > 40) {
			System.out.println("The random number is greater than 40");
			
		} else if (randomNumber == 18) {
			System.out.println("The random number is equal to 18");
			
		} else if (randomNumber != 40) {
			System.out.println("The random number is not equal to 40");
			
		} else if ((25 <= randomNumber ) && (randomNumber <= 40)) {
			// Range-checks -> have to stated as to separate boolean expressions 
			// combined with in and && operator
			System.out.println("The random number is between 25 and 40");
		}
		
		System.out.println("The random number is " + randomNumber);
		
		/* Logical Operators: 
		 * 6 logical operators:
		 * !  : converts boolean operator to its right to the opposite
		 * &  : true if boolean values on both sides are true. (also bit-wise comparison of both operands)
		 * && : true if boolean values on both sides are true. 
		 * 		BUT it will stop if the first expression is already false and not evaluate the second.
		 * |  : true if boolean values on both sides are true. (also bit-wise comparison of both operands)
		 * || : true if one boolean value of both is true. 
		 * 		BUT it will stop if the first expression is already true and not evaluate the second.
		 * 		This is helpful in defensive checks 
		 *      e.g. (if(string != null && string.isEmpty()) -> stops if it is null and will not perform isEmpty())
		 * ^  : Returns true if there 1 true and 1 false boolean value. 
		 * 	    E.g. returns true if the left operand is false and the right operand is true!
		 *      E.g. (false ^ true) => returns true
		 * 
		 * 
		 * */
		if (!(false)) {
			System.out.println("I turned false into true");
		}
		
		// Ternary operator -> Shorthand -> assigns one or the other value based on the condition
		int valueOne = 1;
		int valueTwo = 2;
		
		// If expression in brackets is true -> return the first value (valueOne)
		// If expression in brackets if false -> return the second value (valueTwo)
		int biggestValue = (valueOne > valueTwo) ? valueOne : valueTwo; 
		
		// Switch Statement
		char theGrade = 'B';
		
		// switch -> test the value in brackets against the cases (equal) 
		switch (theGrade) {
		
			case 'A':
				System.out.println("Great Job");
				break;  // breaks the switch testing. If break is not provided it will continue to test all other cases
			
			case 'b': // That way the code is executed if theGrade is 'b' or 'B'
			case 'B':
				System.out.println("Good Job");
				break;
				
			default:  // Default -> if no case matches, execute this code block
				System.out.println("Weird grade");
				
		}
		
	}
	
}
