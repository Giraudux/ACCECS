package modele;

public class Propriete {

	private String expression;
	private RolePropriete role;
	
	
	public Propriete() {
		super();
	}


	public Propriete(String expression, RolePropriete role) {
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


	public RolePropriete getRole() {
		return role;
	}


	public void setRole(RolePropriete role) {
		this.role = role;
	}
	
	
	
}
