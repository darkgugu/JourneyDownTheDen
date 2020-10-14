package capacites;

import personnages.Unites;

public class Capacites implements ICast{

	protected DamageElement type;
	protected int range;
	protected String damageType;
	protected int heal;
	protected int damage;
//	protected int dotDamage;
//	protected int dotDuration;
	public Capacites(DamageElement type, int range, String damageType, int heal, int damage) {
		super();
		this.type = type;
		this.range = range;
		this.damageType = damageType;
		this.heal = heal;
		this.damage = damage;
	}
	
	public int cast(Unites unite) {
		System.out.println(unite.getName() + " lance " + getClass().getSimpleName());
		return 0;
	}
}
