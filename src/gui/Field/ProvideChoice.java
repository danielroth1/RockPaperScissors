package gui.Field;

import game.FigureKind;
import game.Player;
import game.logic.GameLogic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import controll.Controll;
import controll.GameControll;

public class ProvideChoice extends JInternalFrame implements ActionListener{
	
	private ProvideChoice frame = this;
	private Controll controll;
	private GameControll gameControll;
	private GameLogic gameLogic;
	private PlayingField playingField;
	private Images images;
	private Player player; //human player
	
	private JPanel contentPane;
	private JPanel panel;
	private FieldButton scissorBtn;
	private FieldButton rockBtn;
	private FieldButton paperBtn;
	public ProvideChoice(Controll controll, GameControll gameControll, GameLogic gameLogic, PlayingField playingField) {
		this.controll = controll;
		this.gameControll = gameControll;
		this.gameLogic = gameLogic;
		this.playingField = playingField;
		this.images = playingField.getImages();
		this.player = controll.getPlayer();
		
		initiateButtons();
		initiatePanel();
		initiateContentPane();
		initiateFrame();
		this.setVisible(false);
		
	}
	
	private void initiateButtons(){
		scissorBtn = new FieldButton(images.getScissor(), this);
		rockBtn = new FieldButton(images.getRock(), this);
		paperBtn = new FieldButton(images.getPaper(), this);
		
		scissorBtn.setEnabled(true);
		rockBtn.setEnabled(true);
		paperBtn.setEnabled(true);
	}
	
	private void initiatePanel(){
		panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(scissorBtn)
					.addComponent(rockBtn)
					.addComponent(paperBtn))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(scissorBtn)
						.addComponent(rockBtn)
						.addComponent(paperBtn))
				);
		
		layout.setAutoCreateGaps(true);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		panel.setBackground(Color.LIGHT_GRAY);
	}
	
	private void initiateContentPane(){
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(panel);
	}
	
	private void initiateFrame(){
		((BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		FieldButton button = (FieldButton)e.getSource();
		if (button.equals(scissorBtn)){
			gameLogic.setChoiceAfterFightIsDrawn(player, FigureKind.SCISSOR);
		}
		if (button.equals(rockBtn)){
			gameLogic.setChoiceAfterFightIsDrawn(player, FigureKind.ROCK);
		}
		if (button.equals(paperBtn)){
			gameLogic.setChoiceAfterFightIsDrawn(player, FigureKind.PAPER);
		}
	}
	
	/**
	 * opens a new JInternalFrame witch allows the player to choose a new FigureType
	 * it will automatically be opened setChoiceAfterFightIsDrawn()
	 * @param mousePosition
	 */
	public void provideChoice(Boolean mousePosition){
		this.setVisible(true);
		this.toFront();
	}
	
	public void refreshButtonImages(){
		scissorBtn.setIcon(images.getScissor());
		rockBtn.setIcon(images.getRock());
		paperBtn.setIcon(images.getPaper());
	}

}
