package fr.univ.nantes.alma.accecs.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexis Giraudet
 */
public class EnumerateType {
    private String name;
    private Collection<String> values;

    public EnumerateType() {
        name = new String();
        values = new HashSet<String>();
    }

    public EnumerateType(String name, Collection<String> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getValues() {
        return values;
    }

    public void setValues(Collection<String> values) {
        this.values = values;
    }
}
