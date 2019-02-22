package bots;
import elf_kingdom.*;

 /**
  * 
  * This class is actually elf wrapper.
  *	It basically created for managing the elf activity and avoiding exception.
  * 
  * @author yuval
  *
  */
public class MyElf {
	public Elf elf;
	private boolean isActive;
	
	public MyElf(Elf elf) {
		this.elf = elf;
		this.isActive = true;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void disable() {
		this.isActive = false;
	}
}
