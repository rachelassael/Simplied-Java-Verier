package oop.ex6.main.linesReader.lineExceptions;

/**
 * Abstract class representing the Exception thrown when reading a line of the file
 */
public abstract class LineException extends Exception{

    /**
     * Constructor of the class
     * @param msg - message to push
     */
    public LineException(String msg) {
        super(msg);
    }
}
