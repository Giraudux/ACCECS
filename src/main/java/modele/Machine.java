package modele;

import java.util.List;

public class Machine {

	private String nom;
	private List<Variable> variables;
	private List<Propriete> proprietes;
	private List<Evenement> evenements;
	
	public Machine() {
		super();
	}

	public Machine(String nom, List<Variable> variables,
			List<Propriete> proprietes, List<Evenement> evenements) {
		super();
		this.nom = nom;
		this.variables = variables;
		this.proprietes = proprietes;
		this.evenements = evenements;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public List<Propriete> getProprietes() {
		return proprietes;
	}

	public void setProprietes(List<Propriete> proprietes) {
		this.proprietes = proprietes;
	}

	public List<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}
	
	
	
}
