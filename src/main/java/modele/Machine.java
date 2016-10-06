package modele;

import java.util.List;

public class Machine {

	private String name;
	private List<Variable> variables;
	private List<Property> properties;
	private List<Event> events;
	
	public Machine() {
		super();
	}

	public Machine(String name, List<Variable> variables,
			List<Property> properties, List<Event> events) {
		super();
		this.name = name;
		this.variables = variables;
		this.properties = properties;
		this.events = events;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	
	
	
}
