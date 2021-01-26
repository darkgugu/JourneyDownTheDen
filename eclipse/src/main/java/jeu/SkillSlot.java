package jeu;

import java.util.ArrayList;

public abstract class SkillSlot {
	
	public static int isSkillSlot(int x, int y) {
		
		ArrayList<Integer> slotsCoord = new ArrayList<Integer>(10);
		for (int i = 12;i <= 21; i++) {
			slotsCoord.add(i);
		}
		
		int[] tab = Click.cases(x, y);

		
		if(tab[1] == 15) {
			for (int i = 0; i < 10; i++) {
				if(slotsCoord.get(i) == tab[0]) return i;
			}
		}
		return -1;
	}
}
