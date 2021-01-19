package capacites;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import jeu.Deplacement;
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
				" à tué " +
				cible.getClass().getSimpleName() +
				" avec " +
				getName());
		
//		System.out.println(
//					killer.getClass().getSimpleName() + 
//					" à tué " +
//					cible.getClass().getSimpleName() +
//					" avec " +
//					getName());
	}
	
	public String castOK(Unites caster, Unites target, int[] pos, int[] click) {
	
		String validateCast = null;
		Deplacement calcRange = new Deplacement();
		calcRange.calculateCross(range, pos[0], pos[1]);
		calcRange.calculateDiag(range, pos[0], pos[1]);
		List<SimpleEntry<Integer, Integer>> list = calcRange.list;
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(click[0], click[1]);
		
		if (cost < caster.getActionPoint()) {

			validateCast = "OK";
		}
		else {
			
			return "Pas assez de points d'actions !";
		}
		if(list.contains(vars)) {
			
			validateCast = "OK";
		}
		else {
			
			return "Le sort n'a pas une portée suffisante !";
		}
		
		return validateCast;
	}

	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
}
