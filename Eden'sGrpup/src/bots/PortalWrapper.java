package bots;
import elf_kingdom.*;

/**
 * 
 * @author Yuval Rotman
 *
 */

/**
 * A portal wrapper
 */
public class PortalWrapper {
	
	public final Portal portal;
	
	public Roles role;

	
	public PortalWrapper(Portal portal) {
		this.portal = portal;
		role = Roles.NONE;
	}


	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}


	public Portal getPortal() {
		return portal;
	}
	
	
	

}
