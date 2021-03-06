package bots;
import elf_kingdom.*;

/**
 * 
 * @author Yuval Rotman
 *The mana foundation building task
 */
public class BuildManaFoundationTask implements Taskable {
	private GameMannager game;
	private ElfWrapper elfWrapper;
	private Location manaFoundationLocation;
	private boolean hadExceute;
	
	public BuildManaFoundationTask(GameMannager game, ElfWrapper elfWrapper, Location manaFoundationLocation) {
		this.game = game;
		this.elfWrapper = elfWrapper;
		this.manaFoundationLocation = manaFoundationLocation;
		hadExceute = false;
		
	}

	@Override
	public boolean execute() {
		if(elfWrapper != null && elfWrapper.elf != null && elfWrapper.elf.alreadyActed == false) {
			if(elfWrapper.elf.getLocation().equals(manaFoundationLocation)) {
				if(elfWrapper.elf.canBuildManaFountain()) {
					elfWrapper.elf.buildManaFountain();
					hadExceute = true;
					return true;
				}
				else
					return false;
			}
			else {
				if(elfWrapper.elf.alreadyActed == false)
				{
					elfWrapper.elf.moveTo(manaFoundationLocation);
					return true;
				}
				else
					return false;
			}
		}
		else
		return false;
	}

	@Override
	public double getPriority() {
		// TODO Auto-generated method stub
		double mana = game.game.manaFountainCost;
		double time = elfWrapper.elf.distance(manaFoundationLocation)/elfWrapper.getSpeed() + game.game.manaFountainBuildingDuration;
		
		return (mana*-1)*Weights.MANA_WEIGHT + (time*-1)*Weights.TIME_WEIGHT;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return hadExceute;
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
