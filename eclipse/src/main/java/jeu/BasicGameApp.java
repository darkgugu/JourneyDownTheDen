/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package jeu;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.parser.tiled.TiledMap;
import com.almasb.fxgl.parser.tiled.Tileset;
import com.almasb.fxgl.settings.GameSettings;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import capacites.BouleDeFeu;
import capacites.Capacites;
import capacites.Soin;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ui.CharInfoView;
import ui.UIEntity;

public class BasicGameApp extends GameApplication {
	// Playable Entities
	private Player redHeroComponent;
	private Player blueHeroComponent;
	private Player greenHeroComponent;
	private Player selectedUnit;
	// Fake Entities, for UI
	private Entity lineOfUI;
	private Entity grid;
	private Entity InfoUI;
	private Entity SpellUI;
	private Entity rangeTwo;
	boolean gridState = false;
	boolean rangeTwoState = false;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1920);
		settings.setHeight(1080);
		settings.setFullScreenAllowed(true);
		settings.setManualResizeEnabled(true);
		settings.setTitle("Journey down the den");
		settings.setVersion("0.5");
		settings.setAppIcon("JDTD_icon.png");
//		settings.setProfilingEnabled(true);

//To implement later
//		settings.setIntroEnabled(true);
	}

	@Override
	protected void initGame() {
		TiledMap map1 = getAssetLoader().loadTMX("map1.tmx");
		getGameWorld().setLevelFromMap(map1);

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

		getGameWorld().addEntityFactory(new UIEntity());
		

		System.out.println("Red Hero Class : " + redHeroComponent.getHeroClass().getName());
		System.out.println("Green Hero PV : " + greenHeroComponent.getHeroClass().getPv());
//		redHeroComponent.getHeroClass().setSkillsI(new Soin(), 0);
//		redHeroComponent.getHeroClass().setSkillsI(new BouleDeFeu(), 1);
		System.out.println(redHeroComponent.getHeroClass());
		// System.out.println(greenHeroComponent.getHeroClass());
		// new BouleDeFeu().cast(redHeroComponent.getHeroClass(),
		// greenHeroComponent.getHeroClass());
		// System.out.println("Green Hero PV : " +
		// greenHeroComponent.getHeroClass().getPv());

		lineOfUI = Entities.builder().at(0, 900).viewFromNode(new Rectangle(1920, 180, Color.GREY))
				.buildAndAttach(getGameWorld());

		InfoUI = Entities.builder().at(5, 901).viewFromTexture("UI.png").buildAndAttach(getGameWorld());
		
		SpellUI= Entities.builder().at(840, 901).viewFromTexture("spells.png").buildAndAttach(getGameWorld());

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
					grid = Entities.builder().at(0, 0).viewFromTexture("grid.png").buildAndAttach(getGameWorld());
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
		Point2D hotspot = Point2D.ZERO;

		CharInfoView.charInfoUI(getGameScene(), redHeroComponent, blueHeroComponent, greenHeroComponent);

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
					selectedUnit.move(new Point2D(x, y));
				}
			}
		});

		/*
		 * AFFICHAGE DES CASES ADJACENTES
		 */

		getGameScene().getContentRoot().setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				int caseCursorX = ((int) event.getSceneX() / 60);
				int caseCursorY = ((int) event.getSceneY() / 60);
				int casePlayerX = (int) (selectedUnit.getPosition().getX() / 60);
				int casePlayerY = (int) (selectedUnit.getPosition().getY() / 60);

				Player[] persos = new Player[3];
				persos[0] = redHeroComponent;
				persos[1] = blueHeroComponent;
				persos[2] = greenHeroComponent;

				for (int i = 0; i < persos.length; i++) {
					int pX = (int) persos[i].getPosition().getX();
					int pY = (int) persos[i].getPosition().getY();
					new Click();
					int tab[] = Click.cases(x, y);
					if ((caseCursorX == casePlayerX) && (caseCursorY == casePlayerY)) {
//					System.out.println("printed !");

						rangeTwo = getGameWorld().spawn("rangeTwo", new Point2D(pX - 180, pY - 180));
					
					} else { 
						if(rangeTwo != null){
							rangeTwo.removeFromWorld();
						}
					}
				}

//				System.out.println("Coordonées du tabl (" + tab[2] + " , " + tab[3] + ")");
//				System.out.println("Coordonées du joueur (" + casePlayerX + " , " + casePlayerY + ")");

			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

}
