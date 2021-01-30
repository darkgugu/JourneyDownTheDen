package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Test {

	public static void main(String[] args) {

		Pathfinding path = new Pathfinding();
		
		List<SimpleEntry<Integer, Integer>> list = path.distMethod(14, 9, 13, 7); 
		
		System.out.println("PM nécéssaires : " + (list.size()-1) + "   " + list);
		
		//System.out.println(list);
//		SkillSlot.del(getGameWorld());

	}
}
