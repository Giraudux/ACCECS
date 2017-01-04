package fr.univ.nantes.alma.accecs.model;

/**
 * @author Alexis Giraudet
 */
public class VariableEnum extends Variable<String> {
    public VariableEnum(String name, Category category, String type, String defaultValue) {
        super(name, category, type, null, null, defaultValue);
    }
}
