package lessons.Lesson08;

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
		int k = 1;
		
		// Draw the top dashed line of the board
		while (k < 30) {
			System.out.print('-');
			k++;
		}
		
		System.out.println();
		
		// Iterate through all rows
		for (int i = 0; i < battleBoard.length; i++) {
			
			// Iterate through each cell in the row
			for (int j = 0; j < battleBoard[i].length; j++) {
				System.out.print('|' + battleBoard[i][j] + '|');
				
			}
			
			System.out.println();
			
		}
		
		// Draw the bottom dashed line of the board
		while (k < 30) {
			System.out.print('-');
			k++;
		}
	}
	
	public BattleBoard() {
		int maxXBoardSpace = battleBoard.length - 1;
		int maxYBoardSpace = battleBoard[0].length - 1;
	}
	
}
