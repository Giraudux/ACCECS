package fr.univ.nantes.alma.accecs.model;

import java.util.ArrayList;
import java.util.Collection;

import fr.univ.nantes.alma.accecs.model.Variable.Category;

public class Machine {

    private String name;
    private Collection<Variable> variables;
    private Collection<Property> properties;
    private Collection<Event> events;
    private Collection<Variable.Category> generateEventVariables;
    private Collection<Variable> excludeEventVariables;
    private Collection<EnumerateType> sets;

    public Machine() {
        name = new String();
        variables = new ArrayList<Variable>();
        properties = new ArrayList<Property>();
        events = new ArrayList<Event>();
        generateEventVariables = new ArrayList<Category>();
        excludeEventVariables = new ArrayList<Variable>();
        sets = new ArrayList<EnumerateType>();
    }

    public Machine(String name, Collection<Variable> variables, Collection<Property> properties, Collection<Event> events, Collection<Category> generateEventVariables, Collection<Variable> excludeEventVariables, Collection<EnumerateType> enumerateTypes) {
        this.name = name;
        this.variables = variables;
        this.properties = properties;
        this.events = events;
        this.generateEventVariables = generateEventVariables;
        this.excludeEventVariables = excludeEventVariables;
        this.sets = enumerateTypes;
        generateEvents();
    }

    /*public Machine(String name, Collection<Variable> variables, Collection<Property> properties, Collection<Event> events) {
        this.name = name;
        this.variables = variables;
        this.properties = properties;
        this.events = events;
        //Determine the possible events with the known variables
        for (Variable each : this.variables){
        	if (each.getCategory() == Category.INPUT){
        		events.add(calculEventInput(each));
        	}
        	if (each.getCategory() == Category.OUTPUT){
        		events.add(calculEventOutput(each));
        	}
        }
    }*/

    //TODO determiner comment fabriquer un event a partir de variable d'entree
    /*private Event calculEventInput(Variable v){

    	return new Event();
    }*/

    //TODO determiner comment fabriquer un event a partir de variable d'entree
    /*private Event calculEventOutput(Variable v){

    	return new Event();
    }*/

    public Collection<Event> generateEvents() {
        //events.clear();

        for (Variable variable : variables) {
            if (!excludeEventVariables.contains(variable) && generateEventVariables.contains(variable.getCategory())) {
                Event event = new Event();

                Variable.Category category = variable.category;
                //TODO: set mapping configurable
                switch (category) {
                    case CONTROL:
                        event.setName("control_"+variable.getName());
                        event.setCategory(Event.Category.CONTROL);
                        break;
                    case INPUT:
                        event.setName("sensing_"+variable.getName());
                        event.setCategory(Event.Category.SENSING);
                        break;
                    case INTERNAL:
                        event.setName("monitoring_"+variable.getName());
                        event.setCategory(Event.Category.MONITORING);
                        break;
                    case OUTPUT:
                        event.setName("reaction_"+variable.getName());
                        event.setCategory(Event.Category.REACTION);
                        break;
                }

                event.setVariable(variable);
                events.add(event);
            }
        }

        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Variable> getVariables() {
        return variables;
    }

    public void setVariables(Collection<Variable> variables) {
        this.variables = variables;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public Collection<Category> getGenerateEventVariables() {
        return generateEventVariables;
    }

    public void setGenerateEventVariables(Collection<Category> generateEventVariables) {
        this.generateEventVariables = generateEventVariables;
    }

    public Collection<Variable> getExcludeEventVariables() {
        return excludeEventVariables;
    }

    public void setExcludeEventVariables(Collection<Variable> excludeEventVariables) {
        this.excludeEventVariables = excludeEventVariables;
    }

    public Collection<EnumerateType> getSets() {
        return sets;
    }

    public void setSets(Collection<EnumerateType> sets) {
        this.sets = sets;
    }
}
