package modele;

public class Evenement {

	private String expression;
	private RoleEvenement role;
	
	
	public Evenement() {
		super();
	}


	public Evenement(String expression, RoleEvenement role) {
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


	public RoleEvenement getRole() {
		return role;
	}


	public void setRole(RoleEvenement role) {
		this.role = role;
	}
	
	
	
}
