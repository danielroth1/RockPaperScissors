package game;

/**
 * represents a Player, that owns a figure on the playing field
 * currently a player has just a name and a method to check, if 2
 * players have tha same name.
 * @author Daniel Roth
 */
public class Player {
	
	private String name;
	
	
	public Player(String name) {
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * is true if the names of the players equals
	 * @return true is the same names
	 */
	public Boolean equalsName(Player player){
		if (this.getName() == player.getName())
			return true;
		return false;
	}

}
