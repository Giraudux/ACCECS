package fr.univ.nantes.alma.accecs.model;

public class Event {

    private String name;
    private String expression;
    private Category category;

    public Event() {
    }

    public Event(String expression, Category category) {
        this.expression = expression;
        this.category = category;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Category {
        SENSING,
        MONITORING,
        CONTROL,
        REACTION,
        CUSTOM
    }
}
