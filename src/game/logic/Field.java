package game.logic;

import game.Figure;
import game.FigureKind;
import game.Location;
import game.Move;
import game.Player;

import java.util.ArrayList;

import controll.Controll;

/**
 * this class represents a playing field, but not the one from the gui
 * but instead one consisting out of Figure as an Figure[][]-Array that can be used by
 * game logics
 * 
 * An empty field is declared with null, so you can just declar the field with a 
 * figure if you want to place one.
 * @author Daniel Roth
 */
public class Field {
	
	private Controll controll;
	
	private int ROW_COUNT = controll.ROW_COUNT;
	private int COLUMN_COUNT = controll.COLUMN_COUNT;
	
	private Figure[][] model;
	private int counterScissor;
	private int counterRock;
	private int counterPaper;
	private int counterTrap;
	private int counterFlag;
	
	
	/**
	 * creates an empty Field object
	 */
	public Field() {
		model = new Figure[ROW_COUNT][COLUMN_COUNT];
		
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=0; c<COLUMN_COUNT; c++)
				model[r][c] = null;
		}
	}
	
	/**
	 * create a new Field object out of an other
	 * @param field 
	 */
	public Field(Field field){
		this.model = field.getField();
	}
	
	/**
	 * clones the Field and returns the clone
	 * @param field getting cloned
	 * @return the Field clone
	 */
	public Field clone(){
		Field clone = new Field(this);
		return clone;
	}
	

	/**
	 * returns the Figure[][] model
	 * @return model
	 */
	public Figure[][] getField(){
		return model;
	}
	
	/**
	 * sets the field
	 * this method 
	 * @param model Figure[][] that represents the to be set field.
	 */
	public void setField(Figure[][] model){
		this.model = model;
	}
	
	/**
	 * sets a figure on the field at the position
	 * represented by column and row
	 * @param fig Figure to be set
	 * @param c column position
	 * @param r row position
	 */
	public void setField(Figure fig, int r, int c){
		model[r][c] = fig;
	}
	
	/**
	 * rotates the field
	 */
	public void rotate(){
		Figure[][] fieldClone = new Figure[ROW_COUNT][COLUMN_COUNT];
		int ROW_COUNT_clone = 0;
		int COLUMN_COUNT_clone = 0;
		
		
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=COLUMN_COUNT-1; c>-1; c--){
				fieldClone[ROW_COUNT_clone][COLUMN_COUNT_clone] = model[r][c];
				COLUMN_COUNT_clone += 1;
				if (COLUMN_COUNT_clone == COLUMN_COUNT)
					COLUMN_COUNT_clone = 0;
			}
			ROW_COUNT_clone += 1;
		}
		model = fieldClone;
	}
	
	/**
	 * refreshes the counter for FigurKinds on the field for a
	 * given player
	 */
	public void refreshCounters(Player player){
		//create a clone to make sure, that field does not change its values
		Figure[][] fig = this.getField();
		initiateCounters();
		
		
		//the whole field is getting checked
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (fig[r][c] == null)
					continue;
				FigureKind figKind = fig[r][c].getFigureKind();
				Player figPlayer = fig[r][c].getPlayer();
				if (figPlayer.equals(player)){ //the counters only raise, when the unit is owned by player
//					System.out.println("equals player");
					if (figKind == FigureKind.FLAG)
						counterFlag += 1;
					if (figKind == FigureKind.TRAP)
						counterTrap += 1;
					if (figKind == FigureKind.SCISSOR)
						counterScissor += 1;
					if (figKind == FigureKind.ROCK)
						counterRock += 1;
					if (figKind == FigureKind.PAPER)
						counterPaper += 1;
					}
			}
		}
		
	}
	
	/**
	 * initiates the local counters for the different Figure Types
	 * by setting them 0
	 */
	public void initiateCounters(){
		counterFlag = 0;
		counterTrap = 0;
		counterScissor = 0;
		counterRock = 0;
		counterPaper = 0;
	}
	
	/**
	 * checks if the counter values are legal
	 * @return true if values are legal, false if they are not
	 */
	public Boolean isCountersValid(){
		if (counterFlag > 1 ||
				counterTrap > 1 ||
				counterScissor > 4 ||
				counterRock > 4 ||
				counterPaper > 4){
			System.out.println("Maximum number of units reached");
			return false;
		}
		else if (counterFlag == 1 &&
				counterTrap == 1 &&
				counterScissor == 4 &&
				counterRock == 4 &&
				counterPaper == 4)
			return true;
		else{
			System.out.println("There are figures left to be placed");
			return false;
		}
	}
	
	public Boolean isCounterNumberTooHigh(){
		if (counterFlag > 1 ||
				counterTrap > 1 ||
				counterScissor > 4 ||
				counterRock > 4 ||
				counterPaper > 4)
			return true;
		return false;
	}
	
	/**
	 * returns a list that tells about each counter if its maximum
	 * number is reached.
	 * @return ArrayList<Integer> of counters
	 */
	public ArrayList<Boolean> getCountersMaxed(){
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		
		if (counterScissor >= 4)
			list.add(true);
		else
			list.add(false);
		if (counterRock >= 4)
			list.add(true);
		else
			list.add(false);
		if (counterPaper >= 4)
			list.add(true);
		else
			list.add(false);
		if (counterFlag >= 1)
			list.add(true);
		else
			list.add(false);
		if (counterTrap >= 1)
			list.add(true);
		else
			list.add(false);
		return list;
	}

	/**
	 * checks if the counter for the specific figureKind is too high
	 * @param figKind
	 */
	public Boolean isMaxNumberReached(FigureKind figKind){
		if (figKind == FigureKind.SCISSOR)
			if (counterScissor >= 4)
				return true;
		if (figKind == FigureKind.ROCK)
			if (counterRock >= 4)
				return true;
		if (figKind == FigureKind.PAPER)
			if (counterPaper >= 4)
				return true;
		if (figKind == FigureKind.TRAP)
			if (counterTrap >= 1)
				return true;
		if (figKind == FigureKind.FLAG)
			if (counterFlag >= 1)
				return true;
		return false;
	}
	
	/**
	 * checks, if there are more figures of one type placed on
	 * the field than allowed
	 * NOTE Counters have to be reseted with refreshCounters(Field field, Player player);
	 * @param field
	 * @return Boolean is true, if field is valid
	 */
	public Boolean validField(Field field, Player player){
		//the counters are getting refreshed so that if one shows an illegal value
		// you know, that the field can't be valid
		//NOTE the counters have to be reseted
		refreshCounters(player);
//		System.out.println(counterFlag);
//		System.out.println(counterTrap);
//		System.out.println(counterScissor);
//		System.out.println(counterRock);
//		System.out.println(counterPaper);
		
		if (isCountersValid())
			return true;
		else
			return false;
	}
	
	/**
	 * Calculates the possible moves of the field for a player
	 * the array contains ArrayList<Move> Objects witch contain
	 * up to 4 moves for the figure positioned at the same location
	 * as the ArrayList.
	 * @param player Player 
	 * @return ArrayList<Move>[][] containing all possible moves
	 */
	public ArrayList<Move>[][] calcPossibleMoves(Player player){
		@SuppressWarnings("unchecked")
		ArrayList<Move>[][] moveListArray = new ArrayList[ROW_COUNT][COLUMN_COUNT];
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				moveListArray[r][c] = calcPossibleMoves(new Location(r, c), player);
			}
		}
		return moveListArray;
	}
	
	/**
	 * calculates the possible moves of a figure located at p
	 * @param p Point location of figure
	 * @return ArrayList<Move> containing the max. 4 possible moves of the figure
	 */
	public ArrayList<Move> calcPossibleMoves(Location loc, Player playerIn){
		ArrayList<Move> moveList = new ArrayList<Move>();
		int rTo;
		int cTo;
		int rFrom;
		int cFrom;
		Location toLoc;
		for (Move newMove: theoreticalPossibleMoves(loc)){
			toLoc = newMove.getTo(); //aim location
			rTo = newMove.getTo().getRow(); //row of aim location
			cTo = newMove.getTo().getColumn(); //column of aim location
			rFrom = newMove.getFrom().getRow();
			cFrom = newMove.getFrom().getColumn();
			//the move will be added if...
			if (isInField(toLoc) && //if location is in the field
					model[rFrom][cFrom].getFigureKind() != FigureKind.FLAG &&//and is not a flag
					model[rFrom][cFrom].getFigureKind() != FigureKind.TRAP &&//and is not a trap
					(model[rTo][cTo] == null || //and null
						!playerIn.equals(model[rTo][cTo].getPlayer()) //or the figure has NOT the same player
					))
				moveList.add(newMove); //then the move can be added
		}		
		return moveList;
	}
	
	/**
	 * returns the theoretical possible moves of a figure.
	 * All the 4 moves to the nearby fields are meant with that.
	 * @param loc
	 * @return ArrayList<Move> of all theoretical possible moves.
	 */
	private ArrayList<Move> theoreticalPossibleMoves(Location loc){
		ArrayList<Move> moveList = new ArrayList<Move>();
		int r = loc.getRow();
		int c = loc.getColumn();
		Location up = new Location(r+1, c);
		Location down = new Location(r-1, c);
		Location left = new Location(r, c-1);
		Location right = new Location(r, c+1);
		
		moveList.add(new Move(loc, up));
		moveList.add(new Move(loc, down));
		moveList.add(new Move(loc, left));
		moveList.add(new Move(loc, right));
		
		return moveList;
	}
	
	/**
	 * checks if the location is still in the field
	 * @return true if it is
	 */
	public Boolean isInField(Location location){
		if (location.getRow()>ROW_COUNT-1 || location.getRow()<0 ||
				location.getColumn()>COLUMN_COUNT-1 || location.getColumn()<0)
			return false;
		else
			return true;
	}
	
	/**
	 * sets the field with all moving field null.
	 */
	public void setNull(){
		for(int r=0; r<ROW_COUNT; r++){
			for(int c=0; c<COLUMN_COUNT; c++){
				model[r][c] = null;
			}
		}
	}
	
	/**
	 * checks if the aimLoc is as an aim location for the moves in the possible
	 * moves calculated at location loc
	 * @param loc location of possible moves
	 * @param player 
	 * @param aimLoc aim location where it is checked if possible moves aim to it
	 * @return
	 */
	public Boolean isInPossibleMoves(Location loc, Player player, Location aimLoc){
		for (Move newMove: this.calcPossibleMoves(loc, player)){
			if (newMove.getTo().getRow() == aimLoc.getRow()&&
					newMove.getTo().getColumn() == aimLoc.getColumn())
				return true;
		}
		return false;
	}

	public int getCounterScissor() {
		return counterScissor;
	}

	public int getCounterRock() {
		return counterRock;
	}

	public int getCounterPaper() {
		return counterPaper;
	}

	public int getCounterTrap() {
		return counterTrap;
	}

	public int getCounterFlag() {
		return counterFlag;
	}
}
