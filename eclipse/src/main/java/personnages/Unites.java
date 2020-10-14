package personnages;

public class Unites {

	private int pv;
	protected String name;
	protected int physicalBaseDamage;
	private int magicalBaseDamage;
	protected int range;
	protected int actionPoint;
	private int pvMax;
	
	public Unites(int pv, String name, int physicalBaseDamage, int magicalBaseDamage, int range, int actionPoint) {
		super();
		this.pv = pv;
		this.name = name;
		this.physicalBaseDamage = physicalBaseDamage;
		this.setMagicalBaseDamage(magicalBaseDamage);
		this.range = range;
		this.actionPoint = actionPoint;
		this.setPvMax(pv);
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

	public void setPv(int pv) {
		this.pv += pv;
		if (this.pv > this.pvMax) {
			this.pv = this.pvMax;
		}
	}

	public int getPvMax() {
		return pvMax;
	}

	public void setPvMax(int pvMax) {
		this.pvMax = pvMax;
	}
	
	
	
}
