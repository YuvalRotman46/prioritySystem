package bots;

import java.util.ArrayList;

import elf_kingdom.Game;
import elf_kingdom.IceTroll;
import elf_kingdom.LavaGiant;
import elf_kingdom.Tornado;

public class StateMannager {
	
	private GameMannager game; //Uses the GameMannager array lists & castles health
	
	private ArrayList<IceTroll> myIceTrolls;
	private ArrayList<IceTroll> enemyIceTrolls;
	
	private ArrayList<LavaGiant> myLavaGiants;
	private ArrayList<LavaGiant> enemyLavaGiants;
	
	private ArrayList<Tornado> myTornadoes;
	private ArrayList<Tornado> enemyTornadoes;
	
	private int myManaPerTurn;
	private int enemyManaPerTurn;
	
	private Roles currentState = Roles.NONE; // I think it should be a scale
	
	public StateMannager(GameMannager game) {
		this.game=game;
		
		for(IceTroll troll : game.game.getMyIceTrolls())
			myIceTrolls.add(troll);
	
		for(IceTroll troll : game.game.getEnemyIceTrolls())
			enemyIceTrolls.add(troll);
		
		for(LavaGiant giant : game.game.getMyLavaGiants())
			myLavaGiants.add(giant);
		
		for(LavaGiant giant : game.game.getEnemyLavaGiants())
			enemyLavaGiants.add(giant);
		
		for(Tornado tornado : game.game.getMyTornadoes())
			myTornadoes.add(tornado);
		
		for(Tornado tornado : game.game.getEnemyTornadoes())
			enemyTornadoes.add(tornado);
		
		myManaPerTurn = game.game.getMyself().manaPerTurn;
		enemyManaPerTurn = game.game.getEnemy().manaPerTurn;
	}
	
	public void getState() {
		/* depends on us, I think the return value should be a scale;
		 
		 An idea to create the bias, if the scale ranges from one to ten, when ten is a full defense:
		 	- 5 means no bias will be given (1 for no bias).
		 	- the higher | scale number - 5 | the lower the bias
		 	- the bias will be given only to one state attribute
		 */
		
		currentState = Roles.NONE;
	}
	
	public double getBias() {
		
		return 1;
	}
	
}
