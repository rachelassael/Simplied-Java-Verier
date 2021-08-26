package oop.ex6.main.variables.varExceptions;

/**
 * Extends from Variable exception - thrown when variable is final and there was commandn to change it
 */
public class FinalVariableCannotChanged extends VariableException {

    /** Message explaining the error*/
    private final static String ERROR_MSG = "Final variable cannot changed";

    /**
     * Constructor of class
     */
    public FinalVariableCannotChanged(){
        super(ERROR_MSG);
    }
}
