package jeu;

import personnages.playerControlled.Personnages;

public class Tour {
	
	private Personnages persos[] = new Personnages[3];
	private int nbTour = 0;

	public Tour(Personnages perso0, Personnages perso1, Personnages perso2) {
		super();
		persos[0] = perso0;
		persos[1] = perso1;
		persos[2] = perso2;
	}
	
	public void debut() {
		setNbTour(getNbTour() + 1);

		for (int i = 0; i < persos.length; i++) {

			persos[i].setActionPointToMax();
			//persos[i].setMovePointToMax();
			persos[i].setDidMove(false);
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
	
	public Player checkPlayer(Player[] persos, int x, int y) {
		
		for (int i = 0; i < persos.length; i++) {
			
			int[] tabPerso = Click.cases(x, y);
			int[] tabClick = Click.cases(x, y);
			
			
			if(tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {
				
				return persos[i];	
			}
		}	
		return null;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

}
