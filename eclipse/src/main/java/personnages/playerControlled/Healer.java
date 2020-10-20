package personnages.playerControlled;

import capacites.Capacites;

public class Healer extends Personnages{

	private Capacites skills[] = new Capacites[10];
	
	public Capacites[] getSkills() {
		return skills;
	}


	public void setSkills(Capacites a, Capacites b) {
		this.skills[0] = a;
		this.skills[1] = b;
	}

	public Healer() {
		super(95, "Healer", 10, 20, 10, 5);

	}

	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
