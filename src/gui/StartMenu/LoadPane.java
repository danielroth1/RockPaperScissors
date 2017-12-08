package gui.StartMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controll.Controll;


public class LoadPane extends JPanel{

	private Controll controll;
	private LoadOverviewPane loadOverviewPane;
	private LoadPane panel = this;
	
	private JButton loadBtn = new JButton("load");
	private JButton backBtn = new JButton("back");
	
	public LoadPane(Controll controll, LoadOverviewPane loadOverviewPane) {
		this.controll = controll;
		this.loadOverviewPane = loadOverviewPane;
		
		initiatePanel();
		handleButtonSelection();
		
		this.setVisible(false);
	}
	

	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(loadOverviewPane)
						.addGroup(layout.createSequentialGroup()
								.addComponent(loadBtn)
								.addComponent(backBtn))
						)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(loadOverviewPane)
					.addGroup(layout.createParallelGroup()
							.addComponent(loadBtn)
							.addComponent(backBtn))
					)
				);
		
		layout.setAutoCreateGaps(true);
	}
	
	private void handleButtonSelection(){
		loadBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleBackToStartupPane(panel);
			}
		});
	}

}
