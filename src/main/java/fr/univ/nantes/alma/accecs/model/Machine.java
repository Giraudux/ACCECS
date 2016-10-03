package fr.univ.nantes.alma.accecs.model;

import java.util.Collection;

/**
 *
 */
public class Machine {
    private String name;
    private Collection<Event> events;
    private Collection<Property> properties;
    private Collection<Variable> variables;

    public Machine() {
    }

    public Machine(String name, Collection<Event> events, Collection<Property> properties, Collection<Variable> variables) {
        this.name = name;
        this.events = events;
        this.properties = properties;
        this.variables = variables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }

    public Collection<Variable> getVariables() {
        return variables;
    }

    public void setVariables(Collection<Variable> variables) {
        this.variables = variables;
    }
}
