package oop.ex6.main.method.methodExceptions;

/**
 * Exception class extends from MethodException when return is missing
 */
public class MissingReturnStatement extends MethodException {

    /** informative message*/
    private final static String ERROR_MSG = "The method is missing a return statement";

    /**
     * Constructor
     */
    public MissingReturnStatement() {
        super(ERROR_MSG);
    }
}
