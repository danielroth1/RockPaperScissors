package gui.StartMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controll.Controll;


public class SettingsPane extends JPanel{

	private Controll controll;
	private SettingsPane panel = this;

	
	private JButton soundBtn = new JButton("Sound");
	private JButton skinsBtn = new JButton("Skins");
	private JButton abortBtn = new JButton("Abbrechen");
	
	
	public SettingsPane(Controll controll) {
		this.controll = controll;
		
		initiatePanel();
		handleButtonSelection();
		this.setVisible(false);
		
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
//				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//					.addComponent(soundBtn)
					.addComponent(skinsBtn)
					.addComponent(abortBtn))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()	
//				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//				.addComponent(soundBtn)
				.addComponent(skinsBtn)
				.addComponent(abortBtn)
				);
		panel.setOpaque(false);
		layout.setAutoCreateGaps(true);
		
		
	}
	
	private void handleButtonSelection(){
		soundBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		skinsBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleSkinSelection();
			}
		});
		abortBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				apply();
				controll.handleBackToStartupPane(panel);
			}
		});
	}
	
	/**
	 * made settings are getting applied
	 *TODO implement apply settings
	 */
	private void apply(){
		
	}

}
