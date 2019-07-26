package lessons.dataTypes;

import java.util.Arrays;

public class Lesson09 {

	public static void main(String[] args) {
		// ---- Standard Arrays ---- ---- ---- ---- ---- 
		
		// int[] -> array with field types int
		// new int[5] -> arrays are fixed in size. Means array with 10 slots
		int[] numberArray = new int[10];
		
		// Access array by index (starting by 0)
		numberArray[1] = 2;
		
		// Array can also be directly created with values
		// This creates a string array with length 3 and values inside it
		String[] stringArray = {"just", "random", "words"};
		
		// Standard way to iterate through array and assign values
		for (int i = 0; i < numberArray.length; i++ ) {
			numberArray[i] = i;
		}
		
		for (int j = 0; j < numberArray.length; j++) {
			System.out.print("| " + j + " ");
		}
		System.out.print("|");
		
		// ---- Multi-Dimensional-Arrays ---- ---- ---- 
		String[][] multiArray = new String[10][10];
		
		for (int i = 0; i < multiArray.length; i++) {
		
			for (int j = 0; j < multiArray[i].length; j++) {
				multiArray[i][j] = i + " " + j;
			}
			
		}
		System.out.println();

		// Print a horizontal line to separate the results
		int k = 0;
		while(k < 70) {
			System.out.print('-');
			k++;
		}
		System.out.println();
		
		for (int i = 0; i < multiArray.length; i++) {
			
			for (int j = 0; j < multiArray[i].length; j++) {
				System.out.print("| " + multiArray[i][j] + " |");
			}
			System.out.println();
			
		}
		
		int m = 0;
		while(m < 70) {
			System.out.print('-');
			m++;
		}
		System.out.println();
		
		/* ---- Enhanced For-Loop ---- ---- ----
		 * enhanced for loop for simple Array
		 * for (dataType varForRow : arrayName) 
		 */
		for (int row : numberArray) {
			System.out.print(row);
		}
		
		// enhanced for loop for multi-Array
		for (String[] rows : multiArray) {
			for (String column : rows) {
				System.out.print("| " + column + " |");
			}
			System.out.println();
		}
		System.out.println("\n");
		
		/* ---- Arrays-Utils---- ---- ---- 
		 * - Arrays.copyOf(Array, numberOfSlotsToCopy)
		 * 		e.g. Arrays.copyOf(numberArray, 5); -> copy the first five slots
		 * 
		 * - Arrays.copyOfRange(Array, start_copy_index, end_copy_index)
		 * 		e.g. Arrays.copyOfRange(numberArray, 1, 3) -> copy the second, third and fourth slot (index starts at 0)
		 * 
		 * - Arrays.toString(Array) -> turns array to string
		 * 
		 * - Arrays.fill(Array, value)
		 * 		e.g. Arrays.fill(moreNumbers, 2); -> fill each slot with the integer 2
		 * 
		 * - Arrays.sort(Array)
		 * 		e.g. Array.sort(moreNumbers); -> sorts the array (integer, strings alphabetically etc.)
		 * 		Can also sort by using user-defined method -> see method compare and interface Comparator
		 * 		there is also Collections.sort() which works with objects
		 * 		Arrays.sort() works also with primitive data types.
		 * 
		 * - Arrays.binarySearch(integerArray, integer_value_to_be_searched_for)
		 * 		e.g. Arrays.binarySearch(moreNumbers, 2); -> returns index of 2 or a negative int if it is not found.
		 * 		Array has to be sorted first!
		 * 		For String have a look at List and the .contains() method!
		 * */
		int[] numberCopy = Arrays.copyOf(numberArray, 5);
		
		for (int row : numberCopy) {
			System.out.print(row);
		}
		System.out.println();
		
		int[] a = Arrays.copyOfRange(numberArray, 1, 3);
		for (int row: a) {
			System.out.print(row);
		}
		System.out.println();
		
		System.out.println(Arrays.toString(numberCopy));
		
		int[] moreNumbers = new int[100];
		Arrays.fill(moreNumbers, 2);
		System.out.println(Arrays.toString(moreNumbers));
		
		char[][] boardGame = new char[10][10];
		// fill all slots in boardGame-rows with * -> see Lesson08
		for (char[] row : boardGame) {
			Arrays.fill(row, '*');
		}
		
		int[] numToSort = new int[10];
		
		// Fill array with random numbers between 0 and 99
		for (int i = 0; i < numToSort.length; i++) {
			numToSort[i] = (int) (Math.random() * 100);
		}
		
		System.out.println("Before: " + Arrays.toString(numToSort));
		Arrays.sort(numToSort);
		System.out.println("Sorted: " + Arrays.toString(numToSort));
		
		int whereIs50 = Arrays.binarySearch(numToSort, 50);
		System.out.println(whereIs50);
	}

}
