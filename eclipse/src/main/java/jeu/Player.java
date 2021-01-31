package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import capacites.Capacites;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import personnages.playerControlled.Personnages;

public class Player extends Component {
	private PositionComponent position;
	private String name;
	private Personnages HeroClass;
	private Capacites activeSkill;
	//private Pathfinding path = new Pathfinding();
	
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;
    private int speed = 0;
    private String dir = null;
    private double basePos = -100;

	
	
	public Player(Personnages heroClass) {
		super();
		setHeroClass(heroClass);
		
		
        animIdle = new AnimationChannel("archer.png", 4, 32, 42, Duration.seconds(1), 1, 1);
        animWalk = new AnimationChannel("archer.png", 4, 32, 42, Duration.seconds(1), 0, 3);

        texture = new AnimatedTexture(animIdle);
	}
	public Personnages getHeroClass() {
		return HeroClass;
	}
	public void setHeroClass(Personnages heroClass) {
		HeroClass = heroClass;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public PositionComponent getPosition() {
		return position;
	}

	public void setPosition(PositionComponent position) {
		this.position = position;
	}

	public void move(Point2D direction, GameWorld gameworld) {

		int posX = (int) position.getX();
		int posY = (int) position.getY();

		int casePlayerX = (int) (posX / 60);
		int casePlayerY = (int) (posY / 60);

		new Click();
		int tab[] = Click.cases(((int) direction.getX()), ((int) direction.getY()));
		Deplacement move = new Deplacement();
		move.calculateCross(HeroClass.getMovePoint(), casePlayerX, casePlayerY);
		move.calculateDiag(HeroClass.getMovePoint(), casePlayerX, casePlayerY);
		List<SimpleEntry<Integer, Integer>> list = move.list;
		SimpleEntry<Integer, Integer> vars = new SimpleEntry<Integer, Integer>(tab[0], tab[1]);
		List<Entity> entities = gameworld.getEntitiesAt(new Point2D(tab[2], tab[3]));
		List<SimpleEntry<Integer, Integer>> pathf = Pathfinding.distMethod(casePlayerX, casePlayerY, tab[0], tab[1]);
		if (list.contains(vars) && HeroClass.isDidMove() == false && entities.isEmpty() && pathf != null && pathf.size() - 1 <= HeroClass.getMovePoint()) {
			
			position.translateX(tab[2] - position.getX());
			position.translateY(tab[3] - position.getY());
			posX = (int) position.getX();
			posY = (int) position.getY();
			HeroClass.setDidMove(true);
		}
	}
	
//	@Override
//    public void onAdded() {
//        entity.setView(texture);
//    }

    @Override
    public void onUpdate(double tpf) {
    		
    	if(dir != null) {
    	
        	entity.translateX(60 * tpf);
    	    if (texture.getAnimationChannel() == animIdle) {
    	      texture.loopAnimationChannel(animWalk);
    	    }
    	    
        	if(position.getX() == basePos + 60) {
        		
        		basePos = -100;
        		dir = null;
        	}
    	}
    	


        

//        if (speed != 0) {
//
//            if (texture.getAnimationChannel() == animIdle) {
//                texture.loopAnimationChannel(animWalk);
//            }
//
//            speed = (int) (speed * 0.9);
//
//            if (FXGLMath.abs(speed) < 1) {
//                speed = 0;
//                texture.loopAnimationChannel(animIdle);
//            }
//        }
    }

    public void moveRight() {
//        speed = 150;
        dir = "rg";
        basePos = position.getX();
        
        getEntity().setScaleX(1);
    }
    
    public void startMoving() {
    	
    	dir = "rg";
    }
    
    public void stopMoving() {
    	
    	dir = null;
    }
    
	public Capacites getActiveSkill() {
		return activeSkill;
	}
	public void setActiveSkill(Capacites activeSkill) {
		this.activeSkill = activeSkill;
	}
}
