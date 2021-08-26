package oop.ex6.main.variables.varExceptions;

/**
 * Extends from Variable exception - thrown when variable got not correct name
 */
public class NameException extends VariableException{

    /** Message error to push*/
    private final static String ERROR_MSG = "Invalid variable name";

    /**
     * Constructor of class
     */
    public NameException(){
        super(ERROR_MSG);
    }
}
