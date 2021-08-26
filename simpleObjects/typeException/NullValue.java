package oop.ex6.main.simpleObjects.typeException;

/**
 * Extends from scope exception, if null was received
 */
public class NullValue extends TypeException {

    /**
     * informative msg
     */
    private final static String ERROR_NULL = "cannot create new variable with another uninitialized variable";

    /**
     * Constructor
     */
    public NullValue() {
        super(ERROR_NULL);
    }
}
