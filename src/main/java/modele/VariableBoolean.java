package modele;

public class VariableBoolean extends Variable{
	
	private boolean valeurDefaut;

	public VariableBoolean(String nom, RoleVariable role) {
		super(nom, role);
		this.valeurDefaut = false;
	}

	public VariableBoolean(String nom, RoleVariable role, boolean valeurDefaut) {
		super(nom, role);
		this.valeurDefaut = valeurDefaut;
	}

	public boolean isValeurDefaut() {
		return valeurDefaut;
	}

	public void setValeurDefaut(boolean valeurDefaut) {
		this.valeurDefaut = valeurDefaut;
	}
	
	

}
