/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 * Copyright(c) 2014 Dennis Brylow (MagneticChameleon extensions)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 * @author Dennis Brylow
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a world that contains MagneticChameleon critters. <br />
 * It can run in either graphical or text-based mode
 *  (command-line flag "-g"), and reads a starting grid
 *  with critters and rocks from System.in.
 */  
public class PsychicTestRunner
{
    public static void main(String[] args) throws FileNotFoundException
    {
		int steps = 0;
//		if (args.length != 1)
//		{
//			System.err.println("ERROR: require text input file name!");
//			System.exit(-1);
//		}

//		File file = new File(args[0]);
		PsychicGecko4 pg;
		int x, y;
        ActorWorld world = new ActorWorld();
		ArrayList<PsychicGecko4> pglist = new ArrayList<PsychicGecko4>();

//		try {

		//Scanner sc = new Scanner(System.in);
		java.io.File file = new java.io.File("C:\\Users\\Payal\\workspace\\Gridworld\\src\\Input.txt"); 
		Scanner sc = new Scanner(file);
		Scanner line;
		String s;
		
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			if (s.startsWith("Steps"))
			{
				line = new Scanner(s.substring(s.indexOf(' ')));
				steps = line.nextInt();
			}
			else if (s.startsWith("PsychicGecko"))
			{
				line = new Scanner(s.substring(s.indexOf(' ')));
				x = line.nextInt();
				y = line.nextInt();
				pg = new PsychicGecko4();
				pglist.add(pg);
				world.add(new Location(x, y), pg);
			}
			else if (s.startsWith("Rock"))
			{
				line = new Scanner(s.substring(s.indexOf(' ')));
				x = line.nextInt();
				y = line.nextInt();
				world.add(new Location(x, y), new Rock());
			}
		}
		sc.close();
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		args[0].equals("-g");
		
		if ((args.length == 1) && (args[0].equals("-g")))
		{
			world.show();
		}
		else
		{
			for (int i = 0 ; i < steps; i++)
			{
				//System.out.println(world);
				System.out.println("Step " + i);
				for (PsychicGecko4 p : pglist)
				{
					System.out.println("PsychicGecko @ " + p.getLocation() 
									   + ", bearing " + p.getDirection());
				}
				System.out.println("----------------------------");
				world.step();
			}
			System.out.println("Step " + steps);
			for (PsychicGecko4 p : pglist)
			{
				System.out.println("PsychicGecko @ " + p.getLocation() 
								   + ", bearing " + p.getDirection());
			}
		}
    }
}
