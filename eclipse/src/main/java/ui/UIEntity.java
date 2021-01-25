package ui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jeu.EntityType;

public class UIEntity implements EntityFactory {
	/*
	 * Declared entities for UI
	 */
	@Spawns("lineOfUI")
	public Entity newLineOfUI(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.LINEOFUI)
				.viewFromNode(new Rectangle(1920, 180, Color.GREY))
				.build();
	}
	@Spawns("infoUI")
	public Entity newInfoUI(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.INFOUI)
				.viewFromTexture("UI.png")
				.build();
	}
	@Spawns("grid")
	public Entity newGrid(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.INFOUI)
				.viewFromTexture("grid.png")
				.build();
	}
	/*
	 * Declared entities for range
	 */
	@Spawns("range")
	public Entity newRange(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.RANGE)
				.viewFromTexture("range.png")
				.build();
	}
	
	@Spawns("Block")
	public Entity newBlock(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.BLOCK)
				.build();
	}
	/*
	 * Declared entities of type SPELL
	 */
	
	
	@Spawns("spellBorder")
	public Entity newSpellBorder(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("spells.png")
				.build();
	}
	
	@Spawns("Fireball")
	public Entity newSpell1(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("Fireball.png")
				.build();
	}
		@Spawns("Soin")
	public Entity newSpell2(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("Soin.png")
				.build();
	}
	@Spawns("Soin2")
	public Entity newSpell3(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("Soin2.png")
				.build();
	}
	@Spawns("PhysicalDamageBuff")
	public Entity newSpell4(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("PhysicalDamageBuff.png")
				.build();
	}
	@Spawns("Estoc")
	public Entity newSpell5(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("Estoc.png")
				.build();
	}
	@Spawns("Lance")
	public Entity newSpell6(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("Lance.png")
				.build();
	}
	@Spawns("spell7")
	public Entity newSpell7(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("spells.png")
				.build();
	}
	@Spawns("spell8")
	public Entity newSpell8(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("spells.png")
				.build();
	}
	@Spawns("spell9")
	public Entity newSpell9(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("spells.png")
				.build();
	}
	@Spawns("spell10")
	public Entity newSpell10(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.SPELL)
				.viewFromTexture("spells.png")
				.build();
	}
	@Spawns("description")
	public Entity newDescription(SpawnData data) {
		return Entities.builder()
				.from(data)
				.type(EntityType.DESCRIPTION)
				.viewFromTexture("describorder.png")
				.build();
	}
	
}