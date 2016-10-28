package fr.univ.nantes.alma.accecs.model;

public class Property {

    private String expression;
    private Category category;
    public Property() {
    }

    public Property(String expression, Category category) {
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

    public enum Category {
        SAFETY,
        NON_FUNCTIONAL,
        LIVENESS
    }
}
