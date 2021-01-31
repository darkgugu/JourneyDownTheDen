package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Pathfinding {
	
	static ObstacleReader obstacles = new ObstacleReader();
//	String currMap = BasicGameApp.getCurrentMap();
	static String currMap = "map1";
	
	public static List<SimpleEntry<Integer, Integer>> distMethod(int posx, int posy, int ex, int ey) {
		
		SimpleEntry<Integer, Integer> orig = new SimpleEntry<Integer, Integer>(posx, posy);
		SimpleEntry<Integer, Integer> S = new SimpleEntry<Integer, Integer>(posx, posy);
		SimpleEntry<Integer, Integer> E = new SimpleEntry<Integer, Integer>(ex, ey);
		List<SimpleEntry<Integer, Integer>> F = new ArrayList<SimpleEntry<Integer, Integer>>();
		
		int j = 0;
		
		obstacles.reader(currMap);
		
		List<SimpleEntry<Integer, Integer>> visited = new ArrayList<SimpleEntry<Integer, Integer>>();

		visited.add(S);
		F.add(S);
		
		Neighbors up = new Neighbors(), right = new Neighbors(), down = new Neighbors(), left = new Neighbors();
		Neighbors temp;
		Neighbors init = new Neighbors();
		
		init.setDist(100000);
		
		while(!S.equals(E)) {
			
			up.setNeighbor(new SimpleEntry<Integer, Integer>(S.getKey(), S.getValue() - 1));
			right.setNeighbor(new SimpleEntry<Integer, Integer>(S.getKey() + 1, S.getValue()));
			down.setNeighbor(new SimpleEntry<Integer, Integer>(S.getKey(), S.getValue() + 1));
			left.setNeighbor(new SimpleEntry<Integer, Integer>(S.getKey() - 1, S.getValue()));
				
			up.setDist(Math.sqrt(Math.pow((E.getKey() - up.getNeighbor().getKey()), 2) 	+ Math.pow((E.getValue() - up.getNeighbor().getValue()), 2)));
			right.setDist(Math.sqrt(Math.pow((E.getKey() - right.getNeighbor().getKey()), 2) 	+ Math.pow((E.getValue() - right.getNeighbor().getValue()), 2)));
			down.setDist(Math.sqrt(Math.pow((E.getKey() - down.getNeighbor().getKey()), 2) 	+ Math.pow((E.getValue() - down.getNeighbor().getValue()), 2)));
			left.setDist(Math.sqrt(Math.pow((E.getKey() - left.getNeighbor().getKey()), 2) 	+ Math.pow((E.getValue() - left.getNeighbor().getValue()), 2)));
						
			Neighbors[] tab = new Neighbors[4];
			
			tab[0] = up;
			tab[1] = right;
			tab[2] = down;
			tab[3] = left;
			
			temp = init;
			
			for (int i = 0; i < tab.length; i++) {
			
				if (tab[i].getDist() < temp.getDist() && !obstacles.map_obstacle.contains((tab)[i].getNeighbor()) && !visited.contains(tab[i].getNeighbor())) {
					temp = tab[i];
				}
			}
			
			if(temp.getNeighbor() != null) {
			
				S = temp.getNeighbor();
			}
			else {
			
				System.out.println("No path");
				return null;
			}
						
			F.add(S);
			visited.add(S);
			
			//System.out.println(F);
			
			if(j > 0 && checkRetour(orig, S)) {
				
				F.removeAll(F);
				F.add(orig);
				F.add(S);
			}
			j++;
		}
		return F;
	}
	
	public static boolean checkRetour(SimpleEntry<Integer, Integer> orig, SimpleEntry<Integer, Integer> curr) {
		
		if(	(orig.getKey() == curr.getKey() && orig.getValue() == curr.getValue() - 1) ||
			(orig.getKey() == curr.getKey() + 1 && orig.getValue() == curr.getValue()) ||
			(orig.getKey() == curr.getKey() && orig.getValue() == curr.getValue() + 1) || 
			(orig.getKey() == curr.getKey() - 1 && orig.getValue() == curr.getValue())){
			
			return true;
		}
		
		return false;
	}
}
