package oop.ex6.main.linesReader.lineExceptions;

/**
 * Extends from line exception abstract class
 * called when the method call was not right
 */
public class WrongMethodCall extends LineException {

    /**
     * Error message explaining the error
     */
    private final static String ERROR_MSG = "The method call is not right";

    /**
     * Constructor of the class
     */
    public WrongMethodCall() {
        super(ERROR_MSG);
    }
}
