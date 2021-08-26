package oop.ex6.main.condition;

import oop.ex6.main.scope.InnerScope;
import oop.ex6.main.simpleObjects.ObjectFactory;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.Variable;
import oop.ex6.main.condition.conditionExceptions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/***
 * Represents the condition block appearing in the sjava file
 */
public class Condition {

    /**
     * All of the required patterns that represent a condition
     */
    private static final String TRUE = "true", FALSE = "false";

    /**
     * constants representing the type of condition
     */
    private static final ArrayList<String> LEGAL_TYPES = new ArrayList<>(Arrays.asList("int", "double",
            "boolean"));

    /**
     * If the condition string given is Ok
     */
    private boolean isOK;

    /**
     * Constructor of the class
     * @param condition - String inside the condition
     * @param currentScope- the scope which is the condition
     * @throws ConditionException - in case error occurs
     */
    public Condition(String condition, InnerScope currentScope) throws ConditionException {
        createConditions(condition, currentScope);
    }

    /**
     * Checks if the condition is a boolean one
     * @param condition - the string inside the parentheses
     */
    private void checkBoolean(String condition) {
        if (condition.equals(TRUE) || condition.equals(FALSE)) {
            this.isOK = true;
        }
    }

    /**
     * Checks if the variable inside the condition is an initialized variable
     *
     * @param condition - the condition inside the parentheses
     */
    private void checkVariableType(String condition, InnerScope currentScope) {
        Variable var = currentScope.isScopeKnowVar(condition);
        if ((var != null) && (!var.getValue().isNull()) && (LEGAL_TYPES.contains(var.getValue().getType()))) {
            this.isOK = true;
        }
    }

    /**
     * Try to create double object with the condition, if the condition is valid input for double
     * (int/double), change this.isOK to true.
     * @param condition String of the condition
     */
    private void checkDoubleOrIntConstant(String condition) {
        try {
            ObjectFactory.createObj("double", condition);
        } catch (TypeException e) {
            return;
        }
        this.isOK = true;
    }


    /**
     * This method divides the condition line given into the correct number of conditions and check that
     * each part of the condition line is valid
     */
    private void createConditions(String condition, InnerScope currentScope) throws ConditionException {
        String[] conditionArgs = condition.trim().split("[|&]{2}");
        for (int i = 0; i < conditionArgs.length; i += 2) {
            String cond = conditionArgs[i];
            if (conditionArgs[i].equals("")) {
                throw new ConditionException();
            // check if the condition is valid one
            } else {
                cond = cond.trim();
                this.isOK = false;
                this.checkBoolean(cond);
                this.checkVariableType(cond, currentScope);
                this.checkDoubleOrIntConstant(cond);
                if (!this.isOK) {
                    throw new ConditionException();
                }
            }
        }
    }

}

