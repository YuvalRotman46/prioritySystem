package bots;
import java.util.*;


import elf_kingdom.*;

public class SkillzLib {
	
	/*
	 * comments
	 */
	
	/*
	 * constractors  : Location(int : y, int : x){
	 * 
	 */
	
	/*
	 * comments
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
			System.out.println("NO PORTALS! CRASH NOW");
			
		}
		return closest;
	}
    
    
    public static GameObject getClosestEnemy(GameObject obj, Game game){
        //can return null! be careful
        GameObject closest = null;
        int minimumDistance = game.cols;//max distance for which we care for
        if(game.getEnemyCreatures().length != 0){
        // if everything is OK
        closest = game.getEnemyCreatures()[0];//if there are no creatures, stays a null
        for(int i = 0; i<game.getEnemyCreatures().length; i++){
            if(obj.distance(game.getEnemyCreatures()[i]) <= minimumDistance){
                closest = game.getEnemyCreatures()[i];
                minimumDistance = obj.distance(closest);
            }
        }
        }
        
        //checks for all living elves, if there are none might give null
        for(Elf e: game.getEnemyLivingElves()){
            if(obj.distance(e) < minimumDistance){
                closest = e;
                minimumDistance = obj.distance(closest);
            }
        }

        /*returns null when there are no enemies who are distant 
        less than the screen's width
        */
        return closest;

    }
    
    
   
 
 public static ArrayList<Location> getMyMFLocs(Game game){
    	int radius = game.castleSize+ game.portalSize +1;
		Location middle = new Location(game.rows/2, game.cols/2);
		Location myCastle = new Location(game.getMyCastle().location.row, game.getMyCastle().location.col);
		double deltaR = myCastle.row - middle.row;
		double deltaC = myCastle.col - middle.col;
		double theta;
		System.out.println(deltaR);
		System.out.println(deltaC);
		if(deltaC == 0)
			theta = Math.PI/2;
		else
			theta = Math.atan(deltaR/deltaC);
		
		System.out.println(theta);
		if(deltaC > 0)
			theta += Math.PI;
		System.out.println(theta);
		
		System.out.print(radius + game.portalSize);
		ArrayList<Location> locs = new ArrayList<Location>();
		int row1, col1;
		row1 = (int) ((1.3*radius) * Math.sin(theta + Math.PI/5)) + 1;
		col1 = (int) ((1.3*radius) * Math.cos(theta + Math.PI/5)) + 1;
		locs.add(new Location(myCastle.row + row1, myCastle.col + col1));

		row1 = (int) ((1.1*radius) * Math.sin(theta)) + 1;
		col1 = (int) ((1.1*radius) * Math.cos(theta)) + 1;
		locs.add(new Location(myCastle.row + row1, myCastle.col + col1));

		row1 = (int) ((1.3*radius) * Math.sin(theta - Math.PI/5)) + 1;
		col1 = (int) ((1.3*radius) * Math.cos(theta - Math.PI/3)) + 1;
		locs.add(new Location(myCastle.row + row1, myCastle.col + col1));
		return locs;
	}
	
	public static ArrayList<Location> getMyPortalLocs(Game game){
		Location l1, l2;
		int radius = game.castleSize+ game.portalSize+1;
		Location middle = new Location(game.rows/2, game.cols/2);
		Location myCastle = new Location(game.getMyCastle().location.row, game.getMyCastle().location.col);
		int deltaR = myCastle.row - middle.row;
		int deltaC = myCastle.col - middle.col;
		double theta;
		if(deltaC == 0)
			theta = Math.PI/2;
		else
			theta = Math.atan(deltaR/deltaC);
		
		if(deltaC > 0)
			theta += Math.PI;
		Portal p = new Portal();
		ArrayList<Location> locs = new ArrayList<Location>();
		l1 = new Location(myCastle.row + (int) ((1.9*radius)* Math.sin(theta - Math.PI/8) + 1), myCastle.col + (int) ((1.9*radius)* Math.cos(theta + Math.PI/8) + 1));
		l2 = new Location(myCastle.row + (int) ((1.5*radius)* Math.sin(theta - Math.PI/3) + 1), myCastle.col + (int) ((1.5*radius)* Math.cos(theta - Math.PI/5) + 1));
		locs.add(l1);
		locs.add(l2);
		return locs;
	}
 
 
 
 
 
 
 
 
    
	
	public static<T> ArrayList<T> fllipArrayList(ArrayList<T> array){
		ArrayList<T> cop = new ArrayList<>();
		
		for(T t : array) {
			cop.add(t);
		}
		
		ArrayList<T> arrayL = new ArrayList<>();
		while(!cop.isEmpty()) {
			arrayL.add(cop.remove(cop.size()-1));
		}
		
		return arrayL;
	}
	
	
	public static boolean isEnemyElfCloseToCastle(Game game, Elf[] elves) {
        for(Elf elf:elves) {
            if(elf.inRange(game.getMyCastle(), 2000))
                return true;
        }
        return false;
    }
	
	public static void elfScout(Game game, Elf elf) {
		if(elf.isAlive()) {
			if(isInDanger(elf,game)) {
				elf.moveTo(game.getMyCastle());
			}else {
				if(isEnemyElfCloseToCastle(game, game.getEnemyLivingElves())) {
					if(elf.inAttackRange(getClosestObject(game, elf, game.getEnemyLivingElves())))
						elf.attack(getClosestObject(game, elf, game.getEnemyLivingElves()));
					else {
						elf.moveTo(getClosestObject(game, elf, game.getEnemyLivingElves()));
					}
				}else if(elf.inAttackRange(getClosestEnemy(elf, game))) {
						elf.attack(getClosestEnemy(elf, game));
				}else {
					Building min = null;
					if(getClosestObject(game, game.getMyCastle(), game.getEnemyPortals())!=null && getClosestObject(game, game.getMyCastle(), game.getEnemyPortals()).inRange(game.getMyCastle(), 2000)) {
						min = (Building)getClosestObject(game, game.getMyCastle(), game.getEnemyPortals());
					}else if(getClosestObject(game, game.getMyCastle(), game.getEnemyManaFountains())!=null && getClosestObject(game, game.getMyCastle(), game.getEnemyManaFountains()).inRange(game.getMyCastle(), 2000)) {
						min = (Building)getClosestObject(game, game.getMyCastle(), game.getEnemyPortals());
					}else {
						Location l1 = new Location(0,0);
						Location l2 = new Location(game.rows-1,game.cols-1);
						int minDistance = l1.distance(l2);
						for(Building b:game.getEnemyPortals()) { 
							if(minDistance>elf.distance(b)) {
								min=b;
								minDistance=elf.distance(b);
							}
						}
						for(Building b:game.getEnemyManaFountains()) {
							if(minDistance>elf.distance(b)) {
								min=b;
								minDistance=elf.distance(b);
							}
						}
					}
					if(min==null) {
						elf.moveTo(game.getMyCastle());
					}else {
						if(elf.inAttackRange(min))
							elf.attack(min);
						else
							elf.moveTo(min);
					}
				}
			}
		}
	}
	
	
	
	public static ArrayList<GameObject> getElfsAndIceTrolls(Game game){
		ArrayList<GameObject> charot = new ArrayList<>();
		for(Elf e: game.getEnemyLivingElves()) {
			charot.add(e);
		}
		for(IceTroll i : game.getEnemyIceTrolls()) {
			charot.add(i);
		}
		
		return charot;
	}
	
	
	
	
	public static boolean isInDanger(Elf e, Game g){
        int AttackRange3 = howManyObjectsAroundLocation(g, e.getLocation(), g.getEnemyLivingElves(), 3*g.elfAttackRange) + howManyObjectsAroundLocation(g, e.getLocation(), g.getEnemyLivingElves(), 3*g.elfAttackRange);
        int AttackRange2 = howManyObjectsAroundLocation(g, e.getLocation(), g.getEnemyLivingElves(), 3*g.elfAttackRange) + howManyObjectsAroundLocation(g, e.getLocation(), g.getEnemyLivingElves(), 3*g.elfAttackRange);
        int AttackRange = howManyObjectsAroundLocation(g, e.getLocation(), g.getEnemyLivingElves(), g.elfAttackRange) + howManyObjectsAroundLocation(g, e.getLocation(), g.getEnemyLivingElves(), g.elfAttackRange);
        
        if(AttackRange3>=4 || AttackRange2>=3||AttackRange>=1)
        	return true;
        
        return false;
    }
	
	
	public static boolean defendBunker(Game game, Portal p){
        //gets portal p for check
        /*this function returns false if summoning an ice troll is not necessary 
        or impossible, returns true if executed correctly and summoned ice troll*/
        GameObject closest = getClosestEnemy(p, game);
        if(closest == null){
            //no enemies, or enemies further from screen's width distance
            return false;
        }

        int life = 0;
        int maxLife = 0;
        if(closest instanceof Elf){
            life = ((Elf) closest).currentHealth;
            maxLife = ((Elf) closest).maxHealth;
        }else if(closest instanceof LavaGiant){
            life = ((LavaGiant) closest).currentHealth;
            maxLife = ((LavaGiant) closest).maxHealth;
        }else{
            life = ((IceTroll) closest).currentHealth;
            maxLife = ((IceTroll) closest).maxHealth;
        }

        if((closest.distance(game.getMyCastle())<4*game.elfAttackRange||closest.distance(p) < 4*game.elfAttackRange) && life > maxLife / 3){
            if(p.canSummonIceTroll()){
                p.summonIceTroll();
            }else{
                //could'nt summon ice troll for defense, return false
                return false;
            }
        }

        //executed correctly, summoned ice troll 
        return true;

    }
	
	public static void allOutMode(Game game, Elf elf){
        Castle enemyCastle = game.getEnemyCastle();
        int distanceFromCenter = new Location(game.cols/2, game.rows/2).distance(enemyCastle);
        if(elf.distance(enemyCastle) <= 0.5*distanceFromCenter && elf.canBuildPortal()){
            elf.buildPortal();
            return;
        }
        if(elf.distance(enemyCastle) <= distanceFromCenter){
            if(!elf.canCastSpeedUp() && game.getMyMana() > 110){
                elf.castSpeedUp();
                return;
            }
            if(!elf.invisible && elf.canCastInvisibility()){
                elf.castInvisibility();
                return;
            }

        }
        if(elf.inAttackRange(enemyCastle)){
            elf.attack(enemyCastle);
            return;
        }
        elf.moveTo(enemyCastle);

    }
	
	public static<T> ArrayList<T> convertArray2List(T[] array){
		ArrayList<T> ret = new  ArrayList<>();
		for(T t : array) {
			ret.add(t);
		}
		return ret;
	}
	
	public static ArrayList<Portal> getPortalsInRange(ArrayList<Portal> stock, Location loc, int range){
		ArrayList<Portal> newArray = new ArrayList<>();
		for(Portal p : stock) {
			if(p.inRange(loc, range)) {
				newArray.add(p);
			}
		}
		
		return newArray;
	}
	
	public static int getHalfOfAlachson(Game game) {
		int need2BeShores = (game.cols*game.cols) + (game.rows*game.rows);
		int alacson = (int) Math.sqrt(need2BeShores);
		return alacson/2;
	}

	public static GameObject getClosestObject(Game game,GameObject obj,GameObject[] objects) {
        // Can return null!!!!!
        int minDist = new Location(0,0).distance(new Location(game.rows-1,game.cols-1));
        GameObject closest = null;
        for(GameObject o:objects) {
            if(o.distance(obj)<minDist) {
                minDist=o.distance(obj);
                closest = o;
            }
        }
        return closest;
    }
	
	public static MapObject getClosestMapObject(Game game,MapObject obj,MapObject[] objects) {
        // Can return null!!!!!
        int minDist = new Location(0,0).distance(new Location(game.rows-1,game.cols-1));
        MapObject closest = null;
        for(MapObject o:objects) {
            if(o.distance(obj)<minDist) {
                minDist=o.distance(obj);
                closest = o;
            }
        }
        return closest;
    }
	
	public static ArrayList<Building> getBuildingsInRange(Game game,Location loc,int range) {
		/**
		 * if there is a building in range from loc so it will return the building, if not it returns null.
		 * SAFETY : it could return null;
		 */
		
		ArrayList<Building> buildings = new ArrayList<>();
		
		for (Building building : game.getAllBuildings()) {
			if (building.getLocation().inRange(loc, range)) {
				buildings.add(building);
			}
		}
		if(! buildings.isEmpty()) return buildings;
		else return buildings;
	}
	
	
	
	public static boolean isAnyBuildingInRange(Game game, Location loc, int range) {
		ArrayList<Building> buildingsList = getBuildingsInRange(game, loc, range);
		return !buildingsList.isEmpty();
	}
	
	
	public static Building getBuilding(Game game, Location location) {
		/**
		 * if there is a building in the current location it's return reference while else returns null;
		 */
		for(Building building : game.getAllBuildings()) {
			if (building.getLocation().equals(location)) {
				return building;
			}
		}
		return null;
	}
	
	public static boolean isThereMyBuilding(Game game,Location location) {
		Building b = getBuilding(game, location);
		if(b == null || b.owner.equals(game.getMyself()) == false) return false;
		else return true;
	}
	
	
	
}
