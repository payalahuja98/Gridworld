import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


public class PGRunner {
	 public static void main(String[] args){
		PsychicGecko4 cham1 = new PsychicGecko4();
		  PsychicGecko4 cham2 = new PsychicGecko4();
		  ActorWorld world = new ActorWorld();
		  world.add(new Location(1,1),cham1);
		  //cham1.setDirection(Location.);
		  world.add(new Location(8,8),cham2);
		  world.add(new Location(1,4),new Rock());
		  world.add(new Location(2,4),new Rock());
		  //world.add(new Location(3,4),new Rock());
		  //world.add(new Location(8,8),new Rock());
		  /*world.add(new Location(4,4),new Rock());
		  world.add(new Location(5,4),new Rock());
		  world.add(new Location(6,4),new Rock());
		  world.add(new Location(7,4),new Rock());*/
		  //cham1.setColor(Color.RED);
		  ArrayList<Actor> a = new ArrayList<Actor>();
		  a.add(cham1);
		  a.add(cham2);
		  
		
		  world.show();
	 }
}
