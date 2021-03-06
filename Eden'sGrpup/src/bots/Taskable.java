package bots;
/**
 * 
 * @author yuval
 *
 */
public interface Taskable extends Comparable<Taskable> {
	
	/**
	 * 
	 * @return true if execution is completed else false.
	 */
	public  boolean execute();
	
	/**
	 * 
	 * @return the num grade of the importance of a task
	 */
	public double getPriority();
	
	
	/**
	 * 
	 * @return the hadExecute variable
	 */
	public boolean isFinished();
	/**
	 * 
	 * @return true if execution is succeeded else false.
	 */
	public boolean isSucceed();
	
	public void invalidate();
	
	@Override
	public default int compareTo(Taskable arg) {
		if(this.getPriority() > arg.getPriority())
			return -1;
		else if(arg.getPriority() > this.getPriority())
			return 1;
		else return 0;
			
	}

}
