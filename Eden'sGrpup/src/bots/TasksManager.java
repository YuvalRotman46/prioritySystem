package bots;

import java.util.*;
import elf_kingdom.*;


public class TasksManager {
	public static final String MOTO = "! המעז מנצח"; 
	private GameMannager game;
	// tasks => all the tasks priorityQueue , unExecutableTasks => tasks which not had executed, unRunableTasks => tasks which return false while execution
	private PriorityQueue<Taskable> tasks, unExecuteableTasks, unRunableTasks; // the main queue
	
	public TasksManager(GameMannager game) {
		tasks = new PriorityQueue<>();
		unExecuteableTasks = new  PriorityQueue<>();
		unRunableTasks = new PriorityQueue<>();
		
		this.game = game;
	}
	
	public void execute() {
		while(tasks.peek() != null) {
			// start iteration
			
			if(tasks.peek().execute()) {
				Taskable task = (Taskable)tasks.poll();
				if(task.isFinished() == false) {
					unExecuteableTasks.offer(task);
				}
			}else {
				Taskable task = (Taskable)tasks.poll();
				unRunableTasks.offer(task);
				
			}
			
			// end iteration
		}
	}
	
	public void initialQueue() {
		
		/**
		 * the summoning from portal tasks inserting
		 */
		for (Portal portal : this.game.game.getMyPortals()) {
			SummonTrollTask sTrolls = new SummonTrollTask(this.game, portal);
			SummonLavaGiantTask sGiants = new SummonLavaGiantTask(game, portal);
			this.tasks.offer(sTrolls);
			this.tasks.offer(sGiants);
		}
		
		/**
		 * the other other summoning tasks 
		 */
	}

	
	

}
