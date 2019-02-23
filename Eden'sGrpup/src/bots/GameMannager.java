package bots;
import java.util.*;
import elf_kingdom.*;


public class GameMannager {
	
	public final Game game;
	
	private final ArrayList<ElfWrapper> myElfs; // immutable Elf are exist for all over the game. In some cases they can die.
	private final ArrayList<ElfWrapper> enemyElfs;
	
	/**
	 * Reload references for reload objects.
	 * Changes for each turn.
	 */
	private ArrayList<PortalWrapper> myExistPortals = null;
	
	private ArrayList<PortalWrapper> enemyExistPortals = null;
	
	/*
	 * old game members
	 */
	public int myOldCastelHealth, myCurrentCastelHealth, enemyCurrentCastelHealth, enemyOldCastelHealth;
	
	public GameMannager(Game game) {
		this.game = game;
		
		myCurrentCastelHealth = myOldCastelHealth = game.getMyCastle().currentHealth;
		enemyCurrentCastelHealth = enemyOldCastelHealth = game.getEnemyCastle().currentHealth;
		
		this.myElfs = new ArrayList<>();
		for (Elf e : game.getAllMyElves()) {
			myElfs.add(new ElfWrapper(e));
		}
		
		this.enemyElfs = new ArrayList<>();
		for(Elf e : game.getAllEnemyElves()) {
			enemyElfs.add(new ElfWrapper(e));
		}
		
		myExistPortals = new ArrayList<>();
		for (Portal p : game.getMyPortals()) {
			myExistPortals.add(new  PortalWrapper(p));
		}
		
		enemyExistPortals = new ArrayList<>();
		for (Portal p : game.getEnemyPortals()) {
			enemyExistPortals.add(new  PortalWrapper(p));
		}
		
		
	}
	
	public void turnUpdate() {
		
		myExistPortals.clear();
		for (Portal p : game.getMyPortals()) {
			myExistPortals.add(new  PortalWrapper(p));
		}
		
		enemyExistPortals.clear();
		for (Portal p : game.getEnemyPortals()) {
			enemyExistPortals.add(new  PortalWrapper(p));
		}
		
	}
	
	
}
