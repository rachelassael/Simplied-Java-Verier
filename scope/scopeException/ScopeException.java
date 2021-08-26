package oop.ex6.main.scope.scopeException;

/**
 * Abstract class representing scope exceptions
 */
public abstract class ScopeException extends Exception {

    /**
     * Constructor
     * @param error_msg message to push
     */
    ScopeException(String error_msg){
        super(error_msg);
    }
}
