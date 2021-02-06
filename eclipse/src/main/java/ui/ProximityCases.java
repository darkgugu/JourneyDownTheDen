package ui;

import java.util.List;

import com.almasb.fxgl.entity.GameWorld;

import capacites.Capacites;

import java.util.AbstractMap.SimpleEntry;

import javafx.geometry.Point2D;
import jeu.BasicGameApp;
import jeu.Click;
import jeu.Deplacement;
import jeu.Pathfinding;
import jeu.Player;

public class ProximityCases extends BasicGameApp{
	
	private Capacites displaySpell = null;
	
	public void setDisplaySpell(Capacites displaySpell) {
		this.displaySpell = displaySpell;
	}

	public void proxCases(Player player, String casesToDisplay) {

		new Click();
		int tab[] = Click.cases(((int) player.getPosition().getX()), ((int) player.getPosition().getY()));
		
		Deplacement move = new Deplacement();
		
		if(casesToDisplay == "rangeUnit") {
			move.calculateCross(player.getHeroClass().getMovePoint(), tab[0], tab[1]);
			move.calculateDiag(player.getHeroClass().getMovePoint(), tab[0], tab[1]);
		}
		else if(displaySpell != null){
			move.calculateCross(displaySpell.getRange(), tab[0], tab[1]);
			move.calculateDiag(displaySpell.getRange(), tab[0], tab[1]);
		}
		
		List<SimpleEntry<Integer, Integer>> list = move.list;
		
		for (SimpleEntry<Integer, Integer> i : list) {
		
			
			getGameWorld().spawn(casesToDisplay, new Point2D(i.getKey() * 60, i.getValue() * 60));
			
//			List<SimpleEntry<Integer, Integer>> pathf = Pathfinding.distMethod(tab[0], tab[1], i.getKey(), i.getValue());
//			
//
//			if (pathf != null && pathf.size() - 1 <= player.getHeroClass().getMovePoint()) {
//			
//				getGameWorld().spawn(casesToDisplay, new Point2D(i.getKey() * 60, i.getValue() * 60));
//			}
		}
	}
}
