package personnages.IAControlled;

import capacites.GobelinSwordAttack;

public class Gobelin extends Ennemis{

	public Gobelin() {
		super(1000, "Gobelin", 10, 0, 10, 3);
		skills[0] = new GobelinSwordAttack();

	}

}
