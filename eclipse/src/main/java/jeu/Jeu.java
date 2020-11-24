package jeu;

import java.util.List;

import capacites.BouleDeFeu;
import capacites.Soin;
import personnages.IAControlled.Gobelin;
import personnages.playerControlled.Healer;
import personnages.playerControlled.Magician;
import personnages.playerControlled.Personnages;
import personnages.playerControlled.Warrior;

import java.util.AbstractMap.SimpleEntry;



@SuppressWarnings("unused")
public class Jeu{
	

	public static void main(String[] args) {
		
//		Deplacement depl = new Deplacement();
//		
//		depl.calculateCross(3, 4, 4);
//		depl.calculateDiag(3, 4, 4);
//		new Click();
//		int[] tab = Click.cases(1053, 502);
//		
//		List<SimpleEntry<Integer, Integer>> list = depl.list;
//		
//		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(4, 8);
//
//		
//		if(list.contains(vars)) {
//			
//			System.out.println("ok");
//		}
//		
//		System.out.println(tab[0] + "," + tab[1]);
//
//		Healer healer = new Healer();
//		Magician magician = new Magician();
//		Gobelin gobelin = new Gobelin();
//		Warrior warrior = new Warrior();
//		
//		Personnages persos[] = new Personnages[3];
//		
//		persos[0] = healer;
//		persos[1] = magician;
//		persos[2] = warrior;
//		
//		Tour tour = new Tour(persos);
//		
//		for (int i = 0; i < 30; i++) {
//
//			magician.setActionPoint(magician.getActionPoint() - 1);
//			magician.setMovePoint(magician.getMovePoint() - 1);
//			
//			warrior.setActionPoint(warrior.getActionPoint() - 1);
//			warrior.setMovePoint(warrior.getMovePoint() - 1);
//			
//			healer.setActionPoint(healer.getActionPoint() - 1);
//			healer.setMovePoint(healer.getMovePoint() - 1);
//			
//			System.out.print("H/W/M " + healer.getActionPoint());
//			System.out.print(" / " + warrior.getActionPoint());
//			System.out.print(" / " + magician.getActionPoint());
//			System.out.println();
//			
//			if(tour.checkFin()) {
//				tour.debut();
//				System.out.println("//////// TOUR SUIVANT /////////");
//			}
//		}
//	
//		BouleDeFeu bdf = new BouleDeFeu();
//		Soin soin = new Soin();
//		
//		Pregame team = new Pregame();
//		
//		team.setTeam(magician);
//		team.setTeam(healer);
//		team.setTeam(warrior);
//				
//		healer.setSkills(bdf, soin);
//		
//		System.out.println("Gobelin HP : " + gobelin.getPv() + "/" + gobelin.getPvMax());
//		
//		new BouleDeFeu().cast(magician, gobelin);
//		new BouleDeFeu().cast(magician, gobelin);
//
//		soin.cast(healer, magician);
//
//		System.out.println("Gobelin HP : " + gobelin.getPv() + "/" + gobelin.getPvMax());
//		
//		System.out.println(healer);
		
//		BasicGameApp game = new BasicGameApp();
		
//		game.initGameVars(healer.getPv(), healer.getPvMax());
		
//		game.main(args);
	}
}
