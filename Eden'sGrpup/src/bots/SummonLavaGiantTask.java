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
		double time = (this.game.game.lavaGiantSummoningDuration)*-1;
		double cost = (this.game.game.lavaGiantCost)*-1;
	//	double dScore = 0; // will happend
		double astrateg = 0;// group thinking
		
		double _final = time*0.3 + cost*0.2 + /*dScore*0.1+*/astrateg*0.4; // random priority
		return _final;
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
