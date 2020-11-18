/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package jeu;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jeu.Deplacement;

public class BasicGameApp extends GameApplication {
	private Player playerComponent;
	private Player selectedUnit;
	private Entity background;
	private Entity lineOfUI;
	private Entity grid;
	private Entity info_hero1;

	private Entity casesAround;

	boolean gridState = false;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1920); // 791
		settings.setHeight(1080); // 575
		settings.setFullScreenAllowed(true);
		settings.setManualResizeEnabled(true);
		settings.setTitle("Journey down the den");
		settings.setVersion("0.2");
		settings.setAppIcon("JDTD_icon.png");
		settings.setProfilingEnabled(true);

//To implement later
//		settings.setIntroEnabled(true);
	}

	@Override
	protected void initGame() {
//		getGameWorld().setLevelFromMap("mapTest.png");
		getGameWorld().addEntityFactory(new EntityGenerate());
		Entity redHero = getGameWorld().spawn("redHero", new Point2D(0, 0));
//		Entity blueHero = getGameWorld().spawn("blueHero", new Point2D(60, 0));
//		Entity greenHero = getGameWorld().spawn("greenHero", new Point2D(120, 0));

		playerComponent = redHero.getComponent(Player.class);

//		background = Entities.builder().at(0, 0).with(new IrremovableComponent()).viewFromTexture("mapTest.png")
//				.buildAndAttach(getGameWorld());
		lineOfUI = Entities.builder().at(0, 901).viewFromNode(new Rectangle(1920, 200, Color.GREY))
				.buildAndAttach(getGameWorld());
		info_hero1 = Entities.builder().at(5, 905).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());
//To implement
//		info_hero2 = Entities.builder().at(319, 910).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());
//		info_hero3 = Entities.builder().at(633, 910).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());

// 		Repeatable theme
//		getAudioPlayer().loopBGM("town_theme.mp3");
	}

	@Override
	protected void initInput() {
		Input input = getInput();

		input.addAction(new UserAction("Show grid") {
			@Override
			protected void onAction() {
				if (gridState == false) {
					grid = Entities.builder().at(0, 0).viewFromTexture("grille.png").buildAndAttach(getGameWorld());
					gridState = true;
				} else {
					grid.removeFromWorld();
					gridState = false;
				}
			}

		}, KeyCode.F);
	}

	@Override
	protected void initUI() {
		Text textPixels = new Text();
		Point2D hotspot = Point2D.ZERO;

		getGameScene().addUINode(textPixels); // add to the scene graph
		getGameScene().setCursor("cursor.png", hotspot);
		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				System.out.println("Coordonées cursor pixel (" + x + " , " + y + ")");

				//To do si clic sur une unité mettre à jour le selectedUnit
				selectedUnit = playerComponent;
				//To do si selectedUnit not null & clic case déplacement valide alors se déplacer
				selectedUnit.move(new Point2D(x, y));
				
//				playerComponent.move(new Point2D(x, y));
				
//				double posX = playerEntity.getPosition().getX();
//				double posY = playerEntity.getPosition().getY();
//				System.out.println("Coordonées player X Y (" + posX + " , " + posY + ")");
//
//				int casePlayerX = (int) (posX / 60);
//				int casePlayerY = (int) (posY / 60);
//				System.out.println("Coordonées player case (" + casePlayerX + " , " + casePlayerY + ")");
//
//				int tab[] = new Click().cases(x, y);
//				Deplacement move = new Deplacement();
//				move.calculateCross(2, casePlayerX, casePlayerY);
//				move.calculateDiag(2, casePlayerX, casePlayerY);
//				List<SimpleEntry<Integer, Integer>> list = move.list;
//				SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
////				System.out.println("Coordonées du tabl (" + tab[2] + " , " + tab[3] + ")");
//
//				if (list.contains(vars)) {
//
//					playerEntity.translateX(tab[2] - playerEntity.getPosition().getX());
//					playerEntity.translateY(tab[3] - playerEntity.getPosition().getY());
//					posX = playerEntity.getPosition().getX();
//					posY = playerEntity.getPosition().getY();
//					System.out.println("Coordonées player X Y (" + posX + " , " + posY + ")");
//				} else {
//					System.out.println("pas de deplacement");
//				}
//				for (int i = 0; i < 4; i++) {
//					System.out.println(tab[i]);
//				}
			}
		});

		/*
		 * AFFICHAGE DES CASES ADJACENTES
		 */

//		getGameScene().getContentRoot().setOnMouseMoved(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//
//				int caseCursorX = ((int) event.getSceneX() / 60) - 1;
//				int caseCursorY = ((int) event.getSceneY() / 60) - 1;
//				int casePlayerX = (int) (playerEntity.getPosition().getX() / 60);
//				int casePlayerY = (int) (playerEntity.getPosition().getY() / 60);
////				System.out.println("Coordonées du tabl (" + tab[2] + " , " + tab[3] + ")");
////				System.out.println("Coordonées du joueur (" + casePlayerX + " , " + casePlayerY + ")");
//
//				int x = (int) event.getSceneX();
//				int y = (int) event.getSceneY();
//				int tab[] = new Click().cases(x, y);
//
//				if ((caseCursorX == casePlayerX) && (caseCursorY == casePlayerY)) {
////					System.out.println("printed !");
//					casesAround = Entities.builder()
//							.at(tab[2] - playerComponent.getPosition().getX(), tab[3] - playerComponent.getPosition().getY())
//							.viewFromTexture("rangeUnitOf2.png").buildAndAttach(getGameWorld());
//				} else {
////					casesAround.removeFromWorld();
//				}
//			}
//		});

//		Text fenetre = new Text("Ma fenetre");
//		fenetre.setTranslateX(15);
//		fenetre.setTranslateY(940);
//		
//		getGameScene().addUINode(fenetre);
	}

//	@Override
//	protected void initGameVars(Map<String, Object> vars) {
//		vars.put("pixelsMoved", 0);
//	}

	public static void main(String[] args) {
		launch(args);
	}

}
