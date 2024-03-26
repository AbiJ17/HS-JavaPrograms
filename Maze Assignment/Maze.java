package maze;

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 

// This Class contains majority of the code in the maze.txt file
// Creates the maze and solves the maze using a recursive backtracking algorithm
public class Maze {
	
	// 'global' variables, instance variables, class-wide variables
	
		// Stores the maze's updated row and column
		public static int rowStart; 
		public static int colStart = 0; 
		
		// Stores the maze's starting and exiting points (possible directions)
		private static final int RIGHT = 0; 
		private static final int UP = 1; 
		private static final int DOWN = 2; 
		private static final int LEFT = 3; 
		
		private int moveNumber = 0;   // Stores the number of moves made through the maze
		
		private char[][] mazeArray;   // Stores the maze in a 2-D character array
		
		private Scanner input;        // Creates a new scanner instance (stores the data the user enters) 
		
		// This method displays the initial (starting) maze (from a text file)
		public Maze (String fileName) { 
			
			// Test for errors in the maze while being executed
			try { 
				
				// Reads the maze from a text file
				input = new Scanner(new File(fileName)); 
				
				// Stores the rows and columns of the initial maze 
				int rows = input.nextInt(); 
				int columns = input.nextInt(); 
				
				// Stores the maze in a new array 
				mazeArray = new char[rows][columns]; 
				
				// Traverse through the maze from the starting point
				for (int row = 0; row < mazeArray.length; row++) { 
					
					// Prompts user to enter in order to continue traversing
					mazeArray[row] = input.next().toCharArray(); 
					
					// The dot in the maze indicates the new starting spot for the maze
					if (mazeArray[row][0] == '.')
						rowStart = row; 
				}
				
				// Displays the maze
				printMaze(); 
				
				// Closes the scanner class
				input.close();
				
				// Allow user to input data
				input = new Scanner(System.in); 
				
			// Lets user know if an error occurs in the try block. 
			} catch (FileNotFoundException error) { 
				
				System.out.println("File not found " + error); 
				
			}
		}
		
		// This method displays the maze
		private void printMaze() { 
			
			// Creates the rows in the maze
			for (char[] row : mazeArray) { 
				
				// Creates the columns/cells in the maze
				for (char cell : row)
					
					System.out.print(" " + cell);
				
				// Separates each new line with a space
				System.out.println(); 
				
			}
			
			// Creates an additional space
			System.out.println(); 

		}
		
		// This method is recursive (used to traverse through the maze)
		public boolean mazeTraversal(int row, int column) { 
			
			// Replaces each visited dot with an 'X' while traversing through the maze
			mazeArray[row][column] = 'X'; 
			printMaze(); 
			moveNumber++; 
			
			// If there is no exit, return back to the starting location
			if (row == rowStart && column == colStart && moveNumber > 1) { 
				
				System.out.println("Returned to starting location!"); 
				return false; 
				
			// If the maze is exited, then end program
			} else if (mazeExited(column)) { 
				
				System.out.println("Maze successfully exited!"); 
				return true; 
			
			// Continues to traverse through the array to find an exit
			} else { 
				
				// Outputs the number of moves and prompts user to enter the word to continue the maze
				System.out.printf("Total moves: %d - Press 'Enter' to continue...", moveNumber); 
				input.nextLine(); 
				
				// When the next move in the maze hasn't been made yet
				if (nextMove(row, column) == false) { 
					
					// Outputs the updated maze showing the current path
					mazeArray[row][column] = '0'; 
					printMaze(); 
					
					// Outputs the updated number of moves and prompts user to enter the word to continue the maze
					System.out.printf("Total moves: %d - Press 'Enter' to continue...", moveNumber); 
					input.nextLine(); 
					
					// 
					return false; 
					
				}
				
				return true; 
				
			}
		}
		
		// This method makes the next move to a direction to try to exit the maze
		private boolean nextMove(int row, int column) { 
			
			// Stores the directions of the rows and columns in the maze
			int dRow = 0; 
			int dCol = 0; 
			
			// For the 4 possible directions that the maze can go to (up, down, left, right)
			for (int count = 0; count < 4; count++) { 
				
				// Lists the cases of the possible directions that the maze can go to
				switch(count) { 
				
				case DOWN: 
					dRow = 1; 
					dCol = 0; 
					break; 
				
				case RIGHT: 
					dRow = 0; 
					dCol = 1; 
					break; 
					
				case UP: 
					dRow = -1; 
					dCol = 0; 
					break; 
				
				case LEFT: 
					dRow = 0; 
					dCol = -1; 
				
				}
				
				// If it's possible to move in at least 1 direction, call the mazeTraversal method recursively
				if (validMove(row + dRow, column + dCol))
					if (mazeTraversal(row + dRow, column + dCol))
						return true; 
				
			}
			
			// If it's not possible to go into any direction (known as a dead end)
			return false; 
		}
		
		// This method determines whether a valid move in the maze exists
		private boolean validMove(int row, int column) { 
			
			return ((row >= 0) && (row < mazeArray.length) && (column >= 0) && 
					(column < mazeArray[row].length) && (mazeArray[row][column] == '.')); 
			
		
		}
		
		// This method determines whether the user has exited the maze or not
		private boolean mazeExited(int column) { 
			
			// Maze is exited when it reaches the last column in the array
			if (column == mazeArray[0].length - 1)
				return true; 
			else
				return false; 
		}

}
