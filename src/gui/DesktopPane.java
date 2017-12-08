package gui;

import gui.Field.ProvideChoice;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;

import controll.Controll;

public class DesktopPane extends JDesktopPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension GAME_FIELD_SIZE = Controll.GAME_FIELD_SIZE;
	private Dimension MENU_SIZE = Controll.MENU_SIZE;
	private ProvideChoice provideChoice;

	public DesktopPane(ProvideChoice provideChoice) {
		this.provideChoice = provideChoice;
		initiateContents();
		
		initiateDesktopPane();
	}
	
	private void initiateContents(){
		this.add(provideChoice);
	}
	
	private void initiateDesktopPane(){
		//is not necessary with a desktop pane!
//		setBounds(0, 0, (int)PANEL_SIZE.getWidth(), (int)PANEL_SIZE.getHeight());
		this.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(false);
		this.setVisible(true);
		this.setPreferredSize(GAME_FIELD_SIZE);
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	


}
