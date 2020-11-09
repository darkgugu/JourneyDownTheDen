package jeu;

public class Click {
	
	public int[] cases(int x, int y) {
		
		int tab[] = new int[2];
		int xcase = x / 60;
		int ycase = y / 60;
		
		tab[0] = xcase;
		tab[1] = ycase;
		
		return tab;
	}

}
