package oop.ex6.main.method.methodExceptions;

/**
 * Exception class extends from MethodException when name not valid
 */
public class InvalidMethodName extends MethodException{

    /**Informative msg*/
    private final static String ERROR_MSG = "The method name in not valid";

    /**
     * Constructor
     */
    public InvalidMethodName(){
        super(ERROR_MSG);
    }
}
