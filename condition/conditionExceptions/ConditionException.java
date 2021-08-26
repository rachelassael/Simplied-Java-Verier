package oop.ex6.main.condition.conditionExceptions;

/**
 * This class extends from Exception and creates error exception thrown when there is an error with the
 * condition block
 */
public class ConditionException extends Exception {

    /** Error msg to be printed*/
    static final String ERROR_MSG = "This condition format is wrong";

    /**
     * Constructor of the class
     */
    public ConditionException() {
        super(ERROR_MSG);
    }
}


