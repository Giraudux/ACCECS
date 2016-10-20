package fr.univ.nantes.alma.accecs.parser;

import java.util.ArrayList;
import java.util.List;


import fr.univ.nantes.alma.accecs.model.Event;
import fr.univ.nantes.alma.accecs.model.Property;
import fr.univ.nantes.alma.accecs.model.Variable;
import fr.univ.nantes.alma.accecs.model.VariableBoolean;
import fr.univ.nantes.alma.accecs.model.VariableFloat;
import fr.univ.nantes.alma.accecs.model.VariableInteger;
import fr.univ.nantes.alma.accecs.model.Variable.RoleVariable;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParserJSON {

	private List<Variable> variables = new ArrayList<Variable>();
	private List<Property> Properties = new ArrayList<Property>();
	private List<Event> events = new ArrayList<Event>();
	
	
	public ParserJSON() {
		super();
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public List<Property> getProperties() {
		return Properties;
	}

	public void setProperties(List<Property> properties) {
		Properties = properties;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}



	public void parse(String json){
		
			Variable variable = null;
			
			JSONObject jsonMachine = new JSONObject(json);
			//String machineName = jsonMachine.getJSONObject("pageInfo").getString("pageName");
			JSONArray jsonVariables = jsonMachine.getJSONArray("variables");
			
			
			for (int i = 0; i < jsonVariables.length(); i++){
		    
				String variableName = jsonVariables.getJSONObject(i).getString("variableName");
				String variableType = jsonVariables.getJSONObject(i).getString("variableType");
				String variableRole = jsonVariables.getJSONObject(i).getString("variableRole");
		    
				RoleVariable role = null;
				if (variableRole.equals("input")) {
					role = RoleVariable.input;

				} else if (variableRole.equals("output")) {
					role = RoleVariable.output;

				} else if (variableRole.equals("control")) {
					role = RoleVariable.control;

				} else if (variableRole.equals("internal")) {
					role = RoleVariable.internal;

				}
				
				if(variableType.equals("Integer")){
					
					
						
					
					int lowerBound = Integer.parseInt(jsonVariables.getJSONObject(i).getString("variableMin"));
					int upperBound = Integer.parseInt(jsonVariables.getJSONObject(i).getString("variableMax"));
					
					if(jsonVariables.getJSONObject(i).getString("variableInit").trim() != ""){
						int variableInit = Integer.parseInt(jsonVariables.getJSONObject(i).getString("variableInit"));
						variable = new VariableInteger(variableName, role, upperBound, lowerBound, variableInit);
					}else{
						variable = new VariableInteger(variableName, role, upperBound, lowerBound);
					}
					
				
				}
				
				
				
				
				else if(variableType.equals("Float")){
					float lowerBound = Float.parseFloat(jsonVariables.getJSONObject(i).getString("variableMin").replace(",","."));
					float upperBound = Float.parseFloat(jsonVariables.getJSONObject(i).getString("variableMax").replace(",","."));
					
					if(jsonVariables.getJSONObject(i).getString("variableInit").trim() != ""){
						float variableInit = Float.parseFloat(jsonVariables.getJSONObject(i).getString("variableInit").replace(",","."));
						variable = new VariableFloat(variableName, role, upperBound, lowerBound, variableInit);
					}else{
						variable = new VariableFloat(variableName, role, upperBound, lowerBound);
					}
					
					variable = new VariableFloat(variableName, role, upperBound, lowerBound);
				} 
				else if(variableType.equals("Boolean")){
					if(jsonVariables.getJSONObject(i).getString("variableInit").trim() != ""){
						boolean variableInit = Boolean.parseBoolean(jsonVariables.getJSONObject(i).getString("variableInit"));
						variable = new VariableBoolean(variableName, role, variableInit);
					}else{
						variable = new VariableBoolean(variableName, role);
					}
				}
				
				
				variables.add(variable);
			    
			}
		
		}
		
}
