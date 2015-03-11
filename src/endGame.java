import java.util.Vector;

/**
 * @section DESCRIPTION
 * This class holds two functions. One to check if a player is in stale mate, and one to 
 * check if a player is in check mate. This class has two vectors. The vector checkTiles holds a
 * vector of pieces that put the king in check. The mustGoTiles holds every location on the board
 * that the opposing player can move one of their pieces to get out of check. These vectors are used 
 * to find if a player is in check mate
 */
public class endGame {
	/**
	 * This vector checkTiles holds all pieces that puts a king in check	
	 */
	static Vector<pieces> checkTiles = new Vector<pieces>();						//This holds pieces that put the king in check
	/**
	 * This vector mustGoTiles holds all spots that a player can move to get out of check
	 */
	static Vector<pieces> mustGoTiles = new Vector<pieces>();						//This holds coordinates pieces must move to	
	
	
	//Don't forget to clear vector every turn
	
	/**
	 * This function checks to see if a player is in a stale mate.
	 * 
	 * @param board The current board with all pieces set
	 * @param color The current color of the player whose turn it is
	 * 
	 * @return A boolean that is true if the player is in stale mate, false if not
	 */
	public static boolean staleMate(pieces[][] board, String color){
		checkTiles.clear();
		mustGoTiles.clear();
		if(endGameHelpers.ScanBoard(board,color,true)){
			return false;
		}
		return true;
	}
	
	
	/**
	 * Function tests if in checkMate. Checks if the player has any way to get out of checkmate.
	 * Function determines if a player is able to make any valid moves
	 * Called only when king is in check
	 * 
	 * @param board The current board with all pieces set
	 * @param color The color of the person whose turn it is
	 * 
	 * @return A boolean that is true if the player is in check mate, false if not.
	 */
	public static boolean checkMate(pieces [][] board, String color){
		checkTiles.clear();																			//goes ahead and clears
		mustGoTiles.clear();
		pieces tempKing;
		if(color.equals("white")){
			tempKing = new pieces(king.whiteKingRow, king.whiteKingColumn, "king", color);
		}
		else{
			tempKing = new pieces(king.blackKingRow, king.blackKingColumn, "king", color);
		}
		boolean kingCanMove = endGameHelpers.checkKing(board, tempKing);
		if(kingCanMove == true){
			return false;}																			//King has no valid moves. If it has valid moves, can't be checkmate
		checkTiles.clear();																			//goes ahead and clears
		mustGoTiles.clear();
		endGameHelpers.fillCheckTilesVector(board, tempKing);								//adds all pieces that put king in check
		king.checkKnightMove(board, tempKing);														//to vector. Scans every tile that can put king incheck			
		king.checkPawn(board, tempKing);															//Checks all around king piece-fills array
		if(checkTiles.size()>1 && kingCanMove == false){											//more than one piece is putting king in check
			return true;
		}
		return !endGameHelpers.ScanBoard(board,color,false);											//no pieces can block check and king cant move
	}
	
}



