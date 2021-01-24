package personnages.playerControlled;

import capacites.Estoc;

public class Warrior extends Personnages{


	public Warrior() {
		super(100, "Warrior", 1, 5, 20, 5, 2);
		skills[0] = new Estoc();
		skills[1] = new Estoc();
	}
}
