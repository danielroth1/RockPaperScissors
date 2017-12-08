package controll;

import game.Player;
import game.logic.GameLogic;
import game.logic.StartingGridFactory;
import gui.Field.PlayingField;
import gui.Field.ProvideChoice;
import gui.Field.startingGrid.GridPane;

/**
 * this class makes an access for the gameLogic to the gui possible
 * thus the gui is used, the class is the interaction between humanPlayer and
 * gameLogic. The AI does not need to communicate with the gui so it has no access to
 * this class.
 * @author Daniel Roth
 */
public class GameControll {
	
	private GameControll gameControll = this;
	private GameLogic gameLogic;
	private Controll controll;
	private PlayingField playingField;
	private ProvideChoice provideChoice;
	private GridPane gridPane;
	private StartingGridFactory startingGridFactory = new StartingGridFactory();
	
	private Player player;
	private Player playerAI;
	private Boolean quickStartDetector = false;
	
	public void setComponents(PlayingField playingField, ProvideChoice provideChoice, GridPane gridPane){
		this.playingField = playingField;
		this.provideChoice = provideChoice;
		this.gridPane = gridPane;
		
		playingField.setField(gameLogic.getField());
	}
	
	public GameControll(Controll controll, GameLogic gameLogic, Player player, Player playerAI) {
		this.gameLogic = gameLogic;
		this.controll = controll;
		this.player = player;
		this.playerAI = playerAI;
	}
	
	public void gameStarted(){
		
	}
	
	public void provideStartingGrid(){
		if (quickStartDetector)
			gameLogic.setStartingGrid(startingGridFactory.getRandomStartingGrid(player).getField(), player);
		else
			gridPane.setVisible(true);
		playingField.refreshField();
	}
	
	public void handleQickStart(){
		quickStartDetector = true;
	}
	
	/**
	 * is called when the player has to take a move with a figure.
	 */
	public void provideNextMove(){
//		System.out.println("provide next move");
		playingField.provideNextMove();
		
	}
	
	/**
	 * is called when a fight is drawn and a new decision as a figure type
	 * is needed
	 */
	public void provideChoiceAfterFightIsDrawn(){
		playingField.refreshField();
		provideChoice.provideChoice(true);
	}
	
	/**
	 * is called when a figure moved
	 */
	public void FigureMoved(){
		playingField.refreshField();
	}
	
	/**
	 * is called when a figure attacked
	 */
	public void FigureAttacked(){
		playingField.refreshField();
	}
	
	/**
	 * is called when the game is lost
	 */
	public void gameIsLost(){
		controll.gameIsLost();
	}
	
	/**
	 * is called when the game is won
	 */
	public void gameIsWon(){
		controll.gameIsWon();
	}
	
	/**
	 * is called when the game is drawn
	 */
	public void gameIsDrawn(){
		
	}

}
