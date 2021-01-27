package capacites;

import java.util.List;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.components.PositionComponent;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import jeu.EntityType;

public class ParticlesHandler {

	private double x;
	private double y;
	private Capacites sort;
	private GameWorld gm;
	public ParticlesHandler(PositionComponent pos, Capacites sort, GameWorld gm) {
		super();
		this.x = pos.getX();
		this.y = pos.getY();
		this.sort = sort;
		this.gm = gm;
	}
	
	public void spawn() {
		
		if(sort.getClass() == Soin.class) gm.spawn("HealParticle", new Point2D(x, y + 30));
		if(sort.getClass() == Soin2.class) gm.spawn("HealParticle", new Point2D(x, y + 30));
		if(sort.getClass() == Estoc.class) gm.spawn("AttackParticle", new Point2D(x, y + 30));
		if(sort.getClass() == Lance.class) gm.spawn("AttackParticle", new Point2D(x, y + 30));
		if(sort.getClass() == Fireball.class) gm.spawn("FireParticle", new Point2D(x, y + 30));
		if(sort.getClass() == PhysicalDamageBuff.class) gm.spawn("BuffParticle", new Point2D(x, y + 30));

		new Thread(new Runnable() {
			
			@Override
			public void run() {
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				Platform.runLater(new Runnable(){
				    public void run() {

				        Entity temp = null;
						List<Entity> partic = gm.getEntities();
						for (Entity t : partic) {
							if (t.getTypeComponent().isType(EntityType.PARTICLE)) {
								temp = t;
							}
						}
						temp.removeFromWorld();
				    }
				});

			}
		}).start();
		
		
//		gm.spawn("FireballParticle", new Point2D(x + 30, y + 30));
//		new Thread() {
//		    @Override
//		    public void run() {
//		        try {
//		            this.sleep(3000);
//			        Entity temp = null;
//					List<Entity> partic = gm.getEntities();
//					for (Entity t : partic) {
//						if (t.getTypeComponent().isType(EntityType.PARTICLE)) {
//							temp = t;
//							//t.removeComponent(ParticleComponent.class);
//						}
//					}
//					temp.removeFromWorld();
//		        } catch (InterruptedException e) {
//		            e.printStackTrace();
//		        }
//
//		    }
//		}.start();
	}
	
	
}
