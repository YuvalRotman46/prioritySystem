package bots;
import elf_kingdom.*;

/**
 * 
 * @author yuval
 * @version 1.3
 *
 */
public class SummonTrollTask implements Taskable{
	
	private GameMannager game;
	private PortalWrapper portal;
	private boolean hadExecute;

	public SummonTrollTask(GameMannager game, PortalWrapper portal) {
		this.game = game;
		this.portal = portal;
		hadExecute = false;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(this.portal.portal.canSummonIceTroll()) {
		this.portal.portal.summonIceTroll();
		this.hadExecute = true;
		return true;
		}
		else
		return false;
	}

	@Override
	public double getPriority() {
		
		if (portal.portal.alreadyActed || portal.portal.isSummoning) 
			return Double.MIN_VALUE;
		
		double time = this.game.game.iceTrollSummoningDuration;
		double mana = this.game.game.iceTrollCost;
		
		return (time*-1)*Weights.TIME_WEIGHT + (mana-1)*Weights.MANA_WEIGHT;
		
	}
	
	private double getPortalPriority() {
		double priority = 0;
		for(GameObject enemyObject : SkillzLib.getEnemyElfsAndCreatures(this.game.game)) {
			double IPriority = (SkillzLib.getEnemiesCreatureScore(this.game.game, enemyObject))/((double)portal.portal.distance(enemyObject));
			priority += IPriority;
		}
		
		return priority;
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
	
	@Override
	public int compareTo(Taskable arg) {
		// TODO Auto-generated method stub
		if(arg instanceof SummonTrollTask) {
			SummonTrollTask stTask = (SummonTrollTask)arg;
			if(this.getPortalPriority()>stTask.getPortalPriority()) return -1;
			else if(this.getPortalPriority()<stTask.getPortalPriority()) return 1;
			else return 0;
		}
		else return Taskable.super.compareTo(arg);
	}

}
