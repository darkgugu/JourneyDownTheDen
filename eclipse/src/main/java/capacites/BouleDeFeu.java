package capacites;

import personnages.Unites;

public class BouleDeFeu extends Capacites{
		
	private int finalDamages;
	
	
	public BouleDeFeu() {
		super(DamageElement.FIRE, 10, "magical", 0, 10, 4);

	}
	
	public int cast(Unites caster, Unites cible) {
		
		super.cast(caster, cost);
		finalDamages = damage * caster.getMagicalBaseDamage();
		System.out.println("Dégats : " + finalDamages);
		if(cible.setPv(finalDamages)) {
			System.out.println(caster.getClass().getSimpleName() + 
							   " à tué " +
							   cible.getClass().getSimpleName() +
							   " avec " +
							   getClass().getSimpleName()
			);
			
		}
		return finalDamages;
	}
	
	public int getFinalDamages() {
		return finalDamages;
	}
}