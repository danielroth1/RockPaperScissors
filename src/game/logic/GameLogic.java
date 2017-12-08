package game.logic;

import game.AttackResult;
import game.Figure;
import game.FigureKind;
import game.Move;
import game.Player;
import ai.AI;
import controll.Controll;
import controll.GameControll;

/**
 * decides whitch's players turn is and controls
 * all the things going on on the playing field itself
 * interacts mainly with GameControll and Field
 * @author Daniel Roth
 */
public class GameLogic {
	private int ROW_COUNT = Controll.ROW_COUNT;
	private int COLUMN_COUNT = Controll.COLUMN_COUNT;
	private Controll controll;
	private GameControll gameControll;
	private AI ai;
	private Field field;
	private Player player;
	private Player playerAI;
	//FOR DRAW CALCULATION-------
	private FigureKind humanChoice;
	private FigureKind aiChoice;
	private Move drawMove;
	//---------------------------
	
	
	public void setComponents(GameControll gameControll, AI ai){
		this.gameControll = gameControll;
		this.ai = ai;
	}

	public GameLogic(Controll controll, Player humanPlayer, Player aiPlayer) {
		this.controll = controll;
		this.player = humanPlayer;
		this.playerAI = aiPlayer;
		field = new Field();
	}
	
	public Field getField(){
		return field;
	}
	
	/**
	 * this method is called when a game shell start
	 * (maybe here should the players get asserted and not earlier)
	 */
	public void startGame(){
		ai.provideStartingGrid();
		gameControll.provideStartingGrid();
	}
	
	/**
	 * sets the starting grid for a player
	 * @param figureField startingField, should be 14 arrays long
	 * @param player
	 * @param ai is true, if this method is used by ai
	 * @return true, if the starting grid was valid
	 */
	public Boolean setStartingGrid(Figure[][] figureField, Player playerIn){

		Field fieldClone = field.clone();
		
		if (playerAI.equals(playerIn))
			fieldClone.rotate(); //for the ai the field gets rotated, so that it starts at the top
		
		for(int r=0; r<2; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				fieldClone.setField(figureField[r][c], r, c);
//				System.out.println("field = " + figureField[r][c].getFigureKind());
			}
		}
		
		//test if starting grid is still valid
		if (fieldClone.validField(fieldClone, playerIn) == false){
			if (playerIn.equals(playerIn))
				gameControll.provideStartingGrid();
			else
				ai.provideStartingGrid();
			return false;
		}
		else{
			//now it gets back rotated so that the human field is at the bottom
			if (playerAI.equals(playerIn)){
				fieldClone.rotate();
//				System.out.println("rotate back");
			}
			//if the field is ready with 2 starting grids for each player one the game can start
			//and the player has to take his first move
			
			
			field.setField(fieldClone.getField());
//			System.out.println("Successfully startingGrid created");
			if (isPlayingFieldReady()) //currently human player starts always
				gameControll.provideNextMove();
			return true;
		}
	}
	
	/**
	 * checks if the current field is set with 2 starting grids and if
	 * they are ready to start
	 * @return true, if ready
	 */
	private Boolean isPlayingFieldReady(){
		Figure[][] fig = field.getField();
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (r == 2 || r == 3){ //both rows in the middle of the field have to be empty.
					if (fig[r][c] != null)
						return false;
					}
				else{ //upper and lower row must contain on every field figures.
					if (fig[r][c] == null)
						return false;
					}
			}
		}
		return true;
	}
	
	/**
	 * lets a unit move to a new field
	 * there are different kinds of situations you can find yourself in
	 * when moving your figure
	 * 1. regular move: the figure moves regularly to an empty field
	 * 2. fight: the figure moves to a field with a unit owned by an other player
	 * 		a fight will occur which will be
	 * 			- won
	 * 			- lost
	 * 			- draw
	 * A win and a lost can be described easily. A moving player who
	 * 		- wins: 	will set his unit to the enemy field
	 * 		- looses: 	will loose his unit, but will see the enemy figure
	 * 					he attacked because it's discovered
	 * 		- has a draw: 	will has to choose an other figure type for his unit 
	 * 						and a new fight occurs
	 * 		- wins against the flag: will win the game immediately
	 * 		- looses against the trap: 	will loose his figure, as well as the enemy player
	 * 									will loose his trap
	 * @param move the move the figure wants to take
	 * @return true, if the move was successfully (actually this is not necessary to implement)
	 */
	public Boolean newMove(Move move, Player playerIn){
		int rFrom = move.getFrom().getRow();
		int cFrom = move.getFrom().getColumn();
		int rTo = move.getTo().getRow();
		int cTo = move.getTo().getColumn();
		Figure[][] fig = field.getField();
		
		Figure movingFig = fig[rFrom][cFrom];
		
		
		//regular move
		if (fig[rTo][cTo] == null){
			regularMove(move);
			
			//if a move is taken
			moveIsTaken(playerIn);
			return true;
		}
		
		Figure aimFig = fig[rTo][cTo];
		
		//attack move(enemy figure)
		if(!playerIn.equals(aimFig.getPlayer())){
			AttackResult attackResult = movingFig.attack(aimFig);
			System.out.println("ATTACK_RESULT: " + attackResult);
			switch (attackResult){
			case WIN:{
				System.out.println("FIGHT: win");
				fig[rFrom][cFrom].setDiscovered();
				regularMove(move);
				moveIsTaken(playerIn);
				return true;
				}
				
			case LOOSE:{
				System.out.println("FIGHT: loose");
				fig[rFrom][cFrom] = null;
				aimFig.setDiscovered();
				attackHappened(playerIn);
				return true;
				}
				
			case DRAW:{
				System.out.println("FIGHT: draw");
				movingFig.setDiscovered();
				aimFig.setDiscovered();
				
				drawMove = move;
				//new choices of figureKinds
				ai.provideChoiceAfterFightIsDrawn();
				gameControll.provideChoiceAfterFightIsDrawn();

				return true;
				}
				
			case WIN_AGAINST_FLAG:{
				if (movingFig.getPlayer().equals(player)){
					gameControll.gameIsWon();
					ai.gameIsLost();
				}
				else{
					gameControll.gameIsLost();
					ai.gameIsWon();
				}
				System.out.println("FIGHT: win against flag");
				endGameState();
				return true;
				}
				
			case LOOSE_AGAINST_TRAP:{
				System.out.println("FIGHT: loose against trap");
				fig[rFrom][cFrom] = null;
				fig[rTo][cTo] = null;
				attackHappened(playerIn);
				
				return true;
				}
				
				
			}
		}
		//if no move is taken
		else{
			provideNextMove(playerIn);
			return false;
		}
		return true;
	}
	
	/**
	 * is called after a choice after a drawn fight is provided
	 * the move has to be executed again.
	 * @param figKind provided choice
	 */
	public void setChoiceAfterFightIsDrawn(Player playerIn, FigureKind figKind){
		
		Figure[][] fig = field.getField();
		//the figures from and to moved
		Figure figFrom = fig[drawMove.getFrom().getRow()][drawMove.getFrom().getColumn()];
		Figure figTo = fig[drawMove.getTo().getRow()][drawMove.getTo().getColumn()];
		Player movingPlayer = figFrom.getPlayer();
		
		if (playerIn.equals(player)){
			humanChoice = figKind;
			System.out.println("Human Choose: " + humanChoice);
		}
		if (playerIn.equals(playerAI)){
			aiChoice = figKind;
			System.out.println("AI Choose: " + aiChoice);
		}
		//if both players made their choice
		if (humanChoice != null &&
				aiChoice != null){
			//if the moving player is the human
			if (player.equals(movingPlayer)){
				//than the moving figure gets humanChoice FigureKind
				figFrom.setFigureKind(humanChoice);
				figTo.setFigureKind(aiChoice);
				//before new move is executed the old choices have to be deleted
				aiChoice = null;
				humanChoice = null;
				newMove(drawMove, player);
			}
			if (playerAI.equals(movingPlayer)){
				figFrom.setFigureKind(aiChoice);
				figTo.setFigureKind(humanChoice);
				aiChoice = null;
				humanChoice = null;
				newMove(drawMove, playerAI);
			}
			
		}
				
			
	}
	
	/**
	 * can be executed if a figure owned by playerIn attacked an other figure
	 * @param playerIn owner of the attacking figure
	 */
	private void attackHappened(Player playerIn){
		ai.figureAttacked();
		gameControll.FigureAttacked();
		provideNextMove(playerIn);
	}
	
	/**
	 * can be executed if a move was taken by the given player
	 * @param playerIn moving player
	 */
	private void moveIsTaken(Player playerIn){
		ai.figureMoved();
		gameControll.FigureMoved();
		provideNextMove(playerIn);
	}
	
	/**
	 * provides a next move for the given player
	 * @param playerIn player that has to take a turn
	 */
	private void provideNextMove(Player playerIn){
		//as long as possible moves are available
		
		if (arePossibleMovesLeft(playerAI) || arePossibleMovesLeft(player)){
			if (playerIn.equals(player)){
				if (arePossibleMovesLeft(playerAI))
					ai.provideNextMove();
				else //if the ai has no more possible moves the player can take the next turn
					gameControll.provideNextMove();
			}
			if (playerIn.equals(playerAI)){
				if (arePossibleMovesLeft(player))
					gameControll.provideNextMove();
				else //same is here for player
					ai.provideNextMove();
			}
		}
		else{ //no more possible moves for each player -> DRAW
			
		}
	
	}
	
	/**
	 * tests if there are any other possible moves for a player by checking if
	 * the player has only a flag or trap left
	 * @param playerIn figures of this player are getting checked
	 */
	public Boolean arePossibleMovesLeft(Player playerIn){
		Figure[][] fig = field.getField();
		
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (fig[r][c] == null)
					continue;
				if (playerIn.equals(fig[r][c].getPlayer())){
					if (fig[r][c].getFigureKind().equals(FigureKind.SCISSOR)||
							fig[r][c].getFigureKind().equals(FigureKind.ROCK)||
							fig[r][c].getFigureKind().equals(FigureKind.PAPER))
						return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * executes a regular move on the field
	 * that means there is no fight or anything expected
	 * @param move Move to be executed
	 */
	private void regularMove(Move move){
		Figure[][] fig = field.getField();
		//new field refresh
		fig[move.getTo().getRow()][move.getTo().getColumn()] = 
				fig[move.getFrom().getRow()][move.getFrom().getColumn()];
		//old field delete
		fig[move.getFrom().getRow()][move.getFrom().getColumn()] = null;
	}
	
	private void endGameState(){
		field.setNull();
	}
	
}
