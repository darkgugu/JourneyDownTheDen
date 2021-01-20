package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;

import javafx.geometry.Point2D;
import personnages.IAControlled.Ennemis;

public class IAControlledEntity extends Component {
	private PositionComponent position;
	private String name;
	private Ennemis type;
	//private Capacites activeSkill;
	
	
	public Ennemis getType() {
		return type;
	}

	public void setType(Ennemis type) {
		this.type = type;
	}

	public IAControlledEntity(Ennemis type) {
		super();
		setType(type);
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
		System.out.println("Coordonnées player pixels ("  + position.getX() + " , " + position.getY() + ")");
		System.out.println("Coordonnées player case (" + casePlayerX + " , " + casePlayerY + ")");

		new Click();
		int tab[] = Click.cases(((int) direction.getX()), ((int) direction.getY()));
		Deplacement move = new Deplacement();
		move.calculateCross(type.getMovePoint(), casePlayerX, casePlayerY);
		move.calculateDiag(type.getMovePoint(), casePlayerX, casePlayerY);
		List<SimpleEntry<Integer, Integer>> list = move.list;
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
		
		System.out.println("Coordonnées du tabl (" + tab[2] + " , " + tab[3] + ")");
		System.out.println("Coin supérieur gauche de la case (pixels) (" + tab[2] + " , " + tab[3] + ")");

		if (list.contains(vars) && type.isDidMove() == false) {
			
			position.translateX(tab[2] - position.getX());
			position.translateY(tab[3] - position.getY());
			posX = (int) position.getX();
			posY = (int) position.getY();
			type.setDidMove(true);

			System.out.println("Coordonnées player X Y (" + posX + " , " + posY + ") \n\n");

		} else {
			System.out.println("pas de deplacement");
		}
	}
//	public Capacites getActiveSkill() {
//		return activeSkill;
//	}
//	public void setActiveSkill(Capacites activeSkill) {
//		this.activeSkill = activeSkill;
//	}
}
