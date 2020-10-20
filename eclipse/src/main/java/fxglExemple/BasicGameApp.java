/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package fxglExemple;

import java.util.Map;
import capacites.BouleDeFeu;
import capacites.Soin;
import personnages.IAControlled.Gobelin;
import personnages.playerControlled.Healer;
import personnages.playerControlled.Magician;
import personnages.playerControlled.Warrior;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import personnages.playerControlled.Healer;

public class BasicGameApp extends GameApplication {
	private Entity player;
	@SuppressWarnings("unused")
	private Entity background;
	@SuppressWarnings("unused")
	private Entity tileX;
	@SuppressWarnings("unused")
	private Entity tileY;

	@SuppressWarnings("unused")
	private Entity lineOfUI;

	@Override
	protected void initSettings(GameSettings settings) {
//A fix
//		settings.setFullScreenAllowed(true);
//		settings.setFullScreenAllowed(true);
		settings.setWidth(1920); // 791
		settings.setHeight(1080); // 575
		settings.setTitle("Journey down the den");
		settings.setVersion("0.1");
//Implémenter plus tard si on a le temps
//		settings.setIntroEnabled(true);
	}

	@Override
	protected void initGame() {
		background = Entities.builder().at(0, 0).viewFromTexture("mapTest.png").buildAndAttach(getGameWorld());

		player = Entities.builder().at(60, 60).viewFromTexture("down_character.png").buildAndAttach(getGameWorld());
		// Board for scene
		for (int i = 0; i < 1920;) {
			for (int j = 0; j < 920;) {
				tileX = Entities.builder().at(i, j).viewFromNode(new Rectangle(41, 2, Color.DIMGREY))
						.buildAndAttach(getGameWorld());
				tileY = Entities.builder().at(i, j).viewFromNode(new Rectangle(2, 41, Color.DIMGREY))
						.buildAndAttach(getGameWorld());
				j += 40;
			}
			i += 40;
		}
		lineOfUI = Entities.builder().at(0, 918).viewFromNode(new Rectangle(1920, 2, Color.BLACK))
				.buildAndAttach(getGameWorld());
		// Repeatable theme
		getAudioPlayer().loopBGM("town_theme.mp3");
	}

	@Override
	protected void initInput() {
		Input input = getInput();
//test pas fini d'être écrit
//		input.getMousePositionUI();
//		
//		input.addAction(new UserAction("Move towards") {
//			@Override
//			protected void onAction() {
//				if (x1 >= x) {
//					player.setViewFromTexture("right_character.png");
//				}
//				player.translate(40);
//			}
//		}, MouseButton.PRIMARY);

		input.addAction(new UserAction("Move Right") {
			@Override
			protected void onAction() {
				player.setViewFromTexture("right_character.png");
				player.translateX(3); // move right 3 pixels
				// Utiliser getGamestate pour obtenir le nombre de cases parcouru en faisant
				// (taille pixel case) / pixel parcouru
//				getGameState().increment("pixelsMoved", +3);				
			}
		}, KeyCode.D);

		input.addAction(new UserAction("Move Left") {
			@Override
			protected void onAction() {
				player.setViewFromTexture("left_character.png");
				player.translateX(-3); // move left 3 pixels
//				getGameState().increment("pixelsMoved", +3);

			}
		}, KeyCode.Q);

		input.addAction(new UserAction("Move Up") {
			@Override
			protected void onAction() {
				player.setViewFromTexture("up_character.png");
				player.translateY(-3); // move up 3 pixels
//				getGameState().increment("pixelsMoved", +3);

			}
		}, KeyCode.Z);

		input.addAction(new UserAction("Move Down") {
			@Override
			protected void onAction() {
				player.setViewFromTexture("down_character.png");
				player.translateY(3); // move down 3 pixels
//				getGameState().increment("pixelsMoved", +3);

			}
		}, KeyCode.S);
	}

	@Override
	protected void initUI() {
		Text textPixels = new Text();
		Text uiText = new Text("Hello World");
		Point2D hotspot = Point2D.ZERO;

		textPixels.setTranslateX(1920 - 800); // x = 50
		textPixels.setTranslateY(60); // y = 100

		getGameScene().addUINode(textPixels); // add to the scene graph
		textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());

		Text uiScore = getUIFactory().newText("", Color.RED, 16.0);
		getGameScene().setCursor("cursor.png", hotspot);
		getGameScene().addUINode(uiText);
		
		
	    Text textPv = getUIFactory().newText("", Color.BLACK, 22);
	    Text textPvMax = getUIFactory().newText("", Color.BLACK, 22);
//	    15 / 960
	    textPv.setTranslateX(15);
	    textPv.setTranslateY(960);
	    
	    textPvMax.setTranslateX(100);
	    textPvMax.setTranslateY(960);
	    
	    textPv.textProperty().bind(getGameState().intProperty("Pv").asString());
	    textPvMax.textProperty().bind(getGameState().intProperty("PvMax").asString());

	    getGameScene().addUINodes(textPv, textPvMax);
	}

	@Override
	public void initGameVars(Map<String, Object> vars) {
		

		vars.put("pixelsMoved", 0);
		vars.put("Pv", 100);
		vars.put("PvMax", 100);
	}

	public static void main(String[] args) {
		

		launch(args);
	}
}
