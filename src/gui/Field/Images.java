package gui.Field;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * contains all the images
 * @author Daniel Roth
 */
public class Images {
	
	private String projPath = "";
	private String imagesPath;
	private String imagesIconPath;
	private String imagesIconRemotePath;
	
	private String flagPath;
	private String trapPath;
	private String scissorPath;
	private String rockPath;
	private String paperPath;
	private String ghostPath;
	private String scissorActivatedPath;
	private String rockActivatedPath;
	private String paperActivatedPath;
	private String scissorDiscoveredPath;
	private String rockDiscoveredPath;
	private String paperDiscoveredPath;
	private String scissorRemotePath;
	private String rockRemotePath;
	private String paperRemotePath;
	private String ghostRemotePath;
	private String arrowUpPath;
	private String arrowDownPath;
	private String arrowLeftPath;
	private String arrowRightPath;
	private String backgroundMenuPath;
	private String backgroundFieldPath;
	private String endGamePaneLostPath;
	private String endGamePaneWinPath;
	private String team1Path;
	private String team2Path;
	private String team3Path;

	
	private ImageIcon flag;
	private ImageIcon trap;
	private ImageIcon scissor;
	private ImageIcon rock;
	private ImageIcon paper;
	private ImageIcon ghost;
	private ImageIcon scissorActivated;
	private ImageIcon rockActivated;
	private ImageIcon paperActivated;
	private ImageIcon scissorDiscovered;
	private ImageIcon rockDiscovered;
	private ImageIcon paperDiscovered;
	private ImageIcon scissorRemote;
	private ImageIcon rockRemote;
	private ImageIcon paperRemote;
	private ImageIcon ghostRemote;
	private ImageIcon arrowUp;
	private ImageIcon arrowDown;
	private ImageIcon arrowLeft;
	private ImageIcon arrowRight;
	private Image backgroundMenu;
	private Image backgroundField;
	private Image endGamePaneLost;
	private Image endGamePaneWin;
	private ImageIcon team1;
	private ImageIcon team2;
	
	private int local;
	private int remote;


	
	/**
	 * constructor: sets the standard imageLayout 1
	 */
	public Images(){
		setImagesLayout(1, 1);
	}
	
	/**
	 * sets Images with different layouts. There are currently 3 different
	 * layout types available. Feel free to choose between 1, 2 and 3.
	 * 1 represents the standard layout.
	 * @param local layout type for your own team 
	 * @param remote layout type for enemy team
	 */
	public Images(int local, int remote) {
		setImagesLayout(local, remote);
	}
	

	/**
	 * sets the given Images to a specific Icon Layout
	 * there are currently 3 different layoutTypes 1, 2 and 3
	 * @param i layout number
	 */
	public void setImagesLayout(int local, int remote){
		this.local = local;
		this.remote = remote;
		initiatePaths(local, remote);
		initiateIcons();
	}
	
	public void setLocalSkin(int local){
		this.local = local;
		initiatePaths(local, remote);
		initiateIcons();
	}
	
	public void setRemoteSkin(int remote){
		this.remote = remote;
		initiatePaths(local, remote);
		initiateIcons();
	}
	
	public int getLocalSkin(){
		return local;
	}
	
	public int getRemoteSkin(){
		return remote;
	}
	
	/**
	 * initiates the paths for the ImageIcons
	 * with the parameter local you can decide with layout you want to load
	 * there are currently 3 different layouts available 1, 2 and 3
	 * @param local stands for the layout number
	 * @param remote stands for the layout number of the enemy team
	 */
	private void initiatePaths(int local, int remote){
		imagesPath = "/gui/Field/images/";
		imagesIconPath = imagesPath + "layout" + local + "/";
		imagesIconRemotePath = imagesPath + "layout" + remote + "/remote/";
		
		flagPath = projPath + imagesIconPath + "flag.png";
		trapPath = projPath + imagesIconPath + "trap.png";
		scissorPath = projPath + imagesIconPath + "scissor.png";
		rockPath = projPath + imagesIconPath + "rock.png";
		paperPath = projPath + imagesIconPath + "paper.png";
		ghostPath = projPath + imagesIconPath + "ghost.png";
		scissorActivatedPath = projPath + imagesIconPath + "scissorActivated.png";
		rockActivatedPath = projPath + imagesIconPath + "rockActivated.png";
		paperActivatedPath = projPath + imagesIconPath + "paperActivated.png";
		scissorDiscoveredPath = projPath + imagesIconPath + "scissorDiscovered.png";
		rockDiscoveredPath = projPath + imagesIconPath + "rockDiscovered.png";
		paperDiscoveredPath = projPath + imagesIconPath + "paperDiscovered.png";
		scissorRemotePath = projPath + imagesIconRemotePath + "scissor.png";
		rockRemotePath = projPath + imagesIconRemotePath + "rock.png";
		paperRemotePath = projPath + imagesIconRemotePath + "paper.png";
		ghostRemotePath = projPath + imagesIconRemotePath + "ghost.png";
		arrowUpPath = projPath + imagesPath + "arrowUP.png";
		arrowDownPath = projPath + imagesPath + "arrowDOWN.png";
		arrowLeftPath = projPath + imagesPath + "arrowLEFT.png";
		arrowRightPath = projPath + imagesPath + "arrowRIGHT.png";
		backgroundMenuPath = projPath + imagesPath + "backgroundMenu.png";
		backgroundFieldPath = projPath + imagesPath + "backgroundField.png";
		endGamePaneLostPath = projPath + imagesPath + "endGamePaneLost.png";
		endGamePaneWinPath = projPath + imagesPath + "endGamePaneWin.png";
		team1Path = projPath + imagesPath + "team1.png";
		team2Path = projPath + imagesPath + "team2.png";
		team3Path = projPath + imagesPath + "team3.png";
	}
	
	/**
	 * initiates the icons, that can be used for the playing field
	 */
	private void initiateIcons(){
		flag = new ImageIcon(getClass().getResource(flagPath));
		trap = new ImageIcon(getClass().getResource(trapPath));
		scissor = new ImageIcon(getClass().getResource(scissorPath));
		rock = new ImageIcon(getClass().getResource(rockPath));
		paper = new ImageIcon(getClass().getResource(paperPath));
		ghost = new ImageIcon(getClass().getResource(ghostPath));
		scissorActivated = new ImageIcon(getClass().getResource(scissorActivatedPath));
		rockActivated = new ImageIcon(getClass().getResource(rockActivatedPath));
		paperActivated = new ImageIcon(getClass().getResource(paperActivatedPath));
		scissorDiscovered = new ImageIcon(getClass().getResource(scissorDiscoveredPath));
		rockDiscovered = new ImageIcon(getClass().getResource(rockDiscoveredPath));
		paperDiscovered = new ImageIcon(getClass().getResource(paperDiscoveredPath));
		scissorRemote = new ImageIcon(getClass().getResource(scissorRemotePath));
		rockRemote = new ImageIcon(getClass().getResource(rockRemotePath));
		paperRemote = new ImageIcon(getClass().getResource(paperRemotePath));
		ghostRemote = new ImageIcon(getClass().getResource(ghostRemotePath));
		arrowUp = new ImageIcon(getClass().getResource(arrowUpPath));
		arrowDown = new ImageIcon(getClass().getResource(arrowDownPath));
		arrowLeft = new ImageIcon(getClass().getResource(arrowLeftPath));
		arrowRight = new ImageIcon(getClass().getResource(arrowRightPath));
		backgroundMenu = Toolkit.getDefaultToolkit().getImage(getClass().getResource(backgroundMenuPath));
		backgroundField = Toolkit.getDefaultToolkit().getImage(getClass().getResource(backgroundFieldPath));
		endGamePaneLost = Toolkit.getDefaultToolkit().getImage(getClass().getResource(endGamePaneLostPath));
		endGamePaneWin = Toolkit.getDefaultToolkit().getImage(getClass().getResource(endGamePaneWinPath));
		team1 = new ImageIcon(getClass().getResource(team1Path));
		team2 = new ImageIcon(getClass().getResource(team2Path));
	}



	public ImageIcon getFlag() {
		return flag;
	}

	public ImageIcon getTrap() {
		return trap;
	}

	public ImageIcon getScissor() {
		return scissor;
	}

	public ImageIcon getRock() {
		return rock;
	}

	public ImageIcon getPaper() {
		return paper;
	}

	public ImageIcon getGhost() {
		return ghost;
	}

	public ImageIcon getScissorActivated() {
		return scissorActivated;
	}

	public ImageIcon getRockActivated() {
		return rockActivated;
	}

	public ImageIcon getPaperActivated() {
		return paperActivated;
	}

	public ImageIcon getScissorDiscovered() {
		return scissorDiscovered;
	}

	public ImageIcon getRockDiscovered() {
		return rockDiscovered;
	}

	public ImageIcon getPaperDiscovered() {
		return paperDiscovered;
	}

	public ImageIcon getScissorRemote() {
		return scissorRemote;
	}

	public ImageIcon getRockRemote() {
		return rockRemote;
	}

	public ImageIcon getPaperRemote() {
		return paperRemote;
	}

	public ImageIcon getGhostRemote() {
		return ghostRemote;
	}

	public ImageIcon getArrowUp() {
		return arrowUp;
	}

	public ImageIcon getArrowDown() {
		return arrowDown;
	}

	public ImageIcon getArrowLeft() {
		return arrowLeft;
	}

	public ImageIcon getArrowRight() {
		return arrowRight;
	}
	
	public Image getBackgroundMenu(){
		return backgroundMenu;
	}
	
	public Image getBackgroundField(){
		return backgroundField;
	}
	
	public Image getEndGamePaneLost(){
		return endGamePaneLost;
	}
	
	public Image getEndGamePaneWin(){
		return endGamePaneWin;
	}

	public ImageIcon getTeam1() {
		return team1;
	}

	public ImageIcon getTeam2() {
		return team2;
	}

}
