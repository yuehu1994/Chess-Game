/**
 * @section DESCRIPTION
 * This Class is the class for all chess piece
 * Holds row and column which are the location on the board
 * Holds name of the chess piece
 * Holds color white or black 
 */

public class pieces {
	/**
	 * Information about the piece
	 */
	int row;
	int column;
	String name;
	String color;
	/**
	 * Constructor for basic piece
	 * @param row The row of location
	 * @param column The column of location
	 */
	pieces(int row, int column, String name, String color){
		this.row = row;
		this.column = column;
		this.name = name;
		this.color = color;
	}
	/**
	 * Constructor for basic location
	 * @param row The row of location
	 * @param column The column of location
	 */
	pieces(int row, int column){
		this.row = row;
		this.column = column;
		this.name = "NULL";
		this.color = "NULL";
	}
}
