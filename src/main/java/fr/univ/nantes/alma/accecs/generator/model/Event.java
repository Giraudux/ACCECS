package fr.univ.nantes.alma.accecs.generator.model;

public class Event {
	
    public enum RoleEvent {
        SENSING,
        MONITORING,
        CONTROL,
        REACTION
    }

	private String expression;
	private RoleEvent role;
	
	
	public Event() {
		super();
	}


	public Event(String expression, RoleEvent role) {
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


	public RoleEvent getRole() {
		return role;
	}


	public void setRole(RoleEvent role) {
		this.role = role;
	}



	
	
	
}
