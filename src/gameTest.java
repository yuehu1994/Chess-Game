import static org.junit.Assert.*;

import org.junit.Test;
/**
 * @section DESCRIPTION
 * Testing Check based logic. Tests everything to do with putting a king and getting a king out of check.
 * All tests start with king in check.
 */

public class gameTest {
	static pieces [][] board;
	
	/**
	 * Tests if a king can move out of check. Rook puts king in check, king moves out of check.
	 */
	@Test
	public void canMoveKing() {											//This tests getting out of check
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
		//Testing if we can move king
		board[1][4].name = "rook";
		board[1][4].color = "white";
		assertTrue(game.checkAndMovePiece(board,board[4][4],board[4][3]));
	}
	
	/**
	 * Testing that a king can't move itself into check. King tries to move itself into a square enemy
	 * queen is guarding. Move is invalid and fails
	 */
	@Test
	public void cantMoveKing(){		
		//Testing if we can't move king
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[1][5].color = "white";
		board[1][5].name = "queen";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[4][5]));
	}
	
	/**
	 * Test to see if a piece is able to move to block a check. Rook is putting king in check. Horse is able to 
	 * get in the way of the rook and block check. Also checks if horse can move anywhere else and not block 
	 * the check. Tests that moving a piece that does not put own king out of check while own king is currently
	 * in check fails.
	 */
	@Test
	public void checkAndMovePieceBlock(){
		//Testing if we can move a piece to block
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[5][2].color = "black";
		board[5][2].name = "knight";
		board[5][4].color = "black";
		board[5][4].name = "king";
		board[1][4].name = "rook";
		board[1][4].color = "white";
		king.blackKingColumn = 4;
		king.blackKingRow = 5;
		king.whiteKingColumn = 4;
		king.whiteKingRow = 7;
		assertFalse(game.checkAndMovePiece(board, board[5][2], board[3][1]));
		assertTrue(game.checkAndMovePiece(board, board[5][2], board[4][4]));
		
	}
	
	/**
	 * This test is meant to see if we can move princess to put king out of check by blocking the piece 
	 * putting king in check. Also makes sure princess can't move anywhere else that leaves king in check
	 */
	@Test
	public void princessToBlock(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].color = "black";
		board[4][4].name = "king";
		board[1][4].color = "white";
		board[1][4].name = "rook";
		board[4][2].color = "black";
		board[4][2].name = "princess";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		king.whiteKingColumn = 4;
		king.whiteKingRow = 7;
		assertTrue(game.checkAndMovePiece(board,board[4][2],board[3][4]));
		assertFalse(game.checkAndMovePiece(board, board[4][2], board[2][3]));
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].color = "black";
		board[4][4].name = "king";
		board[1][4].color = "white";
		board[1][4].name = "rook";
		board[4][2].color = "black";
		board[4][2].name = "princess";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		king.whiteKingColumn = 4;
		king.whiteKingRow = 7;
		assertTrue(game.checkAndMovePiece(board,board[4][2],board[2][4]));
		assertFalse(game.checkAndMovePiece(board, board[4][2], board[1][5]));
	}
	/**
	 * This test is meant to see if empress can block and put king out of check. Checks to see if empress
	 * can move to a spot that allows king out of check. Also checks an invalid move that leaves king in check.
	 * The invalid move would be valid if king was not in check
	 */
	@Test	
	public void empressToBlock(){
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].color = "black";
		board[4][4].name = "king";
		board[1][4].color = "white";
		board[1][4].name = "rook";
		board[2][2].color = "black";
		board[2][2].name = "empress";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		king.whiteKingColumn = 4;
		king.whiteKingRow = 7;
		assertTrue(game.checkAndMovePiece(board,board[2][2],board[1][4]));
		assertFalse(game.checkAndMovePiece(board, board[2][2], board[4][1]));
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].color = "black";
		board[4][4].name = "king";
		board[1][4].color = "white";
		board[1][4].name = "rook";
		board[2][2].color = "black";
		board[2][2].name = "empress";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		king.whiteKingColumn = 4;
		king.whiteKingRow = 7;
		assertTrue(game.checkAndMovePiece(board,board[2][2],board[2][4]));
		assertFalse(game.checkAndMovePiece(board, board[4][2], board[2][0]));
	}	
	
	/**
	 * Tests that player is not allowed to move a piece that puts their own king in check. For example moving 
	 * a piece that was blocking enemy queen from putting own king in check 
	 */
	@Test
	public void testCauseOwnCheck(){
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//princess can go here
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		king.whiteKingColumn = 0;
		king.whiteKingRow = 0;
		board[0][4].name = "rook";
		board[0][4].color = "white";
		board[2][4].name = "knight";
		board[2][4].color = "black";
		assertFalse(game.checkAndMovePiece(board, board[2][4], board[1][2]));
		board[2][4].name = "empress";
		board[2][4].color = "black";
		assertFalse(game.checkAndMovePiece(board, board[2][4], board[1][2]));
		
	}
	
	/**
	 * Tests helper function for pawn to make sure there are no Index out of bounds errors.
	 */
	@Test
	public void checkPawnFunction(){
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//princess can go here
		board[0][0].name = "king";
		board[0][0].color = "black";
		king.blackKingColumn = 0;
		king.blackKingRow = 0;
		king.whiteKingColumn = 0;
		king.whiteKingRow = 0;
		assertTrue(king.checkPawn(board, board[0][0]));
	}
	
}
