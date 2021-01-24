package personnages.playerControlled;

import capacites.Soin;
import capacites.Soin2;

public class Healer extends Personnages{
	
	public Healer() {
		super(95, "Healer", 1, 2, 10, 10, 8);
		skills[0] = new Soin();
		skills[1] = new Soin2();
	}
}
