//
// Purpose: A working version of Conway's Game of Life: http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life;
// Written Lori L. Gildersleeve, February 2008
//


package gameOfLifeProject;

import java.util.Random;

public class Main {
	
	final static int NUM_ROWS = 6;
	final static int NUM_COLUMNS = 6;
	final static int [][] newCells = new int [NUM_ROWS][NUM_COLUMNS];
	
	public static void main(String[] args) {
	
		int[][] cells = getInitializedArray();
/*
//  Setup for particular pattern testing; must change NUM_ROWS and NUM_COLUMNS to match.
 		int[][] cells = new int[][] {
				{0,0,0,0,0,0},
				{0,1,1,1,0,0},
				{0,0,1,1,1,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};*/
		
		String stringView = getStringView(cells);
		
		System.out.println(stringView);
		
		changeStatus(cells);
		
		stringView = getStringView(cells);
		
		System.out.println(stringView);
		
		changeStatus(cells);
		
		stringView = getStringView(cells);
		
		System.out.println(stringView);
		
		changeStatus(cells);
		
		stringView = getStringView(cells);
		
		System.out.println(stringView);
		
		changeStatus(cells);
		
		stringView = getStringView(cells);
		
		System.out.println(stringView);
		
	}
	
	public static String getStringView( int[][] cells) {
		String value = "";
		
		for (int row=0; row<cells.length; row++) {
			for (int column=0; column<cells[row].length; column++) {
				value += cells[row][column] + " ";
			}
			value += "\n";
		}
			
		return value;
	}
	
	public static int[][] getInitializedArray() {
		
		Random randomGenerator = new Random(System.currentTimeMillis());
		
		//setup new 2D array
		int [][] cells = new int [NUM_ROWS][NUM_COLUMNS];
		
		//load new 2D array with initial values
		final int MAX_VALUE = 100;
		final int MIN_VALUE = 0;
		float randomValue;
		float initialValue;
		
		
		for (int row=0; row<NUM_ROWS; row++) {
			for (int column=0; column<NUM_COLUMNS; column++) {
				
				randomValue = randomGenerator.nextFloat();
				initialValue = ( ( (MAX_VALUE - MIN_VALUE)*randomValue)+ MIN_VALUE);
				if (initialValue <= (MAX_VALUE - 90) ) {
					cells[row][column] = 1;
				} else {
					cells[row][column] = 0;
				}	
			}
		}
		
		return cells;
	}
		
		public static int [][] changeStatus(int [][] cells) {
			for (int row=0; row < NUM_ROWS; row++) {
				for (int column = 0; column < NUM_COLUMNS; column++) {
					getNumNeighborsAlive(cells,row,column);
				}
			}
			
			for (int row=0; row < NUM_ROWS; row++) {
				for (int column = 0; column < NUM_COLUMNS; column++) {
				cells[row][column] = newCells[row][column];	
				}
			}
			
			
			
			return cells;
		}
		
		private static void getNumNeighborsAlive(int [][] cells, int row, int column) {
			int count = 0;
			
			//check above row values
			//check for top boundary, wrap to bottom row if necessary
			int checkRow = row;
			if (checkRow == 0) {
				checkRow = NUM_ROWS -1;
			} else {
				checkRow = row -1;
			}
			
			//check column directly above initial target 
			int checkColumn = column;
			if (cells[checkRow][checkColumn] == 1) {
				count++;
			}
			
			//check column directly above initial target and one space to the left
			//check for boundaries, wrap to alternate side if necessary
			if (checkColumn -1 < 0) {
				checkColumn = NUM_COLUMNS -1;
			} else {
				checkColumn = column -1;
			}
			
			if (cells[checkRow][checkColumn] == 1){
				count++;
			}
			checkColumn = column;
			
			//check column directly above initial target and one space to the right
			//check for boundaries, wrap to alternate side if necessary
			if (checkColumn +1 >= NUM_COLUMNS) {
				checkColumn = 0;
			} else {
				checkColumn = column +1;
			}
			if (cells[checkRow][checkColumn] == 1) {
				count++;
			}
			checkColumn = column;
			
			
			//check below row values
			//check for bottom boundary, wrap to top row if necessary
			checkRow = row;
			if (checkRow == NUM_ROWS -1) {
				checkRow = 0;
			} else { checkRow = row +1;
			
			}
			
			//check column directly below initial target 
			checkColumn = column;
			if (cells[checkRow][checkColumn] == 1) {
				count++;
			}
			
			//check column directly below initial target and one space to the left
			//check for boundaries, wrap to alternate side if necessary
			if (checkColumn -1 < 0) {
				checkColumn = NUM_COLUMNS -1;
			} else {
				checkColumn = column -1;
			}
			if (cells[checkRow][checkColumn] == 1){
				count++;
			}
			checkColumn = column;
			
			//check column directly below initial target and one space to the right
			//check for boundaries, wrap to alternate side if necessary
			if (checkColumn +1 >= NUM_COLUMNS) {
				checkColumn = 0;
			} else {
				checkColumn = column +1;
			}
			if (cells[checkRow][checkColumn] == 1) {
				count++;
			}
			checkColumn = column;
			
			//check column one space to the left of the initial target
			//check for boundaries, wrap to alternate side if necessary
			checkRow = row;
			if (checkColumn -1 < 0) {
				checkColumn = NUM_COLUMNS -1;
			} else {
				checkColumn = column -1;
			}
			if (cells[checkRow][checkColumn] == 1){
				count++;
			}
			checkColumn = column;
			
			//check column one space to the right of the initial target
			//check for boundaries, wrap to alternate side if necessary
			if (checkColumn +1 >= NUM_COLUMNS) {
				checkColumn = 0;
			} else {
				checkColumn = column +1;
			}
			if (cells[checkRow][checkColumn] == 1) {
				count++;
			}
			checkColumn = column;
			
			setNewStatus(count, row, column, cells, newCells);
		}
		
		public static void setNewStatus(int count, int row, int column, int [][] cells, int [][] newCells) {
			
			//check count value and set new cell value
			if (cells[row][column] == 0 && count ==3) {
				newCells[row][column] = 1;
			} else if (cells[row][column] == 0 && count != 3) {
				newCells[row][column] = 0;
			} else if (cells[row][column] == 1 && count <2 || count >3) {
				newCells[row][column] = 0;
			} else { 
				newCells[row][column] = 1;
			}
		}
		
		
		
		
		
		
}
