/**
 * @section DESCRIPTION
 * This class holds helper functions for endGame.java. These are helper functions that 
 * calculate if an engame senerio (check mate or stale mate) is in effect
 */
public class endGameHelpers {

	

	/**
	 *  Is called if King is in check. It scans the board, and looks for all of players pieces. 
	 * Once finds a piece, Checks to see if that piece can block check by blocking the piece putting
	 * the king in check or taking the piece putting the king in check
	 *
	 * @param board The current board with pieces in current positions
	 * @param color The color of the current player
	 * @param staleMate a boolean flag checking if scan is checking for a stalemate or checkmate
	 * 
	 * @return A boolean saying if any piece is able to move if stalemate flag is on or if any piece can block checkmate
	 */
	public static boolean ScanBoard(pieces[][] board,String color, boolean staleMate){
		for( int i=0; i<controller.boardBoundsRow; i++){					//scan through the entire board
			for( int j=0; j<controller.boardBoundsColumn;j++){
	
				if(( board[i][j].name.equals("empty") == false) && board[i][j].color.equals(color)){		//square is not empty and your piece is on it
					pieces tempPiece = new pieces(i,j,board[i][j].name, board[i][j].color);
					if(staleMate){
						
						if(board[i][j].name.equals("king")){
							if(checkKing(board,tempPiece)){
								return true;
							}
						}
						else if(staleMateHelper(board,tempPiece)==false){
							return true;
						}
					}
					else if(board[i][j].name.equals("king") == false){											//don't have to check king	
						if(testKillCheck(board,board[i][j])){
							return true;
							
						}
					}			
				}
			}
		}
		return false;
	}
	
	/**
	 * This is a helper function for ScanBoard
	 * This function tests if the piece can be moved to one of the squares that will terminate a check
	 * Once ScanBoard finds one of the players pieces, it checks if that piece can move to a spot that
	 * terminates the check by comparing valid moves of that piece to the mustGoTiles vector
	 * 
	 * @param board The current board and pieces 
	 * @param piece The piece that is being tested to see if it can block check
	 * 
	 * @return A boolean stating whether or not the piece can terminate check
	 */
	public static boolean testKillCheck(pieces[][] board, pieces piece){
		pieces oldPiece = new pieces(piece.row,piece.column,piece.name,piece.color);
		for(int i = 0; i<endGame.mustGoTiles.size(); i++){
			pieces newPiece = new pieces(endGame.mustGoTiles.get(i).row,endGame.mustGoTiles.get(i).column,endGame.mustGoTiles.get(i).name,endGame.mustGoTiles.get(i).color);
			if(game.movePiece(board, piece, board[endGame.mustGoTiles.get(i).row][endGame.mustGoTiles.get(i).column])){							//checks to see if a piece can move there.
				undoMoveClass.undoMove(board,oldPiece,newPiece);
				return true;
				
			}
		}
		return false;
	}
	
	
		/**
	 * This function fills the CheckTiles vector in endGame.java. It finds all the pieces that put the king
	 * in check, and stores them in the CheckTiles vector. There is a flag that is checked if later it is found
	 * that only a single piece is putting the king in check, and therefore this function will also add in 
	 * tiles to the mustGoTiles.
	 * 
	 * @param board The current board and pieces
	 * @param tempKing The current location of the respective players king
	 */
	
	public static void fillCheckTilesVector(pieces[][] board, pieces tempKing){
		boolean
		done = true;
		done = checkDirection.checkUp(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkDown(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkLeft(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkRight(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkNorthEast(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkNorthWest(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkSouthEast(board, tempKing, tempKing, true);
		if(done == false){
			return;}
		done = checkDirection.checkSouthWest(board, tempKing, tempKing, true);		
		if(done == false){
			return;}
	}
	
	
	
	/**
	 * Checks to see if King has any valid moves. This function tries to move the king to all 8 directional  
	 * spots and tests if those spots are valid. It checks if its blocked and checks if it puts the king in check
	 * 
	 * @param board The current board with current pieces
	 * @param kingPiece The location of the respective players king
	 * 
	 * @return A boolean that is true if the king has valid spots it can move to, or false if it does not
	 */
	
	public static boolean checkKing(pieces[][] board, pieces kingPiece){
		pieces[] straightCoords = findCoordsStraight(kingPiece);
		pieces[] diagonalCoords = findCoordsDiagonal(kingPiece);
		for(int i = 0; i<straightCoords.length;i++){
			int row = straightCoords[i].row;
			int columns = straightCoords[i].column;
			if((row >= 0 && row < controller.boardBoundsRow) && (columns >= 0 && columns < controller.boardBoundsRow)){
				if(king.validMove(board,kingPiece,board[row][columns])){
					return true;
				}
			}
			row = diagonalCoords[i].row;
			columns = diagonalCoords[i].column;
			if((row >= 0 && row < controller.boardBoundsRow) && (columns >= 0 && columns < controller.boardBoundsRow)){
				if(king.validMove(board,kingPiece,board[row][columns])){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This is a helper function for checkKing. It generates an array of up,down,left,right locations
	 * (row/columns) for the checkKing function to use
	 * 
	 * @param piece The location of the king piece
	 * @return coordinates Returns an array holding locations of spots up,down,left,right of the king
	 */
	public static pieces[] findCoordsStraight(pieces piece){
		pieces[] coordinates = new pieces[4];								//coords will be up down left right
		int row = piece.row;
		int column = piece.column;
		coordinates[0] = new pieces(row-1,column);
		coordinates[1] = new pieces(row+1,column);
		coordinates[2] = new pieces(row,column+1);
		coordinates[3] = new pieces(row,column-1);
		return coordinates;	
		
	}
	
	
	/**
	 * This is a helper function for checkKing. It generates an array of all 4 diagonal locations
	 * (row/columns) for the checkKing function to use
	 * 
	 * @param piece The current location of the players respective king
	 * @return coordinates An array that holds all diagonal locations of king moves.
	 */
	public static pieces[] findCoordsDiagonal(pieces piece){
		pieces[] coordinates = new pieces[4];								//coords will be nw ne se sw
		int row = piece.row;
		int column = piece.column;
		coordinates[0] = new pieces(row-1,column-1);
		coordinates[1] = new pieces(row-1,column+1);
		coordinates[2] = new pieces(row+1,column-1);
		coordinates[3] = new pieces(row+1,column+1);
		return coordinates;	
		
	}
	
	/**
	 * This function is a helper for staleMateHelper function. It determines all possible locations
	 * a knight piece is able to move to. 
	 * 
	 * @param piece The current location on the board
	 * @return coordinates An array holds locations of all horse (l shaped) moves from a spot on board
	 */
	public static pieces[] findHorseMoves(pieces piece){					//coords will be nw ne se sw quadrants
		pieces[] coordinates = new pieces[8];
		int row = piece.row;
		int column = piece.column;
		coordinates[0] = new pieces(row-1,column-2);
		coordinates[1] = new pieces(row-2,column-1);
		coordinates[2] = new pieces(row-2,column+1);
		coordinates[3] = new pieces(row-1,column+2);
		coordinates[4] = new pieces(row+1,column-2);
		coordinates[5] = new pieces(row+2,column-1);
		coordinates[6] = new pieces(row+1,column+2);
		coordinates[7] = new pieces(row+2,column+1);
		return coordinates;
	}
	
	/**
	 * This is a helper function for stalemate. It takes a piece of a player and checks if that piece is able
	 * to move anywhere. 
	 * 
	 * @param board The current board and pieces
	 * @param piece The current location selected
	 * 
	 * @return a boolean that is true if that piece can move, false if piece cannot move
	 */
	public static boolean staleMateHelper(pieces [][] board, pieces piece){
		pieces[] upCoords = findCoordsStraight(piece);
		pieces[] diagCoords = findCoordsDiagonal(piece);
		pieces[] knightCoords = findHorseMoves(piece);
		int tempRow;
		int tempColumn;
		pieces oldPiece = new pieces(piece.row,piece.column,piece.name,piece.color);
		if(piece.name.equals("knight") || piece.name.equals("empress") || piece.name.equals("princess")){
			for(int i = 0; i<knightCoords.length;i++){
				tempRow = knightCoords[i].row;
				tempColumn = knightCoords[i].column;
				if(staleMateHelperHelper(piece,oldPiece,tempRow,tempColumn) == false){
					return false;
				}
			}
			return true;
		}
		for(int i = 0;i<upCoords.length;i++){
			tempRow = upCoords[i].row;
			tempColumn = upCoords[i].column;
			if(staleMateHelperHelper(piece,oldPiece,tempRow,tempColumn) == false){
				return false;
			}
			tempRow = diagCoords[i].row;
			tempColumn = diagCoords[i].column;
			if(staleMateHelperHelper(piece,oldPiece,tempRow,tempColumn) == false){
				return false;
			}
		}
			
		return true;
	}

	/**
	 * This function is a helper for stale mate helper. This function calcs if it is able to move to a certain spot.
	 * 
	 * @param piece	the new spot it wants to go
	 * @param oldPiece	the old spot it was at
	 * @param tempRow	the new row	
	 * @param tempColumn the new column
	 * @return
	 */
	public static boolean staleMateHelperHelper(pieces piece,pieces oldPiece,int tempRow, int tempColumn){
		if((tempRow >= 0 && tempRow < controller.boardBoundsRow) && (tempColumn >= 0 && tempColumn < controller.boardBoundsRow)){
			pieces newPiece = new pieces(tempRow,tempColumn,controller.board[tempRow][tempColumn].name,controller.board[tempRow][tempColumn].color);
			if(game.movePiece(controller.board, piece, controller.board[tempRow][tempColumn])){							//checks to see if a piece can move there.
				undoMoveClass.undoMove(controller.board,oldPiece,newPiece);
				return false;
			}
		}
		return true;
	}
}