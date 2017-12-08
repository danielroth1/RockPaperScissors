package game.logic;

import game.Figure;
import game.FigureKind;
import game.Player;

import java.util.ArrayList;

import controll.Controll;

/**
 * this class creates startingGrids of different types
 * @author Daniel Roth
 */
public class StartingGridFactory {
	
	private int ROW_COUNT = Controll.ROW_COUNT;
	private int COLUMN_COUNT = Controll.COLUMN_COUNT;

	
	public StartingGridFactory() {
		
	}
	
	/**
	 * creates a random starting grid consisting out of figures
	 * the player of the figures will be player
	 * @param player of the figures
	 * @return Field random starting grid
	 */
	public Field getRandomStartingGrid(Player player){
		Field field = new Field();
		Figure[][] fig = field.getField();
		
		//creating of an array list containing all the 14 figures
		ArrayList<Figure> l = new ArrayList<Figure>();
		for(int i=0; i<4; i++){
			l.add(new Figure(FigureKind.SCISSOR, player));
			l.add(new Figure(FigureKind.ROCK, player));
			l.add(new Figure(FigureKind.PAPER, player));
		}
		l.add(new Figure(FigureKind.FLAG, player));
		l.add(new Figure(FigureKind.TRAP, player));
		
		//now the figures have to be set randomly to a field array
		int randomNumber;
		
		for (int r=0; r<2; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				randomNumber = (int) (Math.random() * l.size());
				field.setField(l.get(randomNumber), r, c);
//				System.out.println(randomNumber);
				l.remove(randomNumber);
			}
		}
		
		for (int r=ROW_COUNT-1; r>ROW_COUNT-3; r--){
			for (int c=0; c<COLUMN_COUNT; c++){
//				System.out.println(field.getField()[r][c].getFigureKind());
			}
		}
		
		return field;
	}

	public Field getOwnStartingGrid(){
		Field field = new Field();
		
		return field;
	}
}
