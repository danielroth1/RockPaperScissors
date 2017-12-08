package gui.Field.startingGrid;

import game.Player;
import game.logic.GameLogic;
import gui.Field.Images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import controll.Controll;
import controll.GameControll;

/**
 * defines the starting Grid panel
 * includes the gridPane
 * @author Daniel Roth
 */
public class OverviewPane extends JPanel{
	
	private OverviewPane panel = this;
	private Controll controll;
	private GameControll gameControll;
	private GridPane gridPane;
	private Images images;
	
	private JButton backBtn = new JButton("back");
	private JButton startBtn = new JButton("start");
	private JButton newBtn = new JButton("New");
	private JButton randomBtn = new JButton("Random");
	private JRadioButton createRadioBtn = new JRadioButton("Create");
	private JRadioButton deleteRadioBtn = new JRadioButton("Remove");
	private JRadioButton switchRadioBtn = new JRadioButton("Switch");
	private JCheckBox autoNextUnit = new JCheckBox("auto. Change");
	private JToggleButton scissorBtn = new JToggleButton("Scissor");
	private JToggleButton stoneBtn = new JToggleButton("Stone");
	private JToggleButton paperBtn = new JToggleButton("Paper");
	private JToggleButton flagBtn = new JToggleButton("Flag");
	private JToggleButton trapBtn = new JToggleButton("Trap");
	private JLabel scissorLabel = new JLabel("0");
	private JLabel stoneLabel = new JLabel("0");
	private JLabel paperLabel = new JLabel("0");
	private JLabel flagLabel = new JLabel("0");
	private JLabel trapLabel = new JLabel("0");
	
	private JLabel infoLabel = new JLabel();
	
	

	public OverviewPane(Controll controll, GameControll gameControll, GameLogic gameLogic, Images images, Player player) {
		this.controll = controll;
		this.gameControll = gameControll;
		this.images = images;
		gridPane = new GridPane(controll, gameControll, gameLogic, this, images, player);
		
		initiateButtons();
		initiateLabels();
		initiatePanel();
		this.setVisible(false);
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(gridPane)
						.addGroup(layout.createSequentialGroup()
								.addComponent(backBtn)
								.addComponent(startBtn))
								)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(scissorBtn)
								.addComponent(scissorLabel))
						.addGroup(layout.createSequentialGroup()
								.addComponent(stoneBtn)
								.addComponent(stoneLabel))
						.addGroup(layout.createSequentialGroup()
								.addComponent(paperBtn)
								.addComponent(paperLabel))
						.addGroup(layout.createSequentialGroup()
								.addComponent(flagBtn)
								.addComponent(flagLabel))
						.addGroup(layout.createSequentialGroup()
								.addComponent(trapBtn)
								.addComponent(trapLabel))
						.addComponent(newBtn)
						.addComponent(randomBtn)
						.addComponent(createRadioBtn)
						.addComponent(deleteRadioBtn)
						.addComponent(switchRadioBtn)
						.addComponent(autoNextUnit)
						.addComponent(infoLabel))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
						.addComponent(gridPane)
							.addGroup(layout.createParallelGroup()
							.addComponent(backBtn)
							.addComponent(startBtn))
						)
						.addGroup(layout.createSequentialGroup()
								.addGap(20)
								.addGroup(layout.createParallelGroup()
										.addComponent(scissorBtn)
										.addComponent(scissorLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(stoneBtn)
										.addComponent(stoneLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(paperBtn)
										.addComponent(paperLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(flagBtn)
										.addComponent(flagLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(trapBtn)
										.addComponent(trapLabel))
								.addGap(50)
								.addComponent(newBtn)
								.addComponent(randomBtn)
								.addComponent(createRadioBtn)
								.addComponent(deleteRadioBtn)
								.addComponent(switchRadioBtn)
								.addGap(10)
								.addComponent(autoNextUnit)
								.addGap(30)
								.addComponent(infoLabel))
						)
				
				);
		layout.setAutoCreateGaps(true);
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
		this.setBounds(0, 0, (int)Controll.GAME_FIELD_SIZE.getWidth(), (int)Controll.GAME_FIELD_SIZE.getHeight());
		this.setPreferredSize(Controll.GAME_FIELD_SIZE);
	}
	
	private void initiateButtons(){
		randomBtn.setToolTipText("Create a random starting position");
		autoNextUnit.setToolTipText("<html><body><p>Automatically changes to the next unit,<br> " +
				"if the maximum number of units of the current type is reached</html>");
		autoNextUnit.setSelected(true);
		createRadioBtn.setSelected(true);
		scissorBtn.setSelected(true);
		
		randomBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPane.handleRandomBtnSelection();
			}
		});
		newBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPane.handleNewBtnSelection();
			}
		});
		startBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPane.handleStartBtnSelection();
				controll.handleOwnGameStartSelection();
			}
		});
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controll.handleBackToGameOptionsSelection();
			}
		});
		ButtonGroup radios = new ButtonGroup();
		radios.add(createRadioBtn);
		radios.add(deleteRadioBtn);
		radios.add(switchRadioBtn);
		
		ButtonGroup types = new ButtonGroup();
		types.add(scissorBtn);
		types.add(stoneBtn);
		types.add(paperBtn);
		types.add(trapBtn);
		types.add(flagBtn);
		
	}
	
	private void initiateLabels(){
		setInfoLabel("<br><br><br><br>");
		infoLabel.setBorder(BorderFactory.createTitledBorder("Info"));
	}
	
	public GridPane getGridPane(){
		return gridPane;
	}
	
	public Boolean isCreateSelected(){
		return createRadioBtn.isSelected();
	}
	
	public Boolean isDeleteSelected(){
		return deleteRadioBtn.isSelected();
	}
	
	public Boolean isSwitchSelected(){
		return switchRadioBtn.isSelected();
	}
	
	public Boolean isScissorSelected(){
		return scissorBtn.isSelected();
	}
	
	public Boolean isRockSelected(){
		return stoneBtn.isSelected();
	}
	
	public Boolean isPaperSelected(){
		return paperBtn.isSelected();
	}
	
	public Boolean isTrapSelected(){
		return trapBtn.isSelected();
	}
	
	public Boolean isFlagSelected(){
		return flagBtn.isSelected();
	}
	
	public void setScissorSelected(){
		scissorBtn.setSelected(true);
	}
	
	public void setRockSelected(){
		stoneBtn.setSelected(true);
	}
	
	public void setPaperSelected(){
		paperBtn.setSelected(true);
	}
	
	public void setTrapSelected(){
		trapBtn.setSelected(true);
	}
	
	public void setFlagSelected(){
		flagBtn.setSelected(true);
	}
	
	public Boolean isAutoNextUnitSelected(){
		return autoNextUnit.isSelected();
	}
	
	public void setScissor(String s){
		scissorLabel.setText(s);
	}
	
	public void setRock(String s){
		stoneLabel.setText(s);
	}
	
	public void setPaper(String s){
		paperLabel.setText(s);
	}
	
	public void setFlag(String s){
		flagLabel.setText(s);
	}
	
	public void setTrap(String s){
		trapLabel.setText(s);
	}
	
	public void setInfoLabel(String s){
		infoLabel.setText("<html><body><p>" + s + "</p></body></html>");
	}

}
