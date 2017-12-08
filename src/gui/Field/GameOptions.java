package gui.Field;

import game.logic.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controll.Controll;
import controll.GameControll;


public class GameOptions extends JPanel{

	private Controll controll;
	private GameControll gameControll;
	private GameLogic gameLogic;
	private GameOptions panel = this;
	
	private JButton qickStart = new JButton("Quick Start");
	private JButton ownGame = new JButton("Custom Game");
	private JButton backBtn = new JButton("Back");
	
	public GameOptions(Controll controll, GameControll gameControll, GameLogic gameLogic) {
		this.controll = controll;
		this.gameControll = gameControll;
		this.gameLogic = gameLogic;
		
		initiatePanel();
		handleButtonSelection();
		this.setVisible(false);
	}
	
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(qickStart)
						.addComponent(ownGame)
						.addComponent(backBtn))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(qickStart)
						.addComponent(ownGame)
						.addComponent(backBtn))
				);
		layout.setAutoCreateGaps(true);
		panel.setOpaque(false);
	}
	
	private void handleButtonSelection(){
		qickStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleQuickStartSelection();
				gameControll.handleQickStart();
				gameLogic.startGame();
			}
		});
		ownGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleOwnGameSelection();
				gameLogic.startGame();
			}
		});
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleBackToStartupPane(panel);
			}
		});
		
	}

}
