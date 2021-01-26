package personnages.IAControlled;

import personnages.Unites;

public class Ennemis extends Unites{
	
	private int aggroRange;
	private boolean hasAggro;

	public Ennemis(int pv, String name, int physicalBaseDamage, int magicalBaseDamage, int range, int actionPoint, int aggroRange) {
		super(pv, name, physicalBaseDamage, magicalBaseDamage, range, actionPoint, actionPoint);
		this.aggroRange = aggroRange;
		this.hasAggro = false;
	}

	public int getAggroRange() {
		return aggroRange;
	}
	
	public boolean getHasAggro() {
		return hasAggro;
	}
	
	public void setHasAggro(boolean hasAggro) {
		this.hasAggro = hasAggro;
	}

}
