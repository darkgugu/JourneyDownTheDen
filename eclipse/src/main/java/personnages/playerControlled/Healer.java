package personnages.playerControlled;

import capacites.BouleDeFeu;
import capacites.Soin;

public class Healer extends Personnages{
	
	//private Capacites skills[] = new Capacites[10];


	public Healer() {
		super(95, "Healer", 10, 20, 10, 10, 10);
		skills[0] = new Soin();
		skills[1] = new BouleDeFeu();		
	}

	
	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
