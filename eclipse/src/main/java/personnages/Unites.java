package personnages;

public class Unites {

	protected int pv;
	protected String name;
	protected int physicalBaseDamage;
	private int magicalBaseDamage;
	protected int range;
	protected int actionPoint;
	
	public Unites(int pv, String name, int physicalBaseDamage, int magicalBaseDamage, int range, int actionPoint) {
		super();
		this.pv = pv;
		this.name = name;
		this.physicalBaseDamage = physicalBaseDamage;
		this.setMagicalBaseDamage(magicalBaseDamage);
		this.range = range;
		this.actionPoint = actionPoint;
	}

	public int getMagicalBaseDamage() {
		return magicalBaseDamage;
	}

	public void setMagicalBaseDamage(int magicalBaseDamage) {
		this.magicalBaseDamage = magicalBaseDamage;
	}
	
	
	
}
