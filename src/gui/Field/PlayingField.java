package gui.Field;

import game.Figure;
import game.FigureKind;
import game.Location;
import game.Move;
import game.Player;
import game.logic.Field;
import game.logic.GameLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controll.Controll;
import controll.GameControll;


/**
 * contains the playingField (guiField) with all the Buttons
 * refresh playingField with refresh() method.
 * @author Daniel Roth
 */
public class PlayingField extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controll controll;
	private GameControll gameControll;
	private GameLogic gameLogic;
	private PlayingField panel = this;
	private JPanel panelField;
	private Images images = new Images();
	private FieldButton[][] guiField;
	private Field field;
	private Player player;
	private Player playerAI;
	
	private int ROW_COUNT = Controll.ROW_COUNT;
	private int COLUMN_COUNT = Controll.COLUMN_COUNT;
	private String state;
	private FieldButton saveButton = null;
	
	
	/**
	 * sets the field to work with
	 * @param field
	 */
	public void setField(Field field){
		this.field = field;
	}
	
	
	public PlayingField(Controll controll, GameControll gameControll, GameLogic gameLogic) {
		this.controll = controll;
		this.gameControll = gameControll;
		this.gameLogic = gameLogic;
		this.player = controll.getPlayer();
		this.playerAI = controll.getPlayerAI();
		state = "unitNotActivated";
		
		initiateField();
		initiatePanelField();
		initiatePanel();
		this.setVisible(false);
	}
	
	/**
	 * initiates the guiField consisting out of FieldButtos
	 */
	private void initiateField(){
//		System.out.println("initiate field");
		guiField = new FieldButton[ROW_COUNT][COLUMN_COUNT];
		
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=0; c<COLUMN_COUNT; c++){
				guiField[r][c] = new FieldButton(r, c, new ImageIcon(), this);
			}
		}
		
	}
	
	/**
	 * initiates panel. Adds buttons to guiField
	 */
	private void initiatePanelField(){
		panelField = new JPanel();
		GridLayout layout = new GridLayout(ROW_COUNT, COLUMN_COUNT);
		panelField.setLayout(layout);
		
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=0; c<COLUMN_COUNT; c++){
				panelField.add(guiField[r][c]);
			}
		}
		panelField.setBackground(new Color(0, 0, 0, 0));
		panelField.setOpaque(false);
		System.out.println(panelField.getSize().toString());
		panelField.setBounds(0, 0, (int)panelField.getSize().getWidth(), (int)panelField.getSize().getHeight());
		
	}
	
	private void initiatePanel(){
		double y = FieldButton.SIZE.getHeight();
		double x = FieldButton.SIZE.getWidth();
		Dimension dim = new Dimension((int)x * COLUMN_COUNT, (int)y * ROW_COUNT);
		
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setMinimumSize(dim);
		panel.setPreferredSize(dim);
		panel.setMaximumSize(dim);
		panel.add(panelField);
		
	}
	
	
	/**
	 * refreshes the current field by setting all figures
	 */
	public void refreshField(){
//		System.out.println("refreshh field");
		Figure[][] fig = field.getField();
		
		for (int r=ROW_COUNT-1;r>-1;r--){
			for (int c=0;c<COLUMN_COUNT; c++){
				if (fig[r][c] == null){
//					System.out.println("null");
					guiField[r][c].setIcon(new ImageIcon());
					continue;
				}
				
				if (fig[r][c].getPlayer().equals(player)){ //human Player
//					System.out.println("Humand Field");
					if (fig[r][c].isDiscovered() == false){ //if the figure can not be seen by enemy 
						if (fig[r][c].getFigureKind().equals(FigureKind.FLAG))
							guiField[r][c].setIcon(images.getFlag());
						if (fig[r][c].getFigureKind().equals(FigureKind.TRAP))
							guiField[r][c].setIcon(images.getTrap());
						if (fig[r][c].getFigureKind().equals(FigureKind.SCISSOR))
							guiField[r][c].setIcon(images.getScissor());
						if (fig[r][c].getFigureKind().equals(FigureKind.ROCK))
							guiField[r][c].setIcon(images.getRock());
						if (fig[r][c].getFigureKind().equals(FigureKind.PAPER))
							guiField[r][c].setIcon(images.getPaper());
					}
					else{ //if the figure can be seen by enemy
						if (fig[r][c].getFigureKind().equals(FigureKind.SCISSOR))
							guiField[r][c].setIcon(images.getScissorDiscovered());
						if (fig[r][c].getFigureKind().equals(FigureKind.ROCK))
							guiField[r][c].setIcon(images.getRockDiscovered());
						if (fig[r][c].getFigureKind().equals(FigureKind.PAPER))
							guiField[r][c].setIcon(images.getPaperDiscovered());
					}
				}
				if (fig[r][c].getPlayer().equals(playerAI)){ //ai Player
//					System.out.println("AI Field");
					if (fig[r][c].isDiscovered() == false){ //if you can not see the enemy figure
						guiField[r][c].setIcon(images.getGhostRemote());
					}
					else{ //if you can see the enemy figure
						if (fig[r][c].getFigureKind().equals(FigureKind.SCISSOR))
							guiField[r][c].setIcon(images.getScissorRemote());
						if (fig[r][c].getFigureKind().equals(FigureKind.ROCK))
							guiField[r][c].setIcon(images.getRockRemote());
						if (fig[r][c].getFigureKind().equals(FigureKind.PAPER))
							guiField[r][c].setIcon(images.getPaperRemote());
					}
				}
			}
		}
	}
	
	/**
	 * gives the button an activated icon
	 * @param button
	 */
	public void setActivated(FieldButton button){
		int r = button.getRow();
		int c = button.getColumn();
		Figure[][] fig = field.getField();
		if (fig[r][c].getFigureKind() == FigureKind.SCISSOR)
			guiField[r][c].setIcon(images.getScissorActivated());
		if (fig[r][c].getFigureKind() == FigureKind.ROCK)
			guiField[r][c].setIcon(images.getRockActivated());
		if (fig[r][c].getFigureKind() == FigureKind.PAPER)
			guiField[r][c].setIcon(images.getPaperActivated());
	}
	
	
	/**
	 * refreshs a move
	 */
	public void refreshMove(){
		
	}
	
	/**
	 * refreshs only one Field
	 */
	public void refreshOneField(int[][] pos){
		
	}
	
	/**
	 * sets the whole field dis-/enabled
	 * @param b true for enable; false for disable
	 */
	public void setButtonsEnabled(Boolean b){
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				guiField[r][c].setEnabled(b);
			}
		}
	}
	
	/**
	 * sets the figures for a specific player dis-/enabled
	 * @param player specific player
	 * @param b true for enabled; false for disabled
	 */
	public void setButtonsEnabled(Player player, Boolean b){
		Figure[][] fig = field.getField();
		
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (fig[r][c] == null)
					continue;
				if (player.equals(fig[r][c].getPlayer()))
						guiField[r][c].setEnabled(b);
			}
		}
	}
	
	/**
	 * sets one button on the field dis-/enabled
	 * @param r row coordinate
	 * @param c column coordinate
	 * @param b true for enabled; false for disabled
	 */
	public void setButtonsEnabled(int r, int c, Boolean b){
		guiField[r][c].setEnabled(b);
	}
	
	/**
	 * Enables one figure on the field and disables the rest
	 * @param r row coordinate
	 * @param c column coordinate
	 */
	public void setOneButtonsEnabled(int r, int c){
		setButtonsEnabled(false);
		setButtonsEnabled(r, c, true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		FieldButton button = (FieldButton) e.getSource();

		switch (state){
		//describes what happens if the player has to take a figure und takes it
			case "unitNotActivated":
				saveButton = button;
//				System.out.println(button.getRow() + " ," + button.getColumn());
//				setOneButtonsEnabled(button.getRow(), button.getColumn());
				showPossibleMoves(button.getRow(), button.getColumn(), true);
				setActivated(button);
				state = "unitActivated";
				break;
				
		//describes what happens if the player has to take a move or want to disable his choice
			case "unitActivated":
				//if the same field is pressed again
				if (saveButton.equals(button)){
					setButtonsEnabled(player, true);
					showPossibleMoves(button.getRow(), button.getColumn(), false);
					refreshField();
					state = "unitNotActivated";
					}
				else{
					Location from = new Location(saveButton.getRow(), saveButton.getColumn());
					Location to = new Location(button.getRow(), button.getColumn());
					if (field.isInPossibleMoves(from, player, to)){ //if one of the possible moves is clicked
						setButtonsEnabled(false);
						gameLogic.newMove(new Move(from, to), player);
						state = "unitNotActivated";
//						System.out.println("move done!");
					}
					else{
						setButtonsEnabled(player, true);
						showPossibleMoves(saveButton.getRow(), saveButton.getColumn(), false);
						refreshField();
						saveButton = button;
						showPossibleMoves(button.getRow(), button.getColumn(), true);
						setActivated(button);
						state = "unitActivated";
					}
				}
				
		}
		
//		System.out.println(state);
	}
	
	public void provideNextMove(){
		refreshField();
		setButtonsEnabled(player, true);
		state = "unitNotActivated";
	}
	
	/**
	 * Shows the possible moves of a figure in the gui by pointing them out
	 * with arrows.
	 * @param r row location of the unit
	 * @param c column location of the unit
	 */
	private void showPossibleMoves(int r, int c, Boolean enabled){
		Location from;
		Location to;
		ImageIcon up;
		ImageIcon down;
		ImageIcon right;
		ImageIcon left;
		
		if (enabled){
			up = images.getArrowUp();
			down = images.getArrowDown();
			right = images.getArrowRight();
			left = images.getArrowLeft();
		}
		else{
			up = new ImageIcon();
			down = new ImageIcon();
			right = new ImageIcon();
			left = new ImageIcon();
		}
		
		for (Move newMove : field.calcPossibleMoves(new Location(r, c), player)){
			from = newMove.getFrom();
			to = newMove.getTo();
			if (from.getRow() + 1 == to.getRow()){ //if the aim field is one step up
				guiField[to.getRow()][to.getColumn()].setIcon(up);
			}
			if (from.getRow() - 1 == to.getRow()){ //if the aim field is one step down
				guiField[to.getRow()][to.getColumn()].setIcon(down);
			}
			if (from.getColumn() + 1 == to.getColumn()){ //if the aim field is one step to the right
				guiField[to.getRow()][to.getColumn()].setIcon(right);
			}
			if (from.getColumn() - 1 == to.getColumn()){ //if the aim field is one step to the left
				guiField[to.getRow()][to.getColumn()].setIcon(left);
			}
			guiField[to.getRow()][to.getColumn()].setEnabled(enabled);
		}
	}
	
	/**
	 * Shows the possible moves of a figure in the gui by pointing them out
	 * with arrows.
	 * @param loc location of the unit
	 */
	public void showPossibleMoves(Location loc, Boolean enabled){
		showPossibleMoves(loc.getRow(), loc.getColumn(), enabled);
	}
	
	/**
	 * returns the chosen images
	 * @return Images
	 */
	public Images getImages(){
		return images;
	}
	
	/**
	 * sets the images aka skins
	 */
	public void setImages(Images images){
		this.images = images;
	}
	
	/**
	 * sets for the button the 
	 * @param button
	 */
	public void setSelectedImages(JButton button){
		
	}

}
