package personnages.playerControlled;

import capacites.Fireball;
import capacites.Soin;

public class Magician extends Personnages{


	public Magician() {
		super(100, "Magician", 10, 50, 20, 5, 2);
		skills[0] = new Soin();
		skills[1] = new Fireball();

	}

	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
