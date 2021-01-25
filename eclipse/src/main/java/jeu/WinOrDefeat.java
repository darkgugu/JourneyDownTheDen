package jeu;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.entity.view.EntityView;

import javafx.scene.Node;
import personnages.IAControlled.Ennemis;
import personnages.IAControlled.Gobelin;

public class WinOrDefeat extends BasicGameApp {
	private Node node;
	private ViewComponent viewComponent;
	public WinOrDefeat(GameWorld gameWorld, Player playerRed, Player playerBlue, Player greenPlayer) {
//		 node = FXGL.getAssetLoader().loadTexture("blank.png");
		 viewComponent = new ViewComponent();
		 viewComponent.setTexture("blank.png");

//		 getGameScene().addGameView(viewComponent);
	}

	public void gameState(GameWorld gameworld, Player redPlayer, Player bluePlayer, Player greenPlayer, Ennemis gobelin) {
		//Cas de défaite 
		if (redPlayer.getHeroClass().getPv() == 0 && bluePlayer.getHeroClass().getPv() == 0
				&& greenPlayer.getHeroClass().getPv() == 0) {
//			FXGL.exit();
//			 node.setTranslateX(0);
//			 node.setTranslateY(0);
//			getGameScene().addGameView(view);
		}

		//Cas de victoire
		if(gobelin.getPv() == 0) {
			
		}
	}
}
