// Uses recursion to find a solution to a maze.

public class RecursiveMazeProb {
	
	// row and col refer to the starting point
	// maze is a 2-D int array where 0 = unvisited open path, 1 = wall,
	// 2 = visited (but not on the solution path), 3 = solution path
	// Returns whether a solution exists for the given maze starting at row/col
	// and ending at endRow/endCol.
	public static boolean solveMaze(int[][] maze, int row, int col, int endRow,
			int endCol) {
		// base case - start point matches end point (solution exists)
		if (row == endRow && col == endCol) {
			maze[row][col] = 3; // mark this square as being on the solution
								// path
			return true;
		}

		// base case - start point is out of bounds (no solution exists)
		else if (row < 0 || row >= maze.length || col < 0
				|| col >= maze[0].length)
			return false;

		// base case - start point is a wall, or has already been visited
		// (no solution exists)
		else if (maze[row][col] == 1 || maze[row][col] == 2)
			return false;

		else {
			maze[row][col] = 2; // mark this square as visited

			// search north, east, south, west for solution
			if (solveMaze(maze, row - 1, col, endRow, endCol)
					|| solveMaze(maze, row, col + 1, endRow, endCol)
					|| solveMaze(maze, row + 1, col, endRow, endCol)
					|| solveMaze(maze, row, col - 1, endRow, endCol)) {
				maze[row][col] = 3; // if one is found, mark this square as
				// being on the solution path
				return true; // solution exists
			} else
				return false; // no solution found
		}
	}

	// Prints a graphical representation of the maze
	// W = wall
	// ? = visited square that was considered, but is not on the solution path
	// . = solution path
	public static void printMaze(int[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 0)
					System.out.print(" ");
				else if (maze[i][j] == 1)
					System.out.print("W");
				else if (maze[i][j] == 2)
					System.out.print("?");
				else
					System.out.print(".");
			}
			System.out.print("\n");
		}
	}

	// Test main
	public static void main(String[] args) {
		int[][] maze = { { 0, 0, 1, 1, 1, 1 },
						 { 1, 0, 1, 1, 0, 1 },
						 { 1, 0, 0, 0, 0, 1 }, 
						 { 1, 1, 1, 1, 0, 1 },
						 { 1, 0, 0, 0, 0, 0 } };
		printMaze(maze);
		solveMaze(maze, 0, 0, 4, 5);
		printMaze(maze);
	}

}
