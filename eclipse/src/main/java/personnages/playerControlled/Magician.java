package personnages.playerControlled;

import capacites.Fireball;
import capacites.MagicalDamageBuff;

public class Magician extends Personnages{


	public Magician() {
		super(100, "Magician", 1, 5, 20, 5, 1);
		skills[0] = new MagicalDamageBuff();
		skills[1] = new Fireball();

	}

	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
