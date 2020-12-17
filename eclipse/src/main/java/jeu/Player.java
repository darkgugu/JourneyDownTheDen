package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;

import javafx.geometry.Point2D;
import personnages.playerControlled.Personnages;

public class Player extends Component {
	private PositionComponent position;
	private String name;
private Personnages HeroClass;
	
	
	public Player(Personnages heroClass) {
		super();
		setHeroClass(heroClass);
	}
	public Personnages getHeroClass() {
		return HeroClass;
	}
	public void setHeroClass(Personnages heroClass) {
		HeroClass = heroClass;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public PositionComponent getPosition() {
		return position;
	}

	public void setPosition(PositionComponent position) {
		this.position = position;
	}

	public void move(Point2D direction) {

		int posX = (int) position.getX();
		int posY = (int) position.getY();

		int casePlayerX = (int) (posX / 60);
		int casePlayerY = (int) (posY / 60);
		System.out.println("Coordonées player pixels ("  + position.getX() + " , " + position.getY() + ")");
		System.out.println("Coordonées player case (" + casePlayerX + " , " + casePlayerY + ")");

		int tab[] = new Click().cases(((int) direction.getX()), ((int) direction.getY()));
		Deplacement move = new Deplacement();
		move.calculateCross(2, casePlayerX, casePlayerY);
		move.calculateDiag(2, casePlayerX, casePlayerY);
		List<SimpleEntry<Integer, Integer>> list = move.list;
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
		System.out.println("Coin supérieur gauche de la case (pixels) (" + tab[2] + " , " + tab[3] + ")");
		if (list.contains(vars)) {

			position.translateX(tab[2] - position.getX());
			position.translateY(tab[3] - position.getY());
			posX = (int) position.getX();
			posY = (int) position.getY();
			System.out.println("Coordonées player X Y (" + posX + " , " + posY + ") \n\n");
		} else {
			System.out.println("pas de deplacement");
		}
	}
}
