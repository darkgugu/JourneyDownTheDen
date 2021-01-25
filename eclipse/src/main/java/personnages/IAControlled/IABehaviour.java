package personnages.IAControlled;

import java.util.List;

import java.util.AbstractMap.SimpleEntry;
import jeu.Click;
import jeu.Deplacement;
import jeu.IAControlledEntity;
import jeu.Player;
import ui.GameLog;

public class IABehaviour {
	
	public static void isRangeAgressiveSpell(IAControlledEntity unit, Player[] players) {
		
		int range = unit.getType().getSkills()[0].getRange();
		int tab[] = Click.cases(((int) unit.getPosition().getX()), ((int) unit.getPosition().getY()));
		int posx = tab[0];
		int posy = tab[1];
		
		Deplacement calcRange = new Deplacement();
		calcRange.calculateCross(range, posx, posy);
		calcRange.calculateDiag(range, posx, posy);
		List<SimpleEntry<Integer, Integer>> list = calcRange.list;
	
		for(int i = 0;i != players.length;i++) {
			
			int tabPlayer[] = Click.cases(((int) players[i].getPosition().getX()), ((int) players[i].getPosition().getY()));
			
			if(list.contains(new SimpleEntry<Integer, Integer>(tabPlayer[0], tabPlayer[1])) && players[i].getHeroClass().isDead() == false) {
				
				unit.getType().getSkills()[0].cast(unit.getType(), players[i].getHeroClass());
			}
			
		}
	}
	
	public static double[] getDist(IAControlledEntity unit, Player[] players) {
				
		double[] posx = new double[3];
		double[] posy = new double[3];
		double[] dist = new double[3];
		
		for (int i = 0; i < players.length; i++) {

			posx[i] = players[i].getPosition().getX();
			posy[i] = players[i].getPosition().getY();
			
			dist[i] = Math.sqrt(Math.pow((posx[i] - unit.getPosition().getX()), 2) + Math.pow((posy[i] - unit.getPosition().getY()), 2));			
		}
		
		return dist;
	}
	
	public static double[] getTar(IAControlledEntity unit, double dist[], Player[] players) {
		
		Player closePlayer = players[0];
		double closeDist = dist[0];
		double xtar = unit.getPosition().getX();
		double ytar = unit.getPosition().getY();
		double[] tar = new double[2];
		double X = unit.getPosition().getX();
		double Y = unit.getPosition().getY();
		int i = 1;
		int iX = 1;
		int iY = 1;
		
		for (int j = 0; j < players.length; j++) {
			if (players[j].getHeroClass().isDead()) {
				
				dist[j] = 1000000;
			}
		}
		
		if(dist[0] <= dist[1] && dist[0] <= dist[2]) {
			closePlayer = players[0];
			closeDist = dist[0];
		}
		else if(dist[1] <= dist[2] && dist[1] <= dist[0]) {
			closePlayer = players[1];
			closeDist = dist[1];
		}
		else if(dist[2] <= dist[0] && dist[2] <= dist[1]) {
			closePlayer = players[2];
			closeDist = dist[2];
		}
		
		double Xp = closePlayer.getPosition().getX();
		double Yp = closePlayer.getPosition().getY();		
		if(closeDist <= unit.getType().getAggroRange() * 60 && closeDist > 60) {
			
			if (unit.getType().getHasAggro() == false) {
				unit.getType().setHasAggro(true);
				GameLog.setGameLog(unit.getName() + " � reper� l'�quipe !");
			}
			int pm = unit.getType().getMovePoint();

			if(X == Xp) {
				
				xtar = X;
				double delta = Yp - Y;
				ytar = Y + tar2(Math.abs(delta), pm) * (delta/Math.abs(delta));
				tar[0] = xtar;
				tar[1] = ytar;
				return tar;
				
			}
			if(Y == Yp) {
				
				ytar = Y;
				double delta = Xp - X;
				xtar = X + tar2(Math.abs(delta), pm) * (delta/Math.abs(delta));
				tar[0] = xtar;
				tar[1] = ytar;
				return tar;
			}
			while(pm != 0 && X != Xp && Y != Yp) {

				if (i > 0) {
				
					double deltaX = Xp- X;
					xtar = X + 60 * iX * (deltaX/Math.abs(deltaX));
					i = -1;
					pm--;
					iX++;
				}
				else if(i < 0){
					
					if(xtar != Xp || Math.abs(Xp - X) >= 60) {
					
						double deltaY = Yp- Y;
						ytar = Y + 60 * iY * (deltaY/Math.abs(deltaY));
					}
					
					i = 1;
					pm--;
					iY++;
				}
			}
			if(xtar == Xp && ytar == Yp) {
				
				if((Yp - Y)/Math.abs((Yp - Y)) == 1) {
					ytar -= 60;
				}
				else if((Yp - Y)/Math.abs((Yp - Y)) == -1){
					ytar += 60;
				}
			}
			tar[0] = xtar;
			tar[1] = ytar;
			return tar;
		}
		return tar;
	}
	
	public static double tar2(double delta, int pm) {
		
		if(delta > pm * 60) {
			return pm * 60; 
		}
		else {
			return tar2(delta, pm - 1);
		}
	}
	
	public static Player[] reducePlayerTab(Player[] players) {
		
		Player[] playersTab2 = new Player[2];
		Player[] playersTab1 = new Player[1];
		
		int k = 0;
		int j = 0;
		
		for (int i = 0; i < players.length; i++) {
			
			if (players[i].getHeroClass().isDead()) {
				players[i] = null;
				k++;
			}
		}
		for (int i = 0; i < players.length; i++) {
			
			if (players[i] != null) {
				
				if (k == 2) {
				
					playersTab1[j] = players[i];
					j++;
				}
				else if (k == 1) {
					
					playersTab2[j] = players[i];
					j++;
				}
			}
		}
		
		if (k == 1) {
			return playersTab2;
		}
		else if (k == 2) {
			return playersTab1;
		}
		else {
			return players;
		}
	}
}
