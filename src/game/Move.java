package game;


/**
 * represents a move a figure can do
 * @author Daniel Roth
 */
public class Move {
	
	private Location from;
	private Location to;
	
	
	public Move(Location from, Location to) {
		this.from = from;
		this.to = to;
	}
	
	public Location getFrom(){
		return from;
	}
	
	public Location getTo(){
		return to;
	}
	
	

}
