package jeu;

import java.util.List;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import javafx.geometry.Point2D;

public class KillUnit {
	public KillUnit(GameWorld gameWorld, Player playerRed, Player playerBlue, Player playerGreen) {
	}

	public void checkKill(GameWorld gameWorld, Player playerRed, Player playerBlue, Player playerGreen,
			Player playerSelected) {

		if (playerRed.getHeroClass().getPv() <= 0) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(playerRed.getPosition().getX(), playerRed.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
			}
		}
		if (playerBlue.getHeroClass().getPv() <= 0) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(playerBlue.getPosition().getX(), playerBlue.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
				entity.removeComponent(null);
			}
		}
		if (playerGreen.getHeroClass().getPv() <= 0) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(playerGreen.getPosition().getX(), playerGreen.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
			}
			
		}

	}

}
