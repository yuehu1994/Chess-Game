
/**
 * @section DESCRIPTION
 * 
 * This class holds basic game functions. It holds the function that moves pieces, and toggles whether or not
 * a king is in check
 */
public class game {
	/**
	 * Moves pieces-main game play function. This is the function called for any movement of any piece on 
	 * the board. It checks if the move is valid or not, and if it is, moves the piece.
	 * 
	 * @param board The current board and pieces
	 * @param current The currently selected piece the player has chosen to move
	 * @param requested The tile that the user wishes to move the currently selected piece
	 * 
	 * @return a boolean that is true if move is successfully made, false if move is invalid and not made
	 */
	public static boolean checkAndMovePiece(pieces[][] board, pieces current, pieces requested){
		if(current.name.equals("king")){						//trying to move king
			if(king.validMove(board, current, requested)){
				movePieceHelper(board, current,requested);
				return true;
			}
			return false; 										//king move invalid
		}
		pieces oldPiece = new pieces(current.row,current.column,current.name,current.color);		//this holds the old space incase invalid move
		pieces newPiece = new pieces(requested.row,requested.column,requested.name,requested.color);
		pieces TempKing = createTempKing(current);													//create a temporary king for testing
		if(movePiece(board,current,requested)){														//moves piece to spot																		//move itself isn't valid	
				if(king.validHelper(board,TempKing)){													//if true, not in check			
					return true;
				}
			undoMoveClass.undoMove(board,oldPiece,newPiece);													//if not valid moves piece back
			removeCheck(current);
		}	
		return false;
	}
	
	
	/**
	 * This function is mainly a switch, checking what piece it is, making sure is valid, and moving it if it can.
	 * It is a helper function for checkAndMovePiece. It DOES NOT check if the move puts own king in check. Rather,
	 * it just checks if the piece is able to move there
	 * 
	 * @param board The current board with pieces
	 * @param current The current piece that wishes to be moved
	 * @param the requested spot the piece wants to be moved to
	 * 
	 * @return a boolean that is true if piece is moved and valid, false if not.
	 */
	public static boolean movePiece(pieces [][] board, pieces current, pieces requested){
		String PieceName = current.name;
		if(PieceName.equals("empress")){
			if(validMove.empressValid(board, current, requested)){
				movePieceHelper(board,current,requested);
				return true;
			}
		}
		if(PieceName.equals("princess")){
			if(validMove.princessValid(board, current, requested)){
				movePieceHelper(board,current,requested);
				return true;
			}
		}
		if(PieceName.equals("pawn")){
			if(validMove.pawnValid(board, current, requested)){
				movePieceHelper(board, current,requested);
				return true;
			}
		}
		if(PieceName.equals("bishop")){
			if(validMove.bishopValid(board, current, requested)){
				movePieceHelper(board, current,requested);
				return true;
			}
		}
		if(PieceName.equals("queen")){
			if(validMove.queenValid(board, current, requested)){
				movePieceHelper(board, current, requested);
				return true;
			}
		}
		if(PieceName.equals("king")){
			if(king.validMove(board, current, requested)){
				movePieceHelper(board, current,requested);
				return true;
			}
		}
		if(PieceName.equals("rook")){
			if(validMove.rookValid(board, current, requested)){
				movePieceHelper(board, current,requested);
				return true;
			}
		}
		if(PieceName.equals("knight")){
			if(validMove.knightValid(board, current, requested)){
				movePieceHelper(board, current,requested);
				return true;
			}
		}
		return false;
	}
	/**
	 * This is a helper function for movePiece. It moves pieces and updates the board
	 * 
	 * @param board The current board and pieces
	 * @param current The selected piece that will be moved
	 * @param requested The requested location the piece will be moved to
	 */
	public static void movePieceHelper(pieces[][] board, pieces current, pieces requested){
		if(current.name.equals("king")){
			if(current.color.equals("white")){
				king.whiteKingRow = requested.row;
				king.whiteKingColumn = requested.column;
			}
			else{
				king.blackKingRow = requested.row;
				king.blackKingColumn = requested.column;
			}
		}
		board[requested.row][requested.column].name = current.name;
		board[requested.row][requested.column].color = current.color;
		board[current.row][current.column].name = "empty";
		board[current.row][current.column].color ="null";
		
	}
	
	
	/**
	 * This function turns check off for a player. A simple helper function.
	 * 
	 * @param a piece of a player to check if current player is white or black
	 */
	public static void toggleCheck(pieces current){
		if(current.color.equals("white")){
			king.whiteKingCheck = false;
		}
		else{
			king.blackKingCheck = false;
		}
	}
	
	public static void removeCheck(pieces current){
		if(current.color.equals("black")){
			king.whiteKingCheck = false;
		}
		else{
			king.blackKingCheck = false;
		}
	}
	/**
	 * Helper function that creates a temporary king piece for testing
	 * 
	 * @param currentColor A piece of the player so we can get color of king
	 * @return a temporary king test piece with accurate color name and location.
	 * 
	 */
	public static pieces createTempKing(pieces currentColor){
		if(currentColor.color.equals("white")){
			return new pieces(king.whiteKingRow,king.whiteKingColumn,"king",currentColor.color);
		}
		else{
			return new pieces(king.blackKingRow,king.blackKingColumn,"king",currentColor.color);
		}
	}
	
	
}
