package fr.univ.nantes.alma.accecs.generator.model;

public class VariableInteger extends Variable{

	private int borneMax; 
	private int borneMin;
	private int valeurDefaut;
	
	
	public VariableInteger() {
		super();
	}


	public VariableInteger(String nom, RoleVariable role, int borneMax, int borneMin, int valeurDefaut) {
		super(nom, role);
		this.borneMax = borneMax;
		this.borneMin = borneMin;
		this.valeurDefaut = valeurDefaut;
	}


	public VariableInteger(String nom, RoleVariable role, int borneMax, int borneMin) {
		super(nom, role);
		this.borneMax = borneMax;
		this.borneMin = borneMin;
		this.valeurDefaut = 0;
	}


	public int getBorneMax() {
		return borneMax;
	}


	public void setBorneMax(int borneMax) {
		this.borneMax = borneMax;
	}


	public int getBorneMin() {
		return borneMin;
	}


	public void setBorneMin(int borneMin) {
		this.borneMin = borneMin;
	}


	public int getValeurDefaut() {
		return valeurDefaut;
	}


	public void setValeurDefaut(int valeurDefaut) {
		this.valeurDefaut = valeurDefaut;
	}
	
	
	
	
}
