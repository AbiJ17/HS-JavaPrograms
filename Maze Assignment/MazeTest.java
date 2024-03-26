package maze;

//This Class contains the main method that creates a new Maze object
//And then attempts to traverse through the maze to find a pathway from the entrance to the exit
public class MazeTest {
	
		// Main Method
		public static void main(String[] args) { 
			
			// Creates a new maze object
			Maze myMaze = new Maze("maze.txt"); 
			
			// Traverses through the maze to find a pathway
			if (myMaze.mazeTraversal(Maze.rowStart, Maze.colStart) == false)
				System.out.println("Maze has no solution"); 
		}

}
