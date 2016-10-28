package fr.univ.nantes.alma.accecs.model;

/**
 * @author Alexis Giraudet
 */
public abstract class Variable<T> {
    protected String name;
    protected Category category;
    protected Type type;
    protected T lowerBound;
    protected T upperBound;
    protected T defaultValue;

    protected Variable(String name, Category category, Type type, T lowerBound, T upperBound, T defaultValue) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public T getLowerBound() {
        return lowerBound;
    }

    public T getUpperBound() {
        return upperBound;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public enum Category {
        INPUT,
        OUTPUT,
        CONTROL,
        INTERNAL
    }

    public enum Type {
        INTEGER,
        FLOAT,
        BOOLEAN
    }
}
