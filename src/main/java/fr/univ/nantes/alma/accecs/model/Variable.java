package fr.univ.nantes.alma.accecs.model;

/**
 *
 */
public abstract class Variable<T> {
    public enum Role {
        INPUT,
        OUTPUT,
        CONTROL
    }

    protected String name;
    protected T initialisation;
    Role role;

    public Variable() {
    }

    public Variable(String name, T initialisation, Role role) {
        this.name = name;
        this.initialisation = initialisation;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getInitialisation() {
        return initialisation;
    }

    public void setInitialisation(T initialisation) {
        this.initialisation = initialisation;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
