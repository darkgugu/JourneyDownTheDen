package jeu;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Deplacement extends BasicGameApp {

	ObstacleReader obstacles = new ObstacleReader();

	public List<SimpleEntry<Integer, Integer>> list = new ArrayList<SimpleEntry<Integer, Integer>>();

	public void calculateCross(int pm, int casePlayerX, int casePlayerY) {

		for (int i = 1; i <= pm; i++) {

			add(casePlayerX - i, casePlayerY);
			add(casePlayerX, casePlayerY + i);
			add(casePlayerX + i, casePlayerY);
			add(casePlayerX, casePlayerY - i);
		}
	}

	public void calculateDiag(int pm, int casePlayerX, int casePlayerY) {

		if (pm == 0) {

			return;
		}
		for (int j = 1; j <= pm - 1; j++) {

			add(casePlayerX - pm + j, casePlayerY - j);
			add(casePlayerX + pm - j, casePlayerY + j);
			add(casePlayerX + j, casePlayerY - pm + j);
			add(casePlayerX - j, casePlayerY + pm - j);
		}
		calculateDiag(pm - 1, casePlayerX, casePlayerY);

	}

	public void add(int x, int y) {

		obstacles.reader();

		if (!obstacles.map_obstacle.contains(new SimpleEntry<Integer, Integer>(x, y))) {

			list.add(new SimpleEntry<Integer, Integer>(x, y));
		}
	}
}
