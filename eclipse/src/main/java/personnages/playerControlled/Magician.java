package personnages.playerControlled;

import capacites.Fireball;
import capacites.MagicalDamageBuff;

public class Magician extends Personnages{


	public Magician() {
		super(100, "Magician", 1, 5, 20, 5, 8);
		skills[0] = new MagicalDamageBuff();
		skills[1] = new Fireball();
	}
}
