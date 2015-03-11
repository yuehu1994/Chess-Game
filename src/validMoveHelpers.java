/**
 * @section DESCRIPTION
 * Helper functions for validMove. Helps calculations
 */

public class validMoveHelpers {
	
	/**
	 * This function is a helper to determine if a diagonal move is legal for a piece.
	 *
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean findDirectionDiagonal(pieces[][] board, pieces current, pieces requested){
		int direction = 5;
		if(current.row>requested.row && current.column>requested.column){ //NorthWest quadrant
			if(current.row - requested.row == current.column - requested.column){
				direction = 1;		// valid northwest									
			}
		}
		else if(current.row>requested.row && current.column<requested.column){ //NorthEast quadrant
			if(current.row-requested.row == requested.column-current.column){
				direction = 2;		//valid northeast
			}
		}
		else if(current.row<requested.row && current.column>requested.column){ //Southwest quadrant
			if(requested.row - current.row == current.column - requested.column){
				direction = 3;		//valid southwest
			}
		}
		else if(current.row<requested.row && current.column<requested.column){//Southeast quadrant
			if(requested.row - current.row == requested.column - current.column){
				direction = 4;		//valid southeast
			}	
		}
		switch(direction){
		case 1: return checkDirection.checkNorthWest(board, current, requested,false);
		case 2: return checkDirection.checkNorthEast(board, current, requested,false);
		case 3: return checkDirection.checkSouthWest(board, current, requested,false);
		case 4: return checkDirection.checkSouthEast(board, current, requested,false);
		case 5: return false;
		}
		return true;
	}
	
	/**
	 * This is a helper function that checks to see if moving up, down, left, right is valid.
	 * It assumes that input is same column or row, as the calling functions check that input is 
	 * one of those 4 directions
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	public static boolean findDirectionStraight(pieces[][] board, pieces current, pieces requested){
		int direction = 5;																	//find direction of movement
		if(current.row > requested.row){direction = 1;}									//1 = up-java 1.6 doesn't allow switch for strings
		else if(current.row < requested.row){direction = 2;}							//2 = down
		else if(current.column < requested.column){direction = 3;}						//3 = right
		else{direction = 4;}															//4 = left	
		switch(direction){
		case 1: return checkDirection.checkUp(board,current,requested,false);
		case 2: return checkDirection.checkDown(board, current, requested,false);
		case 3: return checkDirection.checkRight(board,current,requested,false);
		case 4: return checkDirection.checkLeft(board,current,requested,false);
		}
		return false;
	}
	
	/**
	 * This function is a helper to determine if moving in an L shape is valid. 
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 * 
	 * @return A boolean indicating if move is valid.
	 */
	 public static boolean knightHelper(pieces[][] board, pieces current, pieces requested){
		int direction = 5;
		pieces addKnight = new pieces(requested.row,requested.column,"knight",current.color);
		if(current.row>requested.row && current.column>requested.column){ //In NorthWest quadrant
			direction = 1;
		}
		else if(current.row>requested.row && current.column<requested.column){ //In NorthEast quadrant
			direction = 2;					
		}
		else if(current.row<requested.row && current.column>requested.column){ //In Southwest quadrant
			direction = 3;		
		}
		else if(current.row<requested.row && current.column<requested.column){//In Southeast quadrant
			direction = 4;		
		}
		switch(direction){
		case 1:if( ((current.row-1 == requested.row) && (current.column-2 == requested.column)) || ((current.row-2 == requested.row) && (current.column-1 == requested.column)) ){
			   		if(board[requested.row][requested.column].color.equals(current.color) == false){
			   			endGame.checkTiles.add(addKnight);
			   			endGame.mustGoTiles.add(addKnight);
			   			return true;
			  		}
			}
		case 2:if ( ((current.row-2 == requested.row) && (current.column+1 == requested.column)) || ((current.row-1 == requested.row)&&(current.column+2 == requested.column))){
					if(board[requested.row][requested.column].color.equals(current.color) == false){
						endGame.checkTiles.add(addKnight);
			   			endGame.mustGoTiles.add(addKnight);	
						return true;
					}
			}
		case 3:if ( ((current.row+1 == requested.row) && (current.column-2 == requested.column)) || ((current.row+2 == requested.row) && (current.column-1 == requested.column))){
					if(board[requested.row][requested.column].color.equals(current.color) == false){
						endGame.checkTiles.add(addKnight);
			   			endGame.mustGoTiles.add(addKnight);
						return true;
					}
			}
		case 4:if ( ((current.row+1 == requested.row) && (current.column+2 == requested.column)) || ((current.row+2 == requested.row) && (current.column+1 == requested.column))){
					if(board[requested.row][requested.column].color.equals(current.color) == false){
						endGame.checkTiles.add(addKnight);
			   			endGame.mustGoTiles.add(addKnight);
						return true;
					}
			}
		}
		return false;
	} 
	
    /**
	 * Helper function to see if a piece movement puts an enemy king in check. Called on pieces that can put enemy 
	 * king in check via straight fashion(rook, queen, empress)
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 */
	public static void kingCheckStraight(pieces[][] board, pieces current, pieces requested){
		if(current.color.equals("white")){
			pieces tempPiece = new pieces(king.blackKingRow,king.blackKingColumn,"temp","null");
			if(findDirectionStraight(board,requested,tempPiece)){
				king.blackKingCheck = true;
			}
		}
		else{
			pieces tempPiece = new pieces(king.whiteKingRow,king.whiteKingColumn,"temp","null");
			if(findDirectionStraight(board,requested,tempPiece)){
				king.whiteKingCheck = true;
			}
		}
	}
	
	
	/**
	 * Helper function to see if a piece movement puts an enemy king in check. Called on pieces that can put enemy 
	 * king in check via diagonal fashion(bishop, queen, princess)
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 */
	public static void kingCheckDiagonal(pieces[][] board, pieces current, pieces requested){
		if(current.color.equals("white")){
			pieces tempPiece = new pieces(king.blackKingRow,king.blackKingColumn,"temp","null");
			if(findDirectionDiagonal(board,requested,tempPiece)){
				king.blackKingCheck = true;
			}
		}
		else{
		pieces tempPiece = new pieces(king.whiteKingRow,king.whiteKingColumn,"temp","null");
			if(findDirectionDiagonal(board,requested,tempPiece)){
				king.whiteKingCheck = true;
			}
		}	
	}
	
	/**
	 * Helper function to see if a piece movement puts an enemy king in check. Called on pieces that can put enemy 
	 * king in check via L shape fashion(knight, princess, empress)
	 * 
	 * @param board The current board and pieces
	 * @param current The current piece player wants to move
	 * @param requested The location player wants to move currently selected piece
	 */
	public static void knightCheck(pieces[][] board, pieces current, pieces requested){
		pieces tempKnight = new pieces(requested.row,requested.column,current.name,current.color);
		
		if(current.color.equals("white")){
			pieces tempKing = new pieces(king.blackKingRow,king.blackKingColumn,"king","black");
			if(knightHelper(board,tempKnight,tempKing)){
				king.blackKingCheck = true;
			}
		}
		else{
			pieces tempPiece = new pieces(king.whiteKingRow,king.whiteKingColumn,"king","white");
			if(knightHelper(board,tempKnight,tempPiece)){
				king.whiteKingCheck = true;
			}
		}
	}
	
}
