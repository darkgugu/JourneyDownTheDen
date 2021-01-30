package jeu;

import javafx.geometry.Point2D;
import personnages.IAControlled.IABehaviour;
import personnages.playerControlled.Personnages;
import ui.CharInfoView;
import ui.GameLog;

public class Tour {

	private Player players[] = new Player[3];
	private Personnages persos[] = new Personnages[3];
	private int nbTour = 0;
	private IAControlledEntity ennemy;
	private IAControlledEntity orc;
	private CharInfoView view;
	private KillUnit killUnit;
	private WinOrDefeat endGame;

	public Tour(Player perso0, Player perso1, Player perso2, IAControlledEntity gobelin, CharInfoView view,
			KillUnit killUnit, WinOrDefeat endGame) {
		super();
		players[0] = perso0;
		players[1] = perso1;
		players[2] = perso2;

		persos[0] = perso0.getHeroClass();
		persos[1] = perso1.getHeroClass();
		persos[2] = perso2.getHeroClass();
		this.ennemy = gobelin;

		this.view = view;
		this.killUnit = killUnit;
		this.endGame = endGame;
	}
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public void updatePerso() {
		persos[0] = players[0].getHeroClass();
		persos[1] = players[1].getHeroClass();
		persos[2] = players[2].getHeroClass();
	}
	public void debut() {
		GameLog.setGameLog("==============/Tour    Ennemi/==============");
		if (ennemy.getType().isDead() == false) {
			double[] tar = ennemyMove();
			ennemy.move(new Point2D(tar[0], tar[1]));
			ennemyAttack();
		}
		GameLog.setGameLog("==============/Nouveau Tour/==============");
		nbTour++;
		for (int i = 0; i < persos.length; i++) {
			persos[i].setActionPointToMax();
			persos[i].setDidMove(false);
			System.out.println(persos[i].getName());
		}
		view.updateInfo(players[0], players[1], players[2], GameLog.getGameLog(), getNbTour());
		killUnit.checkKill(players[0], players[1], players[2], ennemy, orc);
		endGame.gameState(players[0], players[1], players[2], ennemy, orc);

	}

	public double[] ennemyMove() {

		return IABehaviour.getTarCoor(ennemy, IABehaviour.getDist(ennemy, players), players);
	}
	
	public void ennemyAttack() {

		IABehaviour.isRangeAgressiveSpell(ennemy, players);
	}

	public boolean checkFin() {

		int points = 0;

		for (int i = 0; i < persos.length; i++) {
			points += persos[i].getActionPoint();
			points += persos[i].getMovePoint();
		}

		if (points < 1) {

			return true;
		} else {

			return false;
		}
	}

	public Player checkPlayer(Player[] persos, int x, int y) {

		for (int i = 0; i < persos.length; i++) {

			int[] tabPerso = Click.cases(x, y);
			int[] tabClick = Click.cases(x, y);

			if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {

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
