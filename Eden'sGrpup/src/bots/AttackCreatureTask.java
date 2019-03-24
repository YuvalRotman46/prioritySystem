package bots;
import elf_kingdom.*;


public class AttackCreatureTask implements Taskable {
	private GameMannager game;
	private ElfWrapper elfWrapper;
	private Creature enemyCreature; // the enemy creature that we will attack
	private boolean hadExecute;
	
	public AttackCreatureTask(GameMannager game, ElfWrapper elfWrapper, Creature enemyCreature) {
		super();
		this.game = game;
		this.elfWrapper = elfWrapper;
		this.enemyCreature = enemyCreature;
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		/*
		 * the had execute question is still not solved
		 */
		if(elfWrapper != null && elfWrapper.elf != null && elfWrapper.elf.alreadyActed == false) {
			if(elfWrapper.elf.inAttackRange(enemyCreature)) {
				elfWrapper.elf.attack(this.enemyCreature);
				this.hadExecute = true;
				return true;
			}
			else {
				elfWrapper.elf.moveTo(this.enemyCreature);
				// had execute ?
				return true;
			}
		}
		else
		return false;
	}

	@Override
	public double getPriority() {
		// TODO Auto-generated method stub
		double time = 0, mana = 0;
		time = this.enemyCreature.getLocation().distance(this.elfWrapper.elf)/elfWrapper.getSpeed() + this.enemyCreature.currentHealth;
		// Mana no change(not be used)
		
		return (time*-1)*Weights.TIME_WEIGHT + (mana*-1)*Weights.MANA_WEIGHT;
	}
	
	public GameMannager getGame() {
		return game;
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
