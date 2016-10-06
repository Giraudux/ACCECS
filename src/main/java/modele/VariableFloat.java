package modele;

public class VariableFloat extends Variable{

	private float borneMax;
	private float borneMin;
	private float valeurDefaut;
	
	public VariableFloat() {
		super();
	}

	public VariableFloat(String nom, RoleVariable role, float borneMax, float borneMin, float valeurDefaut) {
		super(nom, role);
		this.borneMax = borneMax;
		this.borneMin = borneMin;
		this.valeurDefaut = valeurDefaut;
	}

	public VariableFloat(String nom, RoleVariable role, float borneMax, float borneMin) {
		super(nom, role);
		this.borneMax = borneMax;
		this.borneMin = borneMin;
		this.valeurDefaut = 0;
	}

	public float getBorneMax() {
		return borneMax;
	}

	public void setBorneMax(float borneMax) {
		this.borneMax = borneMax;
	}

	public float getBorneMin() {
		return borneMin;
	}

	public void setBorneMin(float borneMin) {
		this.borneMin = borneMin;
	}

	public float getValeurDefaut() {
		return valeurDefaut;
	}

	public void setValeurDefaut(float valeurDefaut) {
		this.valeurDefaut = valeurDefaut;
	}
	
	
	
	
}
