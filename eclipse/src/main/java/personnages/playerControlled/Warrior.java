package personnages.playerControlled;

import capacites.Estoc;
import capacites.Lance;

public class Warrior extends Personnages{


	public Warrior() {
		super(100, "Warrior", 2, 1, 20, 6, 2);
		skills[0] = new Estoc();
		skills[1] = new Lance();
	}
}
