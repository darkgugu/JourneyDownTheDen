package jeu;

import personnages.playerControlled.Personnages;

public class Tour {
	
	private Personnages persos[] = new Personnages[3];

	public Tour(Personnages[] persos) {
		super();
		this.persos = persos;
	}
	
	public void debut() {
		
		for (int i = 0; i < persos.length; i++) {
			
			persos[i].setActionPointToMax();
			persos[i].setMovePointToMax();
		}
	}
	
	public boolean checkFin() {
		
		int points = 0;
		
		for (int i = 0; i < persos.length; i++) {
			points += persos[i].getActionPoint();
			points += persos[i].getMovePoint();
		}
		
		if (points < 1) {
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public Player CheckPlayer(Player[] persos, int x, int y) {
		
		for (int i = 0; i < persos.length; i++) {
			
			if(Click.cases(x, y) == position.getX()) {
				
				
			}
		}
		
		
		return null;
	}

}
