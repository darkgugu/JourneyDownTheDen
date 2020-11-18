package jeu;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public class EntityGenerate implements EntityFactory {

	@Spawns("redHero")
	public Entity newRedHero(SpawnData data) {
		return  Entities.builder()
				.from(data)
				.type(EntityType.PLAYER_RED)
				.viewFromTexture("redHero.png")
				.with(new Player())
				.build();
	}
	
//	@Spawns("greenHero")
//	public Entity newGreenHero(SpawnData data) {
//		return Entities.builder()
//				.from(data)
//				.type(EntityType.PLAYER_GREEN)
//				.viewFromTexture("greenHero.png")
//				.with(new Player())
//				.build();
//	}
	
//	@Spawns("blueHero")
//	public Entity newBlueHero(SpawnData data) {
//		return  Entities.builder()
//				.from(data)
//				.type(EntityType.PLAYER_BLUE)
//				.viewFromTexture("blueHero.png")
//				.with(new Player())
//				.build();
//	}

}
