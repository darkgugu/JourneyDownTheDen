package capacites;

import personnages.Unites;

public class MagicalDamageBuff extends Capacites{

	public MagicalDamageBuff() {
		super(DamageElement.LIGHT, 10, "magical", 20, 0, 3, "Buff Dégats Magique"
				,"Renforce la puissante des sorts");
		
	}
	
	public int cast(Unites caster, Unites cible) {

		cible.setMagicalBaseDamage(cible.getMagicalBaseDamage() + 5);
		super.cast(caster);

		return 0;
	}

}
