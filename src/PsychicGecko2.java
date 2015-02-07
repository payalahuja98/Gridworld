import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;


public class PsychicGecko2 extends Actor {
	ArrayList<Location> occLoc = new ArrayList<Location>(); 
	ArrayList<Location> neighbor;
	ArrayList<Location> newLoc;
	public void act(){
		if(getGrid() == null){
			return;
		}
		
		occLoc = getGrid().getOccupiedLocations();	
		ArrayList<Location> neighbor = getGrid().getOccupiedAdjacentLocations(this.getLocation());
		ArrayList<Location> newLoc = sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),neighbor);
		beanSearch(newLoc.get(0));
		
	}
	/*public void beanSearch(ArrayList<Location> loc){
	  for(Location l : loc){
		if(getGrid().get(l) instanceof PsychicGecko2 && !(getGrid().get(loc).equals(this))){
			/*Location m = new Location(l.getRow()-1, l.getCol()-1);
			moveTo(m);
			return;
		}
		if(l == null){
			getGrid().put(l, new PsychicBean());
			occLoc.remove(l);
			sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),occLoc);
			beanSearch(occLoc);
		}
		else{
			if(getGrid().get(l) instanceof PsychicBean){
				getGrid().put(l, new Flower());
				beanSearch(occLoc);
			}
			else{
				if(getGrid().get(l) instanceof Flower){
					moveTo(l);
					beanSearch(occLoc);
				}
			}
		}
	  }
	}*/
	
	private int getDirectionToOther(Location loc){
		for(Location l : occLoc){
			if(getGrid().get(l) instanceof PsychicGecko2){
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
	public void beanSearch(Location loc){
		if(getGrid().get(loc) instanceof PsychicGecko2 && !(getGrid().get(loc).equals(this))){  
			return;
		}
		else{
			if(getGrid().get(loc) instanceof Flower){	//if loc is a flower
				moveTo(loc);							//psychic gecko moves there
				neighbor = getGrid().getOccupiedAdjacentLocations(loc);	//neighbor = neighboring locs of these flowers
				newLoc = sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),neighbor); //newLoc = which location to check next
				beanSearch(newLoc.get(0));	//call beanSearch with that location
			}
			else{
				if(getGrid().get(loc) instanceof PsychicBean){
					getGrid().put(loc, new Flower());
					neighbor = getGrid().getOccupiedAdjacentLocations(loc);
					newLoc = sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),neighbor);
					beanSearch(newLoc.get(0));
				}
				else{
					getGrid().put(loc,new PsychicBean());
					neighbor = getGrid().getOccupiedAdjacentLocations(loc);
					newLoc = sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),neighbor);
					beanSearch(newLoc.get(0));
				}
			}
		}
	}
}

