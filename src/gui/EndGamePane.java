package gui;

import gui.Field.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import controll.Controll;
import controll.GameControll;


public class EndGamePane extends JInternalFrame{

	private Controll controll;
	private GameControll gameControll;
	private EndGamePane panel;
	private Images images;
	
	private JLabel label = new JLabel();
	private JLabel labelState = new JLabel();
	private JButton mainMenuBtn = new JButton("Main menu");
	private JButton backToGameBtn = new JButton("Back to game");
	
	private JPanel contentPane;
	private BackgroundPanel backgroundContentPane;
	private DesktopPane desktopPane;
	
	
	public EndGamePane(Controll controll, GameControll gameControll, DesktopPane desktopPane, Images images) {
		this.controll = controll;
		this.gameControll = gameControll;
		this.desktopPane = desktopPane;
		this.images = images;
		
		
		initiateBackgroundContentPane();
		initiateContentPane();
		initiateFont();
		
		
		initiateFrame();
		desktopPane.add(this);
		this.setVisible(false);
		
		handleButtonsSelection();
	}
	
	private void initiateBackgroundContentPane(){
		backgroundContentPane = new BackgroundPanel();
		GroupLayout layout = new GroupLayout(backgroundContentPane);
		backgroundContentPane.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(label)
						.addComponent(labelState)
						.addComponent(mainMenuBtn)
						.addComponent(backToGameBtn))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(label)
						.addComponent(labelState)
						.addGap(50)
						.addComponent(mainMenuBtn)
						.addComponent(backToGameBtn))
				);
		layout.setAutoCreateGaps(true);
		backgroundContentPane.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		
		
//		backgroundContentPane.setBackgroundImage(new ImageIcon());
	}
	
	private void initiateContentPane(){
		contentPane = (JPanel) this.getContentPane();
		contentPane.add(backgroundContentPane);
		
	}
	

	
	private void initiateFrame(){
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.pack();
	}
	
	private void initiateFont(){
		labelState.setFont(labelState.getFont().deriveFont(20f));
		labelState.setForeground(Color.BLACK);
	}
	
	
	public void gameWon(){
		label.setText("You destroyed the enemy flag.");
		labelState.setText("Win!");
		backgroundContentPane.setBackgroundImage(images.getEndGamePaneWin());
		this.pack();
		setLocationRelativeToNull();
		this.toFront();
		this.moveToFront();
	}
	
	public void gameLost(){
		label.setText("Your flag was destroyed.");
		labelState.setText("Loss!");
		backgroundContentPane.setBackgroundImage(images.getEndGamePaneLost());
		this.pack();
		setLocationRelativeToNull();
		this.toFront();
		this.moveToFront();
	}
	
	private void handleButtonsSelection(){
		mainMenuBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleBackToMainMenuSelection();
			}
		});
		backToGameBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleBackToGameSelection();
			}
		});
	}
	
	public void setLocationRelativeToNull(){
		Dimension frameSize = this.getSize();
		Dimension desktopPaneSize = Controll.GAME_FIELD_SIZE;

		this.setLocation((int) (desktopPaneSize.getWidth() - frameSize.getWidth()) / 2,
				(int) (desktopPaneSize.getHeight() - frameSize.getHeight()) / 2);
	}

}
