package personnages.IAControlled;

import capacites.GobelinSwordAttack;

public class Orc extends Ennemis {

	public Orc() {
		super(2500, "Orc", 50, 0, 10, 2, 4);
		skills[0] = new GobelinSwordAttack();
	}

}
