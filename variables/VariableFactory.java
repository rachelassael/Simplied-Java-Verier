package oop.ex6.main.variables;

import oop.ex6.main.simpleObjects.typeException.IllegalTypeException;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.simpleObjects.typeException.NullValue;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.varExceptions.NameException;
import oop.ex6.main.variables.varExceptions.NotInitializedFinal;
import oop.ex6.main.variables.varExceptions.VariableException;

import java.util.ArrayList;

/**
 * Factory of variable objects
 */
public class VariableFactory {

    /**
     * Magic numbers representing constants of type and default strings
     */
    private static final String INT = "int", DOUBLE = "double", STRING = "String", CHAR = "char", BOOLEAN =
            "boolean", DEFAULT_ZERO = "0", DEFAULT_STRING = "\"DEFAULT\"", DEFAULT_CHAR = "\'o\'";

    /**
     * Creates variable when value supplied
     *
     * @param isFinal   is the var final
     * @param varType   - what type of variable
     * @param varsNames the names of variable to create
     * @param value     - the value assigned
     * @param isGlobal  - are they global or not
     * @return - an array list of variables created
     */
    static public ArrayList<Variable> createVariableWithValue(boolean isFinal, String varType,
                                                              String[] varsNames, String value,
                                                              boolean isGlobal) throws VariableException,
            TypeException {
        ArrayList<Variable> allVars = new ArrayList<Variable>();
        for (String varName : varsNames) {
            Variable newVar = new Variable(isFinal, varName, varType, value, isGlobal);
            allVars.add(newVar);
        }
        return allVars;
    }

    /**
     * Creates variable when Variable object supplied
     *
     * @param isFinal       is the var final
     * @param varType       - what type of variable
     * @param varsNames     the names of variable to create
     * @param variableValue - the variable which was assigned to the new variable to be created
     * @param isGlobal      - are they global or not
     * @return - an array list of variables created
     */
    static public ArrayList<Variable> createVariableWithVariable(boolean isFinal, String varType, String[]
            varsNames, Variable variableValue, boolean isGlobal) throws TypeException, VariableException {
        ArrayList<Variable> allVars = new ArrayList<Variable>();
        for (String varName : varsNames) {
            Variable newVar = new Variable(isFinal, varName, varType, variableValue, isGlobal);
            allVars.add(newVar);
        }
        return allVars;
    }

    /**
     * Creates variable of method parameters
     *
     * @param isFinal is the var final
     * @param varType - what type of variable
     * @param varName the name of variable to create
     * @return - an array list of variables created
     */
    static public Variable createParamsVariable(boolean isFinal, String varType, String varName)
            throws TypeException, VariableException {
        String varVal = null;
        switch (varType) {
            case INT:
            case DOUBLE:
            case BOOLEAN:
                varVal = DEFAULT_ZERO;
                break;
            case STRING:
                varVal = DEFAULT_STRING;
                break;
            case CHAR:
                varVal = DEFAULT_CHAR;
                break;
        }
        return new Variable(isFinal, varName, varType, varVal, false);
    }

}
