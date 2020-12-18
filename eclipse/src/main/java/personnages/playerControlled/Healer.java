package personnages.playerControlled;

import capacites.Fireball;
import capacites.Soin;

public class Healer extends Personnages{
	
	public Healer() {
		super(95, "Healer", 10, 20, 10, 10, 2);
		skills[0] = new Fireball();
		skills[1] = new Fireball();
	}

	
	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
