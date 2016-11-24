package fr.univ.nantes.alma.accecs.model;

/**
 * Class which represent the different B variable in the Machine  
 * 
 * @author Alexis Giraudet
 */
public abstract class Variable<T> {
    protected String name;
    protected Category category;
    protected String type;
    protected T lowerBound;
    protected T upperBound;
    protected T defaultValue;

    protected Variable(String name, Category category, String type, T lowerBound, T upperBound, T defaultValue) {
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

    public String getType() {
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
    /**
     * fonction which determine if a variable have a lowerBound and a upperBound
     * 
     * @return boolean 
     * */
    public boolean hasBounds() {
        return lowerBound != null && upperBound != null;
    }

    public enum Category {
        INPUT,
        OUTPUT,
        CONTROL,
        INTERNAL
    }
}
