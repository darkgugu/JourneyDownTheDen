package capacites;

import personnages.Unites;

public class Soin extends Capacites{

	private int finalHeal;
	
	public Soin() {
		super(DamageElement.LIGHT, 10, "magical", 20, 0);
		
	}
	
	public int cast(Unites unite, Unites cible) {

		finalHeal = -1 * heal;
		cible.setPv(finalHeal);
		super.cast(unite);
		System.out.println("Cible : " + cible.getName());
		System.out.println("Soin : " + -finalHeal);
		return finalHeal;
	}

	public int getFinalHeal() {
		return finalHeal;
	}

}
