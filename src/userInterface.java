/*
 *
 * This class generates a user interface
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * @section DESCRIPTION
 * This class creates the UI and runs the main function. This is the VIEW
 * 
 * @author yuehu
 *
 */
public class userInterface extends JFrame {
	
	int numberRows = 8;
	int numberColumns = 8;
	static JPanel boardButtons;
	static JLabel turn = new JLabel("Player turn: white");
	static JLabel check = new JLabel("You are not in check");
	static JLabel whitePoints = new JLabel("White points: 0");
	static JLabel blackPoints = new JLabel("Black points: 0");
	
	static JFrame frame;
	
	/**
	 * Constructor for a new UI
	 */
	public userInterface(){
		boardButtons = new JPanel();
		boardButtons.setLayout(new GridLayout(numberRows,numberColumns));
		boardButtons.setPreferredSize(new Dimension(670,670));
		boardButtons.setBounds(0,0,670,670);
		boardButtons.setBackground(new Color(0,0,0,0));
		for(int i=0;i<numberRows;i++){
			for(int j=0;j<numberColumns;j++){
				addColor(i,j);
				
			}
		}
		addBlackPiece();
		addWhitePiece();
	}	
	
	/**
	 * This helper function adds a color to a tile (white or black color)
	 * @param row this is the row of the tile
	 * @param column this is the column of the tile
	 */
	public void addColor(int row, int column) {
		JPanel tilePiece = new JPanel(new BorderLayout());
		if((row+column)%2 == 0){						//white tile
			tilePiece.setBackground(new Color(224,224,224));
		}
		else{											//black tile
			tilePiece.setBackground(new Color(128,128,128));
		}
		boardButtons.add(tilePiece);
		
	}

	/**
	 * This function adds all buttons to the black side and adds the chess piece icons to those buttons
	 */
	public static void addBlackPiece(){
		JButton rook1 = new JButton(new ImageIcon("src/chessPieces/black_rook.png"));
		rook1 = setUpJButton(rook1,"0");
		rook1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		
		JPanel selectedTile = (JPanel) boardButtons.getComponent(0);
		selectedTile.add(rook1);
		
		JButton rook2 = new JButton(new ImageIcon("src/chessPieces/black_rook.png"));
		rook2 = setUpJButton(rook2,"7");
		rook2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile1 = (JPanel) boardButtons.getComponent(7);
		selectedTile1.add(rook2);
		
		JButton knight1 = new JButton(new ImageIcon("src/chessPieces/black_knight.png"));
		knight1 = setUpJButton(knight1,"1");
		knight1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile2 = (JPanel) boardButtons.getComponent(1);
		selectedTile2.add(knight1);
		
		JButton knight2 = new JButton(new ImageIcon("src/chessPieces/black_knight.png"));
		knight2 = setUpJButton(knight2,"6");
		knight2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile3 = (JPanel)boardButtons.getComponent(6);
		selectedTile3.add(knight2);
		
		JButton bishop1 = new JButton(new ImageIcon("src/chessPieces/black_bishop.png"));
		bishop1 = setUpJButton(bishop1,"2");
		bishop1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile4 = (JPanel)boardButtons.getComponent(2);
		selectedTile4.add(bishop1);
		
		JButton bishop2 = new JButton(new ImageIcon("src/chessPieces/black_bishop.png"));
		bishop2 = setUpJButton(bishop2,"5");
		bishop2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile5 = (JPanel)boardButtons.getComponent(5);
		selectedTile5.add(bishop2);
		
		JButton queen = new JButton(new ImageIcon("src/chessPieces/black_queen.png"));
		queen = setUpJButton(queen,"3");
		queen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile6 = (JPanel)boardButtons.getComponent(3);
		selectedTile6.add(queen);
		
		JButton king = new JButton(new ImageIcon("src/chessPieces/black_king.png"));
		king = setUpJButton(king,"4");
		king.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile7 = (JPanel)boardButtons.getComponent(4);
		selectedTile7.add(king);
		
		for(int i = 8;i<16;i++){
			JButton pawn = new JButton(new ImageIcon("src/chessPieces/black_pawn.png"));
			pawn = setUpJButton(pawn,String.valueOf(i));
			pawn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					controller.buttonHandler(e);
				}
			});
			JPanel selectedTile8 = (JPanel)boardButtons.getComponent(i);
			selectedTile8.add(pawn);
		}
		for(int i = 16;i<48;i++){
			JButton emptyButton = new JButton(new ImageIcon(""));
			
			emptyButton = setUpJButton(emptyButton,String.valueOf(i));
			emptyButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					controller.buttonHandler(e);
				}
			});
			JPanel selectedTile9 = (JPanel)boardButtons.getComponent(i);
			emptyButton.setIcon(null);
			selectedTile9.add(emptyButton);
		}
	}


	/**
	 * This function adds all buttons to the white side and adds the chess piece icons to those buttons
	 */
	public static void addWhitePiece(){
		JButton rook1 = new JButton(new ImageIcon("src/chessPieces/white_rook.png"));
		rook1 = setUpJButton(rook1, "56");
		rook1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile = (JPanel)boardButtons.getComponent(56);
		selectedTile.add(rook1);
		
		JButton rook2 = new JButton(new ImageIcon("src/chessPieces/white_rook.png"));
		rook2 = setUpJButton(rook2, "63");
		rook2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile1 = (JPanel)boardButtons.getComponent(63);
		selectedTile1.add(rook2);
		
		JButton knight1 = new JButton(new ImageIcon("src/chessPieces/white_knight.png"));
		knight1 = setUpJButton(knight1 , "57");
		knight1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile2 = (JPanel)boardButtons.getComponent(57);
		selectedTile2.add(knight1);
		
		JButton knight2 = new JButton(new ImageIcon("src/chessPieces/white_knight.png"));
		knight2 = setUpJButton(knight2,"62");
		knight2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile3 = (JPanel)boardButtons.getComponent(62);
		selectedTile3.add(knight2);
		
		JButton bishop1 = new JButton(new ImageIcon("src/chessPieces/white_bishop.png"));
		bishop1 = setUpJButton(bishop1, "58");
		bishop1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile4 = (JPanel)boardButtons.getComponent(58);
		selectedTile4.add(bishop1);
		
		JButton bishop2 = new JButton(new ImageIcon("src/chessPieces/white_bishop.png"));
		bishop2 = setUpJButton(bishop2, "61");
		bishop2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile5 = (JPanel)boardButtons.getComponent(61);
		selectedTile5.add(bishop2);
		
		JButton queen = new JButton(new ImageIcon("src/chessPieces/white_queen.png"));
		queen = setUpJButton(queen, "59");
		queen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile6 = (JPanel)boardButtons.getComponent(59);
		selectedTile6.add(queen);
		
		JButton king = new JButton(new ImageIcon("src/chessPieces/white_king.png"));
		king = setUpJButton(king,"60");
		king.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.buttonHandler(e);
			}
		});
		JPanel selectedTile7 = (JPanel)boardButtons.getComponent(60);
		selectedTile7.add(king);
		
		for(int i = 48;i<56;i++){
			JButton pawn = new JButton(new ImageIcon("src/chessPieces/white_pawn.png"));
			pawn = setUpJButton(pawn,String.valueOf(i));
			pawn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					controller.buttonHandler(e);
				}
			});
			JPanel selectedTile8 = (JPanel)boardButtons.getComponent(i);
			selectedTile8.add(pawn);
			
		}
	}
	
	/**
	 * Helper function for setting up buttons
	 */
	public static JButton setUpJButton(JButton button, String name){
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setName(name);
		return button;
		
	}
	
	/**
	 * This function sets up the labels for turn, if check or not, and points for each side
	 * @return panel that displays values for turn, check, and points
	 */
	public static JPanel setUpTopPanel(){
		JPanel topMargin = new JPanel(new BorderLayout());
    	JPanel turnPanel = new JPanel(new BorderLayout());
    	JPanel scorePanel = new JPanel(new BorderLayout());
    	turn.setFont(new Font("Courier New", Font.BOLD, 14));
    	check.setFont(new Font("Courier New", Font.BOLD, 14));
    	blackPoints.setFont(new Font("Courier New", Font.BOLD, 14));
    	whitePoints.setFont(new Font("Courier New", Font.BOLD, 14));
    	turnPanel.add(turn,BorderLayout.NORTH);
    	turnPanel.add(check,BorderLayout.WEST);
    	scorePanel.add(blackPoints,BorderLayout.NORTH);
    	scorePanel.add(whitePoints,BorderLayout.SOUTH);
    	topMargin.add(turnPanel,BorderLayout.NORTH);
    	topMargin.add(scorePanel,BorderLayout.SOUTH);
    	return topMargin;
	}
	
	/**
	 * Sets up the menu bar on top of the UI. This menu bar holds reset, forfeit, and undo buttons
	 * 
	 * @return the menu bar with buttons configured
	 */
	public static JMenuBar setUpMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Undo and Forfeit");
		JMenuItem undoButton = new JMenuItem("Undo Move", KeyEvent.VK_B);
		JMenuItem forfeitButton = new JMenuItem("Forfeit Match", KeyEvent.VK_C);
		JMenuItem restartButton = new JMenuItem("Restart Game", KeyEvent.VK_D);
		undoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.undoButton();
			}
		});
		forfeitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.forfeitButton();
			}
		});
		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.newGame();
			}
		});
		menu.add(restartButton);
		menu.add(undoButton);
		menu.add(forfeitButton);
		menuBar.add(menu);
		return menuBar;
	}
	
	/**
	 * Main function of the application. Sets up and displays frame.
	 * 
	 * @param args None
	 */
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	setUp.buildBoard(8, 8);													//set up the board
            	frame = new userInterface();
                frame.setPreferredSize(new Dimension(670,730));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(setUpTopPanel());
                frame.add(boardButtons);
                frame.setJMenuBar(setUpMenuBar());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


	
}
