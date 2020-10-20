package jeu;

import personnages.Unites;

public class Pregame {
	
	private Unites[] team = new Unites[3];

	public Unites[] getTeam() {
		return team;
	}

	public void setTeam(Unites member) {
		
		for (int i = 0; i < team.length; i++) {
			if(team[i] == null) {
				
				team[i] = member;
				break;
			}
		}
	}
}
