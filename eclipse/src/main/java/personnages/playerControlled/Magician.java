package personnages.playerControlled;

import capacites.Capacites;

public class Magician extends Personnages{

	private Capacites skills[] = new Capacites[10];
	
	public Capacites[] getSkills() {
		return skills;
	}


	public void setSkills(Capacites a, Capacites b) {
		this.skills[0] = a;
		this.skills[1] = b;
	}

	public Magician() {
		super(100, "Magician", 10, 50, 20, 5);

	}

	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}