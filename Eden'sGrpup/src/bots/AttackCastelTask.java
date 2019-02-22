package bots;
import elf_kingdom.*;


public class AttackCastelTask implements Taskable {
	private GameMannager game;
	private MyElf elfWrapper;
	private Castle enemyCastel;
	private boolean hadExecute ;
	
	
	
	public AttackCastelTask(GameMannager game, MyElf elfWrapper, Castle enemyCastel) {
		super();
		this.game = game;
		this.elfWrapper = elfWrapper;
		this.enemyCastel = enemyCastel;
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		
		/*
		 * the same quesstion about is_execute
		 */
		
		// TODO Auto-generated method stub
		if(elfWrapper != null && elfWrapper.elf != null && elfWrapper.elf.alreadyActed == false) {
			if (elfWrapper.elf.inAttackRange(enemyCastel)) {
				elfWrapper.elf.attack(enemyCastel);
				hadExecute = true;
				return true;
			}else {
				elfWrapper.elf.moveTo(enemyCastel);
				return true;
			}
		}
		else return false;
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
