/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */
package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;
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
import ui.CharInfoView;
import ui.Description;
import ui.UIEntity;
import ui.GameLog;

public class BasicGameApp extends GameApplication {
	// Playable Entities
	private Player redHeroComponent;
	private Player blueHeroComponent;
	private Player greenHeroComponent;
	public Player selectedUnit;
	// Ennemies entities
	private IAControlledEntity gobelin;
	private Entity grid;
	boolean gridState = false;
	boolean activeSkillOk = false;
	//Methods calls
	private Description description;
	private Tour tour;
	private CharInfoView view;
	private KillUnit killUnit;
	private WinOrDefeat winOrDefeat;
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
		settings.setVersion("0.8");
		settings.setAppIcon("JDTD_icon.png");
		settings.setMenuEnabled(true);
	}

	@Override
	protected void initGame() {
		
		/*LEVELS*/
		TiledMap map1 = getAssetLoader().loadTMX("map1.tmx");
		getGameWorld().addEntityFactory(new EntityGenerate());
		getGameWorld().addEntityFactory(new UIEntity());
		getGameWorld().setLevelFromMap(map1);
		
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

		/*MOBS*/
		Entity goblin1 = getGameWorld().spawn("goblin", new Point2D(1020, 180));
		gobelin = goblin1.getComponent(IAControlledEntity.class);
		gobelin.setName("Gob le gobelin");

		/*UI*/
		getGameWorld().spawn("lineOfUI", new Point2D(0, 900));
		getGameWorld().spawn("infoUI", new Point2D(5, 901));

		for (int i = 0; i < 10; i++) {

			int x = 720 + (60 * i);
			int y = 901;
			getGameWorld().spawn("spellBorder", new Point2D(x, y));
		}

		getGameWorld().spawn("spellBorder", new Point2D(1380, 901));
		getAudioPlayer().loopBGM("town_theme.mp3");
	}

	protected void updateSkillsUI(Player selectedUnit) {

		for (int i = 0; i != 10; i++) {

			int x = 725 + (60 * i);
			int y = 906;
			if (selectedUnit.getHeroClass().getSkills()[i] != null) {
				getGameWorld().spawn(selectedUnit.getHeroClass().getSkills()[i].getClass().getSimpleName(),
						new Point2D(x, y));
			}
		}
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
	}

	@Override
	protected void initUI() {

		Point2D hotspot = Point2D.ZERO;
		view = new CharInfoView(getGameScene(), redHeroComponent, greenHeroComponent, blueHeroComponent, GameLog.getGameLog());
		killUnit = new KillUnit(getGameWorld(), redHeroComponent, blueHeroComponent, greenHeroComponent);
		winOrDefeat = new WinOrDefeat(getGameScene());
		tour = new Tour(redHeroComponent, blueHeroComponent, greenHeroComponent, gobelin, view, killUnit, winOrDefeat);
		description = new Description(getGameScene());
		description.mousePos(selectedUnit);
		
		getGameScene().setCursor("cursor.png", hotspot);
		getGameScene().getContentRoot().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getSceneX();
				int y = (int) event.getSceneY();

				System.out.println(y + " " + y);
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
							System.out.println("Active Skill : " + skill.getName());
						} else {

							GameLog.setGameLog("Il n'y a aucun sort dans cet emplacement !");
							view.updateLog(GameLog.getGameLog());
						}
					} 
					else {
						
						GameLog.setGameLog("Selectionnez une unit� !");
						view.updateLog(GameLog.getGameLog());
					}

				}
				if (event.getButton() == MouseButton.PRIMARY && activeSkillOk) {

					Player[] persos = new Player[3];
					int j = 0;
					int k = 0;
					
					if(!redHeroComponent.getHeroClass().isDead()) {
						persos[j] = redHeroComponent;
						j++;
					}
					else {
						k++;
					}
					if(!greenHeroComponent.getHeroClass().isDead()) {
						persos[j] = greenHeroComponent;
						j++;
					}
					else {
						k++;
					}
					if(!blueHeroComponent.getHeroClass().isDead()) {
						persos[j] = blueHeroComponent;
						j++;
					}
					else {
						k++;
					}
					IAControlledEntity[] IA = new IAControlledEntity[1];
					IA[0] = gobelin;
				
					int[] caster = Click.cases((int) selectedUnit.getPosition().getX(), (int) selectedUnit.getPosition().getY());

					for (int i = 0; i < persos.length - k; i++) {
						int pX = (int) persos[i].getPosition().getX();
						int pY = (int) persos[i].getPosition().getY();
						int[] tabPerso = Click.cases(pX, pY);

						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {

							if (selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(),
									persos[i].getHeroClass(), caster, tabClick) == "OK") {

								selectedUnit.getActiveSkill().cast(selectedUnit.getHeroClass(),
										persos[i].getHeroClass());
							} else {

								GameLog.setGameLog(selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(),
										persos[i].getHeroClass(), caster, tabClick));
							}
							view.updateInfo(redHeroComponent, blueHeroComponent, greenHeroComponent, GameLog.getGameLog(), tour.getNbTour());

							activeSkillOk = false;
						}
					}
					
					for (int i = 0; i < IA.length; i++) {
						int pX = (int) IA[i].getPosition().getX();
						int pY = (int) IA[i].getPosition().getY();
						int[] tabPerso = Click.cases(pX, pY);

						if(tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {

							if(selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(), IA[i].getType(), caster, tabClick) == "OK") {

								selectedUnit.getActiveSkill().cast(selectedUnit.getHeroClass(), IA[i].getType());
								GameLog.setGameLog("Cible : " + IA[i].getName() + " " + IA[i].getType().getPv());
								GameLog.setGameLog(IA[i].getName() + " PV : " + IA[i].getType().getPv() + "/" + IA[i].getType().getPvMax());
							}
							else{

								GameLog.setGameLog(IA[i].getName() + " PV : " + IA[i].getType().getPv() + "/" + IA[i].getType().getPvMax());
								GameLog.setGameLog(selectedUnit.getActiveSkill().castOK(selectedUnit.getHeroClass(), IA[i].getType(), caster, tabClick));
							}
							view.updateInfo(redHeroComponent, blueHeroComponent, greenHeroComponent, GameLog.getGameLog(), tour.getNbTour());
							activeSkillOk = false;
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
						System.out.println("perso " + i + " X " + pX);
						int pY = (int) persos[i].getPosition().getY();
						System.out.println("perso " + i + " X " + pY);

						int[] tabPerso = Click.cases(pX, pY);
						System.out.println(pX + "  " + pY);
						System.out.println(pX + "  " + pY);
						
						if (tabPerso[0] == tabClick[0] && tabPerso[1] == tabClick[1]) {
							ObstacleReader obstacles = new ObstacleReader();
							obstacles.reader();
							if (selectedUnit == null && persos[i].getHeroClass().isDead() == false) {
								selectedUnit = persos[i];
								description.mousePos(selectedUnit);
								updateSkillsUI(selectedUnit);
								proximityCases(selectedUnit);

							} else if (persos[i].getHeroClass().isDead() == false){
								for (Entity entity : list) {
									entity.removeFromWorld();

								}
								if (persos[i] == selectedUnit) {

									selectedUnit = null;
								} else {
									selectedUnit = persos[i];
									description.mousePos(selectedUnit);
									updateSkillsUI(selectedUnit);
									proximityCases(selectedUnit);
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
						System.out.println("move");

						selectedUnit.move(new Point2D(x, y), getGameWorld());
					}
				}
			}
		});
	}

	private void proximityCases(Player player) {
		
		new Click();
		int tab[] = Click.cases(((int) player.getPosition().getX()), ((int) player.getPosition().getY()));
		Deplacement move = new Deplacement();
		move.calculateCross(player.getHeroClass().getMovePoint(), tab[0], tab[1]);
		move.calculateDiag(player.getHeroClass().getMovePoint(), tab[0], tab[1]);
		int tabMob[] = Click.cases((	(int) gobelin.getPosition().getX()), ((int) gobelin.getPosition().getY()));
		int tabRedHero[] = Click.cases(((int) redHeroComponent.getPosition().getX()), ((int) redHeroComponent.getPosition().getY()));
		int tabGreenHero[] = Click.cases(((int) greenHeroComponent.getPosition().getX()), ((int) greenHeroComponent.getPosition().getY()));
		int tabBlueHero[] = Click.cases(((int) blueHeroComponent.getPosition().getX()), ((int) blueHeroComponent.getPosition().getY()));
		
		List<SimpleEntry<Integer, Integer>> list = move.list;
		list.remove(new SimpleEntry<Integer, Integer>(tabMob[0], tabMob[1]));
		if(!redHeroComponent.getHeroClass().isDead()) list.remove(new SimpleEntry<Integer, Integer>(tabRedHero[0], tabRedHero[1]));
		if(!greenHeroComponent.getHeroClass().isDead()) list.remove(new SimpleEntry<Integer, Integer>(tabGreenHero[0], tabGreenHero[1]));
		if(!blueHeroComponent.getHeroClass().isDead()) list.remove(new SimpleEntry<Integer, Integer>(tabBlueHero[0], tabBlueHero[1]));

		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 14; j++) {
				
				SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(i, j);
				
				if(list.contains(vars)) {
					getGameWorld().spawn("range", new Point2D(i * 60, j * 60));
				
				}
			}
		}
	}
	
}