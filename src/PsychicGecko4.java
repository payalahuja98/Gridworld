import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class PsychicGecko4 extends Actor
{
	public int count = 0;
	
	public void act()
	{
		ArrayList <Actor> neighbors = getGrid().getNeighbors(getLocation());
		boolean trigger = false;
		
		testNeighbors:
		for(Actor test : neighbors)
		{	
			if(test instanceof PsychicGecko4)
			{
				trigger = true;
			}
			else if(test instanceof Flower)
			{
				setDirection(test.getDirection());
				moveTo(test.getLocation());
				trigger = true;
			}
		}
		
		if(!trigger)
		{
			Location loc = getLocation();
			ArrayList <Location> locations = sortedList(getGrid().getEmptyAdjacentLocations(loc), desiredDirect(loc), loc);
			
			testLocations:
			for(Location toTest : locations)
			{
				if(beanSearch(toTest))
				{
					System.out.println("break");
					break testLocations;
				}
			}
		}
	}
	
	public boolean beanSearch(Location loc)
	{	System.out.println("search trigger");
	
		ArrayList <Location> locations = sortedList(getGrid().getEmptyAdjacentLocations(loc), desiredDirect(loc), loc);
		
		for(Actor testAct : getGrid().getNeighbors(loc))
		{
				if(testAct != this)
				{
					if(testAct instanceof PsychicGecko4)
					{
						System.out.println("gecko");
						Flower place = new Flower();
						place.putSelfInGrid(getGrid(), loc);
						return true;
					}
					else if(testAct instanceof Flower)
					{
						System.out.println("flower");
						Flower place = new Flower();
						place.putSelfInGrid(getGrid(), loc);
						return true;
					}
				}
		}
		
		beanTest:
		for(Location testLoc : locations)
		{
			System.out.println("bean");
			PsychicBean place = new PsychicBean();
			place.putSelfInGrid(getGrid(), loc);
			
			if(beanSearch(testLoc))
			{
				System.out.println("bean flower");
				Flower replace = new Flower();
				replace.putSelfInGrid(getGrid(), loc);
				return true;
			}
			else
			{
				place.removeSelfFromGrid();
			}
		}
		
		return false;
		
	}
	
	/*public void beanSearch(Location loc) //recursive searching method
	{ 	
		ArrayList <Location> locList = sortedList(getGrid().getValidAdjacentLocations(loc), desiredDirect(loc), loc); //locList is the set of locations to test
		System.out.println(locList);
		
		forLoop:						//names the for loop
		for(Location testLoc : locList) //for each location in the list
		{	
			if(getGrid().get(testLoc) != null)	//if the location contains something
			{
				Actor testAct = getGrid().get(testLoc);	//testAct is the something
				
				if(testAct instanceof PsychicGecko)	//if testAct is a gecko (base case)
				{	System.out.println("trigger");
					Flower place = new Flower();	//create a flower
					getGrid().put(loc, place);		//replace the current bean with the flower
					return;							//return to the beanSearch call location
				}
			}
			else									//if there is nothing in the location
			{
				System.out.println(testLoc);
				PsychicBean place = new PsychicBean();	//create a bean
				System.out.println("hi!");
				getGrid().put(testLoc, place);			//put a bean in testLoc
				beanSearch(testLoc);					//start a search from the bean
				break forLoop;							//when the method returns, exit the for loop.
			}
		}												//if the spot was a rock or a flower, the loop does nothing.
		
		if(getGrid().get(loc) instanceof PsychicBean)	//if there is a bean at loc
		{
			Flower place = new Flower();					//create a flower
			getGrid().put(loc, place);						//replace the current bean with the flower
			return;											//return to the beanSearch call location
		}
	}*/
	
	public int desiredDirect(Location current)
	{
		ArrayList <Location> occupied = getGrid().getOccupiedLocations();
		
		int direct = 0;
		
		locLoop:
		for(Location testLoc : occupied)
		{
			Actor testAct = getGrid().get(testLoc);
			if (testAct instanceof PsychicGecko4 && !testAct.getLocation().equals(getLocation()))
			{
				direct = current.getDirectionToward(testLoc);
				System.out.println(direct);
				break locLoop;
				
			}
		}
		
		return direct;
	}
	
	public ArrayList <Location> sortedList (ArrayList <Location> un, int desiredDirect, Location loc)
	{
		ArrayList <Location> sorted = new ArrayList <Location>();
		ArrayList <Location> unsorted = un;
		
		firstLoop:
		for (Location findIdeal : unsorted)
		{
			int direct = loc.getDirectionToward(findIdeal);
			if(desiredDirect == direct)
			{
				sorted.add(findIdeal);
				unsorted.remove(findIdeal);
				break firstLoop;
			}
		}
		
		for(Location getClose : unsorted)
		{
			boolean trigger = false;
			int unsDiff = Math.abs(loc.getDirectionToward(getClose) - desiredDirect);
			
			thirdLoop:
			for(Location checkEach : sorted)
			{
				int sortDiff = Math.abs(loc.getDirectionToward(checkEach) - desiredDirect);
				
				if(unsDiff < sortDiff)
				{
					int index = sorted.indexOf(checkEach);
					sorted.add(index, getClose);
					trigger = true;
					break thirdLoop;
				}
			}
			
			if(trigger == false)
			{
				sorted.add(getClose);
			}
		}
		
		return sorted;
	}
}
