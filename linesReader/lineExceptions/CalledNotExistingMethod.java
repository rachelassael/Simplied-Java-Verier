package oop.ex6.main.linesReader.lineExceptions;

/**
 * Extends from line exception abstract class
 * called when the line calls a method which does not exit
 */
public class CalledNotExistingMethod extends LineException {

    /**
     * Error message explaining the error
     */
    private final static String ERROR_MSG = "This method does not exist";

    /**
     * Constructor of the class
     */
    public CalledNotExistingMethod(){
        super(ERROR_MSG);
    }


}
