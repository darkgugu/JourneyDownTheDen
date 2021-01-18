package capacites;

import personnages.Unites;
import ui.GameLog;

public abstract class Capacites implements ICast{

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

	public int cast(Unites caster) {
		caster.setActionPoint(caster.getActionPoint() - cost);
		System.out.println(caster.getName() + " lance " + getName());
		return 0;
	}
	
	public abstract int cast(Unites caster, Unites cible);

	@Override
	public void death(Unites killer, Unites cible) {
		
		GameLog.setGameLog(
				killer.getClass().getSimpleName() + 
				" � tu� " +
				cible.getClass().getSimpleName() +
				" avec " +
				getName());
		
//		System.out.println(
//					killer.getClass().getSimpleName() + 
//					" � tu� " +
//					cible.getClass().getSimpleName() +
//					" avec " +
//					getName());
	}

	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
}
