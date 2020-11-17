package jeu;

public class Click {
  
	public static int[] cases(int x, int y) {

		int tab[] = new int[4];
		int xcase = x / 60;
		int ycase = y / 60;
		int xpix = xcase * 60;
		int ypix = ycase * 60;

		tab[0] = xcase;
		tab[1] = ycase;
		tab[2] = xpix;
		tab[3] = ypix;

		return tab;
	}
}
