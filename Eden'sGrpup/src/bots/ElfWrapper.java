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
public class ElfWrapper {
	public Elf elf;
	
	public Roles role;
	
	public ElfWrapper(Elf elf) {
		this.elf = elf;
		role = Roles.NONE;
	}

}
