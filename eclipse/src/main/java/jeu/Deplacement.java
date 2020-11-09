package jeu;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Deplacement {
	
	public List<SimpleEntry<Integer, Integer>> list = new ArrayList<SimpleEntry<Integer, Integer>>();
	
	public void calculateCross(int pm, int posx, int posy) {
				
		for (int i = 1; i <= pm; i++) {
					
			add(posx - i, posy);
			add(posx, posy + i);
			add(posx + i, posy);
			add(posx, posy - i);
		}
	}
	
	public void calculateDiag(int pm, int posx, int posy) {
		
		if(pm == 0) {
			
			return;
		}
		for (int j = 1; j <= pm - 1; j++) {
		
			add(posx - pm + j, posy - j);
			add(posx + pm - j, posy + j);
			add(posx + j, posy - pm + j);
			add(posx - j, posy + pm - j);
		}
		
		calculateDiag(pm - 1, posx, posy);
	}
	
	public int[] cases(int x, int y) {
		
		int tab[] = new int[2];
		int xpix = x / 60 * 60 + 30;
		int ypix = y / 60 * 60 + 30;

		tab[0] = xpix;
		tab[1] = ypix;
		
		return tab;
	}

	public void add(int x, int y) {
		
		list.add(new SimpleEntry<Integer, Integer>(x, y));
	}
}
