/**
 * Class which representing the B events 
 * 
 * 
 * */


package fr.univ.nantes.alma.accecs.model;

public class Event {
	/**
	 * String which represent the expression of the B event (example : )  
	 * */
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
    /**
     * Different category of event
     * 
     * */
    public enum Category {
        SENSING,
        MONITORING,
        CONTROL,
        REACTION
    }
}
