package oop.ex6.main.simpleObjects.typeException;

/**
 * Abstract class represents type exception thrown
 */
public abstract class TypeException extends Exception {

    /**
     * constructor
     * @param errorMsg - msg to push
     */
    TypeException(String errorMsg) {
        super(errorMsg);
    }
}
