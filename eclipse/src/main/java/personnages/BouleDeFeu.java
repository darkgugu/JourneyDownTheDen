package personnages;

public class BouleDeFeu extends Capacites{
		
	private int finalDamages;
	
	
	public BouleDeFeu(Unites unite) {
		super(DamageElement.FIRE, 10, "magical", 0, 10, unite);

		finalDamages = damage * unite.getMagicalBaseDamage();
	}
	
	public int getFinalDamages() {
		return finalDamages;
	}
}