import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;


public class PsychicGecko1 extends Actor {
	ArrayList<Location> occLoc = new ArrayList<Location>();
	
	public void act(){
		if(getGrid() == null){
			return;
		}
		occLoc = getGrid().getOccupiedLocations();
		for(Location l : occLoc){
			if(getGrid().get(l) instanceof PsychicGecko1 && !(getGrid().get(l).equals(this))){
				return;
			}
			else{
				if(getGrid().get(l) instanceof Rock){
					act();
				}
				else{
					if(getGrid().get(l) instanceof PsychicBean){
						getGrid().put(l, new Flower());
					}
				  else{
					if(getGrid().get(l) instanceof Flower){
						moveTo(l);
						act();
					}
					else{
						int direction = getDirectionToOther(this.getLocation());
						ArrayList<Location> toMove = getGrid().getValidAdjacentLocations(this.getLocation());
						ArrayList<Location> sorted = sortByDirection(this.getLocation(), direction, toMove);
						getGrid().put(sorted.get(0), new PsychicBean());
						act();
					}
				}
			}
		}
	}
}
	private int getDirectionToOther(Location loc){
		for(Location l : occLoc){
			if(getGrid().get(l) instanceof PsychicGecko1){
				return loc.getDirectionToward(l);
			}
		}
		return -1;
	}
	private ArrayList<Location> sortByDirection(Location origin, int direction, ArrayList<Location> unsorted){
		ArrayList<Location> decidingFactor = new ArrayList<Location>();
		for(int x = 0; x < unsorted.size(); x++){
			decidingFactor.add(unsorted.get(x));
		}
		for(int i = 0; i < decidingFactor.size(); i++){	
			int iNewDirection = Math.abs(direction - origin.getDirectionToward(decidingFactor.get(i)));
			for(int j = i+1; j < decidingFactor.size(); j++){
				int jNewDirection = Math.abs(direction - origin.getDirectionToward(decidingFactor.get(j)));
				if(iNewDirection > jNewDirection){
					int temp = iNewDirection;							//	    int temp = 45   
					iNewDirection = jNewDirection;					// 	    get(0) = 0
					jNewDirection = temp;						//	    get(1) = 45
				}
			}
		}
		return decidingFactor;
	}
}
