package bots;
import elf_kingdom.*;


public class SummonLavaGiantTask implements Taskable  {
	private GameMannager game;
	private PortalWrapper portal;
	private boolean hadExecute;
	
	

	public SummonLavaGiantTask(GameMannager game, PortalWrapper portal) {
		this.game = game;
		this.portal = portal;
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		if(this.portal.portal.canSummonLavaGiant()){
		    this.portal.portal.summonLavaGiant();
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
	
	public int howManyIceTrollsInCriticCircle() {
		int halfDist = (portal.portal.distance(game.game.getEnemyCastle()))/2;
		Location theLoc = portal.portal.location.towards(game.game.getEnemyCastle(), halfDist);
		return SkillzLib.howManyObjectsAroundLocation(game.game, theLoc, game.game.getEnemyIceTrolls(), halfDist);
	}
	
	public double getSummonLavaGiantPriority() {
		if (portal.portal.alreadyActed || portal.portal.isSummoning) 
			return Double.MIN_VALUE;
		
		double criticEnemies = this.howManyIceTrollsInCriticCircle();
		double dist = portal.portal.distance(game.game.getEnemyCastle());
		
		return (criticEnemies*-1)*Weights.CRITIC_CIRCLE_WEIGHT + (dist*-1)*Weights.DIST_WEIGHT;
		
		
	}

	@Override
	public int compareTo(Taskable arg) {
		
		if(arg instanceof SummonLavaGiantTask) {
		if(this.getSummonLavaGiantPriority() > ((SummonLavaGiantTask)arg).getSummonLavaGiantPriority())
			return -1;
		else if(((SummonLavaGiantTask)arg).getSummonLavaGiantPriority() > this.getSummonLavaGiantPriority())
			return 1;
		else return 0;
			
		}else return Taskable.super.compareTo(arg);
	}
	
	
}
