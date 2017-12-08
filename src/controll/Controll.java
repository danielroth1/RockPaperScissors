package controll;
import game.Player;
import game.logic.GameLogic;
import gui.BackgroundPanel;
import gui.DesktopPane;
import gui.EndGamePane;
import gui.StartupPane;
import gui.Field.GameOptions;
import gui.Field.Images;
import gui.Field.PlayingField;
import gui.Field.PlayingFieldPanel;
import gui.Field.ProvideChoice;
import gui.Field.startingGrid.GridPane;
import gui.Field.startingGrid.OverviewPane;
import gui.StartMenu.LoadOverviewPane;
import gui.StartMenu.LoadPane;
import gui.StartMenu.SettingsPane;
import gui.StartMenu.Skins;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.AI;

/**
 * This class is responsible for the following tasks:
 * - implementation of the frame
 * - implementation of all the panels
 * - GUIController: controlling visibility of
 * the frames
 * 
 *  - initiating GameLogic and GameControll
 *  - creating human and AI player 
 *  and referring them to GameLogic and GameControll
 * @author Daniel Roth
 */
public class Controll {

	
	private JFrame frame = new JFrame("ProjectX");
	private JPanel contentPane;
	private JPanel guiContentPane = new JPanel();
	private BackgroundPanel backgroundGuiContentPane;
	
	private GameControll gameControll;
	private AI ai;
	private GameLogic gameLogic;
	private JPanel startMenuPane;
		private EndGamePane endGamePane;
		private GameOptions gameOptions;
			private PlayingFieldPanel playingFieldPanel;
				private PlayingField playingField;
			private OverviewPane overviewPane;
				private GridPane gridPane;
				
		private StartupPane startupPane;
			private LoadPane loadPane;
				private LoadOverviewPane loadOverviewPane;
			private SettingsPane settingsPane;
				private Skins skins;
				
	private DesktopPane desktopPane;
		private ProvideChoice provideChoice;
			
	private static Controll controll;
	
	private Images images;
	private Player player = new Player("Mensch");
	private Player playerAI = new Player("AI");
	public static int ROW_COUNT = 6;
	public static int COLUMN_COUNT = 7;
	
	public static Dimension MENU_SIZE = new Dimension(400, 400);
	public static Dimension GAME_FIELD_SIZE = new Dimension(700, 600);
	
	
	public Controll() {
		gameLogic = new GameLogic(this, player, playerAI);
		gameControll = new GameControll(this, gameLogic, player, playerAI);
		ai = new AI(gameLogic, playerAI);
		gameLogic.setComponents(gameControll, ai);
		

		startupPane = new StartupPane(this, gameControll);
		loadOverviewPane = new LoadOverviewPane(this, gameControll);
		loadPane = new LoadPane(this, loadOverviewPane);
		settingsPane = new SettingsPane(this);
		
		playingFieldPanel = new PlayingFieldPanel(this, gameControll, gameLogic);
		playingField = playingFieldPanel.getPlayingField();
		gameOptions = new GameOptions(this, gameControll, gameLogic);
		provideChoice = new ProvideChoice(this, gameControll, gameLogic, playingField);
		desktopPane = new DesktopPane(provideChoice);
		skins = new Skins(this, playingField, provideChoice);
		images = playingField.getImages();
		overviewPane = new OverviewPane(this, gameControll, gameLogic, images, player);
		gridPane = overviewPane.getGridPane();
		endGamePane = new EndGamePane(this, gameControll, desktopPane, images);
		
		
		gameControll.setComponents(playingField, provideChoice, gridPane);
		initiateGuiContentPane();
		initiateBackgroundGuiContentPane();

		initiateLayeredPane();
		initiateFrame();
//		setPanelSize();
		
		start();
		frame.pack();
		frame.setLocationRelativeTo(null);
		
	}
	
	public JPanel getPanel(){
		return guiContentPane;
	}

	
	private void initiateFrame(){
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exit();
			}
		});
	}
	
	public void exit(){
		System.exit(0);
	}

	
	private void initiateLayeredPane(){
		contentPane = (JPanel) frame.getContentPane();
		contentPane.setPreferredSize(MENU_SIZE);
//		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(backgroundGuiContentPane);
		contentPane.add(playingFieldPanel);
		contentPane.add(overviewPane);
		contentPane.add(desktopPane);
		contentPane.setComponentZOrder(desktopPane, 0);
	}
	
	
	private void initiateBackgroundGuiContentPane(){
		
		backgroundGuiContentPane = new BackgroundPanel();
		backgroundGuiContentPane.setBackgroundImage(images.getBackgroundMenu());
		backgroundGuiContentPane.setLayout(new BoxLayout(backgroundGuiContentPane, BoxLayout.Y_AXIS));
//		backgroundGuiContentPane.setBackground(Color.BLACK);
		
		backgroundGuiContentPane.setMinimumSize(new Dimension(400, 400));
		backgroundGuiContentPane.setPreferredSize(new Dimension(400, 400));
		backgroundGuiContentPane.setMaximumSize(new Dimension(400, 400));
		
		
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalGlue());
//		box.add(Box.createRigidArea(new Dimension(50, 50)));
		box.add(guiContentPane);
		box.add(Box.createVerticalGlue());
		
		backgroundGuiContentPane.add(box);
		backgroundGuiContentPane.setBounds(0, 0, (int)MENU_SIZE.getWidth(), (int)MENU_SIZE.getHeight());
		
	}
	
	
	private void initiateGuiContentPane(){

		BoxLayout layout = new BoxLayout(guiContentPane, BoxLayout.PAGE_AXIS);
		guiContentPane.setLayout(layout);
		guiContentPane.setOpaque(false);
//		contentPane.setMinimumSize(new Dimension(360, 360));
//		contentPane.setPreferredSize(new Dimension(360, 360));
//		contentPane.setMaximumSize(new Dimension(360, 360));
		
		guiContentPane.add(startupPane);
		guiContentPane.add(gameOptions);
//		contentPane.add(playingFieldPanel);
		guiContentPane.add(loadPane);
		guiContentPane.add(settingsPane);
		guiContentPane.add(skins);
	}
	
	
	/**
	 * returns the human player
	 * @return human player
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * returns the AI player
	 * @return AI player
	 */
	public Player getPlayerAI(){
		return playerAI;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Controll();
	}
	
	public void setVisible(Boolean b){
		if (b)
			frame.setVisible(true);
		else
			frame.setVisible(false);
	}
	
	public void start(){
		startupPane.setVisible(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * if startBtn in StartupPane is pressed
	 */
	public void handleStartSelection(){
		gameOptions.setVisible(true);
		startupPane.setVisible(false);
	}
	
	/**
	 * if loadBtn in StartupPane is pressed
	 */
	public void handleLoadSelection(){
		startupPane.setVisible(false);
		loadPane.setVisible(true);
		loadOverviewPane.setVisible(true);
		
	}
	
	/**
	 * if settingsBtn in StartupPane is pressed
	 */
	public void handleSettingsSelection(){
		startupPane.setVisible(false);
		settingsPane.setVisible(true);
	}
	
	/**
	 * if abortBtn in StartupPane is pressed
	 */
	public void handleAbortSelection(){
		exit();
	}
	
	/**
	 * if you want to get back to startupPane
	 */
	public void handleBackToStartupPane(JPanel panel){
		startupPane.setVisible(true);
		panel.setVisible(false);
		
	}
	
	public void handleQuickStartSelection(){
		playingFieldPanel.showField();
		gameOptions.setVisible(false);
		backgroundGuiContentPane.setVisible(false);
		changeToPlayingField();
		
//		contentPane.setPreferredSize(GAME_FIELD_SIZE);
		
	}
	

	
	public void handleOwnGameSelection(){
		overviewPane.setVisible(true);
		gameOptions.setVisible(false);
		backgroundGuiContentPane.setVisible(false);
		changeToPlayingField();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * decide what happens if the player wants get back to main menu.
	 */
	public void handleBackToMainMenuSelection(){
		endGamePane.setVisible(false);
		playingFieldPanel.setVisible(false);
		backgroundGuiContentPane.setVisible(true);
		startupPane.setVisible(true);
		//back to the menu
//		contentPane.setPreferredSize(MENU_SIZE);
		changeToMenu();
		
	}
	
	/**
	 * decides what happens if the player wants get back to game after loose or win.
	 */
	public void handleBackToGameSelection(){
		endGamePane.setVisible(false);
	}
	
	/**
	 * is called when the game is lost by the player
	 */
	public void gameIsLost(){
		endGamePane.gameLost();
		endGamePane.setVisible(true);
	}
	
	/**
	 * is called when the game is won by the player
	 */
	public void gameIsWon(){
		endGamePane.gameWon();
		endGamePane.setVisible(true);
	}
	
	/**
	 * decides what happens if the skins are getting selected in SETTINGS menu.
	 */
	public void handleSkinSelection(){
		skins.setVisible(true);
		settingsPane.setVisible(false);
	}
	
	/**
	 * decides what happens if the back button in SKINS is getting selected.
	 */
	public void handleSkinsBackBtn(){
		skins.setVisible(false);
		settingsPane.setVisible(true);
	}
	
	/**
	 * decides what happens if the sound is getting selected in SETTINGS menu.
	 */
	public void handleSoundSelection(){
		
	}
	
	/**
	 * decides what happens if the player wants to get back to gameOptions from OverviewPane
	 */
	public void handleBackToGameOptionsSelection(){
		overviewPane.setVisible(false);
		backgroundGuiContentPane.setVisible(true);
		
		gameOptions.setVisible(true);
		changeToMenu();
	}
	
	public void handleOwnGameStartSelection(){
		overviewPane.setVisible(false);
		playingFieldPanel.showField();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private void changeToMenu(){

		contentPane.setPreferredSize(MENU_SIZE);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private void changeToPlayingField(){

		contentPane.setPreferredSize(GAME_FIELD_SIZE);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}


}
