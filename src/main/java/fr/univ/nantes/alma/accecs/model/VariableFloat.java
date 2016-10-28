package fr.univ.nantes.alma.accecs.model;

/**
 * @author Alexis Giraudet
 */
public class VariableFloat extends Variable<Float> {
    public VariableFloat(String name, Category category, Float lowerBound, Float upperBound) {
        super(name, category, Type.FLOAT, lowerBound, upperBound, 0f);
    }

    public VariableFloat(String name, Category category, Float lowerBound, Float upperBound, Float defaultValue) {
        super(name, category, Type.FLOAT, lowerBound, upperBound, defaultValue);
    }
}
