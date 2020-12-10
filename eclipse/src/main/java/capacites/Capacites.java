package capacites;

import personnages.Unites;

public class Capacites implements ICast{

	protected DamageElement type;
	protected int range;
	protected String damageType;
	protected int heal;
	protected int damage;
	protected int cost;
//	protected int dotDamage;
//	protected int dotDuration;
	public Capacites(DamageElement type, int range, String damageType, int heal, int damage, int cost) {
		super();
		this.type = type;
		this.range = range;
		this.damageType = damageType;
		this.heal = heal;
		this.damage = damage;
		this.cost = cost;
	}
	
	public int cast(Unites caster, int cost) {
		caster.setActionPoint(caster.getActionPoint() - cost);
		System.out.println(caster.getName() + " lance " + getClass().getSimpleName());
		return 0;
	}
}
