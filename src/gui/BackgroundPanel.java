package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image image;

	public BackgroundPanel(){
		super();
	}
	

	public void setBackgroundImage(Image image){
		this.image = image;
		repaint();
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, this);
	}

}
