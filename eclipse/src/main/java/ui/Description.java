package ui;

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
	private Text descri;
	private Text consoMana;
	private Text range;
	private int i;

	public Description(GameScene gamescene) {
		descriSpell1 = new Text();
		descriSpell1.setFont(Font.font("Verdana", 25));
		descri = new Text();
		descri.setFont(Font.font("Verdana", 20));
		consoMana = new Text();
		consoMana.setFont(Font.font("Verdana", 20));
		range = new Text();
		range.setFont(Font.font("Verdana", 20));
		gamescene.addUINodes(descriSpell1, descri, consoMana, range);
	}

	public void clean(GameScene gamescene) {
		descriSpell1.setTranslateX(-10);
		descriSpell1.setTranslateY(-10);
		descri.setTranslateX(-10);
		descri.setTranslateY(-10);
		consoMana.setTranslateX(-10);
		consoMana.setTranslateY(-10);
		range.setTranslateX(-10);
		range.setTranslateY(-10);
	}

	/*
	 * Affichage des 10 descriptions de spells en fonction du selectedUnit.
	 */
	public void updateDescriSpell(GameScene gamescene, Player selectedUnit) {
		descriSpell1.setTranslateX(730);
		descriSpell1.setTranslateY(985);
		descri.setTranslateX(730);
		descri.setTranslateY(1020);
		consoMana.setTranslateX(1050);
		consoMana.setTranslateY(985);
		range.setTranslateX(1200);
		range.setTranslateY(985);
		if (selectedUnit == null) {
			return;
		} else {
			if (selectedUnit.getHeroClass().getSkills()[i] != null) {
				descriSpell1.setText(selectedUnit.getHeroClass().getSkills()[i].getName());
				descri.setText(selectedUnit.getHeroClass().getSkills()[i].getDescri());
				consoMana.setText("PA: " + selectedUnit.getHeroClass().getSkills()[i].getCost());
				range.setText("Portée: " + selectedUnit.getHeroClass().getSkills()[i].getRange());
			}
		}
	}

	public void mousePos(Player selectedUnit) {
		getGameScene().getContentRoot().setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				int caseX = x / 60;
				int caseY = y / 60;
				int[] tabClick = Click.cases(x, y);
				int xy = tabClick[0] * 100 + tabClick[1];

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
