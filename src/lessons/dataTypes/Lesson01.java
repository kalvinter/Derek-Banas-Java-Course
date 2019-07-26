package lessons.dataTypes;

public class Lesson01 {

	static String randomString = "String to print";
	static final double PINUM = 3.141529;  // final -> cannot be changed. Name is in all-caps by convention
	
	public static void main(String[] args) {
		System.out.println(randomString);
		System.out.println(PINUM);
		
		int integerOne;
		integerOne = 22;
		
		int integerTwo = integerOne + 1;
		byte bigByte = 127;
		short bigShort = 32767;
		int bigInt = 2100000000;
		long bigLong = 9220000000000000000L;  // Mandatory: L at the end
		float bigFloat = 3.15F;  // Basic decimal number. Mandatory: F at the end
		double bigDouble = 3.12312379123779832;  
		// double -> Bigger and more precise float. Not-Mandatory: D at the end.
		// Why is D not mandatory? Decimal-Figures are by default double type.
		
		System.out.println(Float.MAX_VALUE);  // Can be different depending on the host machine
		System.out.println(Double.MAX_VALUE);  // Can be different depending on the host machine
		
		boolean trueOrFalse = true;
		
		char randomChar = 65;  // 
		char anotherChar = 'A';  // Single-Quotes mean char! Double-quotes mean strings!
		
		// Its the same. Chars can be defined as number or character.
		System.out.println(randomChar + " -> " + anotherChar);
		
		char escapedChars = '\f';  // can represent escape characters.
		/* available escape-characters -> 
		 * \b (backspace), 
		 * \f (form-feed = page-down), 
		 * \n (line-feed = enter), 
		 * \r (carriage-return = pos1)
		 * \t (horizontal tab = tab)
		 * \" (double-quote)
		 * \' (single-quote)
		 * \\ (backslash)
		 * etc.
		 * */ 
		
		String randomString = "I am a random String";
		String anotherString = "Stuff";
		
		String andAnotherString = randomString + anotherString;  // Strings can be joined with +
		
		System.out.println(andAnotherString);
		
		String byteString = Byte.toString(bigByte);  // use .toString-method from Byte-Class!
		String shortString = Short.toString(bigShort);
		String intString = Integer.toString(bigInt);
		// any of the primitive variable types can be converted like that
		
		double aDoubleValue = 3.12345;
		int doubleToInt = (int) aDoubleValue; 
		// variables can be transformed / cast to a different type. by typing the new type in brackets!
		System.out.println(doubleToInt);
		
		// What if the double is larger than the max-integer-value?
		aDoubleValue = 30000000000000000000000000.12345;
		doubleToInt = (int) aDoubleValue; 
		
		// It will just put the max-possible-integer value in the variable-slot.
		System.out.println(doubleToInt);
		
		
		// Use parse-methods from the respective packages to change variable type
		// Integer.parseInt, Double.parseDouble, Float.parseFloat, etc.
		int stringToInt = Integer.parseInt(intString);
		System.out.println(stringToInt);
	};

}
