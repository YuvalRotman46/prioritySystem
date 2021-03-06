package bots;

import elf_kingdom.*;


public class BuildPortalTask implements Taskable {
	private GameMannager game;
	private ElfWrapper elfWrapper;
	private Location portalLoction;
	private boolean hadExecute;
	
	public BuildPortalTask(GameMannager game, ElfWrapper elfWrapper, Location portalLoction) {
		this.game = game;
		this.elfWrapper = elfWrapper;
		this.portalLoction = portalLoction;
		this.hadExecute = false;
	}

	@Override
	public boolean execute() {
		/*
		 * Maybe we have to add another cheacks
		 */
		
		/*
		 * if the elf not in the portal location and the execution is to walk to the portal should we need
		 * to set hadExecute to true ?
		 */
		
		if (this.elfWrapper != null && this.elfWrapper.elf != null && this.elfWrapper.elf.alreadyActed == false) {
			if(this.elfWrapper.elf.getLocation().equals(portalLoction)) {
				// in case that elf is in the summing location
				if (elfWrapper.elf.canBuildPortal()) {
					elfWrapper.elf.buildPortal();
					this.hadExecute = true;
					return true;
				}
				else {
					return false;
				}
			}
			else {
				// in case of elf is not in portal summing location
				this.elfWrapper.elf.moveTo(portalLoction);
				return true;
			}
		}
		else return false;
	}

	@Override
	public double getPriority() {
		// TODO Auto-generated method stub
		double time = elfWrapper.elf.distance(portalLoction)/elfWrapper.getSpeed() + game.game.manaFountainBuildingDuration;
		double mana = game.game.portalCost;
		
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
	
	
	
}
