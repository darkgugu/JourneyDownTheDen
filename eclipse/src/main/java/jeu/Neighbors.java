package jeu;

import java.util.AbstractMap.SimpleEntry;

public class Neighbors {
	
	private SimpleEntry<Integer, Integer> neighbor;
	private double dist;
	
	public SimpleEntry<Integer, Integer> getNeighbor() {
		return neighbor;
	}
	public void setNeighbor(SimpleEntry<Integer, Integer> neighbor) {
		this.neighbor = neighbor;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}

}
