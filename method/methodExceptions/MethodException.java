package oop.ex6.main.method.methodExceptions;

/**
 * Abstract class represents all the method exceptions thrown
 */
public abstract class MethodException extends Exception{

    /**
     * Informative message
     */
    private static final String ERR = "Problem with method line";

    /**
     * Constructor when msg received
     * @param msg - Informative message
     */
    public MethodException(String msg) {
        super(msg);
    }

    /**
     * Constructor when no msg received
     */
    public MethodException() {
        super(ERR);
    }
}
