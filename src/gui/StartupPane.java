package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controll.Controll;
import controll.GameControll;


public class StartupPane extends JPanel{

	private Controll controll;
	private GameControll gameControll;
	private StartupPane panel = this;
	
	private JButton startBtn = new JButton("New Game");
	private JButton loadBtn = new JButton("Load Game");
	private JButton settingsBtn = new JButton("Settings");
	private JButton abortBtn = new JButton("Abort");
	
	
	public StartupPane(Controll controll, GameControll gameControll) {
		this.controll = controll;
		this.gameControll = gameControll;
		
		initiatePanel();
		bindButtons();
		this.setVisible(false);
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(startBtn)
						.addComponent(loadBtn)
						.addComponent(settingsBtn)
						.addComponent(abortBtn))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(startBtn)
						.addComponent(loadBtn)
						.addComponent(settingsBtn)
						.addComponent(abortBtn))
				);
		layout.setAutoCreateGaps(true);
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setOpaque(false);
		
		this.setVisible(false);
	}

	
	private void bindButtons(){
		startBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleStartSelection();
			}
		});
		loadBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleLoadSelection();
			}
		});
		settingsBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleSettingsSelection();
			}
		});
		abortBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleAbortSelection();
			}
		});

	}
	
	
	
}
