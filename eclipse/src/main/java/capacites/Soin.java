package capacites;

import personnages.Unites;
import ui.GameLog;

public class Soin extends Capacites{

	private int finalHeal;
	
	public Soin() {
		super(DamageElement.LIGHT, 10, "magical", 20, 0, 3, "Soin"
				,"Un soin basique et peu couteux");
		
	}
	
	public int cast(Unites caster, Unites cible) {

		finalHeal = -1 * heal;
		cible.setPv(finalHeal);
		super.cast(caster);
		//System.out.println("Cible : " + cible.getName());
		GameLog.setGameLog("Soin : " + -finalHeal);
		return finalHeal;
	}

	public int getFinalHeal() {
		return finalHeal;
	}

}
