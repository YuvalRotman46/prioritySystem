package bots;
import elf_kingdom.*;


public class SummonLavaGiantTask implements Taskable  {
	private GameMannager game;
	private Portal portal;
	private boolean hadExecute;
	
	

	public SummonLavaGiantTask(GameMannager game, Portal portal) {
		this.game = game;
		this.portal = portal;
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		if(this.portal.canSummonLavaGiant()){
		    this.portal.summonLavaGiant();
		    this.hadExecute=true;
		    return true;
		}
		else
		return false;
	}

	@Override
	public double getPriority() {
		
		if (portal.alreadyActed || portal.isSummoning) 
			return Double.MIN_VALUE;
		
		double time = this.game.game.lavaGiantSummoningDuration;
		double mana = this.game.game.lavaGiantCost;
		
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

	/*@Override
	public int compareTo(Taskable argT) {
		if(this.getPriority() > argT.getPriority())
			return -1;
		else if(argT.getPriority() > this.getPriority())
			return 1;
		else return 0;
			
	}*/ // it is already default in the interface taskable
	

}
