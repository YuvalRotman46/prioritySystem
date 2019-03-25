package bots;

import elf_kingdom.*;

public class SummonTornadoTask implements Taskable{
	private GameMannager game;
	private PortalWrapper portal;
	private boolean hadExecute;
	
	public SummonTornadoTask(GameMannager game, PortalWrapper portal) {
		this.game = game;
		this.portal = portal;
		this.hadExecute = false;
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
	
	@Override
	public boolean execute() {
		if(this.portal.portal.canSummonTornado()){
		    this.portal.portal.summonTornado();
		    this.hadExecute=true;
		    return true;
		}
		else
		return false;
	}
	
	@Override
	public double getPriority() {
		
		if (portal.portal.alreadyActed || portal.portal.isSummoning) 
			return Double.MIN_VALUE;
		
		double time = this.game.game.tornadoSummoningDuration;
		double mana = this.game.game.tornadoCost;
		
		return (time*-1)*Weights.TIME_WEIGHT + (mana*-1)*Weights.MANA_WEIGHT;
		
	}
	
	
	public GameMannager getGame() {
		return game;
	}

}
