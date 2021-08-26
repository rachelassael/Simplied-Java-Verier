package oop.ex6.main.method.methodExceptions;

/**
 * Exception class extends from MethodException
 */
public class AlreadyExistingMethod extends MethodException {

    /**Informative message*/
    private static final String EXISTS_ERROR_MSG = "This Method already exists";

    /**
     * Constructor
     */
    public AlreadyExistingMethod() {
        super(EXISTS_ERROR_MSG);
    }
}
