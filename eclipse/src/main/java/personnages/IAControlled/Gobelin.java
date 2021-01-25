package personnages.IAControlled;

import capacites.GobelinSwordAttack;

public class Gobelin extends Ennemis{

	public Gobelin() {
		super(200, "Gobelin", 10, 0, 10, 2, 4);
		skills[0] = new GobelinSwordAttack();

	}

}
