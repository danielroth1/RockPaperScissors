package game;

public enum FigureKind {
	ROCK, PAPER, SCISSOR, TRAP, FLAG;
	
	private FigureKind weakness;
	
	static{
		ROCK.setWeakness(PAPER);
		PAPER.setWeakness(SCISSOR);
		SCISSOR.setWeakness(ROCK);
	}
	
	private void setWeakness(FigureKind weakness){
		this.weakness = weakness;
	}
	
	private FigureKind getWeakness(){
		return weakness;
	}
	
	/**
	 * gives the result of an attack between two FigureKinds
	 * possible results: WIN, DRAW, LOOSE, LOOSE_AGAINST_TRAP, WIN_AGAINST_FLAG
	 * @param kind FigureKind
	 * @return AttackResult
	 */
	public AttackResult attackResult(FigureKind kind){
		if (this == kind.getWeakness())
			return AttackResult.WIN;
		else if (this.equals(kind))
			return AttackResult.DRAW;
		else if (this.getWeakness().equals(kind))
			return AttackResult.LOOSE;
		else if (kind == FigureKind.TRAP)
			return AttackResult.LOOSE_AGAINST_TRAP;
		else if (kind == FigureKind.FLAG)
			return AttackResult.WIN_AGAINST_FLAG;
		return null;
	}
	

}
