package ui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.extra.entity.components.OffscreenCleanComponent;

import jeu.EntityType;

public class UIEntity implements EntityFactory {
	
	@Spawns("rangeTwo")
	public Entity newRangeTwo(SpawnData data) {
		return Entities.builder()
		.from(data)
		.type(EntityType.RANGE_TWO)
		.viewFromTexture("rangeUnit.png")
		.build();
	}

	@Spawns("spell1")
	public Entity newSpell1(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("spells.png")
				.build();
	}
}