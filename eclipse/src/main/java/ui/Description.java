package ui;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.scene.GameScene;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jeu.BasicGameApp;
import jeu.Click;
import jeu.Player;

public class Description extends BasicGameApp {
	private Text descriSpell1;
	private int i;
	public Description(GameScene gamescene) {
		descriSpell1 = new Text();
		descriSpell1.setFont(Font.font("Verdana", 25));
		gamescene.addUINodes(descriSpell1);
		Entity description = getGameWorld().spawn("description", new Point2D(720, 953));
	}

	/*
	 * Pas reussi à cacher le texte correctement donc on le déplace en dehors de
	 * l'écran
	 */
	public void clean(GameScene gamescene) {
		descriSpell1.setTranslateX(-10);
		descriSpell1.setTranslateY(-10);

	}

	/*
	 * Affichage des 10 descriptions de spells en fonction du caster.
	 */
	public void updateDescriSpell(GameScene gamescene, Player selectedUnit) {
		descriSpell1.setTranslateX(730);
		descriSpell1.setTranslateY(980);
		if(selectedUnit.getHeroClass().getSkills()[i] != null) {
			descriSpell1.setText(selectedUnit.getHeroClass().getSkills()[i].getName());
		}
	}

	public void mousePos(Player selectedUnit) {
		getGameScene().getContentRoot().setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				int[] tabClick = Click.cases(x, y);
				int xy = tabClick[0] * 100 + tabClick[1];
				//System.out.println(xy);

				/*
				 * Switch statement en fonction de la case ciblée par la souris
				 */
				switch (xy) {
				case 1215:
					i = 0;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1315:
					i = 1;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1415:
					i = 2;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1515:
					i = 3;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1615:
					i = 4;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1715:
					i = 5;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1815:
					i = 6;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 1915:
					i = 7;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 2015:
					i = 8;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				case 2115:
					i = 9;
					updateDescriSpell(getGameScene(), selectedUnit);
					break;
				default:
					clean(getGameScene());
					break;
				}

			}

		});

	}
}
