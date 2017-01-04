package fr.univ.nantes.alma.accecs.parser;

import fr.univ.nantes.alma.accecs.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class which serves to parse the JSON of a machine to Java class representing the same machine.
 * 
 * 
 * */

@SuppressWarnings("rawtypes")
public class ParserJSON {

    private String name;
    private Collection<Variable> variables;
    private Collection<Property> properties;
    private Collection<Event> events;
    private Collection<Variable.Category> generateEventVariables;
    private Collection<Variable> excludeEventVariables;

    private Collection<EnumerateType> enumerateTypes;

    public ParserJSON() {
        name = new String();
        variables = new ArrayList<Variable>();
        properties = new ArrayList<Property>();
        events = new ArrayList<Event>();
        generateEventVariables = new ArrayList<Variable.Category>();
        generateEventVariables.add(Variable.Category.INPUT);
        generateEventVariables.add(Variable.Category.OUTPUT);
        excludeEventVariables = new ArrayList<Variable>();
        enumerateTypes = new ArrayList<EnumerateType>();
    }
    //@SuppressWarnings({ "rawtypes", "unused" })
	public CategoryVariable convertVariableCategory(String variableCategory){
    	Class c = null;
    	CategoryVariable categoryVar = null;
		try {
			c = Class.forName("fr.univ.nantes.alma.accecs.model."+variableCategory.toUpperCase());
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
    	
		try {
			categoryVar = (CategoryVariable) c.newInstance();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
    	return categoryVar;
    }
	/**
	 * Fonction which transform the JSON machine in Java class
	 * 
	 * @param json : String representing the JSON machine
	 * 
	 * @throws Exception : unknown variable category
	 * */
    public void parse(String json) throws Exception {

        JSONObject jsonMachine = new JSONObject(json);

        name = jsonMachine.getString("name");

        JSONArray jsonVariables = jsonMachine.getJSONArray("variables");
        for (int i = 0; i < jsonVariables.length(); i++) {
            Variable variable;
            JSONObject jsonVariable = jsonVariables.getJSONObject(i);

            String variableName = jsonVariable.getString("name");
            String variableType = jsonVariable.getString("type");
            String variableCategory = jsonVariable.getString("category");
            
            //TODO changer pour category variable 
            Variable.Category category;
            if (variableCategory.equals("input")) {
                category = Variable.Category.INPUT;
            } else if (variableCategory.equals("output")) {
                category = Variable.Category.OUTPUT;
            } else if (variableCategory.equals("control")) {
                category = Variable.Category.CONTROL;
            } else if (variableCategory.equals("internal")) {
                category = Variable.Category.INTERNAL;
            } else {
                throw new Exception("unknown variable category: " + variableCategory);
            }

            if (variableType.equals("integer")) {
                Integer lowerBound = Integer.parseInt(jsonVariable.getString("lowerBound"));
                Integer upperBound = Integer.parseInt(jsonVariable.getString("upperBound"));

                String variableDefaultValue = jsonVariable.getString("defaultValue");
                if (variableDefaultValue.isEmpty()) {
                    variable = new VariableInteger(variableName, category, lowerBound, upperBound);
                } else {
                    variable = new VariableInteger(variableName, category, upperBound, lowerBound, Integer.parseInt(variableDefaultValue));
                }
            } else if (variableType.equals("natural")) {
                Integer lowerBound = Integer.parseInt(jsonVariable.getString("lowerBound"));
                Integer upperBound = Integer.parseInt(jsonVariable.getString("upperBound"));

                String variableDefaultValue = jsonVariable.getString("defaultValue");
                if (variableDefaultValue.isEmpty()) {
                    variable = new VariableNatural(variableName, category, lowerBound, upperBound);
                } else {
                    variable = new VariableNatural(variableName, category, lowerBound, upperBound, Integer.parseInt(variableDefaultValue));
                }
            } else if (variableType.equals("boolean")) {
                if (Boolean.parseBoolean(jsonVariable.getString("defaultValue"))) {
                    variable = new VariableBoolean(variableName, category, Boolean.TRUE);
                } else {
                    variable = new VariableBoolean(variableName, category);
                }
            } else {
                //throw new Exception("unknown variable type: " + variableType);
                variable = new VariableEnum(variableName, category, variableType, jsonVariable.getString("defaultValue"));
            }


            variables.add(variable);

        }

        JSONArray jsonProperties = jsonMachine.getJSONArray("properties");
        for (int i = 0; i < jsonProperties.length(); i++) {
            Property property;
            JSONObject jsonProperty = jsonProperties.getJSONObject(i);

            String propertyExpression = jsonProperty.getString("expression");
            String propertyCategory = jsonProperty.getString("category");

            Property.Category category;
            if (propertyCategory.equals("safety")) {
                category = Property.Category.SAFETY;
            } else if (propertyCategory.equals("non-functional")) {
                category = Property.Category.NON_FUNCTIONAL;
            } else if (propertyCategory.equals("liveness")) {
                category = Property.Category.LIVENESS;
            } else {
                throw new Exception("unknown property category: " + propertyCategory);
            }

            property = new Property(propertyExpression, category);
            properties.add(property);
        }

        JSONArray jsonEnumerateTypes = jsonMachine.getJSONArray("enumerations");
        for(int i = 0; i < jsonEnumerateTypes.length(); i++) {
            EnumerateType enumerateType;
            Collection<String> enumerateTypeValues = new HashSet<String>();
            JSONObject jsonEnumerateType = jsonEnumerateTypes.getJSONObject(i);

            JSONArray jsonEnumerateTypeValues = jsonEnumerateType.getJSONArray("literals");
            for (int j = 0; j < jsonEnumerateTypeValues.length(); j++) {
                enumerateTypeValues.add(jsonEnumerateTypeValues.getString(j));
            }

            enumerateType = new EnumerateType(jsonEnumerateType.getString("name"), enumerateTypeValues);
            enumerateTypes.add(enumerateType);
        }
    }

    public String getName() {
        return name;
    }

    public Collection<Variable> getVariables() {
        return variables;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public Collection<Variable.Category> getGenerateEventVariables() {
        return generateEventVariables;
    }

    public Collection<Variable> getExcludeEventVariables() {
        return excludeEventVariables;
    }

    public Collection<EnumerateType> getEnumerateTypes() {
        return enumerateTypes;
    }
}
