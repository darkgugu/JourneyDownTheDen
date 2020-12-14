package jeu;

public abstract class SkillSlot {
	
	public int isSkillSlot(int x, int y) {
		
		int skillSlot = -1;
		
		/* 	
		 	100 = xmin des coords de la barre de sort
		 	200 = xmax des coords de la barre de sort
		 	300 & 400 = y min & max des sorts
		 */
		if(x >= 100 && x <= 200) {
		
			if(y >= 300 && y <= 400) skillSlot = 0;
			if(y >= 300 && y <= 400) skillSlot = 1;
			if(y >= 300 && y <= 400) skillSlot = 2;
			if(y >= 300 && y <= 400) skillSlot = 3;
			if(y >= 300 && y <= 400) skillSlot = 4;
			if(y >= 300 && y <= 400) skillSlot = 5;
			if(y >= 300 && y <= 400) skillSlot = 6;
			if(y >= 300 && y <= 400) skillSlot = 7;
			if(y >= 300 && y <= 400) skillSlot = 8;
			if(y >= 300 && y <= 400) skillSlot = 9;
		}
		else {
			
			return -1;
		}
		
		return skillSlot;
	}
}
