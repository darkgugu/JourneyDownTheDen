package personnages.playerControlled;

import capacites.Fireball;
import capacites.PhysicalDamageBuff;

public class Magician extends Personnages{


	public Magician() {
		super(100, "Magician", 1, 5, 20, 5, 1);
		skills[0] = new PhysicalDamageBuff();
		skills[1] = new Fireball();
	}
}
