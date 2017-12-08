package ai;

import game.Figure;
import game.FigureKind;
import game.Location;
import game.Move;
import game.Player;
import game.logic.Field;
import game.logic.GameLogic;
import game.logic.StartingGridFactory;

import java.util.ArrayList;

import controll.Controll;

/**
 * This class handles the actions of an easy AI.
 * It has therefore access to just the GameLogic and the Field class, because they should be
 * the only classes that are needed, to communicate with the game logic.
 * @author unknown
 */
public class AI {
	
	private GameLogic gameLogic;
	private Player player;
	private StartingGridFactory startingGridFactory = new StartingGridFactory();
	
	private Field field;
	private int ROW_COUNT = Controll.ROW_COUNT;
	private int COLUMN_COUNT = Controll.COLUMN_COUNT;
	
	
	public AI(GameLogic gameLogic, Player player) {
		this.gameLogic = gameLogic;
		this.player = player;
		field = gameLogic.getField();
	}
	
	/**
	 * provide a starting grid
	 * this method should set a starting grid
	 * (is currently setting a random one)
	 */
	public void provideStartingGrid(){
		gameLogic.setStartingGrid(startingGridFactory.getRandomStartingGrid(player).getField(), player);
	}
	
	/**
	 * provide a next move for a unit
	 * this method calculates currently a random move.
	 */
	public void provideNextMove(){
//		System.out.println("ai provide next move");
		gameLogic.newMove(getRandomMove(), player);
	}
	
	/**
	 * is called when a fight is drawn and a new decision as a figure type
	 * is needed
	 */
	public void provideChoiceAfterFightIsDrawn(){
		gameLogic.setChoiceAfterFightIsDrawn(player, getRandomChoice());
	}
	
	/**
	 * is called when a figure moved.
	 */
	public void figureMoved(){
		
	}
	
	/**
	 * is called when a figure attacked.
	 */
	public void figureAttacked(){
		
	}
	
	/**
	 * is called when the game started after setting the starting grid
	 */
	public void gameStarted(){
		
	}
	
	/**
	 * is called when the game is lost
	 */
	public void gameIsLost(){
		
	}
	
	/**
	 * is called when the game is won
	 */
	public void gameIsWon(){
		
	}
	
	/**
	 * is called when the game is drawn
	 */
	public void gameIsDrawn(){
		
	}
	
	//CREATING OF A RANDOM CHOICE--------------
	/**
	 * returns a random figure kind
	 * (only SCISSOR, ROCK, PAPER)
	 * @return random FigureKind
	 */
	private FigureKind getRandomChoice(){
		FigureKind figKind = null;
		
		ArrayList<FigureKind> figKindList = new ArrayList<FigureKind>();
		figKindList.add(FigureKind.SCISSOR);
		figKindList.add(FigureKind.ROCK);
		figKindList.add(FigureKind.PAPER);
		
		int randomNumber = (int)(Math.random() * figKindList.size());
		figKind = figKindList.get(randomNumber);
		return figKind;
	}
	//-----------------------------------------
	
	
	//CREATING OF A RANDOM MOVE----------------------------------------------------------
	/**
	 * returns a random Move of a random chosen figure on the playingField of the ai
	 * @return s.o.
	 */
	private Move getRandomMove(){
		Move rndMove;
		//list of a random location containing at least one possible move
		Location location = getLocationWithPossibleMove();
		//list of the possible moves of the figure standing on the location
		ArrayList<Move> moveList = field.calcPossibleMoves(location, player);

		//get a random Move from the list
		rndMove = moveList.get((int) (Math.random() * moveList.size()));
		return rndMove;
	}
	
	/**
	 * checks if the given figure has possible moves or
	 * it it even exists (== null)
	 * @param fig
	 * @return Boolean if it's true
	 */
	private Boolean hasPossibleMoves(Location loc){
		int r = loc.getRow();
		int c = loc.getColumn();
		Figure[][] fig = field.getField();
		if (fig[r][c] == null) //there is no figure at the given location
			return false;
		else{
			if (field.calcPossibleMoves(loc, player).isEmpty()) //the figure has no free move
				return false;
			else
				return true;
		}
	}
	
	/**
	 * returns a list of all locations on the field on witch figures are standing
	 * owned by ai.
	 * @return Arraylist<Location>
	 */
	private ArrayList<Location> giveListOfFiguresLocations(){
		ArrayList<Location> colFig = new ArrayList<Location>();
		Figure[][] fig = field.getField();
		
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				//if the field is empty
				if (fig[r][c] == null)
					continue;
				//if the owner of the figure is the ai
				if (player.equals(fig[r][c].getPlayer()))
					colFig.add(new Location(r, c));
			}
		}
		return colFig;
	}
	
	/**
	 * returns a location in the field standing a figure on with a at least one
	 * possible move
	 * @return Location with own figure
	 */
	private Location getLocationWithPossibleMove(){
		Location location;
		ArrayList<Location> listLoc = giveListOfFiguresLocations();
		location = listLoc.get((int) (Math.random() * listLoc.size()));
		
		while (!hasPossibleMoves(location)){
			//find a random place with an own figure positioned at location
			int randomPos = (int) (Math.random() * listLoc.size());
			location = listLoc.get(randomPos);
			listLoc.remove(randomPos);
		
		}
		//if there are possible moves available at a random place
		return location;
	}
	//-------------------------------------------------------------------

}
