package capacites;

import static com.almasb.fxgl.app.DSLKt.random;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;

import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import jeu.EntityType;

public class ParticlesEntity implements EntityFactory{

	@Spawns("FireParticle")
	public Entity fireBallParticle(SpawnData data) {
		
        ParticleEmitter emitter = ParticleEmitters.newFireEmitter(50);
        
        emitter.setBlendMode(BlendMode.ADD);
        emitter.setSize(10, 20);
        emitter.setEmissionRate(1);
        emitter.setStartColor(Color.YELLOW);
        emitter.setStartColor(Color.RED);

		return Entities.builder()
				.from(data)
				.type(EntityType.PARTICLE)
                .with(new ParticleComponent(emitter))
				.build();
	}
	
	@Spawns("AttackParticle")
	public Entity attackParticle(SpawnData data) {
		
        ParticleEmitter emitter = ParticleEmitters.newExplosionEmitter(20);
       
        emitter.setColor(Color.RED);

		return Entities.builder()
				.from(data)
				.type(EntityType.PARTICLE)
                .with(new ParticleComponent(emitter))
				.build();
	}
	
	@Spawns("HealParticle")
	public Entity healParticle(SpawnData data) {
		
        ParticleEmitter emitter = ParticleEmitters.newSmokeEmitter();
        
        emitter.setSize(1, 10);
        emitter.setColor(Color.LIMEGREEN.deriveColor(1, 1, 1, .5).brighter());
        emitter.setEndColor(Color.LIMEGREEN.deriveColor(1, 1, 1, .5).brighter().brighter().brighter());
        emitter.setEmissionRate(1);
        emitter.setSpawnPointFunction(i -> Point2D.ZERO.add(random(-15, 30), random(-20, 10)));

		return Entities.builder()
				.from(data)
				.type(EntityType.PARTICLE)
                .with(new ParticleComponent(emitter))
				.build();
	}
	
	@Spawns("BuffParticle")
	public Entity buffParticle(SpawnData data) {
		
        ParticleEmitter emitter = ParticleEmitters.newSmokeEmitter();
   
        emitter.setSize(1, 10);
        emitter.setColor(Color.CORNFLOWERBLUE.deriveColor(1, 1, 1, .5).brighter());
        emitter.setEndColor(Color.CORNFLOWERBLUE.deriveColor(1, 1, 1, .5).brighter().brighter().brighter());
        emitter.setEmissionRate(1);
        emitter.setSpawnPointFunction(i -> Point2D.ZERO.add(random(-15, 30), random(-20, 10)));

		return Entities.builder()
				.from(data)
				.type(EntityType.PARTICLE)
                .with(new ParticleComponent(emitter))
				.build();
	}
}
