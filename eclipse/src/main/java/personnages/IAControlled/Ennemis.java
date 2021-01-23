package personnages.IAControlled;

import personnages.Unites;

public class Ennemis extends Unites{
	
	private int aggroRange;

	public Ennemis(int pv, String name, int physicalBaseDamage, int magicalBaseDamage, int range, int actionPoint, int aggroRange) {
		super(pv, name, physicalBaseDamage, magicalBaseDamage, range, actionPoint, actionPoint);
		this.aggroRange = aggroRange;
		// TODO Auto-generated constructor stub
	}

	public int getAggroRange() {
		return aggroRange;
	}

}
