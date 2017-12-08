package gui.Field;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * creates a button, that can be used in a playingField
 * @author Daniel Roth
 */
public class FieldButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	/**
	 * size of the button
	 */
	public static Dimension SIZE = new Dimension(80, 80);
	
	
	
	public FieldButton(int row, int column, ImageIcon icon, ActionListener listener) {
		super();
		
		initiateButton();
		this.row = row;
		this.column = column;
		this.setIcon(icon);
		this.setDisabledIcon(icon);
		
		this.addActionListener(listener);
	}
	
	public FieldButton(ImageIcon icon, ActionListener listener){
		this(0, 0, icon, listener);
	}
	
	private void initiateButton(){
		this.setMinimumSize(SIZE);
		this.setPreferredSize(SIZE);
		this.setMaximumSize(SIZE);
		
		this.setBackground(new Color(0,0,0,0));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.setEnabled(false);
	}
	
	/**
	 * gets the row of the button
	 * @return row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * gets the column of the button
	 * @return column
	 */
	public int getColumn(){
		return column;
	}
	
	@Override
	public void setIcon(Icon icon){
		super.setIcon(icon);
		this.setDisabledIcon(icon);
	}
	
}
