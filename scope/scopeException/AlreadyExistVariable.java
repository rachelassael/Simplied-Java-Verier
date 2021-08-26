package oop.ex6.main.scope.scopeException;

/**
 * Extends from scope exception, if the var already exists in it
 */
public class AlreadyExistVariable extends ScopeException {
    /**
     * Informative msg
     */
    private static final String IN_EXISTS_ERROR_MSG = "This Variable already exists";

    /**
     * Constructor
     */
    public AlreadyExistVariable() {
        super(IN_EXISTS_ERROR_MSG);
    }
}
