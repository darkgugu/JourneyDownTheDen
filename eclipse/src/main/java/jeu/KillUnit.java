package jeu;

import java.util.List;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import javafx.geometry.Point2D;
import ui.GameLog;

public class KillUnit {
	public KillUnit(GameWorld gameWorld, Player playerRed, Player playerBlue, Player playerGreen) {
	}

	public void checkKill(GameWorld gameWorld, Player playerRed, Player playerBlue, Player playerGreen,
			Player playerSelected) {
		

		if (playerRed.getHeroClass().getPv() <= 0 && playerRed.getHeroClass().isDead() == false) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(playerRed.getPosition().getX(), playerRed.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
			}
			playerRed.getHeroClass().setDead(true);
			
		}
		if (playerBlue.getHeroClass().getPv() <= 0 && playerBlue.getHeroClass().isDead() == false) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(playerBlue.getPosition().getX(), playerBlue.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
			}
			playerBlue.getHeroClass().setDead(true);
			
		}
		if (playerGreen.getHeroClass().getPv() <= 0 && playerGreen.getHeroClass().isDead() == false) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(playerGreen.getPosition().getX(), playerGreen.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
			}
			playerGreen.getHeroClass().setDead(true);
			
		}

	}

}
