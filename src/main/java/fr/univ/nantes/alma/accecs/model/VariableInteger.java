package fr.univ.nantes.alma.accecs.model;

/**
 * @author Alexis Giraudet
 */
public class VariableInteger extends Variable<Integer> {
    public VariableInteger(String name, Category category, Integer lowerBound, Integer upperBound) {
        super(name, category, Type.INTEGER, lowerBound, upperBound, 0);
    }

    public VariableInteger(String name, Category category, Integer lowerBound, Integer upperBound, Integer defaultValue) {
        super(name, category, Type.INTEGER, lowerBound, upperBound, defaultValue);
    }
}
