package fr.univ.nantes.alma.accecs.model;

/**
 * Class which represent the integer variable
 * @author Alexis Giraudet
 */
public class VariableInteger extends Variable<Integer> {
    public VariableInteger(String name, Category category, Integer lowerBound, Integer upperBound) {
        super(name, category, "INT", lowerBound, upperBound, 0);
    }

    public VariableInteger(String name, Category category, Integer lowerBound, Integer upperBound, Integer defaultValue) {
        super(name, category, "INT", lowerBound, upperBound, defaultValue);
    }
}
