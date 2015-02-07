import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class PsychicGecko3 extends Actor {
	ArrayList<Location> occLoc = new ArrayList<Location>(); 
	ArrayList<Location> neighbor;
	ArrayList<Location> newLoc;
	ArrayList <PsychicBean> pb = new ArrayList <PsychicBean>();
	Flower flower = new Flower();
	//boolean done = false;
	public void act(){
		if(getGrid() == null){
			return;
		}
		
//if this is first time entering act method and is for pg1 do the following part of code
	
	  
	  ArrayList<Location> neigh = getGrid().getValidAdjacentLocations(this.getLocation());
	  for(Location a : neigh){
		if(getGrid().get(a) instanceof PsychicGecko3 && !(a.equals(this))){
			return;
		}
	    else{
	    	//move to flower
	    	if(getGrid().get(a) instanceof Flower){
	    		moveTo(a);
	    	}
	    	else{
	    		//if(done == false){
	    		occLoc = getGrid().getOccupiedLocations();//returns all occupied locations on the grid	//should return (0,0), (8,8) and this seems correct
	    		ArrayList <Location> nextBeanLocation = getGrid().getValidAdjacentLocations(this.getLocation());//this will return (0,1), (1,1), (1,0)
	    		int direction = getDirectionToOther(this.getLocation());//returns 135
	    		
	    		//System.out.println(direction);
	    		
	    		ArrayList<Location> newLoc = sortByDirection(this.getLocation(), direction, nextBeanLocation);//[(0,0), 135, (0,1), (1,1), (1,0) ]//should return (1,1)
	    		PsychicBean temp = new PsychicBean();
	    		temp.putSelfInGrid(this.getGrid(), newLoc.get(0));
	    		pb.add(temp);
	    		temp = null;
	    		//getGrid().put(newLoc.get(0), new PsychicBean());//objective is to put bean at (1,1) so this should put it
	    		
	    		//PsychicBean test = new PsychicBean();
	    		//putSelfInGrid(getGrid(),newLoc.get(0));
	    		//done = true;
	    		beanSearch(newLoc.get(0)); //beanSearch(1,1)
	    		//}
	    		
	    	}
	    }
	  }
	  

//This part of code has to work only once the first time and only for pg1 can be a separate method

		/*occLoc = getGrid().getOccupiedLocations();//returns all occupied locations on the grid	//should return (0,0), (8,8) and this seems correct
		ArrayList <Location> nextBeanLocation = getGrid().getEmptyAdjacentLocations(this.getLocation());//this will return (0,1), (1,1), (1,0)
		int direction = getDirectionToOther(this.getLocation());//returns 135
		ArrayList<Location> newLoc = sortByDirection(this.getLocation(), direction, nextBeanLocation);//[(0,0), 135, (0,1), (1,1), (1,0) ]//should return (1,1)
		getGrid().put(newLoc.get(0), new PsychicBean());//objective is to put bean at (1,1) so this should put it


		beanSearch(newLoc.get(0)); //beanSearch(1,1)*/
	}
	

		

	public void beanSearch(Location loc){//loc = 1,1
		if(getGrid().get(loc) instanceof PsychicGecko3 && !(getGrid().get(loc).equals(this))){
			//neighbors pg2 
			//moveTo(loc);
			getGrid().put(loc, new PsychicBean());
			putFlower();
		}
	    else{
	     // i
			ArrayList <Location> nextBeanLocation = getGrid().getValidAdjacentLocations(loc);//should return Empty Adjacent locations of (1,1)
			int direction = getDirectionToOther(this.getLocation());//returns 135
			ArrayList<Location> newLoc = sortByDirection(this.getLocation(), direction, nextBeanLocation);//[(1,1), 135, (0,1), (1,1), (1,0) ]//should return (2,2)
			//getGrid().put(newLoc.get(0), new PsychicBean());//objective is to put bean at (2,2) so this should put bean at 2,2
		   PsychicBean temp = new PsychicBean();
  		   temp.putSelfInGrid(this.getGrid(), newLoc.get(0));
  		   pb.add(temp);
  		   temp = null;
			beanSearch(newLoc.get(0));//beanSearch(2,2)
	    }
	}	

	public void putFlower(){
  // public ArrayList <Location> flowerLoc = new ArrayList<Location>();
   //find all locations occupied by beans
   ArrayList<Location> beanLoc = getGrid().getOccupiedLocations();
   for(Location l : beanLoc){
	   if(getGrid().get(l) instanceof PsychicBean){
		   getGrid().remove(l);
		   //getGrid().put(l, new Flower());
		   flower.putSelfInGrid(getGrid(), l);
		   
	   }
   /*remove beans
   put flowers on each of these locations*/			
}					


		
		
		/*ArrayList<Location> neighbor = getGrid().getOccupiedAdjacentLocations(this.getLocation());//will return nothing in the first round as 0,0 and 8,8 do not have any neighbors
		ArrayList<Location> newLoc = sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),neighbor);//this should work for pg1 only//objective is to get (1,1)
		beanSearch(newLoc.get(0));//this should work only for pg1 and not for pg2 otherwise it is going into a loop//how do you ensure get(0)=pg1//objective is to have parameter as (1,1)*/
		
	}
	/*public void beanSearch(ArrayList<Location> loc){
	  for(Location l : loc){
		if(getGrid().get(l) instanceof PsychicGecko2 && !(getGrid().get(loc).equals(this))){
			/*Location m = new Location(l.getRow()-1, l.getCol()-1);
			moveTo(m);
			return;
		}
		if(l == null){// location (1,1) is not null but is empty so need to change condition 
			getGrid().put(l, new PsychicBean());//objective is to put bean at (1,1) so this should put it
			occLoc.remove(l);//???? what is the objective of this part of code
			sortByDirection(this.getLocation(),getDirectionToOther(this.getLocation()),occLoc);//
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
			//System.out.println(l.toString());
			if(getGrid().get(l) instanceof PsychicGecko3 && !(getGrid().get(l).equals(this))){
				return loc.getDirectionToward(l);
				
			}
		}
		return -1;
	}
	private ArrayList<Location> sortByDirection(Location origin, int direction, ArrayList<Location> unsorted){
		//ArrayList<Location> decidingFactor = new ArrayList<Location>();
		//int[] decidingFactor = new int[unsorted.size()];
		/*for(int x = 0; x < unsorted.size(); x++){
			decidingFactor[x] = unsorted.get(x);
		}*/
		for(int i = 0; i < unsorted.size(); i++){	
			int iNewDirection = Math.abs(direction - origin.getDirectionToward(unsorted.get(i)));
			for(int j = i+1; j < unsorted.size(); j++){
				int jNewDirection = Math.abs(direction - origin.getDirectionToward(unsorted.get(j)));
				if(iNewDirection > jNewDirection){
					//int temp = iNewDirection;							//	    int temp = 45   
					//iNewDirection = jNewDirection;					// 	    get(0) = 0
					//jNewDirection = temp;
					/*decidingFactor[i] = jNewDirection;
					decidingFactor[j] = iNewDirection;*/
				unsorted.add(i,unsorted.remove(j));
				//unsorted.remove(unsorted.get(j));
				unsorted.add(j,unsorted.get(i+1));
					
				}
			}
		}
		
		return unsorted;
	}
	/*public void beanSearch(Location loc){
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
	}*/
	/*public ArrayList<Location> sortByDirection(Location origin, int direction, ArrayList<Location> unsorted)
	{
		ArrayList <Location> sorted = new ArrayList<Location>(); //creates the ArrayList for the sorted locations
		
		for(Location test : unsorted)	//for each unsorted location
		{
			int testDirect = test.getDirectionToward(origin); //testDirect is the location's direction towards the origin
			if(sorted.isEmpty()) //if sorted is empty
			{
				sorted.add(test);	//add the test location
			}
			else		//if sorted is not empty
			{
				boolean added = false;		//specify that the element has not been added to the arrayList
				for(Location check : sorted)		//for each element in the sorted ArrayList
				{
					int checkDirect = check.getDirectionToward(origin);		//checkDirect is the location's direction towards the origin
					int index = sorted.indexOf(check);		//index is the location of check w/in the arrayList
					if (testDirect < checkDirect)		//if the new location's direction is smaller than check's direction
					{
						sorted.add(index, test);		//add the new location and push the other locations back one
						added = true;					//specify that the location has been added
						break;							//get out of the for loop
					}
				}
				if(!added)								//if the new location was never added to the arrayList
				{
					sorted.add(test);					//add it to the end
				}
			}
		}
		return sorted;									//return the sorted ArrayList
	}*/
}

