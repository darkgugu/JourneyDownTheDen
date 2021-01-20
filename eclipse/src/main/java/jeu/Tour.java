package jeu;

import personnages.IAControlled.IABehaviour;
import personnages.playerControlled.Personnages;

public class Tour {
	
	private Player players[] = new Player[3];
	private Personnages persos[] = new Personnages[3];
	private int nbTour = 0;
	private IAControlledEntity gobelin;

	public Tour(Player perso0, Player perso1, Player perso2, IAControlledEntity gobelin) {
		super();
		players[0] = perso0;
		players[1] = perso1;
		players[2] = perso2;
		
		persos[0] = perso0.getHeroClass();
		persos[1] = perso1.getHeroClass();
		persos[2] = perso2.getHeroClass();
		this.gobelin = gobelin;
	}
	
	public void debut() {
		
		ennemyTurn();
		
		for (int i = 0; i < persos.length; i++) {

			setNbTour(getNbTour() + 1);
			persos[i].setActionPointToMax();
			//persos[i].setMovePointToMax();
			persos[i].setDidMove(false);
		}
	}
	
	public void ennemyTurn() {
		
		IABehaviour.isRangeAgressiveSpell(gobelin, players);
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
