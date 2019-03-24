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
		double time = this.game.game.iceTrollSummoningDuration;
		double mana = this.game.game.iceTrollCost;
		
		return (time*-1)*Weights.TIME_WEIGHT + (mana-1)*Weights.MANA_WEIGHT;
		
	}
	
	public GameMannager getGame() {
		return game;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stu
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
