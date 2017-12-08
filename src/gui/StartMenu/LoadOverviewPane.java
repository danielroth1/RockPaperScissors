package gui.StartMenu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import controll.Controll;
import controll.GameControll;


/**
 * Describes the list of loadable Games
 * @author Daniel Roth
 */
public class LoadOverviewPane extends JPanel{

	private Controll controll;
	private GameControll gameControll;
	private LoadPane loadPane;
	private LoadOverviewPane panel = this;
	
	private Dimension PANEL_SIZE = new Dimension(200, 200);
	
	public LoadOverviewPane(Controll controll, GameControll gameControll) {
		this.controll = controll;
		this.gameControll = gameControll;
		
		initiatePanel();
		
		this.setVisible(false);
	}
	
	public void setComponents(LoadPane loadPane){
		this.loadPane = loadPane;
	}
	
	private void initiatePanel(){
		panel.setMinimumSize(PANEL_SIZE);
		panel.setPreferredSize(PANEL_SIZE);
		panel.setMaximumSize(PANEL_SIZE);
		panel.setBackground(Color.CYAN);
	}
	
	

}
