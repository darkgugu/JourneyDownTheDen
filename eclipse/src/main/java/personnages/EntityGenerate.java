package personnages;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import jeu.EntityType;
import jeu.IAControlledEntity;
import jeu.Player;
import personnages.IAControlled.Gobelin;
import personnages.IAControlled.Orc;
import personnages.playerControlled.Healer;
import personnages.playerControlled.Magician;
import personnages.playerControlled.Warrior;

public class EntityGenerate implements EntityFactory {

	@Spawns("redHero")
	public Entity newRedHero(SpawnData data) {
		return  Entities.builder()
				.from(data)
				.type(EntityType.PLAYER_RED)
				.viewFromTexture("priest.png")
				.with(new Player(new Healer()))
				.build();
	}
	
	@Spawns("greenHero")
	public Entity newGreenHero(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.PLAYER_GREEN)
				.viewFromTexture("warrior.png")
				.with(new Player(new Warrior()))
				.build();
	}
	
	@Spawns("blueHero")
	public Entity newBlueHero(SpawnData data) {
		return  Entities.builder()
				.from(data)
				.type(EntityType.PLAYER_BLUE)
				.viewFromTexture("magician.png")
				.with(new Player(new Magician()))
				.build();
	}

	@Spawns("goblin")
	public Entity newGoblin(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.GOBLIN)
				.viewFromTexture("goblin.png")
				.with(new IAControlledEntity(new Gobelin()))
				.build();
	}
	
	@Spawns("priestFace")
	public Entity newPriestFace(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.FACE)
				.viewFromTexture("priestFace.png")
				.build();
	}
	@Spawns("warriorFace")
	public Entity newWarriorFace(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.FACE)
				.viewFromTexture("warriorFace.png")
				.build();
	}
	
	@Spawns("magicianFace")
	public Entity newMagicianFace(SpawnData data) {
		return Entities.builder()
				.from(data)
			.type(EntityType.FACE)
				.viewFromTexture("magicianFace.png")
				.build();	
	}
	
	@Spawns("orc")
	public Entity newOrc(SpawnData data) {
		return Entities.builder()
			.from(data)
			.type(EntityType.FACE)
				.viewFromTexture("orc.png")
				.with(new IAControlledEntity(new Orc()))
				.build();	
	}
	
}
