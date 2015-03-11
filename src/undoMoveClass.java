import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * @section DESCRIPTION
 * 
 * This class deals with undoing a move. Also does logic behind the undo move button in the UI
 * 
 */
public class undoMoveClass {
	static Stack<pieces> oldPieces = new Stack<pieces>();
	static Stack<pieces> newPieces = new Stack<pieces>();
	static Stack<Icon> oldIcons = new Stack<Icon>();
	static Stack<Icon> newIcons = new Stack<Icon>();
	static Stack<JButton> oldButtons = new Stack<JButton>();
	static Stack<JButton> newButtons = new Stack<JButton>();
	
	public static void undoButtonHelper(pieces[][] board){
		if(oldPieces.size()>0){
			pieces oldPiece = oldPieces.pop();
			pieces newPiece = newPieces.pop();
			Icon oldIcon = oldIcons.pop();
			Icon newIcon = newIcons.pop();
			JButton oldButton = oldButtons.pop();
			JButton newButton = newButtons.pop();
			controller.switchPlayers();
			undoMove(board,oldPiece,newPiece);
			oldButton.setIcon(oldIcon);
			newButton.setIcon(newIcon);
			String playerTurn = "Player turn: ";
			userInterface.turn.setText(playerTurn.concat(controller.turn));
		}
	}
	
	
	
	/**
	 * undo the move in logic
	 * 
	 * @param board the current playing board
	 * @param oldSpot the old spot
	 * @param newSpot the new spot
	 */
	public static void undoMove(pieces[][] board, pieces oldSpot, pieces newSpot){
		board[oldSpot.row][oldSpot.column].name = oldSpot.name;
		board[oldSpot.row][oldSpot.column].color = oldSpot.color;
		board[newSpot.row][newSpot.column].name = newSpot.name;
		board[newSpot.row][newSpot.column].color = newSpot.color;
		if(oldSpot.name.equals("king")){
			if(oldSpot.color.equals("white")){
				king.whiteKingRow = oldSpot.row;
				king.whiteKingColumn=oldSpot.column;
			}
			else{
				king.blackKingRow = oldSpot.row;
				king.blackKingColumn = oldSpot.column;
			}
		}
		checkIfUndoPutsPlayerInCheck(board);
	}
	
	/**
	 * Helper function. Goal of this function is to see if the undo puts the player back in check. If it does, 
	 * display that they are in check
	 * @param board
	 */
	public static void checkIfUndoPutsPlayerInCheck(pieces[][] board){
		pieces tempKing;
		if(controller.turn.equals("white")){
			tempKing = new pieces(king.whiteKingRow,king.whiteKingColumn,"king",controller.turn);
		}
		else{
			tempKing = new pieces(king.blackKingRow,king.blackKingColumn,"king",controller.turn);
		}
		if(king.validHelper(board,tempKing) == false){								//king in check
			userInterface.check.setText("You are in check!");
		}
		else{
			userInterface.check.setText("You are not in check");
		}
	}
	
	/**
	 * This function empties all 4 stacks. This will be only called when a new game is initiated 
	 */
	public static void clearStacks(){
		oldPieces.clear();
		newPieces.clear();
		oldIcons.clear();
		newIcons.clear();
		oldButtons.clear();
		newButtons.clear();
	}
	
	
	
	
}
