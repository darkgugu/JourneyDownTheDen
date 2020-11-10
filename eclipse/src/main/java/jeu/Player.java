package jeu;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;

import javafx.geometry.Point2D;

public class Player extends Component {
	private PositionComponent position;

	public PositionComponent getPosition() {
		return position;
	}

	public void setPosition(PositionComponent position) {
		this.position = position;
	}

	public void move(Point2D direction) {
		System.out.println("Move on");
		System.out.println(position.getX() + " " + position.getY());
		System.out.println("Move off");
	}
}
