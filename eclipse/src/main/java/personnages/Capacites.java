package personnages;

public class Capacites {

	protected DamageElement type;
	protected int range;
	protected String damageType;
	protected int heal;
	protected int damage;
//	protected int dotDamage;
//	protected int dotDuration;
	public Capacites(DamageElement type, int range, String damageType, int heal, int damage, Unites unite) {
		super();
		this.type = type;
		this.range = range;
		this.damageType = damageType;
		this.heal = heal;
		this.damage = damage;
	}
}
