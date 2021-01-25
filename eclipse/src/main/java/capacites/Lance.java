package capacites;

import personnages.Unites;
import ui.GameLog;

public class Lance extends Capacites{
		
	private int finalDamages;

	public Lance() {
		super(DamageElement.EARTH, 3, "physical", 0, 15, 3, "Coup de Lance"
				,"Coup de lance d'une petite portée");
		
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
