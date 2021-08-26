package oop.ex6.main.variables;

import oop.ex6.main.simpleObjects.*;
import oop.ex6.main.simpleObjects.typeException.*;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.varExceptions.*;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class of variable object
 */
public class Variable {

    /** Pattern representing variable*/
    private static final Pattern VAR_NAME_PATTERN = Pattern.compile("(?:[a-zA-Z]+[\\w_]*)|_[\\w_]+");

    /** true if the variable is final, false otherwise */
    private final boolean isFinal;

    /** the name of the variable */
    private String name;

    /** the value of the variable */
    private GeneralObject variableValue;

    /** true if variable is global, false otherwise*/
    private Boolean isGlobal;

    /** The value given to the variable*/
    private CharSequence originalValue;



    /**
     * create a variable instance according to the given values.
     * @param isFinal true if the variable is final, false otherwise
     * @param varName String, the variable name
     * @param varType String, the name of the variable type
     * @param varValue String, the value of the variable
     */
    public Variable(boolean isFinal, String varName, String varType, String varValue, Boolean isGlobal)
            throws VariableException, TypeException {
        this.isFinal = isFinal;
        this.checkName(varName);
        this.isGlobal = isGlobal;
        if (isGlobal) {
            this.originalValue = varValue;
        }
        this.variableValue = ObjectFactory.createObj(varType, varValue);
        /* if the variable is final and it didn't initialized */
        if (this.isFinal && this.variableValue.isNull()) {
            throw new NotInitializedFinal();
        }
    }

    /**
     * create a variable instance according to the given values.
     * @param isFinal true if the variable is final, false otherwise
     * @param varName String, the variable name
     * @param varType String, the name of the variable type
     * @param varValue String, the value of the variable
     */
    public Variable(boolean isFinal, String varName, String varType, Variable varValue, Boolean isGlobal)
            throws VariableException, TypeException{
        if (varValue == null) {
            throw new NullValue();
        }
        this.isFinal = isFinal;
        this.checkName(varName);
        this.isGlobal = isGlobal;
        this.variableValue = ObjectFactory.createObj(varType, varValue);
        /* if the variable is final and it didn't initialized */
        if (this.isFinal && this.variableValue.isNull()) {
            throw new NotInitializedFinal();
        }
    }

    /**
     * checks if the variable's name is legal
     * @param varName String, the name of the variable
     * @throws NameException if the name is invalid
     */
    private void checkName(String varName) throws NameException {
        Matcher matcher = VAR_NAME_PATTERN.matcher(varName);
        if (!matcher.matches()) throw new NameException();
        this.name = varName;
    }

    /**
     * @return true if the variable is final, false otherwise
     */
    public boolean getIsFinal() {
        return this.isFinal;
    }

    /**
     * Returns the name of variable obj
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the value of the variable obj
     **/
    public GeneralObject getValue() {
        return this.variableValue;
    }

    /**
     * Sets the value of variable
     **/
    public void setValue(GeneralObject newVal) throws FinalVariableCannotChanged {
        if (this.isFinal) {
            throw new FinalVariableCannotChanged();
        } else {
            this.variableValue = newVal;
        }
    }

    /**
     * Changes the variable value with another value
     * @param newValue - new val
     */
    public void changeVarValueWithValue(String newValue) throws TypeException,VariableException {
        if (this.isFinal) {
            throw new FinalVariableCannotChanged();
        }
        String expectedType = this.getValue().getType();
        this.variableValue = ObjectFactory.createObj(expectedType, newValue);
    }

    /**
     * Changes the variable value with another value
     * @param newValueVar - the variable value of anoter variable obj
     */
    public void changeVarValueWithVariable(Variable newValueVar) throws VariableException, TypeException {
        if (this.isFinal) {
            throw new FinalVariableCannotChanged();
        }
        String expectedType = this.getValue().getType();
        this.variableValue = ObjectFactory.createObj(expectedType, newValueVar);
    }

    /**
     * Returns if the variable is global
     */
    public Boolean getIsGlobal(){
        return this.isGlobal;
    }

    /**
     * If the Variable is global Variable, change the value to the original value.
     * @param variable - reset to old variable obj
     */
    public void resetGlobalVar(Variable variable) {
        if (this.isGlobal) {
            variable.variableValue.setValue(this.originalValue);
        }
    }
}
