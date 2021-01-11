package jeu;

import java.util.Hashtable;

public class Click {

	public static int[] cases(int x, int y) {

		int tab[] = new int[6];
		int xcase = x / 60;
		int ycase = y / 60;
		int xpix = xcase * 60;
		int ypix = ycase * 60;

		tab[0] = xcase;
		tab[1] = ycase;
		tab[2] = xpix;
		tab[3] = ypix;
		tab[4] = 60;
		tab[5] = 120;
		return tab;
	}

	// Table de hachage contenant les cases bloquées
	Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();

	public static int[] casesNonValides(int x, int y) {
		int tabNonValid[] = new int[50];
		tabNonValid[0] = 60;
		tabNonValid[1] = 60;
		return tabNonValid;
	}

}
