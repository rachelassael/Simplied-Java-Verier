package oop.ex6.main.scope.scopeException;

/**
 * Extends from scope exception, if the vars does not exist but was called
 */
public class InExistVariable extends ScopeException {

    /** informative message*/
    private static final String IN_EXISTS_ERROR_MSG = "This Variable is not exists";

    /**
     * Constructor
     */
    public InExistVariable() {
        super(IN_EXISTS_ERROR_MSG);
    }
}

