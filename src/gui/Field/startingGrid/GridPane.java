package gui.Field.startingGrid;

import game.Figure;
import game.FigureKind;
import game.Player;
import game.logic.Field;
import game.logic.GameLogic;
import game.logic.StartingGridFactory;
import gui.Field.FieldButton;
import gui.Field.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controll.Controll;
import controll.GameControll;

/**
 * represents the field in the startingGrid panel
 * @author Daniel Roth
 */
public class GridPane extends JPanel implements ActionListener{
	
	private Controll controll;
	private GameControll gameControll;
	private GameLogic gameLogic;
	private OverviewPane overviewPane;
	private Images images;
	private GridPane panel = this;
	private Field field;
	private Player player;
	private FieldButton[][] buttonField;
	private StartingGridFactory factory = new StartingGridFactory();
	
	private int ROW_COUNT = Controll.ROW_COUNT;
	private int COLUMN_COUNT = Controll.COLUMN_COUNT;
	private Dimension BUTTON_SIZE = FieldButton.SIZE;
	
	private Boolean switchActive = false;
	private FieldButton switchingBtn = null;
	

	public GridPane(Controll controll, GameControll gameControll, GameLogic gameLogic, OverviewPane overviewPane, Images images, Player player) {
		this.controll = controll;
		this.gameControll = gameControll;
		this.gameLogic = gameLogic;
		this.overviewPane = overviewPane;
		this.images = images;
		this.player = player;
		
		initiateButtonField();
		initiatePanel();
		initiateField();
	}
	
	private void initiateButtonField(){
		buttonField = new FieldButton[ROW_COUNT][COLUMN_COUNT];
		
		for (int r=ROW_COUNT-1; r>-1;r--){
			for (int c=0; c<COLUMN_COUNT; c++){
				buttonField[r][c] = new FieldButton(r, c, new ImageIcon(), this);
				buttonField[r][c].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			}
		}
		
		setButtonsEnabled(true);
	}
	
	private void initiateField(){
		field = new Field();
	}
	
	private void initiatePanel(){
		GridLayout layout = new GridLayout(ROW_COUNT, COLUMN_COUNT);
		this.setLayout(layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		for (int r=ROW_COUNT-1; r>-1; r--){
			for (int c=0; c<COLUMN_COUNT; c++){
				panel.add(buttonField[r][c]);
			}
		}
		
		Dimension dim = new Dimension((int)(BUTTON_SIZE.getWidth() * COLUMN_COUNT), 
				(int)(BUTTON_SIZE.getHeight() * ROW_COUNT));
		
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
	}
	
	
	public void actionPerformed(ActionEvent e){
		FieldButton button = (FieldButton) e.getSource();
		Figure[][] fig = field.getField();
		int r = button.getRow();
		int c = button.getColumn();
		Figure switchingFig = null;
		
		if (overviewPane.isDeleteSelected()){
			fig[r][c] = null;
			refreshField();
		}
		if (overviewPane.isSwitchSelected()){
			if (switchActive == false){
				switchActive = true;
				switchingBtn = button;
				setActivated(button);
			}
			else{
				switchActive = false;
				switchingFig = fig[switchingBtn.getRow()][switchingBtn.getColumn()];
				field.setField(fig[r][c], switchingBtn.getRow(), switchingBtn.getColumn());
				field.setField(switchingFig, r, c);
				refreshField();
			}
		}
		if (overviewPane.isCreateSelected()){
			
			handleCreatedSelected(button);
			
		}
		refreshCounters();
	}
	
	private void handleCreatedSelected(FieldButton button){
		Figure[][] fig = field.getField();
		int r = button.getRow();
		int c = button.getColumn();
		Figure saveFig = fig[r][c];
		FigureKind selectedKind = null;
		
		if (overviewPane.isScissorSelected()){
			selectedKind = FigureKind.SCISSOR;
//			handleFigureKindsButtonsSelection(selectedKind, button);
			field.setField(new Figure(FigureKind.SCISSOR, player), r, c);
		}
		if (overviewPane.isRockSelected()){
			selectedKind = FigureKind.ROCK;
//			handleFigureKindsButtonsSelection(selectedKind, button);
			field.setField(new Figure(FigureKind.ROCK, player), r, c);
		}
		if (overviewPane.isPaperSelected()){
			selectedKind = FigureKind.PAPER;
//			handleFigureKindsButtonsSelection(selectedKind, button);
			field.setField(new Figure(FigureKind.PAPER, player), r, c);
		}
		if (overviewPane.isFlagSelected()){
			selectedKind = FigureKind.FLAG;
//			handleFigureKindsButtonsSelection(selectedKind, button);
			field.setField(new Figure(FigureKind.FLAG, player), r, c);
		}
		if (overviewPane.isTrapSelected()){
			selectedKind = FigureKind.TRAP;
//			handleFigureKindsButtonsSelection(selectedKind, button);
			field.setField(new Figure(FigureKind.TRAP, player), r, c);
		}
		refreshCounters();
		
		//autoUnitSelection -> next button auto selected
		handleFigureKindsButtonsSelection(selectedKind, button);

		if (field.isCounterNumberTooHigh()){ //if the max number of figures of a type is on the field
			overviewPane.setInfoLabel("Maximale Anzahl an Einheiten dieses Types gesetzt.");
			//old figures will be reset
			if (saveFig == null)
				fig[r][c] = null;
			else
				field.setField(saveFig, r, c);
		}
		refreshField();
	}
	
	private void handleFigureKindsButtonsSelection(FigureKind selectedKind, FieldButton button){
		if (overviewPane.isAutoNextUnitSelected()){
			if (field.isMaxNumberReached(selectedKind)){
				handleAutoNextUnitSelection(selectedKind);
				}
		}
	}
	
	/**
	 * sets the next available button representing a figure type that can be set
	 * to field
	 */
	private void handleAutoNextUnitSelection(FigureKind figKind){
		 //get the next available button
			int index = 0;

			for (Boolean b: field.getCountersMaxed()){
				if (!b){ //if b false then the figure type at the given index is under used
					if (index == 0)
						overviewPane.setScissorSelected();
					if (index == 1)
						overviewPane.setRockSelected();
					if (index == 2)
						overviewPane.setPaperSelected();
					if (index == 3)
						overviewPane.setFlagSelected();
					if (index == 4)
						overviewPane.setTrapSelected();
					return;
					}
				index++;
			}
			
	}
	
	/**
	 * returns an integer that defines the position of the given figureKind in a
	 * list created by field.getCountersMaxed()
	 * @param figKind
	 * @return integer representing position of FigureKind
	 */
	private int getIndex(FigureKind figKind){
		if (figKind == FigureKind.SCISSOR)
			return 0;
		if (figKind == FigureKind.ROCK)
			return 1;
		if (figKind == FigureKind.PAPER)
			return 2;
		if (figKind == FigureKind.FLAG)
			return 3;
		if (figKind == FigureKind.TRAP)
			return 4;
		return 5;
	}
	
	/**
	 * gives the button an activated icon
	 * @param button
	 */
	public void setActivated(FieldButton button){
		int r = button.getRow();
		int c = button.getColumn();
		Figure[][] fig = field.getField();
		if (fig[r][c] == null)
			return;
		if (fig[r][c].getFigureKind() == FigureKind.SCISSOR)
			buttonField[r][c].setIcon(images.getScissorActivated());
		if (fig[r][c].getFigureKind() == FigureKind.ROCK)
			buttonField[r][c].setIcon(images.getRockActivated());
		if (fig[r][c].getFigureKind() == FigureKind.PAPER)
			buttonField[r][c].setIcon(images.getPaperActivated());
	}
	
	/**
	 * dis-/enables the field
	 * @param b true for enable, false for disable
	 */
	public void setButtonsEnabled(Boolean b){
		for (int r=0; r<2; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				buttonField[r][c].setEnabled(b);
			}
		}
	}
	
	public void refreshField(){
//		System.out.println("refresh field");
		Figure[][] fig = field.getField();
		Boolean fieldNotFull = false;
		
		for (int r=ROW_COUNT-1;r>-1;r--){
			for (int c=0;c<COLUMN_COUNT; c++){
				if (fig[r][c] == null){
					buttonField[r][c].setIcon(new ImageIcon());
					if (r<2)
						fieldNotFull = true;
					continue;
				}
				
				if (fig[r][c].getPlayer().equals(player)){ //human Player
//					System.out.println("Humand Field");
					if (fig[r][c].isDiscovered() == false){ //if the figure can not be seen by enemy 
						if (fig[r][c].getFigureKind().equals(FigureKind.FLAG))
							buttonField[r][c].setIcon(images.getFlag());
						if (fig[r][c].getFigureKind().equals(FigureKind.TRAP))
							buttonField[r][c].setIcon(images.getTrap());
						if (fig[r][c].getFigureKind().equals(FigureKind.SCISSOR))
							buttonField[r][c].setIcon(images.getScissor());
						if (fig[r][c].getFigureKind().equals(FigureKind.ROCK))
							buttonField[r][c].setIcon(images.getRock());
						if (fig[r][c].getFigureKind().equals(FigureKind.PAPER))
							buttonField[r][c].setIcon(images.getPaper());
					}
				}
			}
		}
		if (!fieldNotFull)
			overviewPane.setInfoLabel("Das Spielfeld ist voll!");
		if (fieldNotFull)
			overviewPane.setInfoLabel("");
	}
	
	/**
	 * is called when the random button in overviewPane is pressed.
	 */
	public void handleRandomBtnSelection(){
		field = factory.getRandomStartingGrid(player);
		refreshField();
		refreshCounters();
	}
	
	/**
	 * is called when the new button in overviewPane is pressed.
	 */
	public void handleNewBtnSelection(){
		field.setNull();
		refreshField();
		refreshCounters();
	}
	
	public void handleStartBtnSelection(){
		gameLogic.setStartingGrid(field.getField(), player);
	}

	private void refreshCountersGui(){
		overviewPane.setScissor(Integer.toString(field.getCounterScissor()));
		overviewPane.setRock(Integer.toString(field.getCounterRock()));
		overviewPane.setPaper(Integer.toString(field.getCounterPaper()));
		overviewPane.setFlag(Integer.toString(field.getCounterFlag()));
		overviewPane.setTrap(Integer.toString(field.getCounterTrap()));
		
	}
	
	public void refreshCounters(){
		field.refreshCounters(player);
		refreshCountersGui();
	}
}
