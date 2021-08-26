package oop.ex6.main.variables.varExceptions;

/**
 * Abstract class representing the Exception thrown when creating a new Variable object
 */
public abstract class VariableException extends Exception {

    /**
     * Constructor of class
     * @param errorMsg - error msg to push
     */
    public VariableException(String errorMsg) {
        super(errorMsg);
    }
}
