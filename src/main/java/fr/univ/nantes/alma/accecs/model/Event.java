package fr.univ.nantes.alma.accecs.model;

/**
 *
 */
public class Event {
    public enum Role {
        SENSING,
        MONITORING,
        CONTROL,
        REACTION
    }

    private String name;
    private Role role;

    public Event() {
    }

    public Event(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
