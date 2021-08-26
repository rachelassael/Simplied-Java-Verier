package oop.ex6.main.simpleObjects.typeException;

/**
 * Extends from type exception, if the type is not valid
 */
public class IllegalTypeException extends TypeException {

    /**
     * informative msg
     */
    private final static String ERROR_MSG = "Invalid Type";

    /**
     * Constructor
     */
    public IllegalTypeException(){
        super(ERROR_MSG);
    }
}
