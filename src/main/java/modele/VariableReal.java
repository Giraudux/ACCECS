package modele;

public class VariableReal extends Variable{

	private float borneMax;
	private float borneMin;
	private float valeurDefaut;
	
	public VariableReal() {
		super();
	}

	public VariableReal(float borneMax, float borneMin, float valeurDefaut) {
		super();
		this.borneMax = borneMax;
		this.borneMin = borneMin;
		this.valeurDefaut = valeurDefaut;
	}

	public VariableReal(float borneMax, float borneMin) {
		super();
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
