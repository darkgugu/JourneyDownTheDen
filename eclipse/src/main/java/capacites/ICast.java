package capacites;

import personnages.Unites;

public interface ICast {

	public int cast(Unites unite, int cost);
	
	public void death(Unites killer, Unites cible);
}
