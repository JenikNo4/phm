package cz.simek.phm;

/**
 * Roles from the spring security tutorial application.
 * 
 * @author tom
 * 
 */
public enum Role {

	SUPERVISOR("supervisor"),

	TELLER("teller"),

	USER("user"),

	ADMIN("ADMIN");

	private final String springSecurityRoleName;

	Role(String springSecurityRoleName) {
		this.springSecurityRoleName = springSecurityRoleName;
	}

	public String getSpringSecurityRoleName() {
		return springSecurityRoleName;
	}

}
