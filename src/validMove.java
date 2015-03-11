/**
 * @section DESCRIPTON
 * Purpose of this class is to check to see if requested move is valid or not. Each piece has individual function
 * These check if a piece is legally allowed to move piece. It does not check if the move will put own king in check.
 * This class will indicate if the move will put the enemy king into check.
 */

public class validMove {

	/**
	 * This function is to test to see if a pawn can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean pawnValid(pieces[][] board, pieces current, pieces requested){
		
		if( requested.name.equals("empty") == false){						//check to see if wants to capture a piece
			if (current.color.equals("white")){
				if( ((current.column+1 == requested.column)||(current.column-1 == requested.column)) && (current.row-1==requested.row)){
					return true;
				}
			}
			else{
				if( ((current.column+1 == requested.column)||(current.column-1 == requested.column)) && (current.row+1==requested.row)){
					return true;
				}
			}
		}
		else if( requested.column == current.column){
			if(current.color.equals("white")){
				if(current.row-1 == requested.row){												//able to move up 1 step
					if(current.row-2 == king.blackKingRow && (current.column+1 == king.blackKingColumn || current.column-1 == king.blackKingColumn)){
						king.blackKingCheck = true;
					}
					return true;
				}
				if((current.row-2 == requested.row) && current.row == 6 && board[current.row-1][current.column].name.equals("empty")){						//able to move up 2 steps
					if(current.row-3 == king.blackKingRow && (current.column+1 == king.blackKingColumn || current.column-1 == king.blackKingColumn)){
						king.blackKingCheck = true;
					}
					return true;
				}
			}
			else{
				if(current.row+1 == requested.row){												//able to move up 1 step
					if(current.row+2 == king.whiteKingRow && (current.column+1 == king.whiteKingColumn || current.column-1 == king.blackKingColumn)){
						king.whiteKingCheck = true;
					}
					return true;
				}
				if((current.row+2 == requested.row) && current.row == 1 && board[current.row+1][current.column].name.equals("empty")){						//able to move up 2 steps
					if(current.row+3 == king.whiteKingRow && (current.column+1 == king.whiteKingColumn || current.column-1 == king.blackKingColumn)){
						king.whiteKingCheck = true;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This function is to test to see if a rook can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean rookValid(pieces[][] board, pieces current, pieces requested){
		if(current.row == requested.row || current.column == requested.column){
			boolean validMove = validMoveHelpers.findDirectionStraight(board, current, requested);
			if(validMove){
				validMoveHelpers.kingCheckStraight(board,current,requested);
			}
			return validMove;
		}
		return false;
	}
	
	/**
	 * This function is to test to see if a knight can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean knightValid(pieces[][] board, pieces current, pieces requested){
		boolean validMove = validMoveHelpers.knightHelper(board,current,requested);
		if(validMove){
			validMoveHelpers.knightCheck(board,current,requested);
		}
		return validMove;
	}
	/**
	 * This function is to test to see if a bishop can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean bishopValid(pieces[][] board, pieces current, pieces requested){
		boolean validMove = validMoveHelpers.findDirectionDiagonal(board,current,requested);
		if(validMove){
			validMoveHelpers.kingCheckDiagonal(board,current,requested);
		}
		return validMove;
	}
	
	/**
	 * This function is to test to see if a queen can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean queenValid(pieces[][] board, pieces current, pieces requested){
		if(current.row == requested.row || current.column == requested.column){
			boolean validMove = validMoveHelpers.findDirectionStraight(board, current, requested);
			if(validMove){
				validMoveHelpers.kingCheckStraight(board,current,requested);
				validMoveHelpers.kingCheckDiagonal(board,current,requested);
			}
			return validMove;
		}
		boolean validMove = validMoveHelpers.findDirectionDiagonal(board,current,requested);
		if(validMove){
			validMoveHelpers.kingCheckDiagonal(board,current,requested);
			validMoveHelpers.kingCheckStraight(board,current,requested);
		}
		return validMove;
	}
	
	/**
	 * This function is to test to see if a princess (knight + bishop) can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean princessValid(pieces[][] board, pieces current, pieces requested){
		boolean validMove = validMoveHelpers.findDirectionDiagonal(board,current,requested);
		if(validMove){
			validMoveHelpers.kingCheckDiagonal(board,current,requested);
			validMoveHelpers.knightCheck(board,current,requested);
			return true;
		}
		validMove = validMoveHelpers.knightHelper(board,current,requested);
		if(validMove){
			validMoveHelpers.knightCheck(board,current,requested);
			validMoveHelpers.kingCheckDiagonal(board,current,requested);
		}
		return validMove;
	}
	
	/**
	 * This function is to test to see if a empress (knight + rook) can be legally moved to a new location
	 * Also checks if the pawn move (if move is legal) puts enemy king in check
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean empressValid(pieces[][] board, pieces current, pieces requested){
		if(current.row == requested.row || current.column == requested.column){
			boolean validMove = validMoveHelpers.findDirectionStraight(board, current, requested);
			if(validMove){
				validMoveHelpers.kingCheckStraight(board,current,requested);
				validMoveHelpers.kingCheckDiagonal(board,current,requested);
			}
			return validMove;
		}
		else{
			boolean validMove = validMoveHelpers.knightHelper(board,current,requested);
			if(validMove){
				validMoveHelpers.knightCheck(board,current,requested);
				validMoveHelpers.kingCheckStraight(board,current,requested);
			}
			return validMove;
		}
		
	}
	
}
	

