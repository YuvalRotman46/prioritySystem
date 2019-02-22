package bots;

import elf_kingdom.*;


public class AttackManaFountainTask implements Taskable {
	private GameMannager game;
	private MyElf elfWrapper;
	private ManaFountain enemyManaFountain;
	private boolean hadExecute;
	
	public AttackManaFountainTask(GameMannager game, MyElf elfWrapper, ManaFountain enemyManaFountain) {
		super();
		this.game = game;
		this.elfWrapper = elfWrapper;
		this.enemyManaFountain = enemyManaFountain;
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(elfWrapper != null && elfWrapper.elf != null && elfWrapper.elf.alreadyActed == false) {
			
			if(elfWrapper.elf.inAttackRange(this.enemyManaFountain)) {
				elfWrapper.elf.attack(this.enemyManaFountain);
				this.hadExecute = true;
				return true;
			}
			else {
				elfWrapper.elf.moveTo(this.enemyManaFountain);
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
