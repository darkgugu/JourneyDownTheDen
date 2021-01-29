package ui;

import java.util.List;

import com.almasb.fxgl.entity.GameWorld;

import java.util.AbstractMap.SimpleEntry;

import javafx.geometry.Point2D;
import jeu.BasicGameApp;
import jeu.Click;
import jeu.Deplacement;
import jeu.IAControlledEntity;
import jeu.Pathfinding;
import jeu.Player;

public class ProximityCases extends BasicGameApp{

	public static void proxCases(Player player, Player redPlayer, Player bluePlayer, Player greenPlayer, IAControlledEntity gobelin, GameWorld gameWorld) {

		Pathfinding path = new Pathfinding();
		new Click();
		int tab[] = Click.cases(((int) player.getPosition().getX()), ((int) player.getPosition().getY()));
		
		Deplacement move = new Deplacement();
		move.calculateCross(player.getHeroClass().getMovePoint(), tab[0], tab[1]);
		move.calculateDiag(player.getHeroClass().getMovePoint(), tab[0], tab[1]);
		
		int tabMob[] = Click.cases((	(int) gobelin.getPosition().getX()), ((int) gobelin.getPosition().getY()));
		int tabRedHero[] = Click.cases(((int) redPlayer.getPosition().getX()), ((int) redPlayer.getPosition().getY()));
		int tabGreenHero[] = Click.cases(((int) greenPlayer.getPosition().getX()), ((int) greenPlayer.getPosition().getY()));
		int tabBlueHero[] = Click.cases(((int) bluePlayer.getPosition().getX()), ((int) bluePlayer.getPosition().getY()));
		
		List<SimpleEntry<Integer, Integer>> list = move.list;
		list.remove(new SimpleEntry<Integer, Integer>(tabMob[0], tabMob[1]));
		if(!redPlayer.getHeroClass().isDead()) list.remove(new SimpleEntry<Integer, Integer>(tabRedHero[0], tabRedHero[1]));
		if(!greenPlayer.getHeroClass().isDead()) list.remove(new SimpleEntry<Integer, Integer>(tabGreenHero[0], tabGreenHero[1]));
		if(!bluePlayer.getHeroClass().isDead()) list.remove(new SimpleEntry<Integer, Integer>(tabBlueHero[0], tabBlueHero[1]));

//		for (int i = 0; i < 31; i++) {
//			for (int j = 0; j < 14; j++) {
//				
//				SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(i, j);
//				
//				List<SimpleEntry<Integer, Integer>> pathf = path.distMethod(tab[0], tab[1], i, j); 
//				
//				if(list.contains(vars) && pathf != null && pathf.size() < player.getHeroClass().getMovePoint()) {
//					gameWorld.spawn("range", new Point2D(i * 60, j * 60));
//				
//				}
//			}
//		}
		
		for (SimpleEntry<Integer, Integer> i : list) {
			
			List<SimpleEntry<Integer, Integer>> pathf = path.distMethod(tab[0], tab[1], i.getKey(), i.getValue());
			

			if (pathf != null && pathf.size() - 1 <= player.getHeroClass().getMovePoint()) {
			
				gameWorld.spawn("range", new Point2D(i.getKey() * 60, i.getValue() * 60));
			}
		}
	}
}
