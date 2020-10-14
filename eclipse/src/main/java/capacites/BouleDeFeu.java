package capacites;

import personnages.Unites;

public class BouleDeFeu extends Capacites{
		
	private int finalDamages;
	
	
	public BouleDeFeu() {
		super(DamageElement.FIRE, 10, "magical", 0, 10);

	}
	
	@Override
	public int cast(Unites unite) {
		
		super.cast(unite);
		finalDamages = damage * unite.getMagicalBaseDamage();
		System.out.println("Dégats : " + finalDamages);
		return finalDamages;
	}
	
	public int getFinalDamages() {
		return finalDamages;
	}
}