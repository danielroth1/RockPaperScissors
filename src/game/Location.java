package game;

/**
 * a location on the playing field
 * @author Daniel Roth
 */
public class Location {
	
	private int row;
	private int column;
	
	
	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}

}
