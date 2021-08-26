package oop.ex6.main.scope;

import oop.ex6.main.scope.scopeException.AlreadyExistVariable;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;

/**
 * Represents inner scope
 */
public class InnerScope extends Scope {

    /**
     * All global vars changed into this scope
     */
    private ArrayList<Variable> globalVarChanged;

    /**
     * this scope is child of
     */
    private InnerScope parentScope;

    /**
     * Belongs to global scope
     */
    private GlobalScope globalScope;

    /**
     * Constructor
     *
     * @param parentScope - the parent inner scope
     * @param globalScope - global scope
     */
    public InnerScope(InnerScope parentScope, GlobalScope globalScope) {
        if (parentScope != null) {
            this.parentScope = parentScope;
            this.globalVariables = parentScope.getGlobalVariables();
            this.globalVariables.addAll(parentScope.getLocalVariables());
        } else {
            this.globalVariables = globalScope.getGlobalVariables();
        }
        this.knownMethods = globalScope.getKnownMethods();
        this.isGlobal = false;
        this.localVariables = new ArrayList<>();
        this.globalVarChanged = new ArrayList<>();
        this.globalScope = globalScope;
    }

    /**
     * Returns this parent scope
     */
    public InnerScope getParentScope() {
        return this.parentScope;
    }

    /**
     * Add variable to local inner scope vars
     *
     * @param newVar var to add
     * @throws AlreadyExistVariable
     */
    @Override
    public void addVariable(Variable newVar) throws AlreadyExistVariable {
        if (this.isVarExistInLocal(newVar.getName()) != null) {
            throw new AlreadyExistVariable();
        } else {
            this.localVariables.add(newVar);
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
        Variable varInLocal = this.isVarExistInLocal(varName);
        if (varInLocal != null) {
            return varInLocal;
        } else {
            return this.isVarExistInGlobal(varName);
        }
    }

    /**
     * Get variable name and returns this variable if it is exist in local variables, otherwise returns null.
     *
     * @param varName the name of the variable.
     * @return Variable object or null
     */
    public Variable isVarExistInLocal(String varName) {
        if (this.localVariables != null) {
            for (Variable var : this.localVariables) {
                if (var.getName().equals(varName)) {
                    return var;
                }
            }
        }
        return null;
    }
}
