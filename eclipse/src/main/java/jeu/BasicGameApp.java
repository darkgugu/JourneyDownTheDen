/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package jeu;

import java.util.List;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.parser.tiled.TiledMap;
import com.almasb.fxgl.settings.GameSettings;

import capacites.Capacites;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import personnages.EntityGenerate;
import ui.CharInfoView;
import ui.Description;
import ui.UIEntity;
import ui.GameLog;
import ui.ProximityCases;
import capacites.ParticlesEntity;
import capacites.ParticlesHandler;


public class BasicGameApp extends GameApplication {
	// Playable Entities
	private Player redHeroComponent;
	private Player blueHeroComponent;
	private Player greenHeroComponent;
	public Player selectedUnit;
	// Ennemies entities
	private IAControlledEntity gobelin;
	private IAControlledEntity orque;
	private Entity grid;
	// Check
	boolean gridState = false;
	boolean activeSkillOk = false;

	// Methods calls
	private Description description;
	private Tour tour;
	private CharInfoView view;
	private KillUnit killUnit;
	private WinOrDefeat winOrDefeat;

	private static String currentMap;
	private IAControlledEntity currentMob;

	public static void main(String[] args) {
		launch(args);
	}

	public BasicGameApp() {

	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1920);
		settings.setHeight(1080);
		settings.setFullScreenAllowed(true);
		settings.setManualResizeEnabled(true);
		settings.setTitle("Journey down the den");
		settings.setVersion("0.9");
		settings.setAppIcon("JDTD_icon.png");
		settings.setMenuEnabled(true);
	}

	@Override
	protected void initGame() {

		/* LEVELS */
		TiledMap map1 = getAssetLoader().loadTMX("map1.tmx");
		getGameWorld().setLevelFromMap(map1);
		currentMap = "map1";
		
		getGameWorld().addEntityFactory(new EntityGenerate());
		getGameWorld().addEntityFactory(new UIEntity());
		getGameWorld().addEntityFactory(new ParticlesEntity());
		
		/*UNITS*/
		Entity redHero = getGameWorld().spawn("redHero", new Point2D(120, 360));
		Entity blueHero = getGameWorld().spawn("blueHero", new Point2D(420, 360));
		Entity greenHero = getGameWorld().spawn("greenHero", new Point2D(720, 360));
		redHeroComponent = redHero.getComponent(Player.class);
		redHeroComponent.setName("red");
		blueHeroComponent = blueHero.getComponent(Player.class);
		blueHeroComponent.setName("blue");
		greenHeroComponent = greenHero.getComponent(Player.class);
		greenHeroComponent.setName("green");

		/* MOBS */
		Entity goblin = getGameWorld().spawn("goblin", new Point2D(1020, 180));
		gobelin = goblin.getComponent(IAControlledEntity.class);
		gobelin.setName("Gob le gobelin");
		currentMob = gobelin;

		/* UI */
		getGameWorld().spawn("lineOfUI", new Point2D(0, 900));
		getGameWorld().spawn("infoUI", new Point2D(5, 901));
		getGameWorld().spawn("description", new Point2D(720, 960));

		for (int i = 0; i < 10; i++) {

			int x = 720 + (60 * i);
			int y = 901;
			getGameWorld().spawn("spellBorder", new Point2D(x, y));
		}

		getGameWorld().spawn("spellBorder", new Point2D(1380, 901));
		getAudioPlayer().loopBGM("town_theme.mp3");
	}

	protected void init2() {
		
		TiledMap map2 = getAssetLoader().loadTMX("map2.tmx");
		getGameWorld().setLevelFromMap(map2);
		currentMap = "map2";
		/* UNITS */
		Entity redHero = getGameWorld().spawn("redHero", new Point2D(120, 360));
		Entity blueHero = getGameWorld().spawn("blueHero", new Point2D(420, 360));
		Entity greenHero = getGameWorld().spawn("greenHero", new Point2D(900, 420));
		redHeroComponent = redHero.getComponent(Player.class);
		redHeroComponent.setName("red");
		blueHeroComponent = blueHero.getComponent(Player.class);
		blueHeroComponent.setName("blue");
		greenHeroComponent = greenHero.getComponent(Player.class);
		greenHeroComponent.setName("green");
		

		/* UI */
		getGameWorld().spawn("lineOfUI", new Point2D(0, 900));
		getGameWorld().spawn("infoUI", new Point2D(5, 901));
		getGameWorld().spawn("description", new Point2D(720, 960));
		
		
		
		/* MOBS */
		Entity orc = getGameWorld().spawn("orc", new Point2D(620, 180));
		orque = orc.getComponent(IAControlledEntity.class);
		getOrque().setName("Azog le tyran");
		currentMob = orque;
		

		tour = new Tour(redHeroComponent, blueHeroComponent, greenHeroComponent, getOrque(), view, killUnit, winOrDefeat);

		for (int i = 0; i < 10; i++) {

			int x = 720 + (60 * i);
			int y = 901;
			getGameWorld().spawn("spellBorder", new Point2D(x, y));
		}

		getGameWorld().spawn("spellBorder", new Point2D(1380, 901));

		Player players[] = new Player[3];
		players[0] = redHeroComponent;
		players[1] = blueHeroComponent;
		players[2] = greenHeroComponent;
		tour.setPlayers(players);
		tour.updatePerso();
		tour.debut();
	}

	public static String getCurrentMap() {
		return currentMap;
	}

	@Override
	protected void initInput() {
		
		Input input = getInput();
		input.addAction(new UserAction("Show grid") {
			@Override
			protected void onActionBegin() {
				if (gridState == false) {
					grid = Entities.builder().at(0, 0).viewFromTexture("grid.png").buildAndAttach(getGameWorld());
					gridState = true;
				} else {
					grid.removeFromWorld();
					gridState = false;
				}
			}
		}, KeyCode.F);

		input.addAction(new UserAction("End/Skip") {
			@Override
			protected void onActionBegin() {
				tour.debut();
			}
		}, KeyCode.S);
		
		input.addAction(new UserAction("Particles1") {
			@Override
			protected void onActionBegin() {
				
			}
		}, KeyCode.E);		
		
		input.addAction(new UserAction("Particles") {
			@Override
			protected void onActionBegin() {
				//particle.removeComponent(ParticleComponent.class);
			}
		}, KeyCode.D);	
	}

	@Override
	protected void initUI() {

		Point2D hotspot = Point2D.ZERO;
		view = new CharInfoView(getGameScene(), redHeroComponent, greenHeroComponent, blueHeroComponent,
				GameLog.getGameLog());
		killUnit = new KillUnit();
		winOrDefeat = new WinOrDefeat(getGameScene());
		winOrDefeat.setBasicGameApp(this);
		tour = new Tour(redHeroComponent, blueHeroComponent, greenHeroComponent, getGobelin(), view, killUnit,
				winOrDefeat);
		description = new Description(getGameScene());
		description.mousePos(selectedUnit);
		getGameScene().setCursor("cursor.png", hotspot);

		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();

				List<Entity> list = getGameWorld().getEntitiesByType(EntityType.RANGE);
				int[] tabClick = Click.cases(x, y);

				int skillSlot = SkillSlot.isSkillSlot(x, y);

				if (tabClick[0] == 23 && tabClick[1] == 15) {
					tour.debut();
				}

				if (skillSlot != -1) {

					if (selectedUnit != null) {

						if (selectedUnit.getHeroClass().getSkills()[skillSlot] != null) {

							Capacites skill = selectedUnit.getHeroClass().getSkills()[skillSlot];
							selectedUnit.setActiveSkill(skill);

							activeSkillOk = true;
						} else {

							GameLog.setGameLog("Il n'y a aucun sort dans cet emplacement !");
						}
					} else {

						GameLog.setGameLog("Selectionnez une unité !");
					}

				}
				if (event.getButton() == MouseButton.PRIMARY && activeSkillOk) {

					Player[] persos = new Player[3];
					int j = 0;
					int k = 0;

					if (!redHeroComponent.getHeroClass().isDead()) {
						persos[j] = redHeroComponent;
						j++;
					} else {
						k++;
					}
					if (!greenHeroComponent.getHeroClass().isDead()) {
						persos[j] = greenHeroComponent;
						j++;
					} else {
						k++;
					}
					if (!blueHeroComponent.getHeroClass().isDead()) {
						persos[j] = blueHeroComponent;
						j++;
					} else {
						k++;
					}
					IAControlledEntity[] IA = new IAControlledEntity[1];
					if(currentMap == "map1") {
						IA[0] = getGobelin();

					}
					if(currentMap == "map2") {
						IA[0] = getOrque();
					}
					int[] caster = Click.cases((int) selectedUnit.getPosition().getX(),
							(int) selectedUnit.getPosition().getY());

					for (int i = 0; i < persos.length - k; i++) {
						int pX = (int) persos[i].getPosition().getX();
						int pY = (int) persos[i].getPosition().getY();
						int[] tabPerso = Click.cases(pX, pY);

						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {

							if (selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(),
									persos[i].getHeroClass(), caster, tabClick) == "OK") {

								selectedUnit.getActiveSkill().cast(selectedUnit.getHeroClass(), persos[i].getHeroClass());
								new ParticlesHandler(persos[i].getPosition(), selectedUnit.getActiveSkill(), getGameWorld()).spawn();

							} else {

								GameLog.setGameLog(selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(),
										persos[i].getHeroClass(), caster, tabClick));
							}
							view.updateInfo(redHeroComponent, blueHeroComponent, greenHeroComponent,
									GameLog.getGameLog(), tour.getNbTour());

							activeSkillOk = false;
						}
					}

					for (int i = 0; i < IA.length; i++) {
						if (IA[i].getType().isDead() == false) {
							int pX = (int) IA[i].getPosition().getX();
							int pY = (int) IA[i].getPosition().getY();
							int[] tabPerso = Click.cases(pX, pY);

							if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {

								if (selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(), IA[i].getType(),
										caster, tabClick) == "OK") {

								selectedUnit.getActiveSkill().cast(selectedUnit.getHeroClass(), IA[i].getType());
								GameLog.setGameLog("Cible : " + IA[i].getName() + " " + IA[i].getType().getPv());
								GameLog.setGameLog(IA[i].getName() + " PV : " + IA[i].getType().getPv() + "/" + IA[i].getType().getPvMax());
								new ParticlesHandler(IA[i].getPosition(), selectedUnit.getActiveSkill(), getGameWorld()).spawn();
							}
							else{

									GameLog.setGameLog(IA[i].getName() + " PV : " + IA[i].getType().getPv() + "/"
											+ IA[i].getType().getPvMax());
									GameLog.setGameLog(selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(),
											IA[i].getType(), caster, tabClick));
								}
								view.updateInfo(redHeroComponent, blueHeroComponent, greenHeroComponent,
										GameLog.getGameLog(), tour.getNbTour());
								activeSkillOk = false;
							}
						}

					}
				}

				if (event.getButton() == MouseButton.SECONDARY) {

					Player[] persos = new Player[3];
					persos[0] = redHeroComponent;
					persos[1] = blueHeroComponent;
					persos[2] = greenHeroComponent;

					for (int i = 0; i < persos.length; i++) {
						int pX = (int) persos[i].getPosition().getX();
						int pY = (int) persos[i].getPosition().getY();

						int[] tabPerso = Click.cases(pX, pY);

						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {
							ObstacleReader obstacles = new ObstacleReader();
							obstacles.reader(currentMap);
							if (selectedUnit == null && persos[i].getHeroClass().isDead() == false) {
								selectedUnit = persos[i];
								description.mousePos(selectedUnit);
								view.updateSkillsUI(selectedUnit);
								ProximityCases.proxCases(selectedUnit, redHeroComponent, blueHeroComponent,
										greenHeroComponent, currentMob, getGameWorld());

							} else if (persos[i].getHeroClass().isDead() == false) {
								for (Entity entity : list) {
									entity.removeFromWorld();

								}
								if (persos[i] == selectedUnit) {

									selectedUnit = null;
								} else {
									selectedUnit = persos[i];
									description.mousePos(selectedUnit);
									view.updateSkillsUI(selectedUnit);
									ProximityCases.proxCases(selectedUnit, redHeroComponent, blueHeroComponent,
											greenHeroComponent, currentMob, getGameWorld());
								}
							}
						} else {

						}

					}
				} else {
					List<Entity> target = getGameWorld().getEntitiesAt(new Point2D(x, y));
					for (Entity t : target) {
						if (t.getTypeComponent().isType(EntityType.BLOCK)) {
							return;
						}
					}

					if (selectedUnit != null) {
						for (Entity entity : list) {
							entity.removeFromWorld();
						}
						selectedUnit.move(new Point2D(x, y), getGameWorld());
					}
				}
			}
		});
	}

	public IAControlledEntity getGobelin() {
		return gobelin;
	}
	public IAControlledEntity getOrque() {
		return orque;
	}
}