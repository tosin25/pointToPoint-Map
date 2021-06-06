// get a point and destination then finds the shortest distance from the point to destination
import java.util.*;

/**
 * 
 * @author EECS2030 Team
 * 
 */

public class Map {
	boolean [][] map; 
	private int row;
	private int column;
	private Set<String> visited;
	
	public int getRow() {
		return row;
	}
	// here a getter is used in order to access the code in the constructor
	
	public int getColumn() {
		return column;
	}
	// here a getter is used in order to access the code in the constructor
	
	/**
	 * This is the constructor that constructs the city map, 
	 * which is a grid of row by column.
	 * @param row is the number of east-west streets of the city
	 * @param column is the number of north-south streets of the city
	 */
	public Map(int row, int column) {
		this.row = row;
		this.column = column;
		this.map = new boolean[row][column];
		this.visited = new HashSet<String>();
		// Please implement the constructor
	}
	/**
	 * This method checks the correctness of the input parameters. If the preconditions are not met 
	 * an exception is thrown, otherwise depending to the direction, it calls 
	 * one of the four recursive functions of goSouthWest, goSouthEast, goNorthWest and goNorthEast.
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre the integer parameters should be in the range of the city grid.(i.e. [0, N) if N is the number of east-west streets and [0, M) if 
	 * M is the number of north-south streets.) 
	 * @exception IllegalArgumentException if any of the precondition did not meet.
	 */
	
	private boolean inBounds(int value, int bound) {
		// value must be between 0 and bound, bound exclusive
		return (value >= 0 && value < bound);
		
	}
	// value represents startRow, destRow, startCol, destCol and bound represents the number of rows, columns, streets given
	// inBounds(int value, int bound) is used to check if the startRow, destRow, startCol, destCol are in the correct boundary, that is to check
	// if it is below 0 and greater than the number of rows and columns provided in which case we cannot start the code
	
	public String getPath (int startRow, int startCol, int destRow, int destCol , String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		
		if (!inBounds(startRow, this.row) || !inBounds(destRow, this.row) || !inBounds(startCol, this.column) || !inBounds(destCol, this.column)) {
            throw new IllegalArgumentException();
            // if the startRow, destRow, startCol, destCol provided is not in the correct boundary then this throws an Exception
        }
		
        if (startRow == destRow && startCol == destCol) {
            return "";
         // if the startRow, destRow, startCol, destCol provided is the same as the row and column given then this returns an empty string 
            // this is because the start and the destination are in the same intersection
        }
        
        boolean goingEast = (destCol - startCol) >= 0;
        // here we are going either up or down because if destCol - startCol >= 0 this means the startCol is on the bottom of the destCol 
        // and we go up; and if destCol - startCol is not >= 0 this means the startCol is on the top of the destCol and we go down
        
        // this statement below determines if we go either left or right because if destRow - startRow >= 0 this means the 
    	// startRow is on the left of the destRow then it means we should go right; and if destRow - startRow not >= 0 this means the 
        // startRow is on the right of the destRow then it means we should go left
        
        if ((destRow - startRow) >= 0) {
        	// here determines if we are going right and either up or down
            if (goingEast) {
            	// here determines if we are going right and up; so here we go to goNorthEast
                return goNorthEast(startRow, startCol, destRow, destCol, "");
            } 
            else {
            	// here determines if we are going right and down; so here we go to goNorthWest
                return goNorthWest(startRow, startCol, destRow, destCol, "");
            }
        }
        else {
        	// here determines if we are going left and either up or down
            if (goingEast) {
            	// here determines if we are going left and up; so here we go to goSouthEast
                return goSouthEast(startRow, startCol, destRow, destCol, "");
            } 
            else {
            	// here determines if we are going left and down; so here we go to goSouthWest
                return goSouthWest(startRow, startCol, destRow, destCol, "");
            }
        }
	}
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point.  
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol >= destCol </code>
	 */
	
	private String goSouthWest (int startRow, int startCol, int destRow, int destCol , String path) {
		if (startRow != destRow) {
            startRow--;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
		// here the startRow decreases to go left
		
        if (startCol != destCol) {
            startCol--;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
        // here the startCol decreases to go down

        if (startRow == destRow && startCol == destCol) {
            return path.trim();
        }
        // here when the startRow == destRow and startCol == destCol it stops returns the path
        
        boolean goingEast = (destCol - startCol) >= 0;
        // here if boolean is true you go up, if false you go down
        
        if (startRow < destRow) {
        	// here we should go up
            if (goingEast) {
            	// here we should go right and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthEast
                return goNorthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthWest
                return goNorthWest(startRow, startCol, destRow, destCol, path);
            }

        }
        else {
        	// here we should go down
            if (goingEast) {
            	// here we should go right and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthEast
                return goSouthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthWest
                return goSouthWest(startRow, startCol, destRow, destCol, path);
            }
        }
		// recursion ends here
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goSouthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		if (startRow != destRow) {
            startRow--;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
		// here the startRow decreases to go left
		
        if (startCol != destCol) {
            startCol++;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
        // here the startCol increases to go up

        if (startRow == destRow && startCol == destCol) {
            return path.trim();
        }
        // here when the startRow == destRow and startCol == destCol it stops returns the path
        
        boolean goingEast = (destCol - startCol) >= 0;
        // here if boolean is true you go up, if false you go down
        
        if (startRow < destRow) {
            // here we should go up
            if (goingEast) {
            	// here we should go right and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthEast
                return goNorthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthWest
                return goNorthWest(startRow, startCol, destRow, destCol, path);
            }

        } 
        else {
            // here we should go down
            if (goingEast) {
            	// here we should go right and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthEast
                return goSouthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthWest
                return goSouthWest(startRow, startCol, destRow, destCol, path);
            }
        }
        
        // recursion ends here
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goNorthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		if (startRow != destRow) {
            startRow++;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
		// here the startRow increases to go right
		
        if (startCol != destCol) {
            startCol++;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
        // here the startCol increases to go up

        if (startRow == destRow && startCol == destCol) {
            return path.trim();
        }
        // here when the startRow == destRow and startCol == destCol it stops returns the path
        
        boolean goingEast = (destCol - startCol) >= 0;
        // here if boolean is true you go up, if false you go down
        
        if (startRow < destRow) {
            // here we should go up
            if (goingEast) {
            	// here we should go right and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthEast
                return goNorthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthWest
                return goNorthWest(startRow, startCol, destRow, destCol, path);
            }
        } 
        else {
            // here we should go down
            if (goingEast) {
            	// here we should go right and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthEast
                return goSouthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthWest
                return goSouthWest(startRow, startCol, destRow, destCol, path);
            }
        }
        
        // recursion ends here
        // Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and <code> startCol >= destCol </code>
	 */
	private String goNorthWest (int startRow, int startCol, int destRow, int destCol , String path) {
		if (startRow != destRow) {
            startRow++;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
		// here the startRow increases to go right
		
        if (startCol != destCol) {
            startCol--;
            path += "("+ (startRow) + "," + (startCol) + ") ";
        }
        // here the startCol decreases to go down

        if (startRow == destRow && startCol == destCol) {
            return path.trim();
        }
        // here when the startRow == destRow and startCol == destCol it stops returns the path
        
        boolean goingEast = (destCol - startCol) >= 0;
        // here if boolean is true you go up, if false you go down
        
        if (startRow < destRow) {
            // here we should go up
            if (goingEast) {
            	// here we should go right and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthEast
                return goNorthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and up; and the startRow, startCol, destRow, destCol, path should be returned in the goNorthWest
                return goNorthWest(startRow, startCol, destRow, destCol, path);
            }
        } 
        else {
            // here we should go down
            if (goingEast) {
            	// here we should go right and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthEast
                return goSouthEast(startRow, startCol, destRow, destCol, path);
            } 
            else {
            	// here we should go left and down; and the startRow, startCol, destRow, destCol, path should be returned in the goSouthWest
                return goSouthWest(startRow, startCol, destRow, destCol, path);
            }
        }
		
        // recursion ends here
        // Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
	}
	
	/**
	 * This method find a path from (startRow, startCol) to a border point of the city. 
	 * Please note that the starting point should be included in the path.
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @return is a path from (starting row, staring col) to a border point of the city. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 */
	
	
	
	private String findPathHelper(int row, int col) {
		// this helper method is used to find the random path that is needed to help for public String findPath to
		// find an escape route from the police and a way out of the city map and returns the path needed to escape
		if (!inBounds(row, this.row) || ! inBounds(col, this.column)) {
			// here if the row and column we're in is >= 0 and < the number of streets we return an empty string
			return "";
		}
		
		if (this.visited.contains(row + "|" + col)) {
			// here if visited should contains the row, column it should return place
			return "place";
		}
		
		this.visited.add(row + "|" + col);
		// here visited should add the row, column
		Random random = new Random();
		int place = random.nextInt(2);
		// this is to determine what random path place should take 
		
		if (place == 1) {
			// here when position is equal to 1 then the random generator should take it through this path and here it should go left
			return "(" + row + "," + col + ") " + findPathHelper(row - 1, col);
		}
		// here the random generator should take position through this path and here it should go up
		return "(" + row + "," + col + ") " + findPathHelper(row, col - 1);
	}
	
	public String findPath (int startRow, int startCol) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		String place =  findPathHelper(startRow, startCol);
		// place is the intersection we are on, take to avoid getting caught by the police
		
		while (place.substring(place.length() - 1).contentEquals("place")) {
			this.visited.clear();
			// here the game starts over if the random escape route generated fails
			findPathHelper(startRow, startCol);
			// this is where the helper method is called
		}
		return place;
		// this returns the intersections 
	}

	
} // end of class

 