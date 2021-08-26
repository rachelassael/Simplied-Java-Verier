package oop.ex6.main.scope;

import oop.ex6.main.method.Method;
import oop.ex6.main.scope.scopeException.AlreadyExistVariable;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents standard scope
 */
public abstract class Scope {

    /**
     * known methods of scope
     */
    protected ArrayList<Method> knownMethods;

    /**
     * global variables the scope knows
     */
    protected ArrayList<Variable> globalVariables;

    /**
     * local vars of the scope
     */
    protected ArrayList<Variable> localVariables;

    /**
     * which global vars have changed
     */
    protected ArrayList<Variable> globalVarChanged;

    /** is it a global scope?*/
    protected boolean isGlobal;

    /**
     * Constructor
     */
    public Scope() {
        this.knownMethods = new ArrayList<>();
        this.globalVariables = new ArrayList<>();
        this.isGlobal = true;
        this.globalVarChanged = new ArrayList<>();
        this.localVariables = new ArrayList<>();
    }


    /**
     * Get method name and returns this method if it is exist, otherwise returns null.
     *
     * @param methodName the name of the method.
     * @return Method object or null
     */
    public Method isMethodExist(String methodName) {
        for (Method method : this.knownMethods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Add var to scope
     * @param newVar new variable
     * @throws ScopeException
     */
    public abstract void addVariable(Variable newVar) throws ScopeException;


    /**
     * Get variable name and returns this variable in the most inner Scope that exist in the global scopes,
     * return null if this variable is not exist in the global variables.
     *
     * @param varName the name of the variable.
     * @return Variable object or null
     */
    public Variable isVarExistInGlobal(String varName) {
        for (Variable var : this.globalVariables) {
            if (var.getName().equals(varName)) {
                return var;
            }
        }
        return null;
    }


    /**
     * Get variable name and returns this variable in the most inner Scope that exist, return null if this
     * variable is not exist in the local and global variables.
     *
     * @param varName the name of the variable.
     * @return Variable object or null
     */
    public abstract Variable isScopeKnowVar(String varName);


    /**
     * This method returns the knownMethods of the scope
     *
     * @return array list of Methods
     */
    public ArrayList<Method> getKnownMethods() {
        return knownMethods;
    }

    /**
     * This method returns the globalVariables of the scope
     *
     * @return - array list of variables
     */
    public ArrayList<Variable> getGlobalVariables() {
        return globalVariables;
    }

    public ArrayList<Variable> getLocalVariables() {
        return localVariables;
    }

    /**
     * Returns if is global or not
     */
    public boolean getIsGlobal() {
        return this.isGlobal;
    }


    /**
     * This method adds the old Variable that was changed in the inner scope
     *
     * @param oldGlobalVar - Variable that was changed
     */
    public void addGlobalVariablesChanged(Variable oldGlobalVar) {
        this.globalVarChanged.add(oldGlobalVar);
    }

    /**
     * Returns the array list of variable that are global and were changed during the reading
     * of an inner scope
     */
    public ArrayList<Variable> getGlobalVarChanged() {
        return this.globalVarChanged;
    }


}

