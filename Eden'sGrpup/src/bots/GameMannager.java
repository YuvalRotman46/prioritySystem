package bots;
import java.util.*;
import elf_kingdom.*;


public class GameMannager {
	
	public final Game game;
	
	private final ArrayList<ElfWrapper> myElfs; // immutable Elf are exist for all over the game. In some cases they can die.
	private final ArrayList<ElfWrapper> enemyElfs;// immutable Elf are exist for all over the game. In some cases they can die.
	
	/**
	 * Reload references for reload objects.
	 * Changes for each turn.
	 */
	private ArrayList<ElfWrapper> myLivingElfs;
	
	private ArrayList<ElfWrapper> enemyLivingElfs;
	
	private ArrayList<PortalWrapper> myExistPortals;
	
	private ArrayList<PortalWrapper> enemyExistPortals;
	
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
		
		this.myLivingElfs = new ArrayList<>();
		for(ElfWrapper elfWrapper: this.myElfs) {
			if(elfWrapper.elf.isAlive())
				myLivingElfs.add(elfWrapper);
		}
		
		this.enemyLivingElfs = new ArrayList<>();
		for(ElfWrapper elfWrapper: enemyElfs) {
			if(elfWrapper.elf.isAlive())
				enemyLivingElfs.add(elfWrapper);
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
	
	/**
	 * Updating the whole values need to be active after creation of game.
	 * 
	 * */
	public void turnUpdate() {
		
		this.myLivingElfs.clear();
		for(ElfWrapper elfWrapper: this.myElfs) {
			elfWrapper.updateTurn();
			if(elfWrapper.elf.isAlive())
				myLivingElfs.add(elfWrapper);
		}
		
		this.enemyLivingElfs.clear();
		for(ElfWrapper elfWrapper: enemyElfs) {
			elfWrapper.updateTurn();
			if(elfWrapper.elf.isAlive())
				enemyLivingElfs.add(elfWrapper);
		}
		
		myExistPortals.clear();
		for (Portal p : game.getMyPortals()) {
			myExistPortals.add(new  PortalWrapper(p));
		}
		
		enemyExistPortals.clear();
		for (Portal p : game.getEnemyPortals()) {
			enemyExistPortals.add(new  PortalWrapper(p));
		}
		
		myOldCastelHealth = myCurrentCastelHealth;
		enemyOldCastelHealth = enemyCurrentCastelHealth;
		myCurrentCastelHealth = game.getMyCastle().currentHealth;
		enemyCurrentCastelHealth = game.getEnemyCastle().currentHealth;
		
	}

	
	
	/*
	 * The all game getters
	 */
	
	public Game getGame() {
		return game;
	}

	public ArrayList<ElfWrapper> getMyElfs() {
		return myElfs;
	}

	public ArrayList<ElfWrapper> getEnemyElfs() {
		return enemyElfs;
	}

	public ArrayList<ElfWrapper> getMyLivingElfs() {
		return myLivingElfs;
	}

	public ArrayList<ElfWrapper> getEnemyLivingElfs() {
		return enemyLivingElfs;
	}

	public ArrayList<PortalWrapper> getEnemyExistPortals() {
		return enemyExistPortals;
	}
	
	
	public ArrayList<PortalWrapper> getMyExistPortals() {
		return myExistPortals;
	}

	public int getMyCastelDeltaHelth() {
		return myOldCastelHealth-enemyOldCastelHealth;
	}
	
	public int getEnemyCastelDeltaHealth() {
		return enemyOldCastelHealth-enemyCurrentCastelHealth;
	}
	
	
}
