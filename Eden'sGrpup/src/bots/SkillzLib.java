package bots;
import elf_kingdom.*;


public class SkillzLib {
	
	/*
	 * constractors  : Location(int : y, int : x){
	*/
	
	public static Location copyAndGetLocation(Location location) {
		return new Location(location.row, location.col);
	}
	
	
	public static int howManyObjectsAroundLocation(Game game, Location location, GameObject[] objects, int range) {
    	int count=0;
    	for(GameObject object: objects) {
    		if(object.inRange(location, range))
    			count++;
    	}
    	return count;
    }
    public static Portal returnClosestPortal(Location l, Portal[] stock) {
        int minDist = Integer.MAX_VALUE;
        Portal minPortal = null;
        for(Portal p : stock) {
            if (p.location.distance(l)<minDist) {
                minDist = p.location.distance(l);
                minPortal = p;
            }
        }
        return minPortal;
    }

    public static Portal getClosestPortal(Game game, Castle enemyCastle){
		Portal[] portals = game.getMyPortals();
		Portal closest = null;
		if(portals.length > 0){
			int dist = portals[0].distance(enemyCastle);
			closest = portals[0];
			for(int i = 1; i<game.getMyPortals().length; i++){
				if(portals[i].distance(enemyCastle) < dist){
					dist = portals[i].distance(enemyCastle);
					closest = portals[i];
				}
			}
		}else{
			//System.out.println("NO PORTALS! CRASH NOW");
			
		}
		return closest;
	}
}
