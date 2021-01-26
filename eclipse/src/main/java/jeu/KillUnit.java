package jeu;

import java.util.List;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import ui.GameLog;

public class KillUnit extends BasicGameApp {
	public KillUnit(GameWorld gameWorld, Player playerRed, Player playerBlue, Player playerGreen) {
	}
	
	public void checkKill(Player playerRed, Player playerBlue, Player playerGreen, IAControlledEntity gobelin) {
		

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
		if (gobelin.getType().getPv() <= 0 && gobelin.getType().isDead() == false) {
			List<Entity> target = FXGL.getApp().getGameWorld()
					.getEntitiesAt(new Point2D(gobelin.getPosition().getX(), gobelin.getPosition().getY()));
			for (Entity entity : target) {
				entity.removeFromWorld();
			}
			gobelin.getType().setDead(true);
			GameLog.setGameLog("HERE");
			getInput().mockKeyPress(KeyCode.ESCAPE);
			getInput().mockKeyRelease(KeyCode.ESCAPE);
			
		}

	}

}
