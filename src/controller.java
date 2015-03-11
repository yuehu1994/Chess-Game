import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is the controller for UI and logic
 * Also contains the game loop
 * 
 */
public class controller {
	static String turn = "white";						//this is whose turn it is 
	static pieces [][] board;
	static int boardBoundsRow = 8;
	static int boardBoundsColumn = 8;
	static JButton firstButton = null;
	static JButton secondButton = null;
	static boolean firstButtonPressed = false;
	static Color oldColor;
	static JPanel firstSelected;
	static int location = 0,firstRow = 0,firstColumn = 0,secondRow = 0,secondColumn = 0, blackPoints = 0, whitePoints = 0;
	
	/**
	 * This class is the main game loop. Waits for two squares to be picked, and then tries to move it. Checks
	 * for invalid moves, checks, checkmates, and stalemates.
	 * 
	 * @param e the button pressed 
	 */
	public static void buttonHandler(ActionEvent e){	
		
		if(firstButtonPressed == false){																	//	
			firstButton = (JButton)e.getSource();
			location = Integer.parseInt(firstButton.getName());
			firstRow = location/boardBoundsRow;
			firstColumn = location%boardBoundsColumn;
			firstSelected = (JPanel)userInterface.boardButtons.getComponent(location);
			oldColor = firstSelected.getBackground();
			if(board[firstRow][firstColumn].color.equals(turn) == false){									//picked opponent piece
				return;
			}
			firstButtonPressed = true;
			firstSelected.setBackground(new Color(255,255,51));
		}
		else{
			secondButton = (JButton)e.getSource();
			location = Integer.parseInt(secondButton.getName());
			secondRow = location/boardBoundsRow;
			secondColumn = location%boardBoundsColumn;
			firstButtonPressed = false;
			firstSelected.setBackground(oldColor);
			if(board[secondRow][secondColumn].color.equals(turn)){									//own piece can't move there pick new move
				return;
			}
			pieces oldPiece = new pieces(firstRow,firstColumn,board[firstRow][firstColumn].name,board[firstRow][firstColumn].color);
			pieces newPiece = new pieces(secondRow,secondColumn,board[secondRow][secondColumn].name,board[secondRow][secondColumn].color);
			if(game.checkAndMovePiece(board, board[firstRow][firstColumn], board[secondRow][secondColumn])){
				pushUndoVectors(firstButton.getIcon(), secondButton.getIcon(),oldPiece, newPiece);
				secondButton.setIcon(firstButton.getIcon());
				firstButton.setIcon(null);
				if(turn.equals("white")){
					king.whiteKingCheck = false;
				}
				if(turn.equals("black")){
					king.blackKingCheck = false;
				}
				
				switchPlayers();
				String playerTurn = "Player turn: ";
				userInterface.turn.setText(playerTurn.concat(turn));
				if(king.whiteKingCheck==true || king.blackKingCheck == true){
					String checkMate = checkEndGame();
					userInterface.check.setText("You are in check!");
					checkMateScenario(checkMate);
				}
				else{
					userInterface.check.setText("You are not in check");
				}
				if(endGame.staleMate(board, turn)){
					JOptionPane.showMessageDialog(userInterface.frame, "StaleMate!");
					newGame();
				}
			
			}
			else{
				JOptionPane.showMessageDialog(userInterface.frame, "Invalid Move! Please try again");
			}
		}
		
	}
	
	/**
	 * Push all data into vectors for undo purposes. Pushes pieces and images that undo function needs
	 * @param firstImage	image of a piece on a board	
	 * @param secondImage	image of a piece on a board
	 * @param firstPiece	first piece on a board-old spot where undo will put the piece
	 * @param secondPiece	second piece on a board-new spot-where piece is now
	 */
	public static void pushUndoVectors(Icon firstImage, Icon secondImage, pieces firstPiece, pieces secondPiece){
		undoMoveClass.oldPieces.push(firstPiece);
		undoMoveClass.newPieces.push(secondPiece);
		undoMoveClass.oldIcons.push(firstImage);
		undoMoveClass.newIcons.push(secondImage); 
		undoMoveClass.oldButtons.push(firstButton);
		undoMoveClass.newButtons.push(secondButton);
	}
	
	/**
	 * This is the handler for the undo button. This undos the last move
	 */
	public static void undoButton(){
		undoMoveClass.undoButtonHelper(board);
	}
	
	/**
	 * This is the handler for the forfeit button. This brings up a prompt and if both sides agree, a forfeit 
	 * is done.
	 */
	public static void forfeitButton(){
		String message;
		if(turn.equals("white")){
			message = "White would like to forfeit!\nDo you accept Black?";
		}
		else{
			message = "Black would like to forfeit!\nDo you accept White?";
		}
		
		Object[] options = {"Yes","No"};
		int dialogButton = JOptionPane.showOptionDialog(userInterface.frame,message,"Forfeit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
		if(dialogButton == JOptionPane.YES_OPTION){
			newGame();
			if(turn.equals("white")){
				incrementPoints("black");
			}
			else{
				incrementPoints("white");
			}
		}
	}
	
	/**
	 * Simple helper function that switches turns. Switches to opponent turn
	 */
	public static void switchPlayers(){
		if(turn.equals("white")){
			turn = "black";
		}
		else{
			turn = "white";
		}
	}
	
	/**
	 * Helper function that checks if a check mate is in effect
	 * 
	 * @return a string depicting which side wins or if no check mate
	 */
	public static String checkEndGame(){
		if(turn.equals("white") && king.whiteKingCheck == true){
			if(endGame.checkMate(board, "white")){
				incrementPoints("black");
				return "Check Mate! Black Wins!\n Press OK to start new game";
			}
		}
		if(turn.equals("black") && king.blackKingCheck == true){
			if(endGame.checkMate(board, "black")){
				incrementPoints("white");
				return "Check Mate! White Wins!\n Press OK to start new game";
			}
		}
		return "noCheckMate";
	}
	
	/**
	 * Helper function that increments the points for each side
	 * 
	 * @param color of the side to increment
	 */
	public static void incrementPoints(String side){
		if(side.equals("white")){
			whitePoints++;
			String tempString = "White points: ";
			userInterface.whitePoints.setText(tempString.concat(Integer.toString(whitePoints)));
		}
		else{
			blackPoints++;
			String tempString = "Black points: ";
			userInterface.blackPoints.setText(tempString.concat(Integer.toString(blackPoints)));
		}
	}
	
	/**
	 * Handles the case where checkmate is reached. Displays popup dialog box, sets points,
	 * and resets board for new game
	 * 
	 * @param color of the losing side
	 */
	public static void checkMateScenario(String loser){
		if(loser.equals("noCheckMate")){
			return;
		}
		JOptionPane.showMessageDialog(userInterface.frame, loser);
		
		userInterface.check.setText("");
		userInterface.turn.setText("Player turn: white");
		turn = "white";
		newGame();
	}
	
	/**
	 * This is a helper function that sets up a new game. Both in the view and in the model.
	 */
	public static void newGame(){
		setUp.buildBoard(8,8);
		for(int i = 0; i < boardBoundsRow * boardBoundsColumn; i++){
			JPanel selectedTile = (JPanel) userInterface.boardButtons.getComponent(i);
			selectedTile.removeAll();
			selectedTile.revalidate();
		}
		userInterface.addBlackPiece();
		userInterface.addWhitePiece();
		userInterface.check.setText("You are not in check");
		king.blackKingCheck = false;
		king.whiteKingCheck = false;
		turn = "white";
		String playerTurn = "Player turn: ";
		userInterface.turn.setText(playerTurn.concat(turn));
		undoMoveClass.clearStacks();
	}
}
