package modele;

public class Property {
	
    public enum RoleProperty {
        SAFETY,
        NON_FUNCTIONAL,
        LIVENESS
    }

	private String expression;
	private RoleProperty role;
	
	
	public Property() {
		super();
	}


	public Property(String expression, RoleProperty role) {
		super();
		this.expression = expression;
		this.role = role;
	}


	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}


	public RoleProperty getRole() {
		return role;
	}


	public void setRole(RoleProperty role) {
		this.role = role;
	}
	
	
	
}
