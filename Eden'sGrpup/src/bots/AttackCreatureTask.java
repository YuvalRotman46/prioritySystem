package bots;
import elf_kingdom.*;


public class AttackCreatureTask implements Taskable {
	private GameMannager game;
	private MyElf elfWrapper;
	private Creature enemyCreature; // the enemy creature that we will attack
	private boolean hadExecute;
	
	public AttackCreatureTask(GameMannager game, MyElf elfWrapper, Creature enemyCreature) {
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
		 * the had execute quesstion is still not solved
		 */
		if(elfWrapper != null && elfWrapper.elf != null && elfWrapper.isActive()) {
			if(elfWrapper.elf.inAttackRange(enemyCreature)) {
				elfWrapper.elf.attack(this.enemyCreature);
				elfWrapper.disable();
				this.hadExecute = true;
				return true;
			}
			else {
				elfWrapper.elf.moveTo(this.enemyCreature);
				elfWrapper.disable();
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
		return 0;
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
