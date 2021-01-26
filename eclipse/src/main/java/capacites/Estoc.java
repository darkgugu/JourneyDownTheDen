package capacites;

import personnages.Unites;
import ui.GameLog;

public class Estoc extends Capacites{
	
	private int finalDamages;

	public Estoc() {
		super(DamageElement.EARTH, 1, "physical", 0, 10, 2, "Coup d'Estoc"
				, "Frappe de toute sa force avec sa lame");
	}

	public int cast(Unites caster, Unites cible) {
		
		super.cast(caster);
		finalDamages = damage * caster.getPhysicalBaseDamage();
		GameLog.setGameLog("Dégats : " + finalDamages);
		if(cible.setPv(finalDamages)) {

			super.death(caster, cible);
		}
		return finalDamages;
	}
	
	public int getFinalDamages() {
		return finalDamages;
	}

}
