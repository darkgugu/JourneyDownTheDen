package capacites;

import personnages.Unites;

public class PhysicalDamageBuff extends Capacites{

	public PhysicalDamageBuff() {
		super(DamageElement.LIGHT, 10, "magical", 20, 0, 3, "Augmentation Dégats Physiques"
				,"Renforce la puissance des attaques physiques");
		
	}
	
	public int cast(Unites caster, Unites cible) {

		cible.setPhysicalBaseDamage(cible.getPhysicalBaseDamage() + 5);
		super.cast(caster);

		return 0;
	}

}
