package oop.ex6.main.linesReader;

import oop.ex6.main.scope.Scope;
import oop.ex6.main.scope.scopeException.InExistVariable;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.Variable;
import oop.ex6.main.variables.varExceptions.VariableException;
import java.util.ArrayList;


/**
 * Extends from ReadOneLine - purpose is to read an assignment of variable line
 */
public class ReadVariableAssignmentLine extends ReadOneLine {

    /**
     * Reads the assignment line in file
     * @param line             line string given
     * @param currentScope     curr scope
     * @param globalVarChanged - array list of variables to remember what was changed
     */
    static void readLine(String line, Scope currentScope, ArrayList<Variable> globalVarChanged) throws
            ScopeException, TypeException, VariableException {
        String[] lineParts = line.split("\\s*[=;]\\s*");
        String varForChangeName = lineParts[0].replaceAll("\\s*", "");
        Variable varForChange = currentScope.isScopeKnowVar(varForChangeName);
        if (varForChange == null) {
            throw new InExistVariable();
        }
        if ((!currentScope.getIsGlobal()) && (varForChange.getIsGlobal())) {
            globalVarChanged.add(varForChange);
        }
        String newValue = lineParts[1].replaceAll("\\s*", "");
        // is the new value is another variable
        Variable varForNewValue = currentScope.isScopeKnowVar(newValue);
        // The new value is Variable
        if (varForNewValue != null) {
            varForChange.changeVarValueWithVariable(varForNewValue);
        } else {
            varForChange.changeVarValueWithValue(newValue);
        }
    }
}
