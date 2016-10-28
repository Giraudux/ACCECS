package fr.univ.nantes.alma.accecs.model;

import java.util.ArrayList;
import java.util.Collection;

public class Machine {

    private String name;
    private Collection<Variable> variables;
    private Collection<Property> properties;
    private Collection<Event> events;

    public Machine() {
        name = new String();
        variables = new ArrayList<Variable>();
        properties = new ArrayList<Property>();
        events = new ArrayList<Event>();
    }

    public Machine(String name, Collection<Variable> variables, Collection<Property> properties, Collection<Event> events) {
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
}
