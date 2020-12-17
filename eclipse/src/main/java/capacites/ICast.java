package capacites;

import personnages.Unites;

public interface ICast {

	public int cast(Unites unite);
	
	public void death(Unites killer, Unites cible);
}
