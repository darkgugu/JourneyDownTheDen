package personnages;

import capacites.Capacites;

public class Unites {

	private int pv;
	protected String name;
	protected int physicalBaseDamage;
	private int magicalBaseDamage;
	protected int range;
	protected int actionPoint, actionPointMax;
	protected int movePoint, movePointMax;
	private int pvMax;
	protected Capacites skills[];

	protected boolean didMove = false;

	public Unites(int pv, String name, int physicalBaseDamage, int magicalBaseDamage, int range, int actionPoint, int movePoint) {
		super();
		this.pv = pv;
		this.name = name;
		this.physicalBaseDamage = physicalBaseDamage;
		this.setMagicalBaseDamage(magicalBaseDamage);
		this.range = range;
		this.actionPoint = actionPoint;
		this.actionPointMax = actionPoint;
		this.movePoint = movePoint;
		this.movePointMax = movePoint;		
		this.setPvMax(pv);
		this.skills = new Capacites[10];
	}

	public void setActionPointToMax() {
		this.actionPoint = actionPointMax;
	}

	public int getActionPoint() {
		return actionPoint;
	}

	public int getMovePoint() {
		return movePoint;
	}

	public void setActionPoint(int actionPoint) {
		this.actionPoint = actionPoint;
	}

	public void setMovePoint(int movePoint) {
		this.movePoint = movePoint;
	}

	public void setMovePointToMax() {
		this.movePoint = movePointMax;
	}


	public int getMagicalBaseDamage() {
		return magicalBaseDamage;
	}

	public void setMagicalBaseDamage(int magicalBaseDamage) {
		this.magicalBaseDamage = magicalBaseDamage;
	}

	public String getName() {
		return name;
	}

	public int getPv() {
		return pv;
	}

	public boolean setPv(int pv) {
		
		this.pv -= pv;
		if (this.pv > this.pvMax) {
			this.pv = this.pvMax;
		}
		if(this.pv <= 0) {
			
			return true;
		}
		else {
		
			return false;
		}
		
	}

	public int getPvMax() {
		return pvMax;
	}

	public void setPvMax(int pvMax) {
		this.pvMax = pvMax;
	}
	
	public Capacites[] getSkills() {
		return skills;
	}


	public void setSkills(Capacites skill, int i) {
		this.skills[i] = skill;
	}

	public boolean isDidMove() {
		return didMove;
	}

	public void setDidMove(boolean didMove) {
		this.didMove = didMove;
	}
	public int getPhysicalBaseDamage() {
		return physicalBaseDamage;
	}

	public void setPhysicalBaseDamage(int physicalBaseDamage) {
		this.physicalBaseDamage = physicalBaseDamage;
	}

	
	
	
}
