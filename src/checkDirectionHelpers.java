/**
 * @section DESCRIPTION
 * This class is helper functions for checkDirection
 * These functions check if space is a piece that puts king in check
 *
 */
public class checkDirectionHelpers {
	/**
	 * This function checks if a piece is queen rook or empress in the up/down/left/right direction
	 * Helper for checkDirection up and down. 
	 * 
	 * @param board The current board and pieces
	 * @param current The selected tile location on board
	 * @param tempRow The row iterator
	 * 
	 * @return A boolean if that piece is a queen rook or empress
	 */
	
	public static boolean checkDirectionsStraightHelper(pieces[][] board, pieces current,int tempRow, int tempColumn){
		String tempName = board[tempRow][tempColumn].name;
		String tempColor = board[tempRow][tempColumn].color;
		if((tempName.equals("queen")||tempName.equals("rook")||tempName.equals("empress")) && (tempColor.equals(current.color) == false)){
			pieces addPiece = new pieces(tempRow,tempColumn,tempName,tempColor);
			endGame.checkTiles.add(addPiece);
			return false;																		//queen, rook, or empress in the way
		}
		else{
			endGame.mustGoTiles.clear();
			return true;}																		//safe due to another piece
	}
	
	/**
	 * This function checks if a piece is queen bishop or princess in the diagonal directions
	 * Helper for checkDirection up and down. 
	 * 
	 * @param board The current board and pieces
	 * @param current The selected tile location on board
	 * @param tempRow The row iterator
	 * 
	 * @return A boolean if that piece is a queen bishop or princess
	 */
	public static boolean checkDirectionsDiagonalHelper(pieces[][] board, pieces current, int tempRow, int tempColumn){
		String tempName = board[tempRow][tempColumn].name;
		String tempColor = board[tempRow][tempColumn].color;
		if((tempName.equals("queen")||tempName.equals("princess")||tempName.equals("bishop")) && (tempColor.equals(current.color) == false)){
			pieces addPiece = new pieces(tempRow,tempColumn,tempName,tempColor);
			endGame.checkTiles.add(addPiece);
			return false;																		//queen,bishop, or princess in the way
		}
		else{
			endGame.mustGoTiles.clear();
			return true;}																		//safe due to another piece

	}
}
