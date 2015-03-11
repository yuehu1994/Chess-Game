import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @section DESCRIPTION
 * 
 * These are unit tests that test validity of moves. Mainly tests validMove.java. Tests each piece 
 * Checks if piece can eat another piece, spaces it can/can't move
 * 
 */
public class validMoveTest {
	/**
	 * Test that checks validity of pawn. Checks if it can take enemy piece, put king in check, move 1 square
	 * or move 2 squares the beginning
	 */
	@Test
	public void testpawnValid() {								//pawn works!
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//checks if pawn can eat another piece
		board[1][4].name = "pawn";
		board[1][4].color = "black";
		board[2][5].name = "knight";
		board[2][5].color = "white";
		assertTrue(validMove.pawnValid(board, board[1][4], board[2][5]));
		
		//checks if pawn can move up 2 spaces if at start
		assertTrue(validMove.pawnValid(board, board[1][4], board[3][4]));
		//checks if pawn can put a king in check
		board[4][5].name = "king";
		board[4][5].color = "white";
		king.whiteKingColumn = 5;
		king.whiteKingRow = 4;
		validMove.pawnValid(board, board[1][4], board[3][4]);
		assertTrue(king.whiteKingCheck);
		//spot pawn should not be able to go to 
		assertFalse(validMove.pawnValid(board, board[1][4], board[6][6]));
	}
	
	/**
	 * Tests validity of rook. Checks spots rook can move, spots rook can't move, and whether a move will
	 * put enemy king in check
	 */
	@Test
	public void testrookValid(){						//Testing rook
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//rook should be able to move to this spot
		board[4][2].name = "rook";
		board[4][2].color = "black";
		assertTrue(validMove.rookValid(board, board[4][2], board[4][6]));
		assertTrue(validMove.rookValid(board, board[4][2], board[4][0]));
		assertTrue(validMove.rookValid(board, board[4][2], board[2][2]));
		assertTrue(validMove.rookValid(board, board[4][2], board[6][2]));
		board[4][5].name = "queen";
		board[4][5].color = "black";
		//rook should not be able to move here
		assertFalse(validMove.rookValid(board, board[4][2], board[4][7]));
		//check if rook can put king in check
		king.whiteKingColumn = 5;
		king.whiteKingRow = 6;
		board[6][5].name = "king";
		board[6][5].color = "white";
		validMove.rookValid(board, board[4][2], board[6][2]);
		assertTrue(king.whiteKingCheck);	
	}
	
	/**
	 * Tests validity of knight. Checks spots knight can move, knights can't move, and whether knight
	 * can put a king in check
	 */
	@Test
	public void testknightValid(){							//Testing knight
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//knight should be able to move here
		board[7][6].name = "knight";
		board[7][6].color= "black";
		assertTrue(validMove.knightValid(board, board[7][6], board[5][7]));
		
		//knight should not be able to move here
		assertFalse(validMove.knightValid(board, board[2][2], board[5][5]));
		
		//knight should put king in check
		king.whiteKingColumn = 5;
		king.whiteKingRow = 5;
		king.whiteKingCheck = false;
		board[5][5].name = "king";
		board[5][5].color = "white";
		validMove.knightValid(board, board[2][2], board[4][3]);
		assertTrue(king.whiteKingCheck);
	}

	/**
	 * Tests validity of bishop. Checks spots bishop can move, can't move, and whether it can put king in check 
	 */
	@Test
	public void testbishopValid(){							//testing bishop
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//bishop should be able to move here
		board[7][5].name = "bishop";
		board[7][5].color = "black";
		assertTrue(validMove.bishopValid(board, board[7][5], board[5][7]));
		//assertTrue(validMove.bishopValid(board, board[4][4], board[5][5]));
		//assertTrue(validMove.bishopValid(board, board[4][4], board[2][6]));
		//assertTrue(validMove.bishopValid(board, board[4][4], board[6][2]));
		
		//bishop should not be able to move here
		assertFalse(validMove.bishopValid(board, board[7][5], board[4][4]));
		
		//bishop should be able to put the king in check
		king.whiteKingColumn = 7;
		king.whiteKingRow = 5;
		board[5][7].name = "king";
		board[5][7].color = "white";
		validMove.bishopValid(board, board[4][4], board[6][6]);
		assertTrue(king.whiteKingCheck);
		
	}
	
	/**
	 * Tests validity of queen. Checks places queen can move, can't move, and checks if queen can put
	 * opponent king in check
	 */
	@Test
	public void testqueenValid(){
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//queen can go here
		board[4][4].name = "queen";
		board[4][4].color = "black";
		assertTrue(validMove.queenValid(board, board[4][4], board[2][2]));
		assertTrue(validMove.queenValid(board, board[4][4], board[2][4]));
		assertTrue(validMove.queenValid(board, board[4][4], board[1][7]));
		assertTrue(validMove.queenValid(board, board[4][4], board[6][6]));
		assertTrue(validMove.queenValid(board, board[4][4], board[4][6]));
		assertTrue(validMove.queenValid(board, board[4][4], board[6][4]));
		assertTrue(validMove.queenValid(board, board[4][4], board[7][1]));
		assertTrue(validMove.queenValid(board, board[4][4], board[4][1]));
		
		//queen can't go here
		assertFalse(validMove.queenValid(board, board[4][4], board[0][2]));
		assertFalse(validMove.queenValid(board, board[4][4], board[6][5]));
		
		//queen blocked
		board[3][3].name = "knight";
		board[3][3].color = "black";
		assertFalse(validMove.queenValid(board, board[4][4], board[1][1]));
		
		//queen can put king in check
		king.whiteKingColumn = 7;
		king.whiteKingRow = 5;
		board[5][7].name = "king";
		board[5][7].color = "white";
		validMove.queenValid(board, board[4][4], board[4][7]);
		assertTrue(king.whiteKingCheck);
				
	}
	
	
	/**
	 * Test empress piece- combo of knight and rook. Checks places empress can move and can't move
	 */
	@Test
	public void testEmpressValid(){
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//empress can go here
		board[4][4].name = "empress";
		board[4][4].color = "black";
		assertTrue(validMove.empressValid(board, board[4][4], board[6][3]));
		assertTrue(validMove.empressValid(board,board[4][4], board[1][4]));
		assertFalse(validMove.empressValid(board,board[4][4], board[7][5]));	
	}
	
	/**
	 * Tests for Princess piece- combo of knight and biship. Checks places princess can and can't move
	 */
	@Test
	public void testPrincessValid(){
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		//princess can go here
		board[4][4].name = "princess";
		board[4][4].color = "black";
		assertTrue(validMove.princessValid(board, board[4][4], board[6][3]));
		assertTrue(validMove.princessValid(board,board[4][4], board[1][1]));
		assertFalse(validMove.princessValid(board,board[4][4], board[1][5]));
			
	}
	
	/**
	 * Large test checking validity of king movements. Checks if king can move to not guarded spot, can't move to
	 * spots more than 1 block away. Checks places king can't move due to enemy piece guarding spot. 
	 */
	
	@Test
	public void testKingValid(){
		/*
		 * Test to check if king can move
		 */
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		//king should be able to move here
		assertTrue(game.checkAndMovePiece(board, board[4][4], board[4][5]));						//this passes
	

		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		king.whiteKingColumn = 0;
		king.whiteKingRow = 0;
		//king should be able to move here		
		assertTrue(game.checkAndMovePiece(board, board[4][4], board[3][4]));						//this passes
		
	
		/*
		 * Test to check a spot king cannot move (not 1 sq away)
		 */
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		//king should not be able to move here
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[4][7]));
	

		/*
		 * Test to check if rook can stop king
		 */
	 	board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		
		board[0][5].name = "rook";
		board[0][5].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[4][5]));

		board = new pieces[8][8];
		
		/*
		 * Test to check if queen can block king
		 */
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		board[6][3].name = "queen";
		board[6][3].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[5][3]));
	
		/*
		 * Test to check if bishop can block king
		 */
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		//Check Bishop guards
		board[1][6].name = "bishop";
		board[1][6].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[3][4]));
	
		/*
		 * Check to see if pawn can block king
		 */
		board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		//Check pawn guards
		board[4][3].name = "pawn";
		board[4][3].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[3][4]));
	
		
		/*
		 * Test to see if opponent king can block 
		 */
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		
		king.whiteKingColumn = 5;
		king.whiteKingRow = 6;
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		board[6][5].name = "king";
		board[6][5].color = "white";
		board[4][4].name = "king";
		board[4][4].color = "black";
		assertFalse(game.checkAndMovePiece(board,board[4][4],board[5][5]));
	}
	
	/**
	 * Checks to see if  empress princess and knight can prevent enemy king from going to a 
	 * certain tile.
	 */
	@Test
	public void  checkKnightGuards(){
		pieces [][] board = new pieces[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new pieces(i,j,"empty","null");
			}
		}
		board[4][4].name = "king";
		board[4][4].color = "black";
		king.blackKingColumn = 4;
		king.blackKingRow = 4;
		//Check knight guards
		board[7][6].name = "empress";
		board[7][6].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[5][5]));
		board[7][6].name = "knight";
		board[7][6].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[5][5]));
		board[7][6].name = "princess";
		board[7][6].color = "white";
		assertFalse(game.checkAndMovePiece(board, board[4][4], board[5][5]));

	}

}
