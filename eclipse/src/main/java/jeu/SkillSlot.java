package jeu;

import java.util.ArrayList;

public abstract class SkillSlot {
	
	public static int isSkillSlot(int x, int y) {
		
		ArrayList<Integer> slotsCoord = new ArrayList<Integer>(10);
		//Collections.addAll(slotsCoord, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
		slotsCoord.add(12);
		slotsCoord.add(13);
		slotsCoord.add(14);
		slotsCoord.add(15);
		slotsCoord.add(16);
		slotsCoord.add(17);
		slotsCoord.add(18);
		slotsCoord.add(19);
		slotsCoord.add(20);
		slotsCoord.add(21);
		int[] tab = Click.cases(x, y);

		
		if(tab[1] == 15) {
			for (int i = 0; i < 10; i++) {
				//System.out.println(i);
				if(slotsCoord.get(i) == tab[0]) return i;
			}
		}
		return -1;
	}
}
