package modele;

public abstract class Variable {

	private String nom;
	private RoleVariable role;
		

	public Variable() {
		super();
	}


	public Variable(String nom, RoleVariable role) {
		super();
		this.nom = nom;
		this.role = role;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public RoleVariable getRole() {
		return role;
	}


	public void setRole(RoleVariable role) {
		this.role = role;
	}

	
	
	
}
