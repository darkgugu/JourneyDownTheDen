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
	private Entity playerEntity;
	private Player playerComponent;
	@SuppressWarnings("unused")
	private Entity background;

	@SuppressWarnings("unused")
	private Entity lineOfUI;

	@SuppressWarnings("unused")
	private Entity grid;

	@SuppressWarnings("unused")
	private Entity info_hero1;
//To implement
//	private Entity info_hero2;
//	private Entity info_hero3;

	private Entity goblin;
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

//To implement 
//		settings.setIntroEnabled(true);
	}

	@Override
	protected void initGame() {
		background = Entities.builder().at(0, 0).with(new IrremovableComponent()).viewFromTexture("mapTest.png")
				.buildAndAttach(getGameWorld());
//		player = Entities.builder().at(30, 30).viewFromTexture("down_hero1.png").buildAndAttach(getGameWorld());
		playerEntity = Entities.builder().at(0, 0).viewFromTexture("down.png").with(new Player())
				.buildAndAttach(getGameWorld());
		lineOfUI = Entities.builder().at(0, 901).viewFromNode(new Rectangle(1920, 200, Color.GREY))
				.buildAndAttach(getGameWorld());
		info_hero1 = Entities.builder().at(5, 905).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());
		goblin = Entities.builder().at(400, 90).viewFromTexture("goblin_down.png").buildAndAttach(getGameWorld());
		playerComponent = playerEntity.getComponent(Player.class);
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
//					for (int i = 0; i < 1920;) {
//						for (int j = 0; j < 900;) {
//							Entities.builder().at(i, j).viewFromNode(new Rectangle(61, 2, Color.DIMGREY))
//									.buildAndAttach(getGameWorld());
//							Entities.builder().at(i, j).viewFromNode(new Rectangle(2, 61, Color.DIMGREY))
//									.buildAndAttach(getGameWorld());
//							j += 60;
//						}
//						i += 60;
//					}
					grid = Entities.builder().at(0, 0).viewFromTexture("grille.png").buildAndAttach(getGameWorld());
					gridState = true;
				} else {
					grid.removeFromWorld();
					gridState = false;
				}
			}

		}, KeyCode.F);

//		input.addAction(new UserAction("Hide grid") {
//			@Override
//			protected void onAction() {
//				for (int i = 1920 - 1; i >= 0;) {
//					for (int j = 920 - 1; j >= 0;) {
//						List<Entity> list = getGameWorld().getEntitiesAt(new Point2D(i, j));
//						for(Entity e :list) {
//							if(e.getViewComponent().getEntity().get instanceof Rectangle) {
//								e.removeFromWorld();
//							}
//						}
////				        getGameWorld().getEntities()(
////				                tileX,
////				                tileY)

////				                .forEach(Entities::destroy);
//						j -= 40;
//					}
//					i -= 40;
//				}
//			}
//
//		}, KeyCode.H);

	}

	@Override
	protected void initUI() {
		Text textPixels = new Text();
//		Text uiText = new Text("Hello World");
		Point2D hotspot = Point2D.ZERO;

//		textPixels.setTranslateX(15); // x = 50
//		textPixels.setTranslateY(960); // y = 100
		getGameScene().addUINode(textPixels); // add to the scene graph
//		textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());

//		Text uiScore = getUIFactory().newText("TEST", Color.RED, 16.0):

		getGameScene().setCursor("cursor.png", hotspot);
//		getGameScene().addUINode(uiText);

		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				System.out.println("Coordonées cursor pixel (" + x + " , " + y + ")");

				playerComponent.move(new Point2D(x, y));

				double posX = playerEntity.getPosition().getX();
				double posY = playerEntity.getPosition().getY();
				System.out.println("Coordonées player X Y (" + posX + " , " + posY + ")");

				int casePlayerX = (int) (posX / 60);
				int casePlayerY = (int) (posY / 60);
				System.out.println("Coordonées player case (" + casePlayerX + " , " + casePlayerY + ")");

				int tab[] = new Click().cases(x, y);
				Deplacement move = new Deplacement();
				move.calculateCross(2, casePlayerX, casePlayerY);
				move.calculateDiag(2, casePlayerX, casePlayerY);
				List<SimpleEntry<Integer, Integer>> list = move.list;
				SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
//				System.out.println("Coordonées du tabl (" + tab[2] + " , " + tab[3] + ")");

				if (list.contains(vars)) {

					playerEntity.translateX(tab[2] - playerEntity.getPosition().getX());
					playerEntity.translateY(tab[3] - playerEntity.getPosition().getY());
					posX = playerEntity.getPosition().getX();
					posY = playerEntity.getPosition().getY();
					System.out.println("Coordonées player X Y (" + posX + " , " + posY + ")");
				} else {
					System.out.println("pas de deplacement");
				}
//				int tab2 = (int) (tab[2] - player.getPosition().getX() - 60 + 10);
//				int tab3 = (int) (tab[3] - player.getPosition().getY() - 60 - 10);
//				System.out.println("translateX = " + tab2 + " et translateY =" + tab3);
				for (int i = 0; i < 4; i++) {
					System.out.println(tab[i]);
				}
			}
		});

		getGameScene().getContentRoot().setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				int caseCursorX = ((int) event.getSceneX() / 60) - 1;
				int caseCursorY = ((int) event.getSceneY() / 60) - 1;
				int casePlayerX = (int) (playerEntity.getPosition().getX() / 60);
				int casePlayerY = (int) (playerEntity.getPosition().getY() / 60);
//				System.out.println("Coordonées du tabl (" + tab[2] + " , " + tab[3] + ")");
//				System.out.println("Coordonées du joueur (" + casePlayerX + " , " + casePlayerY + ")");

				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				int tab[] = new Click().cases(x, y);

				if ((caseCursorX == casePlayerX) && (caseCursorY == casePlayerY)) {
//					System.out.println("printed !");
					casesAround = Entities.builder()
							.at(tab[2] - playerEntity.getPosition().getX(), tab[3] - playerEntity.getPosition().getY())
							.viewFromTexture("rangeUnitOf2.png").buildAndAttach(getGameWorld());
				} else {
//					casesAround.removeFromWorld();
				}
			}
		});

//		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//
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
