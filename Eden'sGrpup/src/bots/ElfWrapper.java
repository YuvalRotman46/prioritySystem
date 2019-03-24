package bots;
import elf_kingdom.*;


 /**
  * 
  * This class is actually elf wrapper.
  *	It basically created for managing the elf activity and avoiding exception.
  * 
  * @author yuval
  */
public class ElfWrapper {
	public final Elf elf;
	
	public Roles role;
	
	private int elfOldHealth, elfCurrentHealth;
	
	public ElfWrapper(Elf elf) {
		this.elf = elf;
		role = Roles.NONE;
		elfCurrentHealth = elfOldHealth = elf.currentHealth;
	}
	
	public void updateTurn() {
		if(elf.isAlive()) {
		
		/*if(elf.currentHealth == elf.maxHealth)
			elfCurrentHealth = elf.currentHealth;*/
		
		elfOldHealth = elfCurrentHealth;
		elfCurrentHealth = elf.currentHealth;
		}
		else
			elfCurrentHealth = 0; // for case he dead.	
	}
	
	public int getSpeed() {
		int elfSpeed = 0;
		// we adding now the speeds
		elfSpeed += elf.maxSpeed;
		if(this.isSpeedUp()) elfSpeed *= 3;// if the elf has the speedUp spoken.
		
		return elfSpeed;
	}
	
	
	/*
	 * need to check implemetaion
	 */
	
	public boolean isSpeedUp() {
		for(Spell s :elf.currentSpells) {
			if(s.toString().equals("SpeedUp")) return true;
		}
		
		return false;
	}
	
	
	public boolean isInvisiable() {
		for(Spell s :elf.currentSpells) {
			if(s.toString().equals("Invisibility")) return true;
		}
		
		return false;
	}
	
	
	
	public int getDeltaHealth() {
		return elfOldHealth-elfCurrentHealth;
	}

	
	public Roles getRole() {
		return role;
	}

	
	public void setRole(Roles role) {
		this.role = role;
	}

	
	public Elf getElf() {
		return elf;
	}
	
	
}
