package jeu;

import com.almasb.fxgl.scene.GameScene;

import javafx.geometry.Point2D;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WinOrDefeat {
	private Text text;
	private BasicGameApp basicGameApp;
	private int x;
	private int y;

	public WinOrDefeat(GameScene gameScene) {
		text = new Text();
		text.setTranslateX(840);
		text.setTranslateY(420);
		text.setFont(Font.font("Verdana", 50));
		gameScene.addUINodes(text);
	}

	public void setBasicGameApp(BasicGameApp basicGameApp) {
		this.basicGameApp = basicGameApp;
	}

	public void gameState(Player redPlayer, Player bluePlayer, Player greenPlayer, IAControlledEntity gobelin, IAControlledEntity orc) {
		// Cas de defaite
		if (redPlayer.getHeroClass().isDead() == true && bluePlayer.getHeroClass().isDead() == true
				&& greenPlayer.getHeroClass().isDead() == true) {
			basicGameApp.getGameWorld().spawn("rectangle", new Point2D(840, 380));
			text.setText("DEFAITE");
		}
		System.out.println(x + "    " + y);
		// Si le gobelin meurt on passe au niveau suivant
		if (gobelin.getType().isDead() == true && basicGameApp.getCurrentMap().equals("map1")) {
//			jddFactory.level2();
			basicGameApp.init2();

		}

//		 Cas de victoire (le gobelin sera remplacé par l'orc)
		
//		if (orc.getType().isDead() == true) {
//			basicGameApp.getGameWorld().spawn("rectangle", new Point2D(840, 380));
//			text.setText("VICTOIRE");
//		}

	}
}