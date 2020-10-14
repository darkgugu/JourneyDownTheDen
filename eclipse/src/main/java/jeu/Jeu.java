package jeu;

import capacites.BouleDeFeu;
import capacites.Soin;
import personnages.playerControlled.Healer;
import personnages.playerControlled.Magician;

public class Jeu {

	public static void main(String[] args) {

		Healer healer = new Healer();
		Magician magician = new Magician();		
		
		BouleDeFeu bdf = new BouleDeFeu();
		Soin soin = new Soin();
		
		healer.setSkills(bdf, soin);
		
		//new BouleDeFeu().cast(magician);
		
		System.out.println("Magician HP : " + magician.getPv());
		
		soin.cast(healer, magician);

		System.out.println("Magician HP : " + magician.getPv());
		
		//System.out.println(healer);
		
	}

}
