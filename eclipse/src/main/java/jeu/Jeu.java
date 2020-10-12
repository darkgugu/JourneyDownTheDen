package jeu;

import capacites.BouleDeFeu;
import personnages.playerControlled.Healer;

public class Jeu {

	public static void main(String[] args) {

		Healer healer = new Healer();
		
		BouleDeFeu bdf = new BouleDeFeu(healer);
		
		System.out.println(bdf.getFinalDamages());

	}

}
