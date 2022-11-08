import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton{
	private int r, c; //position
	private int count; //how many surrounding bombs does it have?
	private boolean mine;
	private boolean flagged;
	
	
	
	public Tile(int r, int c) {
		this.r = r;
		this.c = c;
	}

	
	public void toggle() {
		flagged = !flagged;
		if(flagged) {
			this.setIcon(new ImageIcon("Images/flag.png"));
		}else {
			this.setIcon(null);
		}
	}
	
	
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	
	
	
}
