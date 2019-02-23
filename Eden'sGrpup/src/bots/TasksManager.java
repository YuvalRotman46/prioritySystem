package bots;

import java.util.*;
import elf_kingdom.*;


public class TasksManager {
	public static final String MOTO = "המעז מנצח"; 
	private GameMannager game;
	// tasks => all the tasks priorityQueue , unExecutableTasks => tasks which not had executed, unRunableTasks => tasks which return false while execution
	private PriorityQueue<Taskable> tasks/*the main queue*/, unExecuteableTasks, unRunableTasks; // the queue + trash queues
	
	public TasksManager(GameMannager game) {
		tasks = new PriorityQueue<>();
		unExecuteableTasks = new  PriorityQueue<>();
		unRunableTasks = new PriorityQueue<>();
		
		this.game = game;
	}
	
	public boolean execute() {
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
		
		return this.tasks.size() == 0;
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

	
	public boolean addTask(Taskable task) {
		return this.tasks.offer(task);
	}

	public GameMannager getGame() {
		return game;
	}

	public PriorityQueue<Taskable> getTasks() {
		return tasks;
	}

	public PriorityQueue<Taskable> getUnExecuteableTasks() {
		return unExecuteableTasks;
	}

	public PriorityQueue<Taskable> getUnRunableTasks() {
		return unRunableTasks;
	}

	
	
	
}
