package oop.ex6.main.linesReader.lineExceptions;


/**
 * Extends from line exception abstract class
 * called when a scope was open but was not closed as supposed
 */
public class UnclosedScope extends LineException {

    /**
     * Error message explaining the error
     */
    private final static String ERROR_MSG = "The Inner scope was not closed as well";

    /**
     * Constructor of the class
     */
    public UnclosedScope(){
        super(ERROR_MSG);
    }


}
