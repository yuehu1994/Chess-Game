/**
 *@section DESCRIPTION
 * This class initializes the controller.board design and adds all the pieces to the controller.board. It 
 * constructs the design of the board before the first move, or the start state of the board
 */

public class setUp {
	
	/**
	 * Function that initialized the board to standard chess board at the start.
	 * 
	 * @param rowNumber-number of rows
	 * @param columnNumber-number of columns
	 */
	public static void buildBoard( int rowNumber, int columnNumber){	
		controller.board = new pieces[rowNumber][columnNumber];
		for (int i = 0;i<columnNumber;i++){
			controller.board[1][i] = new pieces(1,i,"pawn","black");												//This initializes black pawns
			controller.board[6][i] = new pieces(6,i,"pawn","white");												//This initializes white pawns	
		}
		for (int i = 0;i<columnNumber;i++){
			switch(i){							
			case 0: controller.board[0][0] = new pieces(0,0,"rook","black");
					controller.board[7][0] = new pieces(7,0,"rook","white");
					controller.board[0][7] = new pieces(0,7,"rook","black");
					controller.board[7][7] = new pieces(7,7,"rook","white");
					
			case 1: controller.board[0][1] = new pieces(0,1,"knight","black");
					controller.board[7][1] = new pieces(7,1,"knight","white");
					controller.board[0][6] = new pieces(0,6,"knight","black");
					controller.board[7][6] = new pieces(7,6,"knight","white");
										
			case 2: controller.board[0][2] = new pieces(0,2,"bishop","black");
					controller.board[7][2] = new pieces(7,2,"bishop","white");
					controller.board[0][5] = new pieces(0,5,"bishop","black");
					controller.board[7][5] = new pieces(7,5,"bishop","white");
					
			case 3: controller.board[0][3] = new pieces(0,3,"queen","black");
					controller.board[7][3] = new pieces(7,3,"queen","white");
					
			
			case 4: controller.board[0][4] = new pieces(0,4,"king","black");
					controller.board[7][4] = new pieces(7,4,"king","white");
			}
		}
		for(int i = 2; i < rowNumber-2;i++){																//This nested for loop fills rest of controller.board with empty pieces
			for(int j = 0; j < columnNumber;j++){
				controller.board[i][j] = new pieces(i,j,"empty","null");
			}
		}
	}
	
	
	
	
}
