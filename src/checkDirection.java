/**
 * @section DESCRIPTION 
 * 
 * This File holds helper functions that checks to see if going in some direction is legal. Directions include up down left right 
 * northwest northeast south west south east. Also used in king logic to check if a move puts a king in danger-illegal king move
 */

public class checkDirection {
	/**
	 * This function checks to see if going directly up is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkUp(pieces[][] board, pieces current, pieces requested,boolean king){
		if(king){																							//for king scan all the way up
			int tempRow = current.row-1;
			int tempColumn = current.column;
			while(tempRow>=0){										
				pieces mustGoAdd = new pieces(tempRow,tempColumn,board[tempRow][tempColumn].name,board[tempRow][tempColumn].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[tempRow][tempColumn].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsStraightHelper(board,current,tempRow,tempColumn);																//safe due to another piece
				}
				tempRow--;
			}
			endGame.mustGoTiles.clear();
			return true;
		}
		for(int i = current.row-1;i>requested.row;i--){														//scan for pieces
			if(board[i][current.column].name.equals("empty") == false){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This function checks to see if going directly down is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkDown(pieces[][] board, pieces current, pieces requested,boolean king){
		if(king){			
			int tempRow = current.row+1;
			int tempColumn = current.column;
			while(tempRow<controller.boardBoundsRow){										
				pieces mustGoAdd = new pieces(tempRow,tempColumn,board[tempRow][tempColumn].name,board[tempRow][tempColumn].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[tempRow][tempColumn].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsStraightHelper(board,current,tempRow,tempColumn);																		//safe due to another piece
				}
				tempRow++;
			}
			endGame.mustGoTiles.clear();
			return true;
		}
		for(int i = current.row+1;i<requested.row;i++){
			if(board[i][current.column].name.equals("empty") == false){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This function checks to see if going directly right is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkRight(pieces[][] board, pieces current, pieces requested, boolean king){
		if(king){			
			int tempRow = current.row;
			int tempColumn = current.column+1;
			while(tempColumn<controller.boardBoundsRow){										
				pieces mustGoAdd = new pieces(tempRow,tempColumn,board[tempRow][tempColumn].name,board[tempRow][tempColumn].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[tempRow][tempColumn].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsStraightHelper(board,current,tempRow,tempColumn);																		//safe due to another piece
				}
				tempColumn++;
			}
			endGame.mustGoTiles.clear();
			return true;
		}
		for(int i = current.column+1;i<requested.column;i++){		
			if(board[current.row][i].name.equals("empty") == false){
				return false;				
			}
		}
		return true;
	}
	
	/**
	 * This function checks to see if going directly left is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkLeft(pieces[][] board, pieces current, pieces requested, boolean king){
		if(king){			
			int tempRow = current.row;
			int tempColumn = current.column-1;
			while(tempColumn>=0){									
				pieces mustGoAdd = new pieces(tempRow,tempColumn,board[tempRow][tempColumn].name,board[tempRow][tempColumn].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[tempRow][tempColumn].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsStraightHelper(board,current,tempRow,tempColumn);
				}
				tempColumn--;
			}
			endGame.mustGoTiles.clear();
			return true;
		}
		for(int i = current.column-1;i>requested.column;i--){
			if(board[current.row][i].name.equals("empty") == false){
					return false;
			}	
		}
		return true;
	}
	
	/**
	 * This function checks to see if going directly northwest is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkNorthWest(pieces[][] board, pieces current, pieces requested,boolean king){
		int rowIndex = current.row-1;
		int columnIndex = current.column-1;
		if(king){
			while(rowIndex >=0 && columnIndex >=0){
				pieces mustGoAdd = new pieces(rowIndex,columnIndex,board[rowIndex][columnIndex].name,board[rowIndex][columnIndex].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[rowIndex][columnIndex].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsDiagonalHelper(board, current, rowIndex, columnIndex);
				}
				rowIndex--;
				columnIndex--;
			}
			endGame.mustGoTiles.clear();
			return true;											
		}
		while(rowIndex != requested.row && columnIndex != requested.column){
			if(board[rowIndex][columnIndex].name.equals("empty") == false){
				return false;
			}
			rowIndex--;
			columnIndex--;
		}
		return true;
	}
	
	/**
	 * This function checks to see if going directly northeast is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkNorthEast(pieces[][] board, pieces current, pieces requested,boolean king){
		int rowIndex = current.row-1;
		int columnIndex = current.column+1;
		if(king){
			while(rowIndex >=0 && columnIndex < controller.boardBoundsColumn){
				pieces mustGoAdd = new pieces(rowIndex,columnIndex,board[rowIndex][columnIndex].name,board[rowIndex][columnIndex].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[rowIndex][columnIndex].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsDiagonalHelper(board, current, rowIndex, columnIndex);																					
				}
				rowIndex--;
				columnIndex++;
			}
			endGame.mustGoTiles.clear();																	//safe due to another piece
			return true;											
		}
		while(rowIndex != requested.row && columnIndex != requested.column){
			if(board[rowIndex][columnIndex].name.equals("empty") == false){
				return false;
			}
			rowIndex--;
			columnIndex++;
		}
		return true;
	}
	
	
	/**
	 * This function checks to see if going directly south west is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkSouthWest(pieces[][] board, pieces current, pieces requested,boolean king){
		int rowIndex = current.row+1;
		int columnIndex = current.column-1;
		if(king){
			while(rowIndex < controller.boardBoundsRow && columnIndex >=0){
				pieces mustGoAdd = new pieces(rowIndex,columnIndex,board[rowIndex][columnIndex].name,board[rowIndex][columnIndex].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[rowIndex][columnIndex].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsDiagonalHelper(board, current, rowIndex, columnIndex);																		//safe due to another piece
				}
				rowIndex++;
				columnIndex--;
			}
			endGame.mustGoTiles.clear();
			return true;											
		}
		while(rowIndex != requested.row && columnIndex != requested.column){
			if(board[rowIndex][columnIndex].name.equals("empty") == false){
				return false;
			}
			rowIndex++;
			columnIndex--;
		}
		return true;
				
	}
	
	/**
	 * This function checks to see if going directly south east is clear. The king flag chooses if it wants to use it
	 * for the purpose of scanning a path or the purpose of seeing if a piece in the direction puts the king in check.
	 * If flag is on, also fills the vectors used in check mate functions. If flag is on, the parameter current is 
	 * location of the king and function scans in direction seeing if a piece is putting the king in check in that direction.
	 * 
	 * @param board The playing current playing board with pieces on it
	 * @param current The piece that is currently chosen. This is the piece the player wishes to move
	 * @param requested The tile is chosen by the player for the current piece to move to
	 * @king flag to choose whether to scan to check move validity or see if a piece puts king in check
	 * 
	 * @return returns true if valid for moves or if nothing in the direction causes a check on king.
	 */
	public static boolean checkSouthEast(pieces[][] board, pieces current, pieces requested,boolean king){
		int rowIndex = current.row+1;
		int columnIndex = current.column+1;
		if(king){
			while(rowIndex < controller.boardBoundsRow && columnIndex < controller.boardBoundsColumn){
				pieces mustGoAdd = new pieces(rowIndex,columnIndex,board[rowIndex][columnIndex].name,board[rowIndex][columnIndex].color);
				endGame.mustGoTiles.add(mustGoAdd);
				if(board[rowIndex][columnIndex].name.equals("empty") == false){
					return checkDirectionHelpers.checkDirectionsDiagonalHelper(board, current, rowIndex, columnIndex);								//safe due to another piece
				}
				rowIndex++;
				columnIndex++;
			}
			endGame.mustGoTiles.clear();
			return true;											//reach edge of board. safe for king
		}
		while(rowIndex != requested.row && columnIndex != requested.column){
			if(board[rowIndex][columnIndex].name.equals("empty") == false){
				return false;
			}
			rowIndex++;
			columnIndex++;
		}
		return true;
	}
	
	
}
