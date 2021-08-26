package oop.ex6.main.method.methodExceptions;


/**
 * Exception class extends from MethodException when return is missing
 */
public class WrongParameterGiven extends MethodException {

    /** Informative message*/
    private final static String ERROR_MSG = "The method received a wrong param line";

    /**
     * Constructor
     */
    public WrongParameterGiven() {
        super(ERROR_MSG);
    }
}
