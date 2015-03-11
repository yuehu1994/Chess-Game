

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @section DESCRIPTION
 * Purpose of this test file to to test Check and stale mate conditions.
 * Checks both when in check mate and when not in check mate
 */
public class checkAndStalemateTest {
	static pieces [][] board;
	
	
	/**
	 * Tests king not in check mate scenario. No enemy piece putting king in check 
	 */
	@Test
	public void notCheckMate() {
		
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		king.blackKingCheck = true;
		board[4][4].color = "black";
		board[4][4].name = "king";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
	
		assertFalse(endGame.checkMate(board, "black"));
	}
	
	/**
	 * test function that king is in check mate. Player cannot move and game is over
	 */
	@Test
	public void CheckMate2(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		king.blackKingCheck = true;
		board[4][4].color = "black";
		board[4][4].name = "king";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		
		board[0][3].color = "white";
		board[0][3].name = "queen";
		
		board[5][0].name = "rook";
		board[5][0].color = "white";
		
		board[2][7].name = "princess";
		board[2][7].color = "white";
		
		board[2][6].name = "empress";
		board[2][6].color = "white";
		
		board[2][5].name = "rook";
		board[2][5].color = "white";
		
	
		assertTrue(endGame.checkMate(board, "black"));
	}

	/**
	 * Function tests corner check mate. Makes sure there are no Index out of bounds errors
	 */
	@Test
	public void checkMateCorner(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		king.blackKingCheck = true;
		board[0][7].color = "black";
		board[0][7].name = "king";
		king.blackKingColumn = 7;
		king.blackKingRow = 0;
		
		board[3][4].color = "white";
		board[3][4].name = "queen";
		board[1][7].color = "black";
		board[1][7].name = "pawn";
		board[0][0].color = "white";
		board[0][0].name = "rook";
		assertTrue(endGame.checkMate(board,"black"));
	
	}
	
	
	/**
	 * Tests a scenario where king cannot move, but another piece owned by player can block the check, preventing
	 * a check mate. King cannot move and in check by enemy queen, but own empress can block queen
	 */

	
	@Test
	public void notCheckMate2(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		king.blackKingCheck = true;
		board[7][0].color = "black";
		board[7][0].name = "king";
		king.blackKingColumn = 0;
		king.blackKingRow = 7;
		
		board[0][0].color = "white";
		board[0][0].name = "queen";
		board[7][1].color = "black";
		board[7][1].name = "empress";
		assertFalse(endGame.checkMate(board,"black"));
	}
	
	/**
	 * Tests a stale mate. Player is in stale mate, and has no piece he can move. Game is over.
	 */
	
	@Test 
	public void testStaleMate(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].color = "black";
		board[4][4].name = "king";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		board[0][3].color = "white";
		board[0][3].name = "empress";
		board[5][0].color = "white";
		board[5][0].name = "rook";
		board[7][5].color = "white";
		board[7][5].name = "rook";
		board[3][7].color = "white";
		board[3][7].name = "empress";
		board[4][0].name = "pawn";
		board[4][0].color = "black";
		assertTrue(endGame.staleMate(board, "black"));
		
		
	}
	/**
	 * Checks if player is not in stale mate. Same set up as checkStaleMate, except this time player has extra queen 
	 * he/she is able to move.
	 */
	
	@Test
	public void testNotStaleMate(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].color = "black";
		board[4][4].name = "king";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		board[0][3].color = "white";
		board[0][3].name = "empress";
		board[5][0].color = "white";
		board[5][0].name = "rook";
		board[7][5].color = "white";
		board[7][5].name = "rook";
		board[3][7].color = "white";
		board[3][7].name = "empress";
		board[4][0].name = "pawn";
		board[4][0].color = "black";
		board[5][7].color = "black";
		board[5][7].name = "queen";
		assertFalse(endGame.staleMate(board, "black"));
	}
}