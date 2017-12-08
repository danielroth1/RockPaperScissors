package gui.Field;

import game.logic.GameLogic;
import gui.BackgroundPanel;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.GroupLayout;

import controll.Controll;
import controll.GameControll;


/**
 * contains the playing field
 * @author Daniel Roth
 */
public class PlayingFieldPanel extends BackgroundPanel{

	private Controll controll;
	private GameControll gameControll;
	private GameLogic gameLogic;
	private PlayingFieldPanel panel = this;
	private PlayingField playingField;
	private Images images;
	public static Dimension PANEL_SIZE = Controll.GAME_FIELD_SIZE;

	
	public PlayingFieldPanel(Controll controll, GameControll gameControll, GameLogic gameLogic) {
		this.gameControll = gameControll;
		this.gameLogic = gameLogic;
		this.controll = controll;
		this.playingField = new PlayingField(controll, gameControll, gameLogic);
		this.images = playingField.getImages();
		
		initiatePanel();
		handleButtonSelection();
		this.setVisible(false);
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(50, 50, 50)
						.addComponent(playingField)
					)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(50, 50, 50)
						.addComponent(playingField)
						)
				);
		
		panel.setMinimumSize(PANEL_SIZE);
		panel.setPreferredSize(PANEL_SIZE);
		panel.setMaximumSize(PANEL_SIZE);
		panel.setBounds(new Rectangle(0, 0, (int)PANEL_SIZE.width, (int)PANEL_SIZE.height));
		panel.setBackgroundImage(images.getBackgroundField());
		panel.setOpaque(true);
		
	}
	
	private void handleButtonSelection(){
	}
	
	/**
	 * sets PlayingFieldPanel as well as PlayingField visible
	 */
	public void showField(){
		panel.setVisible(true);
		playingField.setVisible(true);
	}
	
	/**
	 * hides PlayingFieldPanel as well as PlayingField
	 */
	public void hideField(){
		panel.setVisible(false);
		playingField.setVisible(false);
	}
	
	
	/**
	 * returns the playingField
	 * @return playingField
	 */
	public PlayingField getPlayingField(){
		return playingField;
	}
}
