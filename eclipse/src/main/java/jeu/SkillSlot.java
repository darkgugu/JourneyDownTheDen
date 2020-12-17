package jeu;

public abstract class SkillSlot {
	
	public static int isSkillSlot(int x, int y) {
		//780 840
		int skillSlot = -1;
		
		/* 	
		 	100 = xmin des coords de la barre de sort
		 	200 = xmax des coords de la barre de sort
		 	300 & 400 = y min & max des sorts
		 */
		if(y >= 900 && y <= 960) {
		
			if(x >= 840 && x <= 900) skillSlot = 0;
//			if(x >= 41 && x <= 80) skillSlot = 1;
//			if(x >= 300 && x <= 400) skillSlot = 2;
//			if(x >= 300 && x <= 400) skillSlot = 3;
//			if(x >= 300 && x <= 400) skillSlot = 4;
//			if(x >= 300 && x <= 400) skillSlot = 5;
//			if(x >= 300 && x <= 400) skillSlot = 6;
//			if(x >= 300 && x <= 400) skillSlot = 7;
//			if(x >= 300 && x <= 400) skillSlot = 8;
//			if(x >= 300 && x <= 400) skillSlot = 9;
		}
		else {
			
			return -1;
		}
		
		return skillSlot;
	}
}
