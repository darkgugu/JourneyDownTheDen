package personnages.playerControlled;

public class Warrior extends Personnages{


	public Warrior() {
		super(100, "Warrior", 10, 50, 20, 5, 2);

	}

	@Override
	public String toString() {
		
		String spellBar = "Sort 1 : " + skills[0].getClass().getSimpleName() + "\nSort 2  : " + skills[1].getClass().getSimpleName();
		return spellBar;
	}

}
