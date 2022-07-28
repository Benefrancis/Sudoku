package br.com.benefrancis.sudoku;

/**
 * Sudoku Solver
 * 
 * @author Francis
 *
 */
public class SudokuSolver {
	/**
	 * Tamanho do grid. É Uma matriz de 9x9 posições
	 */
	private static final short GRID_SIZE = 9;

	public static void main(String[] args) {

		// @formatter:off
 		short[][] board = { 
				{ 7, 0, 2, 0, 5, 0, 6, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 3, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 9, 5, 0, 0 },
				{ 8, 0, 0, 0, 0, 0, 0, 9, 0 },
				{ 0, 4, 3, 0, 0, 0, 7, 5, 0 }, 
				{ 0, 9, 0, 0, 0, 0, 0, 0, 8 },
				{ 0, 0, 9, 7, 0, 0, 0, 0, 5 }, 
				{ 0, 0, 0, 2, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 7, 0, 4, 0, 2, 0, 3 } 
				};
 		// @formatter:on
 		
 		printBoard(board);

		if (solveBoard(board)) {
			System.out.println("Solved Sucessfully!");
		} else {
			System.out.println("Unsolvable board :(");
		}
		printBoard(board);
	}

	private static boolean isNumberInRow(short[][] board, short number, short row) {
		for (short i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInColumn(short[][] board, short number, short column) {
		for (short i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInBox(short[][] board, short number, short row, short column) {
		short localBoxRow = (short) (row - row % 3);
		short localBoxColumn = (short) (column - column % 3);

		for (short i = localBoxRow; i < localBoxRow + 3; i++) {
			for (short j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isValidPlacement(short[][] board, short number, short row, short column) {
		// @formatter:off
 		return  !isNumberInRow(board, number, row) && 
				!isNumberInColumn(board, number, column) && 
				!isNumberInBox(board, number, row, column);
 		// @formatter:on

	}

	private static boolean solveBoard(short[][] board) {

		for (short row = 0; row < GRID_SIZE; row++) {
			for (short column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (short numbrToTry = 1; numbrToTry <= GRID_SIZE; numbrToTry++) {
						if (isValidPlacement(board, numbrToTry, row, column)) {
							board[row][column] = numbrToTry;
							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static void printBoard(short[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			if (row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			for (int column = 0; column < GRID_SIZE; column++) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
	}

}
