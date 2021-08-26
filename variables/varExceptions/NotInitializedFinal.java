package oop.ex6.main.variables.varExceptions;

/**
 * Extends from Variable exception - thrown when variable was set final but not initialized
 */
public class NotInitializedFinal extends VariableException {

    /** Error msg to push*/
    private final static String ERROR_MSG = "Final variable was not initialized";

    /**
     * Constructor of class
     */
    public NotInitializedFinal(){
        super(ERROR_MSG);
    }
}
