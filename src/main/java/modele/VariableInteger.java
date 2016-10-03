package modele;

public class VariableInteger extends Variable{

	private int borneMax; 
	private int borneMin;
	private int valeurDefaut;
	
	
	public VariableInteger() {
		super();
	}


	public VariableInteger(int borneMax, int borneMin, int valeurDefaut) {
		super();
		this.borneMax = borneMax;
		this.borneMin = borneMin;
		this.valeurDefaut = valeurDefaut;
	}


	public VariableInteger(int borneMax, int borneMin) {
		super();
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