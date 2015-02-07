import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class MagneticChameleon extends Critter{
	//processActors goes through list of neighbors of current object and checks to see if any of them are magnetic chameleons
	public void processActors(ArrayList<Actor> actors){		
				ArrayList<Actor> mcNeigh = super.getActors(); //mcNeigh is the array list of neighboring actors
				//for loop goes through each of the neighbors and checks if they are magnetic chameleons
				for(int i = 0; i < mcNeigh.size(); i++){
					if(mcNeigh.get(i) instanceof MagneticChameleon){
						changeColor(mcNeigh.get(i));	//if neighbor is also a magnetic chameleon, neighbor changes color
					}
				}
				
			
		
    }
	//changeColor modifies the rgb values of the current object and its magnetic chameleon neighbor to be halfway between current object's old color and neighbor's old color
	public void changeColor(Actor a){  	 //for example:
		Color c = getColor();			 //c = YELLOW
		int red1 = c.getRed();			 //red1 = red value of YELLOW
		int green1 = c.getGreen();		 //green1 = green value of YELLOW
		int blue1 = c.getBlue();		 //blue1 = blue value of YELLOW
		Color c2 = a.getColor();		 //c2 = BLACK
		int red2 = c2.getRed();			 //red2 = red value of BLACK
		int green2 = c2.getGreen();		 //green2 = green value of BLACK
		int blue2  = c2.getBlue();		 //blue2 = blue value of BLACK
		
		int redAvg = (red1 + red2)/2;	//redAvg is the average of the red values of the current object and its magnetic chameleon neighbor
		int greenAvg = (green1 + green2)/2;  //greenAvg is the average of the green values of the current object and its magnetic chameleon neighbor
		int blueAvg = (blue1 + blue2)/2;   //blueAvg is the average of the blue values of the current object and its magnetic chameleon neighbor
		
		Color afterCol = new Color(redAvg,greenAvg,blueAvg);   //afterCol is the color halfway between  current object's old color and neighbor's old color
		setColor(afterCol);   //set afterCol as current object and its magnetic chameleon neighbor's new color
	}
	//if provided with a valid location, makeMove() will move one magnetic chameleon to another magnetic chameleon's neighboring location and make sure they face the same direction
	public void makeMove(Location loc){
		//if there are no more valid locations to move to, current object removes itself from the grid 
		if(loc == null){
			removeSelfFromGrid();
		}
		else{
			//System.out.println(getLocation().getDirectionToward(loc));
			setDirection(getLocation().getDirectionToward(loc));
			//super.makeMove(loc);
			moveTo(loc);  //move to location provided by selectMoveLocation()
			ArrayList<Actor> mcNeigh = super.getActors(); //mcNeigh is an array list of neighboring actors
			//for loop goes through each neighboring actor
			for(int i = 0; i < mcNeigh.size(); i++){
				//if a neighboring actor is a magnetic chameleon, it doesn't move, only changes its direction to match the other magnetic chameleon
				if(mcNeigh.get(i) instanceof MagneticChameleon){	
					setDirection(mcNeigh.get(i).getDirection());
				}
			}
		  
		}
	}
	
	//selectMoveLocation() chooses a location to move to among the locations provided by getMoveLocations()
		public Location selectMoveLocation(ArrayList<Location> moveLocs){
			Location colleague;		//colleague is another magnetic chameleon in the grid
			ArrayList<Location> mcLocs = getGrid().getOccupiedLocations(); //mcLocs is the list of occupied locations in the grid
			mcLocs.remove(getLocation()); //we remove the current object so as to prevent comparisons with self
			/*for(Location l : mcLocs){
				System.out.println(mcLocs.toString());
			}*/
			ArrayList<Location> neighLoc = new ArrayList<Location>(); //neighboring locations available for moving
			neighLoc = super.getMoveLocations();
			/*for(Location l : neighLoc){
				System.out.println(neighLoc.toString());
			}*/
			//for loop goes through list of occupied locations on the grid and checks which ones contain magnetic chameleons
			//System.out.println(mcLocs.size());
			for(int x = 0; x < mcLocs.size(); x++){
				//System.out.println(mcLocs.get(x));
				if(getGrid().get(mcLocs.get(x)) instanceof MagneticChameleon){
						//System.out.println(mcLocs.get(x));
						ArrayList<Actor> neighbors = new ArrayList<Actor>();  //neighbors is an array list of actors that are neighbors to the current object
						neighbors = getGrid().getNeighbors(getLocation());
						//for loop goes through each neighbor and checks if any are magnetic chameleons
						for(int b = 0; b < neighbors.size(); b++){
							//if any of the neighbors is a magnetic chameleon, no more moves
							if(neighbors.get(b) instanceof MagneticChameleon){
								return getLocation();
							} //end if
							
							
						} //end for
								
					
					colleague = mcLocs.get(x); //colleague is set to the occupied location of the other magnetic chameleon
					double iniDistance = Math.sqrt(Math.pow(colleague.getRow() - neighLoc.get(0).getRow(),2) + Math.pow(colleague.getCol() - neighLoc.get(0).getCol(),2));; //initDistance initialized to the distance between the first neighboring location and another magnetic chameleon
					int leastEl = 0; //leastEl is the initial element for which iniDistance is calculated
					//for loop goes through all the valid locations for moving to find the closest to the other magnetic chameleon
					for(int k = 1; k < neighLoc.size(); k++){
						double distance = Math.sqrt(Math.pow(colleague.getRow() - neighLoc.get(k).getRow(),2) + Math.pow(colleague.getCol() - neighLoc.get(k).getCol(),2)); //distance is the distance between each valid location and the other magnetic chameleon
						//compare distance and iniDistance to keep track of shortest distance
						if(distance < iniDistance){
							iniDistance = distance;	//if distance is less than iniDistance, iniDistance is set to distance and leastEl is set to k
							leastEl = k; //element number of nearestlocation is recorded
						} //end of if(distance < iniDistance)
						
					} // end of for(int k = 1; k < neighLoc.size(); k++)
					//System.out.println("leastEl: " + leastEl);
					//if(conditions checks whether direction of moveTo() is same as direction of chameleon
					if(getLocation().getDirectionToward(neighLoc.get(leastEl)) == getDirection()){
						return neighLoc.get(leastEl);
					}
					else{
					//for loop to resolve issue of tie between two two squares
					for(int k = 0; k < neighLoc.size(); k++){
						//if loop ignores the element with minimum distance outputted by previous for loop
						if( k != leastEl){
						double distance = Math.sqrt(Math.pow(colleague.getRow() - neighLoc.get(k).getRow(),2) + Math.pow(colleague.getCol() - neighLoc.get(k).getCol(),2)); //distance is the distance between each valid location and the other magnetic chameleon
						//compare distance and iniDistance to see if they are equal (meaning there is a tie)
						if(distance == iniDistance){
							leastEl = k; //leastEl is set to k 
							Location iniLocation = neighLoc.get(leastEl);
							int dir = getLocation().getDirectionToward(iniLocation);
							//System.out.println("iniLocation: " + iniLocation + " " + "dir: " + dir);
							/*if(dir == 0){
								return 
							}*/
							
							
						} //end of (distance == iniDistance)
						
						}//end of (k != leastEl)
						
					} // end of for(int k = 1; k < neighLoc.size(); k++)
					
					
					return neighLoc.get(leastEl); //returns location of the neighboring location that is closest to another chameleon
				}
				//} // end of else(neighLoc.get(a) == mcLocs.get(x))
			//} // end of for(int a = 0; a < neighLoc.size(); a++)
		} // end of if(getGrid().get(mcLocs.get(x)) instanceof MagneticChameleon)
		//else{
		//			return getLocation(); //returning getLocation() in case there is no other magnetic chameleon on the grid and no movement is required
		} //end of else(getGrid().get(mcLocs.get(x)) instanceof MagneticChameleon)
	  //}//end of (int x = 0; x < mcLocs.size(); x++)
			return getLocation();
			
		} // end of selectMove() method
	} // end of MagneticChameleon class