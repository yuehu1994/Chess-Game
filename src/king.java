/**
 * @section DESCRIPTION
 * 
 * This class holds all the functions and data for both players Kings. It sees if a king can move, 
 * and sees if a king is in check. It holds functions that checks if a move for a king is valid.
 * Also does all the handling for check scenarios.
 */

public class king {

	/**
	 * variables that holds locations of kings and current check status
	 */
	static int whiteKingRow = 7;								//these hold locations of kings. Initially row 7
	static int blackKingRow = 0;								//black king starts in row 0
	static int whiteKingColumn = 4;								//both kings starts in column 4
	static int blackKingColumn = 4;
	static boolean whiteKingCheck = false;						//these boolean hold if kings are in check or not
	static boolean blackKingCheck = false;
	
	/**
	 * This class sees if a king movement is valid or not. It first checks if requested piece is one space away,
	 * checks if the spot is blocked by own piece, and checks to see if the move will put the king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current king piece
	 * @param requested The requested spot the player wants the king to go
	 * 
	 * @return a boolean indicating if a king can move to the requested positino
	 */
	public static boolean validMove(pieces[][] board, pieces current, pieces requested){
		boolean valid = false;		//first check to see if requested spot is one block away
		if(current.row-requested.row == 1 || current.row-requested.row == 0 || current.row-requested.row == -1){				//checks to see if requested move is 1 space away
			if(current.column-requested.column == 1 || current.column - requested.column == 0 || current.column - requested.column == -1){
				valid = true;
			}
		}
		if(requested.color.equals(current.color)){return false;}									//This makes sure own piece isn't blocking
		if(valid == false){
			return false;
		}
		pieces tempKing = new pieces(requested.row,requested.column,current.name,current.color);	//temp king-assumes move can be made-checks from there
		return validHelper(board,tempKing);
	}
	
	/**
	 * This helper function helps validMove. It checks if the new location that the player wants the king to move 
	 * to will put the king in check. A king cannot move to a position where it puts itself in check
	 * 
	 * @param tempKing location of requested king movement
	 * 
	 * @return boolean that is true if space is safe, false if space is guarded by another piece
	 */
	public static boolean validHelper(pieces[][] board, pieces tempKing){
		boolean valid = false;
		valid = checkAllDirections(board,tempKing);			//next check if opponent queen rook bishops are blocking the spot
		if(valid == false){
			return false;
		}
		valid = checkKnightMove(board,tempKing);				//checks if any opponents knights are holding space 
		if(valid == false){
			return false;
		}
		valid = checkPawn(board,tempKing);
		if(valid == false){
			return false;
		}
		valid = checkKings(board,tempKing);
		if(valid == false){
			return false;
		}
		return true;
	}
	
	
	/**
	 * Helper function for validHelper. Checks to see if enemy king is in a position to hinder prevent requested 
	 * king movement.
	 *
	 * @param board The current board and pieces
	 * @param requested The requested space player wishes to move king
	 * 
	 * @return a boolean indicating if space is safe from enemy king or not
	 **/
	public static boolean checkKings(pieces[][] board, pieces requested){
		if(requested.color.equals("white")){
			if( (requested.row - blackKingRow <=1 && requested.row - blackKingRow>=-1 ) && (requested.column - blackKingColumn<=1 && requested.column - blackKingColumn>=-1)){
				return false;
			}
		}
		else if(requested.color.equals("black")){
			if( (requested.row - whiteKingRow <=1 && requested.row - whiteKingRow>=-1 ) && (requested.column - whiteKingColumn<=1 && requested.column - whiteKingColumn>=-1)){
				return false;
			}
		}
		return true;

	}
	/**
	 * Helper function that checks all 8 directions of a square and sees if it is safe for king to 
	 * move to said square. This function checks if a requested square is guarded by enemy rooks,
	 * bishops,queens,empress,or princess.
	 * 
	 * @param board The current board and pieces
	 * @param requested The location player wants to move his/her king
	 * 
	 * @return boolean that indicates if enemy rook,bishop,queen,empress,or princess guards the square	
	 */
	public static boolean checkAllDirections(pieces[][] board, pieces requested){
		if(checkDirection.checkUp(board, requested, requested, true) == false){return false;}					//check up direction
		if(checkDirection.checkDown(board, requested, requested, true)==false){return false;}				//check down direction
		if(checkDirection.checkLeft(board, requested, requested, true)==false){return false;}				//check left
		if(checkDirection.checkRight(board, requested, requested, true)==false){return false;}				//check right
		if(checkDirection.checkSouthWest(board, requested, requested, true)==false){return false;}			//check sw
		if(checkDirection.checkSouthEast(board, requested, requested, true)==false){return false;}			//check sw
		if(checkDirection.checkNorthWest(board, requested, requested, true)==false){return false;}			//check nw
		if(checkDirection.checkNorthEast(board, requested, requested, true)==false){return false;}			//check ne
		
		return true;
	}
	
	/**
	 * Helper function checks to see if an enemy knight is holding requested square, preventing king movement
	 * 
	 * @param board The current board and pieces
	 * @param requested The requested location of king 
	 * 
	 *  @return a boolean that indicates if a knight prevents king movement
	 */
	public static boolean checkKnightMove(pieces[][] board, pieces requested){
		boolean topHalfKnight = checkKnightTop(board,requested,"knight");
		boolean topHalfEmpress = checkKnightTop(board,requested,"empress");
		boolean topHalfPrincess = checkKnightTop(board,requested,"princess");
		if(topHalfKnight == false || topHalfEmpress == false || topHalfPrincess == false){
			return false;
		}
		boolean bottomHalfKnight = checkKnightBottom(board,requested,"knight");
		boolean bottomHalfEmpress = checkKnightBottom(board,requested,"empress");
		boolean bottomHalfPrincess = checkKnightBottom(board,requested,"princess");
		if(bottomHalfKnight == false || bottomHalfEmpress == false || bottomHalfPrincess == false){
			return false;
		}
		return true;

	}
	
	/** 
	 * knightCheck helper function. Checks top 4 L spaces for enemy empress,princess, or knight. 
	 * 
	 * @param board The current board and pieces
	 * @param requested The requested location to move the king	
	 * @param name The piece function is checking for-empress princess or knight
	 * 
	 * @return A boolean indicating if spot is being guarded by enemy empress princess or knight
	*/
	public static boolean checkKnightTop(pieces[][] board,pieces requested,String name){
		boolean guarded = true;
		if(requested.row-1 >= 0 && requested.column-2 >=0){
			if(board[requested.row-1][requested.column-2].name.equals(name) && board[requested.row-1][requested.column-2].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row-1,requested.column-2,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
			}
		}
		if(requested.row-2 >= 0 && requested.column-1 >= 0){
			if(board[requested.row-2][requested.column-1].name.equals(name) && board[requested.row-2][requested.column-1].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row-2,requested.column-1,name,requested.color); 
				endGame.checkTiles.add(tempKnight);	
				endGame.mustGoTiles.add(tempKnight);
			}
		}
		if(requested.row-2 >= 0 && requested.column+1 < controller.boardBoundsColumn){
			if(board[requested.row-2][requested.column+1].name.equals(name) && board[requested.row-2][requested.column+1].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row-2,requested.column+1,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
			}
		}
		if(requested.row-1 >= 0 && requested.column +2 < controller.boardBoundsColumn){
			if(board[requested.row-1][requested.column+2].name.equals(name) && board[requested.row-1][requested.column+2].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row-1,requested.column+2,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
			}
		}
		return guarded;
	}
	
	/** 
	 * knightCheck helper function. Checks bottom 4 L spaces for enemy empress,princess, or knight. 
	 * 
	 * @param board The current board and pieces
	 * @param requested The requested location to move the king	
	 * @param name The piece function is checking for-empress princess or knight
	 * 
	 * @return A boolean indicating if spot is being guarded by enemy empress princess or knight
	*/
	public  static boolean checkKnightBottom(pieces[][] board, pieces requested,String name){
		boolean guarded = true;
		if(requested.row+1 < controller.boardBoundsRow && requested.column+2 < controller.boardBoundsColumn){
			if(board[requested.row+1][requested.column+2].name.equals(name) && board[requested.row+1][requested.column+2].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row+1,requested.column+2,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
				
			}
		}
		if(requested.row+2 < controller.boardBoundsRow && requested.column+1 < controller.boardBoundsColumn){
			if(board[requested.row+2][requested.column+1].name.equals(name) && board[requested.row+2][requested.column+1].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row+2,requested.column+1,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
				
			}
		}
		if(requested.row+1 < controller.boardBoundsRow && requested.column-2 >=0){
			if(board[requested.row+1][requested.column-2].name.equals(name) && board[requested.row+1][requested.column-2].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row+1,requested.column-2,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
				return false;
			}
		}
		if(requested.row+2 < controller.boardBoundsRow && requested.column-1 >= 0){
			if(board[requested.row+2][requested.column-1].name.equals(name) && board[requested.row+2][requested.column-1].color.equals(requested.color)==false){	//horse
				guarded = false;
				pieces tempKnight = new pieces(requested.row+2,requested.column-1,name,requested.color); 
				endGame.checkTiles.add(tempKnight);
				endGame.mustGoTiles.add(tempKnight);
				return false;
			}
		}
		return true;
	}


	/**
	 * This function is to check if a pawn is preventing a king from moving to a space.
	 * 
	 * @param board The current board and pieces
	 * @param requested The requested location of the king to move to
	 * 
	 * @return a boolean indicating if space is being valid or not
	 */
	public static boolean checkPawn(pieces[][] board, pieces requested){
		if(requested.color.equals("white")){								//white king selected
			if(requested.row-1 >= 0 && requested.column-1>=0){
				if(board[requested.row-1][requested.column-1].name.equals("pawn") && board[requested.row-1][requested.column-1].color.equals(requested.color)==false){	//enemy pawn
					return false;
				}
			}
			if(requested.row-1 >= 0 && requested.column+1 < controller.boardBoundsColumn){
				if(board[requested.row-1][requested.column+1].name.equals("pawn") && board[requested.row-1][requested.column+1].color.equals(requested.color)==false){	//horse
					return false;
				}
			}
			return true;
		}
		else{
			if(requested.row+1 >= 0 && requested.column-1>=0){
				if(board[requested.row+1][requested.column-1].name.equals("pawn") && board[requested.row+1][requested.column-1].color.equals(requested.color)==false){	//enemy pawn
					return false;
				}
			}
			if(requested.row+1 >= 0 && requested.column+1 < controller.boardBoundsColumn){
				if(board[requested.row+1][requested.column+1].name.equals("pawn") && board[requested.row+1][requested.column+1].color.equals(requested.color)==false){	//horse
					return false;
				}
			}
			return true;
		}
	}

}







