package jeu;

public class Click {
	
	public int[] cases(int x, int y) {
	
		int tab[] = new int[4];
		int xcase = x / 40;
		int ycase = y / 40;
		int xpix = xcase *40+20;
		int ypix = ycase *40+20;

		tab[0] = xcase;
		tab[1] = ycase;
		tab[2] = xpix;
		tab[3] = ypix;
		
		return tab;
	}

}
