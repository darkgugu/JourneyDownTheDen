package capacites;

import personnages.Unites;
import ui.GameLog;

public class GobelinSwordAttack extends Capacites{
	
	private int finalDamages;

	public GobelinSwordAttack() {
		super(DamageElement.EARTH, 1, "physical", 0, 3, 5, "Tourbillon"
				,"");
	}

	@Override
	public int cast(Unites caster, Unites cible) {
		super.cast(caster);
		finalDamages = damage * caster.getPhysicalBaseDamage();
		GameLog.setGameLog("Dégats : " + finalDamages);
		System.out.println("Dégats : " + finalDamages);
		if(cible.setPv(finalDamages)) {

			super.death(caster, cible);
		}
		return finalDamages;
	}

}
