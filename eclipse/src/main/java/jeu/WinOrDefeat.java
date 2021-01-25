package jeu;

import com.almasb.fxgl.entity.GameWorld;

import javafx.geometry.Point2D;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import personnages.IAControlled.Ennemis;
import personnages.IAControlled.Gobelin;

public class WinOrDefeat extends BasicGameApp {
	private Text text;
	public WinOrDefeat(GameWorld gameWorld, Player playerRed, Player playerBlue, Player greenPlayer) {
		 text = new Text();
		 text.setTranslateX(540);
		 text.setTranslateY(960);
		 text.setFont(Font.font("Verdana", 50));
	}

	public void gameState(GameWorld gameworld, Player redPlayer, Player bluePlayer, Player greenPlayer, Gobelin gobelin) {
		//Cas de defaite 
		if (redPlayer.getHeroClass().getPv() == 0 && bluePlayer.getHeroClass().getPv() == 0
				&& greenPlayer.getHeroClass().getPv() == 0) {
			getGameWorld().spawn("rectangle", new Point2D(326, 945));
			text.setText("DEFAITE");
		}

		//Cas de victoire
		if(gobelin.getPv() == 0) {
			getGameWorld().spawn("rectangle", new Point2D(326, 945));
			text.setText("VICTOIRE");

			
		}
	}
}