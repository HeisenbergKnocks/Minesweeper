import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener {
	
	Tile[][] board = new Tile[8][10];
	
	
	//method to generate 10 mines
	public void generateMines() {
		
		int count = 0;
		while(count < 10) { //generate 10 mines
			
			//generate a random location (row, col)
			int row = (int)(Math.random() * board.length);
			int col = (int)(Math.random() * board[0].length);
			while(board[row][col].isMine()) {
				row = (int)(Math.random() * board.length);
				col = (int)(Math.random() * board[0].length);
			}
			//System.out.println("add mine");
			board[row][col].setMine(true); //set this tile to a Mine!
			count++; //increment mine count!
		}
		
	}
	
	
	
	public void reveal(int r, int c) {
		
		//System.out.println("revealing");
		
		//base-case is when you have nothing to do!
		if(r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0 || board[r][c].isEnabled() == false) {
			return;
		}else if(board[r][c].getCount() != 0) {
			board[r][c].setText(board[r][c].getCount() + "");
			board[r][c].setEnabled(false);
		}else {
			board[r][c].setEnabled(false);
			reveal(r-1, c); //north
			reveal(r+1, c); //south
			reveal(r, c-1); //west
			reveal(r, c+1); //east
		}
		
	}
	
	
	 
	/* Method that will take a given Tile and check to see if it is a mine
	 * If it is a mine, then update the mine count for the surrounding tiles
	 */
	public void updateCount(int r, int c) {
		
		//if no mine at location r, c exit!
		if(!board[r][c].isMine()) return;
		
		for(int row = r-1; row <= r + 1; row++) {
			for(int col = c-1; col <= c + 1; col++) {
				
				try {
					board[row][col].setCount(board[row][col].getCount() + 1);
				}catch(Exception e) {
					//do nothing! you went out of bounds
				}
				
			}
		}
		
	}
	
	
	
	public void checkWon() {
		
		int counter = 0;
		
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c].isEnabled()) {
					counter++;
				}
			}
		}
		
		if(counter == 10) {
			System.out.println("GG 2 EZ");
		}else {
			//System.out.println("literal goteem");
		}
		
	}
	
	
	
	//count the mines per tile
	public void countMines() {
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				updateCount(row,col);
			}
		}
		
	}
	
	
	
	/* Method for displaying the mines and or the surrounding mine count for each
	 * Tile object on the GUI
	 */
	public void displayMines() {
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("Images/mine.png"));
					//board[row][col].setText("B");
				}
				
			}
		}
		repaint();
		
	}
	
	
	
	public Board() {
		//construct the JFrame holding the tiles!
		JFrame frame = new JFrame("Minesweeper");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//add a layout manager (grid)
		frame.setLayout(new GridLayout(8, 10));
		
		
		
		//add the board!
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				Tile t = new Tile(row, col);
				t.addMouseListener(this);
				frame.add(t); //add it to the GUI
				
				board[row][col] = t; //for data structure
				
			}
		}
		
		
		//generate mines (call helper method)
		generateMines();
		//displayMines();
		countMines();
		frame.setVisible(true);
	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getButton()==1) { //left-click
			
			//System.out.println("left");
			Tile t = (Tile)(e.getComponent());
			
			if(t.isMine()) {
				
				System.out.println("Game Over");
				displayMines();

				//System.exit(-1);
			}else {
				reveal(t.getR(), t.getC());
			}
			
			checkWon();
			
		}else if(e.getButton()==3) { //right-click
			
			//System.out.println("right");
			Tile t = (Tile)(e.getComponent());
			t.toggle();
			
			//t.setIcon(new ImageIcon("Images/flag.png"));
			
			checkWon();
			
		}
		
	}


	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
