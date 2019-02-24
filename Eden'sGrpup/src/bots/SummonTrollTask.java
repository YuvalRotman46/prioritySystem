package bots;
import elf_kingdom.*;

/**
 * 
 * @author yuval
 * @version 1.0
 *
 */
public class SummonTrollTask implements Taskable{
	
	private GameMannager game;
	private Portal portal;
	private boolean hadExecute;

	public SummonTrollTask(GameMannager game, Portal portal) {
		this.game = game;
		this.portal = portal;
		hadExecute = false;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(this.portal.canSummonIceTroll()) {
		this.portal.summonIceTroll();
		this.hadExecute = true;
		return true;
		}
		else
		return false;
	}

	@Override
	public double getPriority() {
		double time = (this.game.game.iceTrollSummoningDuration)*-1;
		double mana = (this.game.game.iceTrollCost)*-1;
		
		return time*0.6 + 0.4*mana;
		
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
