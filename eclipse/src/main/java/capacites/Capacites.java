package capacites;

import personnages.Unites;

public class Capacites implements ICast{

	protected DamageElement type;
	protected int range;
	protected String damageType;
	protected int heal;
	protected int damage;
	protected int cost;
	protected String name;
//	protected int dotDamage;
//	protected int dotDuration;
	public Capacites(DamageElement type, int range, String damageType, int heal, int damage, int cost, String name) {
		super();
		this.type = type;
		this.range = range;
		this.damageType = damageType;
		this.heal = heal;
		this.damage = damage;
		this.cost = cost;
		this.name = name;
	}
	
	public int cast(Unites caster, int cost) {
		caster.setActionPoint(caster.getActionPoint() - cost);
		System.out.println(caster.getName() + " lance " + name);
		return 0;
	}

	@Override
	public void death(Unites killer, Unites cible) {
		
		System.out.println(
					killer.getClass().getSimpleName() + 
					" � tu� " +
					cible.getClass().getSimpleName() +
					" avec " +
					name);
	}
}
