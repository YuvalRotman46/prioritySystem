package bots;

import elf_kingdom.Building;
import elf_kingdom.Location;
import elf_kingdom.ManaFountain;

public class AttackBuildingTask implements Taskable {
	private GameMannager game;
	private ElfWrapper elfWrapper;
	private Building enemyBuilding;
	private boolean hadExecute;
	
	public AttackBuildingTask(GameMannager game, ElfWrapper elfWrapper) {
		super();
		this.game = game;
		this.elfWrapper = elfWrapper;
		this.enemyBuilding = SkillzLib.optimalBuildingToAttack(game.game, elfWrapper); 
		// !!!!!!!!!!!!!!!! CAN BE NULL !!!!!!!!!!!!!!!!!!!
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(enemyBuilding!=null) {
			if(elfWrapper != null && elfWrapper.elf != null && elfWrapper.elf.alreadyActed == false) {
				
				if(elfWrapper.elf.inAttackRange(this.enemyBuilding)) {
					elfWrapper.elf.attack(this.enemyBuilding);
					this.hadExecute = true;
					return true;
				}
				else {
					elfWrapper.elf.moveTo(this.enemyBuilding);
					return true;
				}	
			}
			else
				return false;
		}
		return false;
	}


	@Override
	public double getPriority() {
		// TODO Auto-generated method stub
		if(enemyBuilding!=null) {
			double time = 0, mana = 0;
			time = this.enemyBuilding.getLocation().distance(this.elfWrapper.elf)/elfWrapper.getSpeed() + this.enemyBuilding.currentHealth;
			// Mana no change(not be used)
		
			return (time*-1)*Weights.TIME_WEIGHT + (mana*-1)*Weights.MANA_WEIGHT;
		}
		return (Double.MAX_VALUE)*(-1);
	}
	
	
	
	public GameMannager getGame() {
		return this.game;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return hadExecute;
	}

	@Override
	public boolean isSucceed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}
}
