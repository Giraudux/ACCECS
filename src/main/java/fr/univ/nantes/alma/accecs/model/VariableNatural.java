package fr.univ.nantes.alma.accecs.model;

/**
 * Class which represent the natural variable
 * @author Alexis Giraudet
 */
public class VariableNatural extends Variable<Integer> {
    public VariableNatural(String name, Category category, Integer lowerBound, Integer upperBound) {
        super(name, category, "NAT", lowerBound, upperBound, 0);
    }

    public VariableNatural(String name, Category category, Integer lowerBound, Integer upperBound, Integer defaultValue) {
        super(name, category, "NAT", lowerBound, upperBound, defaultValue);
    }
}
