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
	
		for(int i = 0;i != 3;i++) {
			
			int tabPlayer[] = Click.cases(((int) players[i].getPosition().getX()), ((int) players[i].getPosition().getY()));
			
			if(list.contains(new SimpleEntry<Integer, Integer>(tabPlayer[0], tabPlayer[1]))) {
				
				unit.getType().getSkills()[0].cast(unit.getType(), players[i].getHeroClass());
			}
			
		}
	}
	
	public static double[] getDist(IAControlledEntity unit, Player[] players) {
		
		double[] posx = new double[3];
		double[] posy = new double[3];
		double[] dist = new double[3];
		
		for (int i = 0; i < 3; i++) {

			posx[i] = players[i].getPosition().getX();
			posy[i] = players[i].getPosition().getY();
			
			//GameLog.setGameLog("Player " + i + " position : " + posx[i] + "," + posy[i]);
			
			dist[i] = Math.sqrt(Math.pow((posx[i] - unit.getPosition().getX()), 2) + Math.pow((posy[i] - unit.getPosition().getY()), 2));
			
			//GameLog.setGameLog("Distance = " + dist[i]);
		}
		
		return dist;
	}
	
	public static void closestPlayer(IAControlledEntity unit, double dist[], Player[] players) {
		
		Player closePlayer = players[0];
		double closeDist = dist[0];
		double xtar;
		double ytar;
		
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
		
		GameLog.setGameLog("Le joueur le plus proche est " + closePlayer.getName());
		
		//return closePlayer;
		
		if(closeDist <= unit.getType().getAggroRange() * 60 && closeDist > 60) {
			
			GameLog.setGameLog(closePlayer.getName() + " est à portée de " + unit.getName() + " et n'est pas au cac");
			if(unit.getPosition().getX() == closePlayer.getPosition().getX()) {
				
				xtar = unit.getPosition().getX();
				double delta = closePlayer.getPosition().getY() - unit.getPosition().getY();
				ytar = unit.getPosition().getY() + tar2(Math.abs(delta), unit.getType().getMovePoint());
				GameLog.setGameLog("Deplacement en case " + xtar/60 + "," + ytar/60);
				
			}
			if(unit.getPosition().getY() == closePlayer.getPosition().getY()) {
				
				ytar = unit.getPosition().getY();
				double delta = closePlayer.getPosition().getX() - unit.getPosition().getX();
				xtar = unit.getPosition().getX() + tar2(Math.abs(delta), unit.getType().getMovePoint());
				GameLog.setGameLog("Deplacement en case " + xtar/60 + "," + ytar/60);
			}
			
		}
	}
	
	public static double tar2(double delta, int pm) {
		
		if(delta > pm * 60) {
			return pm * 60; 
		}
		else {
			return tar2(delta, pm - 1);
		}
	}
	
	public static void moveIA() {
		
		
	}
}
