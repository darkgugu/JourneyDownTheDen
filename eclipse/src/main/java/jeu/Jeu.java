package jeu;

import personnages.BouleDeFeu;
import personnages.Healer;

public class Jeu {

	public static void main(String[] args) {

		Healer healer = new Healer();
		
		BouleDeFeu bdf = new BouleDeFeu(healer);
		
		System.out.println(bdf.getFinalDamages());

	}

}
