package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import capacites.BouleDeFeu;
import capacites.Soin;
import fxglExemple.BasicGameApp;
import personnages.IAControlled.Gobelin;
import personnages.playerControlled.Healer;
import personnages.playerControlled.Magician;
import personnages.playerControlled.Warrior;

public class Jeu{
	

	public static void main(String[] args) {
		
		Deplacement depl = new Deplacement();
		
		depl.calculateCross(3, 4, 4);
		depl.calculateDiag(3, 4, 4);
		int[] tab = new Click().cases(1053, 502);
		
		List<SimpleEntry<Integer, Integer>> list = depl.list;
		
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(4, 8);

		
		if(list.contains(vars)) {
			
			System.out.println("ok");
		}
		
		System.out.println(tab[0] + "," + tab[1]);

//		Healer healer = new Healer();
//		Magician magician = new Magician();
//		Gobelin gobelin = new Gobelin();
//		Warrior warrior = new Warrior();
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
