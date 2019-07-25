package lessons.lesson08_materials;

import java.util.Arrays;

public class BattleBoard {

	static char[][] battleBoard = new char[10][10];
	
	private static int maxXBoardSpace = battleBoard.length - 1;
	private static int maxYBoardSpace = battleBoard[0].length - 1;
		
	
	public static int getMaxXBoardSpace() {
		return maxXBoardSpace;
	}
	
	public static int getMaxYBoardSpace() {
		return maxYBoardSpace;
	}
	
	public static void buildBattleBoard() {
		
		// for loop - shorthand
		for(char[] row : battleBoard) {
			Arrays.fill(row,  '*');  // fill all slots of the array with '*'
		}
	}
	
	public static void redrawBoard() {
		int k = 0;
		
		// Draw the top dashed line of the board
		while (k < 30) {
			System.out.print("-");
			k++;
		}
		
		System.out.println();
		
		// Iterate through all rows
		for (int i = 0; i < battleBoard.length; i++) {
			
			// Iterate through each cell in the row
			for (int j = 0; j < battleBoard[i].length; j++) {
				System.out.print("|" + battleBoard[i][j] + "|");
				
			}
			
			System.out.println();
			
		}
		
		// Draw the bottom dashed line of the board
		int m = 0;
		while (m < 30) {
			System.out.print("-");
			m++;
		}
		System.out.println();
	}
	
	public BattleBoard() {
		maxXBoardSpace = battleBoard.length - 1;
		maxYBoardSpace = battleBoard[0].length - 1;
	}
	
}
