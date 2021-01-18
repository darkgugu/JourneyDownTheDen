package capacites;

import personnages.Unites;
import ui.GameLog;

public class Fireball extends Capacites{
		
	private int finalDamages;
	
	
	public Fireball() {
		super(DamageElement.FIRE, 10, "magical", 0, 10, 4, "Boule de Feu");

	}
	
	public int cast(Unites caster, Unites cible) {
		
		super.cast(caster);
		finalDamages = damage * caster.getMagicalBaseDamage();
		GameLog.setGameLog("Dégats : " + finalDamages);
		System.out.println("Dégats : " + finalDamages);
		if(cible.setPv(finalDamages)) {

			super.death(caster, cible);
		}
		return finalDamages;
	}
	
	public int getFinalDamages() {
		return finalDamages;
	}
}