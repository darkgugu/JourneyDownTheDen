package jeu;

import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import javafx.geometry.Point2D;

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
	
	public static void surbri(GameWorld gm, int x, int y, String border) {
				
		gm.spawn(border, new Point2D(x, y));
	}
	
	public static void del(GameWorld gm, EntityType entity) {
		
		List<Entity> partic = gm.getEntitiesByType(entity);
		for (Entity t : partic) {
			if (t.getTypeComponent().isType(entity)) {
				t.removeFromWorld();
			}
		}
	}
}
