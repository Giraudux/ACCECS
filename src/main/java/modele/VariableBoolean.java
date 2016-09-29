package modele;

public class VariableBoolean extends Variable{
	
	private boolean valeurDefaut;

	public VariableBoolean() {
		super();
	}

	public VariableBoolean(boolean valeurDefaut) {
		super();
		this.valeurDefaut = valeurDefaut;
	}

	public boolean isValeurDefaut() {
		return valeurDefaut;
	}

	public void setValeurDefaut(boolean valeurDefaut) {
		this.valeurDefaut = valeurDefaut;
	}
	
	

}
