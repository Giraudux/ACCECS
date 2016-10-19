package fr.univ.nantes.alma.accecs.model;

public abstract class Variable {
	
	public enum RoleVariable {
		input,
		output,
		control,
		internal;
	}


	private String name;
	private RoleVariable role;
		

	public Variable() {
		super();
	}


	public Variable(String name, RoleVariable role) {
		this.name = name;
		this.role = role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public RoleVariable getRole() {
		return role;
	}


	public void setRole(RoleVariable role) {
		this.role = role;
	}

	public String toString(){
		return this.name + " " + this.role;
	}


	
	
	
}
