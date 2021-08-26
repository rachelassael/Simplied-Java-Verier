package oop.ex6.main.linesReader.lineExceptions;

/**
 * Extends from line exception abstract class
 * called when the line creates a comment which is not valid
 */
public class InValidCommentLine extends LineException {

    /**
     * Error message explaining the error
     */
    private static final String IN_VALID_COMMENT_MSG = "There is a comment line in wrong format";

    /**
     * Constructor of the class
     */
    public InValidCommentLine() {
        super(IN_VALID_COMMENT_MSG);
    }
}
