/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package jeu;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.parser.tiled.TiledMap;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.box2d.collision.ContactID.Type;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.settings.ReadOnlyGameSettings;

import capacites.Fireball;
import capacites.Capacites;
import capacites.Soin;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ui.CharInfoView;
import ui.UIEntity;

public class BasicGameApp extends GameApplication {
	// Playable Entities
	private Player redHeroComponent;
	private Player blueHeroComponent;
	private Player greenHeroComponent;
	private Player selectedUnit;
	private Entity Block;
	// Fake Entities, for UI
	private Entity grid;
	private Entity rangeTwo;
	boolean gridState = false;
	boolean activeSkillOk = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1920);
		settings.setHeight(1080);
		settings.setFullScreenAllowed(true);
		settings.setManualResizeEnabled(true);
		settings.setTitle("Journey down the den");
		settings.setVersion("0.5");
		settings.setAppIcon("JDTD_icon.png");
		settings.setProfilingEnabled(true);

//To implement later
//		settings.setIntroEnabled(true);
	}

	@Override
	protected void initGame() {
		/*
		 * LEVELS
		 */
		TiledMap map1 = getAssetLoader().loadTMX("map1.tmx");
		getGameWorld().addEntityFactory(new EntityGenerate());
		getGameWorld().addEntityFactory(new UIEntity());
		getGameWorld().setLevelFromMap(map1);
		/*
		 * UNITS
		 */
		Entity redHero = getGameWorld().spawn("redHero", new Point2D(0, 0));
		Entity blueHero = getGameWorld().spawn("blueHero", new Point2D(60, 0));
		Entity greenHero = getGameWorld().spawn("greenHero", new Point2D(120, 0));
		redHeroComponent = redHero.getComponent(Player.class);
		redHeroComponent.setName("red");
		blueHeroComponent = blueHero.getComponent(Player.class);
		blueHeroComponent.setName("blue");
		greenHeroComponent = greenHero.getComponent(Player.class);
		greenHeroComponent.setName("green");
		/*
		 * UI
		 */
		Entity lineofUI = getGameWorld().spawn("lineOfUI", new Point2D(0, 900));
		Entity InfoUI = getGameWorld().spawn("infoUI", new Point2D(5, 901));
		Entity SpellUI1 = getGameWorld().spawn("spell1", new Point2D(720, 901));
		Entity SpellUI2 = getGameWorld().spawn("spell2", new Point2D(780, 901));
		Entity SpellUI3 = getGameWorld().spawn("spell3", new Point2D(840, 901));
		Entity SpellUI4 = getGameWorld().spawn("spell4", new Point2D(900, 901));
		Entity SpellUI5 = getGameWorld().spawn("spell5", new Point2D(960, 901));
		Entity SpellUI6 = getGameWorld().spawn("spell6", new Point2D(1020, 901));
		Entity SpellUI7 = getGameWorld().spawn("spell7", new Point2D(1080, 901));
		Entity SpellUI8 = getGameWorld().spawn("spell8", new Point2D(1140, 901));
		Entity SpellUI9 = getGameWorld().spawn("spell9", new Point2D(1200, 901));
		Entity SpellUI10 = getGameWorld().spawn("spell10", new Point2D(1260, 901));

//		System.out.println("Red Hero Class : " + redHeroComponent.getHeroClass().getName());
//		System.out.println("Green Hero PV : " + greenHeroComponent.getHeroClass().getPv());
//		redHeroComponent.getHeroClass().setSkillsI(new Soin(), 0);
//		redHeroComponent.getHeroClass().setSkills(new Soin(), 1);
		// System.out.println(redHeroComponent.getHeroClass());

		// System.out.println(greenHeroComponent.getHeroClass());
		// new BouleDeFeu().cast(redHeroComponent.getHeroClass(),
		// greenHeroComponent.getHeroClass());
		// System.out.println("Green Hero PV : " +
		// greenHeroComponent.getHeroClass().getPv());

//		System.out.println("Red Hero PA " + redHeroComponent.getHeroClass().getActionPoint());
//		new Fireball().cast(redHeroComponent.getHeroClass(), greenHeroComponent.getHeroClass());
//		System.out.println("Green Hero PV : " + greenHeroComponent.getHeroClass().getPv());
//		System.out.println("Red Hero PA " + redHeroComponent.getHeroClass().getActionPoint());

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
	protected void initPhysics() {
		FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYERCOMPONENT, EntityType.BLOCK) {
			// order of types is the same as passed into the constructor
			@Override
			protected void onCollisionBegin(Entity player, Entity block) {
				player.removeFromWorld();

			}
		});
	}

	@Override
	protected void initUI() {
		Point2D hotspot = Point2D.ZERO;
		CharInfoView.charInfoUI(getGameScene(), redHeroComponent, blueHeroComponent, greenHeroComponent);
		getGameScene().setCursor("cursor.png", hotspot);
//		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				int x = (int) event.getSceneX();
//				int y = (int) event.getSceneY();
//
//				Fireball activeSkill = new Fireball();
//				int skillSlot = SkillSlot.isSkillSlot(x, y);
//
//				System.out.println("Coordonï¿½es cursor pixel (" + x + " , " + y + ")");
//				Player[] persos = new Player[3];
//				persos[0] = redHeroComponent;
//				persos[1] = blueHeroComponent;
//				persos[2] = greenHeroComponent;
//
//				if (skillSlot != -1) {
//
//					if (selectedUnit.getHeroClass().getSkills()[skillSlot].getName() == "Boule de Feu") {
//
//						// Fireball activeSkill = new Fireball();
//						activeSkillOk = true;
//						System.out.println("Fireball");
//
//						// .cast(selectedUnit.getHeroClass(), selectedUnit.getHeroClass());
//					}
//					if (selectedUnit.getHeroClass().getSkills()[skillSlot].getName() == "Soin") {
//
//						System.out.println("Pas encore implï¿½mentï¿½");
//						// Soin activeSkill = new Soin();
//						// activeSkillOk = true;
//						// .cast(selectedUnit.getHeroClass(), selectedUnit.getHeroClass());
//					}
//
//					// Introspection
////					try {
////						Capacites ent = (Capacites) Class.forName("capacites.Fireball").newInstance();
////						ent.cast(selectedUnit.getHeroClass());
////					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//
//				}
//
//				if (event.getButton() == MouseButton.SECONDARY) {
//					for (int i = 0; i < persos.length; i++) {
//						int pX = (int) persos[i].getPosition().getX();
//						int pY = (int) persos[i].getPosition().getY();
//						int[] tabPerso = Click.cases(pX, pY);
//						int[] tabClick = Click.cases(x, y);
//
//						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {
//
//							if (activeSkillOk) {
//
//								activeSkill.cast(selectedUnit.getHeroClass(), persos[i].getHeroClass());
//								System.out.println("Target : " + persos[i].getName());
//								activeSkillOk = false;
//
//							} else {
//
//								selectedUnit = persos[i];
//								System.out.println(selectedUnit.getName());
//
//							}
//						}
//					}
//				} else {
//					selectedUnit.move(new Point2D(x, y));
//				}
//			}
//		});

		/*
		 * AFFICHAGE DES CASES ADJACENTES
		 */
		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();
				System.out.println(x + " " + y);
				List<Entity> list = getGameWorld().getEntitiesByType(EntityType.RANGE_TWO);
				List<Entity> listBlock = getGameWorld().getEntitiesByType(EntityType.BLOCK);

				if (event.getButton() == MouseButton.SECONDARY) {

					Player[] persos = new Player[3];
					persos[0] = redHeroComponent;
					persos[1] = blueHeroComponent;
					persos[2] = greenHeroComponent;
//					int[] tabCasesNonValides = Click.casesNonValides(x, y);

					for (int i = 0; i < persos.length; i++) {
						int pX = (int) persos[i].getPosition().getX();
						int pY = (int) persos[i].getPosition().getY();
						int[] tabPerso = Click.cases(pX, pY);
						int[] tabClick = Click.cases(x, y);
						System.out.println(pX + "  " + pY);
						System.out.println(pX + "  " + pY);
						// si tabClick == caseNonValide alors pas de déplacement
//						if (tabClick[0] != tabCasesNonValides[0] && tabClick[1] != tabCasesNonValides[1]) {
						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {
							if (selectedUnit == null) {
								selectedUnit = persos[i];
								/*
								 * for(int i = 0; i < nbr de cases valides (jusqu'à 4) < i++) { afficher case a
								 * emplacement valide etc etc }
								 * 
								 */
								rangeTwo = getGameWorld().spawn("rangeTwo", new Point2D(pX - 120, pY - 120));
							} else {
								for (Entity entity : list) {
									entity.removeFromWorld();
								}
								if (persos[i] == selectedUnit) {
									selectedUnit = null;
								} else {
									selectedUnit = persos[i];
									rangeTwo = getGameWorld().spawn("rangeTwo", new Point2D(pX - 120, pY - 120));
								}
							}
						} else {

						}
//						} else {
//							System.out.println("dont move");
//						}

					}
				} else {
					List<Entity> target = getGameWorld().getEntitiesAt(new Point2D(x, y));
					for (Entity t : target) {
						if (t.getTypeComponent().isType(EntityType.BLOCK)) {
							return ;
						}
					}

					if (selectedUnit != null) {
						for (Entity entity : list) {
							entity.removeFromWorld();
						}
						System.out.println("move");
						selectedUnit.move(new Point2D(x, y));
					}
				}
			}
		});
	}

}