package jeu;

import java.util.List;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.physics.box2d.dynamics.contacts.Position;
import com.almasb.fxgl.scene.FXGLScene;

import javafx.geometry.Point2D;
import personnages.IAControlled.Gobelin;
import personnages.playerControlled.Healer;

public class KillUnit extends BasicGameApp {
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
