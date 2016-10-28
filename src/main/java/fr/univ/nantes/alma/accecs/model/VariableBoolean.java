package fr.univ.nantes.alma.accecs.model;

/**
 * @author Alexis Giraudet
 */
public class VariableBoolean extends Variable<Boolean> {
    public VariableBoolean(String name, Category category) {
        super(name, category, Type.BOOLEAN, false, true, false);
    }

    public VariableBoolean(String name, Category category, Boolean defaultValue) {
        super(name, category, Type.BOOLEAN, false, true, defaultValue);
    }
}
