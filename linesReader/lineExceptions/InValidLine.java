package oop.ex6.main.linesReader.lineExceptions;

/**
 * Extends from line exception abstract class
 * called when the line is not valid format
 */
public class InValidLine extends LineException {

    /**
     * Error message explaining the error
     */
    private final static String ERROR_MSG = "The format of the line is not valid";

    /**
     * Constructor of the class
     */
    public InValidLine(){
        super(ERROR_MSG);
    }
}
