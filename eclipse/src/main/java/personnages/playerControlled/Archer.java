package personnages.playerControlled;

public class Archer extends Personnages{

	public Archer() {
		super(100, "Archer", 10, 50, 20, 5, 10);

	}

	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
