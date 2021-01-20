package personnages.IAControlled;

import java.util.List;

import java.util.AbstractMap.SimpleEntry;

import jeu.Click;
import jeu.Deplacement;
import jeu.IAControlledEntity;
import jeu.Player;

public class IABehaviour {
	
	public static void isRangeAgressiveSpell(IAControlledEntity unit, Player[] players) {
		
		int range = unit.getType().getSkills()[0].getRange();
		int tab[] = Click.cases(((int) unit.getPosition().getX()), ((int) unit.getPosition().getY()));
		int posx = tab[0];
		int posy = tab[1];
		
		Deplacement calcRange = new Deplacement();
		calcRange.calculateCross(range, posx, posy);
		calcRange.calculateDiag(range, posx, posy);
		List<SimpleEntry<Integer, Integer>> list = calcRange.list;
	
		for(int i = 0;i != 3;i++) {
			
			int tabPlayer[] = Click.cases(((int) players[i].getPosition().getX()), ((int) players[i].getPosition().getY()));
			
			if(list.contains(new SimpleEntry<Integer, Integer>(tabPlayer[0], tabPlayer[1]))) {
				
				unit.getType().getSkills()[0].cast(unit.getType(), players[i].getHeroClass());
			}
			
		}
	}
}
