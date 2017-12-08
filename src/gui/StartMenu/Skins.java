package gui.StartMenu;

import gui.Field.Images;
import gui.Field.PlayingField;
import gui.Field.ProvideChoice;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controll.Controll;

public class Skins extends JPanel implements ActionListener{
	
	private Skins skins = this;
	private Controll controll;
	private PlayingField playingField;
	private ProvideChoice provideChoice;
	private Images images;

	private JButton skin1Btn = new JButton();
	private JButton skin2Btn = new JButton();
	private JButton skin3Btn = new JButton();
	private JToggleButton ownTeamBtn = new JToggleButton("your team");
	private JToggleButton enemyTeamBtn = new JToggleButton("enemy team");
	private JButton backBtn = new JButton("back");
	
	public Skins(Controll controll, PlayingField playingField, ProvideChoice provideChoice){
		this.controll = controll;
		this.playingField = playingField;
		this.provideChoice = provideChoice;
		this.images = playingField.getImages();
		
		
		initiateButtons();
		initiatePanel();
		this.setVisible(false);
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(skin1Btn)
						.addComponent(skin2Btn)
						.addComponent(skin3Btn)
						.addGroup(layout.createSequentialGroup()
								.addComponent(backBtn)
								.addComponent(ownTeamBtn)
								.addComponent(enemyTeamBtn))
						));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(skin1Btn)
				.addComponent(skin2Btn)
				.addComponent(skin3Btn)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(backBtn)
						.addComponent(ownTeamBtn)
						.addComponent(enemyTeamBtn))
				);
		layout.setAutoCreateGaps(true);
		this.setOpaque(false);
		
	}
	
	private void initiateButtons(){
		setButton(skin1Btn, images.getTeam1());
		setButton(skin2Btn, images.getTeam2());
		skin1Btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		skin2Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		skin3Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		
		ownTeamBtn.setToolTipText("Chose the skin of your team.");
		enemyTeamBtn.setToolTipText("Chose the skin of the enemy team.");
		ButtonGroup group = new ButtonGroup();
		group.add(ownTeamBtn);
		group.add(enemyTeamBtn);
		ownTeamBtn.setSelected(true);
		
		ownTeamBtn.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				setBorder(images.getLocalSkin());
			}
		});
		enemyTeamBtn.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				setBorder(images.getRemoteSkin());
			}
		});
		backBtn.addActionListener(this);
	}
	
	/**
	 * sets the buttons with images and resizing
	 * @param button to be set
	 * @param icon of the button
	 */
	private void setButton(JButton button, Icon icon){
		
		button.setIcon(icon);
		button.setBackground(new Color(0, 0, 0, 0));
		button.setOpaque(false);
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		JButton button = (JButton) e.getSource();
		decideButton(button);
	}
	
	/**
	 * decides what happens if the button is pressed
	 * @param button
	 */
	private void decideButton(JButton button){
		if (button == backBtn)
			controll.handleSkinsBackBtn();
		
		if (ownTeamBtn.isSelected()){
			if (this.skin1Btn == button)
				images.setLocalSkin(1);
			if (this.skin2Btn == button)
				images.setLocalSkin(2);
			if (this.skin3Btn == button)
				images.setLocalSkin(3);
		}
		if (enemyTeamBtn.isSelected()){
			if (this.skin1Btn == button)
				images.setRemoteSkin(1);
			if (this.skin2Btn == button)
				images.setRemoteSkin(2);
			if (this.skin3Btn == button)
				images.setRemoteSkin(3);
		}
		if (this.skin1Btn == button)
			setBorder(1);
		if (this.skin2Btn == button)
			setBorder(2);
		if (this.skin3Btn == button)
			setBorder(3);
		
		provideChoice.refreshButtonImages();
	}

	/**
	 * sets the border of the teamlayout button
	 * @param button getting border
	 */
	private void setBorder(int skinNumber){
		if (skinNumber == 1){
			skin1Btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			skin2Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			skin3Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		}
		if (skinNumber == 2){
			skin1Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			skin2Btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			skin3Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		}
		if (skinNumber == 3){
			skin1Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			skin2Btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			skin3Btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		}

	}
}
