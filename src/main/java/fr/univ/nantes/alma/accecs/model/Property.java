package fr.univ.nantes.alma.accecs.model;

/**
 *
 */
public class Property {
    public enum Role {
        SAFETY,
        NON_FUNCTIONAL,
        LIVENESS
    }

    private String name;
    private Role role;

    public Property() {
    }

    public Property(String name, Role role) {
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
