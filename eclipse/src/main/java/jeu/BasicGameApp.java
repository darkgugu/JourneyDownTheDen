/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package jeu;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BasicGameApp extends GameApplication {
	private Player redHeroComponent;
	private Player blueHeroComponent;
	private Player greenHeroComponent;
	private Player selectedUnit;
	private Entity background;
	private Entity lineOfUI;
	private Entity grid;
	private Entity info_hero1;

	private Entity casesAround;

	boolean gridState = false;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1920);
		settings.setHeight(1080);
		settings.setFullScreenAllowed(true);
		settings.setManualResizeEnabled(true);
		settings.setTitle("Journey down the den");
		settings.setVersion("0.3");
		settings.setAppIcon("JDTD_icon.png");
		settings.setProfilingEnabled(true);

//To implement later
//		settings.setIntroEnabled(true);
	}

	@Override
	protected void initGame() {
		getGameWorld().setLevelFromMap("level1.tmx");
		getGameWorld().addEntityFactory(new EntityGenerate());
		Entity redHero = getGameWorld().spawn("redHero", new Point2D(0, 0));
		Entity blueHero = getGameWorld().spawn("blueHero", new Point2D(60, 0));
		Entity greenHero = getGameWorld().spawn("greenHero", new Point2D(120, 0));

		redHeroComponent = redHero.getComponent(Player.class);
		redHeroComponent.setName("red");
		blueHeroComponent = blueHero.getComponent(Player.class);
		blueHeroComponent.setName("blue");
		greenHeroComponent = greenHero.getComponent(Player.class);
		greenHeroComponent.setName("green");
		selectedUnit = redHeroComponent;
//		background = Entities.builder().at(0, 0).with(new IrremovableComponent()).viewFromTexture("mapTest.png")
//				.buildAndAttach(getGameWorld());
		lineOfUI = Entities.builder().at(0, 901).viewFromNode(new Rectangle(1920, 200, Color.GREY))
				.buildAndAttach(getGameWorld());
		info_hero1 = Entities.builder().at(5, 905).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());
//To implement
//		info_hero2 = Entities.builder().at(319, 910).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());
//		info_hero3 = Entities.builder().at(633, 910).viewFromTexture("Hero1_full.png").buildAndAttach(getGameWorld());

// 		Repeatable theme
		getAudioPlayer().loopBGM("town_theme.mp3");
	}

	@Override
	protected void initPhysics() {
		FXGL.getPhysicsWorld()
				.addCollisionHandler(new CollisionHandler(EntityType.PLAYER_RED, EntityType.PLAYER_GREEN) {

					// order of types is the same as passed into the constructor
					@Override
					protected void onCollisionBegin(Entity redHero, Entity greenHero) {
						greenHero.removeFromWorld();
					}
				});
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
				Player[] persos = new Player[3];
				persos[0] = redHeroComponent;
				persos[1] = blueHeroComponent;
				persos[2] = greenHeroComponent;
				if (event.getButton() == MouseButton.SECONDARY) {
					for (int i = 0; i < persos.length; i++) {
						int pX = (int) persos[i].getPosition().getX();
						int pY = (int) persos[i].getPosition().getY();
						int[] tabPerso = Click.cases(pX, pY);
						int[] tabClick = Click.cases(x, y);

						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {
							selectedUnit = persos[i];
							System.out.println(selectedUnit.getName());
						}
					}
				} else {
					//if check available case == true je me déplace
					selectedUnit.move(new Point2D(x, y));
				}
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
