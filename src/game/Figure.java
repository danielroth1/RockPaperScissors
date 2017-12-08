package game;

/**
 * represents a figure on the playing field
 * @author Daniel Roth
 */
public class Figure {
	
	private FigureKind figureKind;
	private Player player;
	private Boolean discovered;
	
	
	public Figure(FigureKind figureKind, Player player) {
		this.figureKind = figureKind;
		this.player = player;
		this.discovered = false;
	}
	
	public FigureKind getFigureKind(){
		return figureKind;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * checks if the Figure is discovered
	 * @return true if it is else false
	 */
	public Boolean isDiscovered(){
		if (discovered)
			return true;
		else
			return false;
	}
	
	/**
	 * sets the figure discovered
	 */
	public void setDiscovered(){
		discovered = true;
	}
	
	/**
	 * sets the figureKind of the figure
	 * @param figureKind to be set
	 */
	public void setFigureKind(FigureKind figureKind){
		this.figureKind = figureKind;
	}
	
	/**
	 * sets the player of the figure
	 * @param player to be set
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * returns the AttackResult between two figures
	 * @param f figure that gets attacked
	 * @return AttackResult -> (WIN, LOOSE, DRAW, WIN_AGAINST_FLAG, LOOSE_AGAINST_TRAP)
	 */
	public AttackResult attack(Figure f){
		AttackResult a = this.getFigureKind().attackResult(f.getFigureKind());
		
		for (AttackResult newAttackResult : AttackResult.values()){
		if (a == newAttackResult)
			return newAttackResult;}
		return null;
	}

}
