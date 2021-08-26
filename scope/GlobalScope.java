package oop.ex6.main.scope;

import oop.ex6.main.method.Method;
import oop.ex6.main.method.methodExceptions.AlreadyExistingMethod;
import oop.ex6.main.scope.scopeException.AlreadyExistVariable;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.variables.Variable;

/**
 * Represents object of global scope
 */
public class GlobalScope extends Scope {

    /**
     * Constructor
     */
    public GlobalScope() {
        super();
    }


    /**
     * Add method to methods list
     *
     * @param newMethod - new method object to add to global list
     */
    public void addMethod(Method newMethod) throws AlreadyExistingMethod {
        if (this.isMethodExist(newMethod.getName()) != null) {
            throw new AlreadyExistingMethod();
        } else {
            this.knownMethods.add(newMethod);
        }
    }

    /**
     * Adds variable to list of global vars
     * @param newVar new var to add
     * @throws ScopeException
     */
    public void addVariable(Variable newVar) throws ScopeException {
        if (this.isScopeKnowVar(newVar.getName()) != null) {
            throw new AlreadyExistVariable();
        } else {
            this.globalVariables.add(newVar);
        }
    }

    /**
     * Get variable name and returns this variable in the most inner Scope that exist, return null if this
     * variable is not exist in the local and global variables.
     *
     * @param varName the name of the variable.
     * @return Variable object or null
     */
    @Override
    public Variable isScopeKnowVar(String varName) {
        return this.isVarExistInGlobal(varName);
    }
}
