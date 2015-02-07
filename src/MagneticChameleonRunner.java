import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


public class MagneticChameleonRunner {
  public static void main(String[] args){
	  MagneticChameleon cham1 = new MagneticChameleon();
	  MagneticChameleon cham2 = new MagneticChameleon();
	  ActorWorld world = new ActorWorld();
	  world.add(new Location(1,1),cham1);
	  //cham1.setDirection(Location.);
	  world.add(new Location(5,9),cham2);
	  //world.add(new Location(3,3),new Rock());
	  world.add(new Location(7,6),new Rock());
	  world.add(new Location(8,6),new Rock());
	  world.add(new Location(8,8),new Rock());
	  world.add(new Location(7,8),new Rock());
	  world.add(new Location(6,8),new Rock());
	  world.add(new Location(6,7),new Rock());
	  world.add(new Location(3,2),new Rock());
	  cham1.setColor(Color.RED);
	  ArrayList<Actor> a = new ArrayList<Actor>();
	  a.add(cham1);
	  a.add(cham2);
	  
	
	  world.show();
  }

}
