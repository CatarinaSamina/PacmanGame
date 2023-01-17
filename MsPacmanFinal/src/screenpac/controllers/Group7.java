package screenpac.controllers;

import screenpac.model.Node;
import screenpac.model.GameStateInterface;
import screenpac.model.GhostState;
import screenpac.features.NearestPillDistance;
import screenpac.features.NearestPowerPillDistance;
import screenpac.features.Utilities;
import games.pacman.ghost.Ghost;
import screenpac.extract.Constants;

public class Group7 implements AgentInterface, Constants {
	/*
    This simple pill eater just heads for the nearest pill each time
	 */
	NearestPillDistance npd;
	NearestPowerPillDistance ppd;
	GhostState ghostS;
	Ghost gh;
	
	

	public Group7() {
		npd = new NearestPillDistance();
		ppd = new NearestPowerPillDistance();

	}

	public int action(GameStateInterface gs) {
		// choose the adjacent current with the
		// nearest pill
		// check that copying works!
		gs = gs.copy();
		int dir = 0;
		Node current = gs.getPacman().current;
		npd.score(gs, current);
		ppd.score(gs, current);
		GhostState[] ghosts = gs.getGhosts();
		for (int i = 0; i < ghosts.length; i++) {
            if(Utilities.manhattan(ghosts[i].current, current) < 2 && !gs.getGhosts()[i].edible()){
            	System.out.println(Utilities.manhattan(ghosts[i].current, current) + "Distancia curta");
            	Node next1 = Utilities.getClosest(current.adj, ppd.closest, gs.getMaze());
            	dir = Utilities.getWrappedDirection(current, next1, gs.getMaze());
            	System.out.println("primeiro "+dir);
            	return dir%=2;

            }	
            else if(Utilities.manhattan(ghosts[i].current, current) < 2 && gs.getGhosts()[i].edible()){
            	Node next = Utilities.getClosest(current.adj, npd.closest, gs.getMaze());
            	return Utilities.getWrappedDirection(current, next, gs.getMaze());
            	//System.out.println("segundo " + dir);
            } 
            	
            else {
            	Node next = Utilities.getClosest(current.adj, npd.closest, gs.getMaze());
            	dir = Utilities.getWrappedDirection(current, next, gs.getMaze());
             	System.out.println("terceiro " + dir);
            	}
		}
		System.out.println( gs.getGhosts()[0].edible());
		return dir;
	}
	
	public int reverse(int dir) {
		if(dir == 0)
			return 2;
		else if(dir == 1)
			return 3;
		else if(dir == 2)
			return 0;
		else
			return 1;
	}
	
	
}