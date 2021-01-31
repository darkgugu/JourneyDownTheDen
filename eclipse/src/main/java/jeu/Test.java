package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Test {

	public static void main(String[] args) {
		
		List<SimpleEntry<Integer, Integer>> list = Pathfinding.distMethod(14, 9, 18, 9); 
		
		System.out.println("PM nécéssaires : " + (list.size()-1) + "   " + list);
		
		//System.out.println(list);
//		SkillSlot.del(getGameWorld());

	}
}
