package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;

import capacites.Capacites;
import javafx.geometry.Point2D;
import personnages.playerControlled.Personnages;

public class Player extends Component {
	private PositionComponent position;
	private String name;
	private Personnages HeroClass;
	private Capacites activeSkill;
	
	
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

	public void move(Point2D direction, GameWorld gameworld) {

		int posX = (int) position.getX();
		int posY = (int) position.getY();

		int casePlayerX = (int) (posX / 60);
		int casePlayerY = (int) (posY / 60);

		new Click();
		int tab[] = Click.cases(((int) direction.getX()), ((int) direction.getY()));
		Deplacement move = new Deplacement();
		move.calculateCross(HeroClass.getMovePoint(), casePlayerX, casePlayerY);
		move.calculateDiag(HeroClass.getMovePoint(), casePlayerX, casePlayerY);
		List<SimpleEntry<Integer, Integer>> list = move.list;
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
		List<Entity> entities = gameworld.getEntitiesAt(new Point2D(tab[2], tab[3]));
		if (list.contains(vars) && HeroClass.isDidMove() == false && entities.isEmpty()) {
			
			position.translateX(tab[2] - position.getX());
			position.translateY(tab[3] - position.getY());
			posX = (int) position.getX();
			posY = (int) position.getY();
			HeroClass.setDidMove(true);
		}
	}
	
	
	public Capacites getActiveSkill() {
		return activeSkill;
	}
	public void setActiveSkill(Capacites activeSkill) {
		this.activeSkill = activeSkill;
	}
}
