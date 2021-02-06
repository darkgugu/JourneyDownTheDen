package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.util.Duration;
import personnages.IAControlled.Ennemis;

public class IAControlledEntity extends Component {
	private PositionComponent position;
	private String name;
	private Ennemis type;
	//private Capacites activeSkill;
	
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;
    private String dir = null;
    private List<SimpleEntry<Integer, Integer>> path = null;
	
	
	public Ennemis getType() {
		return type;
	}

	public void setType(Ennemis type) {
		this.type = type;
	}

	public IAControlledEntity(Ennemis type) {
		super();
		setType(type);
        animIdle = new AnimationChannel(type.getName().toLowerCase() + "_walk.png", 10, 84, 60, Duration.seconds(1), 1, 1);
        animWalk = new AnimationChannel(type.getName().toLowerCase() + "_walk.png", 10, 84, 60, Duration.seconds(1), 0, 9);
        texture = new AnimatedTexture(animIdle);
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

		new Click();
		int tab[] = Click.cases(((int) direction.getX()), ((int) direction.getY()));
		Deplacement move = new Deplacement();
		move.calculateCross(type.getMovePoint(), casePlayerX, casePlayerY);
		move.calculateDiag(type.getMovePoint(), casePlayerX, casePlayerY);
		List<SimpleEntry<Integer, Integer>> list = move.list;
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
		
		if (list.contains(vars)) {
			
			position.translateX(tab[2] - position.getX());
			position.translateY(tab[3] - position.getY());
			posX = (int) position.getX();
			posY = (int) position.getY();
		}
	}
	
	@Override
    public void onAdded() {
        entity.setView(texture);
    }

}
