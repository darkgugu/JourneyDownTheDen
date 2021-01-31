package jeu;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

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
	
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;
    private String dir = null;
    private List<SimpleEntry<Integer, Integer>> path = null;

	
	
	public Player(Personnages heroClass) {
		super();
		setHeroClass(heroClass);
		
		
        animIdle = new AnimationChannel("archer_walk.png", 10, 60, 60, Duration.seconds(1), 1, 1);
        animWalk = new AnimationChannel("archer_walk.png", 10, 60, 60, Duration.seconds(1), 0, 9);
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
		if (list.contains(vars) && HeroClass.isDidMove() == false && entities.isEmpty()) {
			
			path = Pathfinding.distMethod(casePlayerX, casePlayerY, tab[0], tab[1]);

			if(path != null && path.size() - 1 <= HeroClass.getMovePoint()) {
			
//				position.translateX(tab[2] - position.getX());
//				position.translateY(tab[3] - position.getY());
//				posX = (int) position.getX();
//				posY = (int) position.getY();
				HeroClass.setDidMove(true);
			}
			
		}
	}
	
	@Override
    public void onAdded() {
        entity.setView(texture);
    }

    @Override
    public void onUpdate(double tpf) {
    			
    	if(path != null) {
    		
    		if (path.size() > 1) {
    			
                if (texture.getAnimationChannel() == animIdle) {
                    texture.loopAnimationChannel(animWalk);
                }

    			int x1 = path.get(0).getKey() * 60;
    			int y1 = path.get(0).getValue() * 60;
    			int x2 = path.get(1).getKey() * 60;
    			int y2 = path.get(1).getValue() * 60;
    			
    			if(x2 - x1 != 0) {
    				
    				dir = "x";
    			
    				if(x2 - x1 > 0) {
    					
    					entity.translateX(60 * tpf);
    				}
    				else {
    					entity.translateX(-60 * tpf);
    				}
    			}
    			else {
    				
    				dir = "y";
    				
    				if(y2 - y1 > 0) {
    					
    					entity.translateY(60 * tpf);
    				}
    				else {
    					entity.translateY(-60 * tpf);
    				}
    			}
			}
    		else {
                texture.loopAnimationChannel(animIdle);
    		}
    	}
    	
		if(dir == "x") {
    		
			if(position.getX() == path.get(1).getKey() * 60) {
        		
        		dir = null;
        		if(path.size() > 1) path.remove(0);
        	}
		}
		
		if(dir == "y") {
    		
			if(position.getY() == path.get(1).getValue() * 60) {
        		
        		dir = null;
        		if(path.size() > 1) path.remove(0);
        	}
		}
    }

    public void moveRight() {
//        speed = 150;
        dir = "rg";
        position.getX();
        
        getEntity().setScaleX(1);
    }
    
	public Capacites getActiveSkill() {
		return activeSkill;
	}
	public void setActiveSkill(Capacites activeSkill) {
		this.activeSkill = activeSkill;
	}
}
