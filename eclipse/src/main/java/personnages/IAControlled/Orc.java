package personnages.IAControlled;

import capacites.GobelinSwordAttack;

public class Orc extends Ennemis {

	public Orc() {
		super(250, "Orc", 25, 0, 10, 2, 4);
		skills[0] = new GobelinSwordAttack();
	}

}
