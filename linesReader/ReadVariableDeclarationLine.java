package oop.ex6.main.linesReader;

import oop.ex6.main.scope.Scope;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.Variable;
import oop.ex6.main.variables.VariableFactory;
import oop.ex6.main.variables.varExceptions.VariableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extends from ReadOneLine - purpose is to read a declaration of variable line
 */
public class ReadVariableDeclarationLine extends ReadOneLine {

    /** Pattern of Type keyword, with or without spaces */
    private final static Pattern TYPE_PATTERN = Pattern.compile("\\s*(?:String|int|double|boolean|char)\\s*");

    /** Pattern of Type keyword, with or without spaces */
    private final static String COMMAS_SPLIT = "\\s*[,;]\\s*";

    /** A pattern of line that start with final starting line */
    private final static Pattern FINAL_START = Pattern.compile("\\s*final\\s+");

    /**
     * creates the Variable objects as define in the given line String and add them to the given scope. throw
     * an exception if there is a problem with the variables defining.
     * @param line String of one line of variables defining
     * @param currentScope The scope that the given line is appear
     * @throws TypeException if the value is not match to the variable type
     * @throws VariableException if there is final variable without initialize, or if one of the variables
     * name is not valid.
     */
    static void createVariable(String line, Scope currentScope) throws TypeException,
            VariableException, ScopeException {
        boolean isFinal = false;
        // If the line start with final keyword, set isFinal and cut the line.
        Matcher finalMatcher = FINAL_START.matcher(line);
        if (finalMatcher.find()) {
            isFinal = true;
            line = line.substring(finalMatcher.end());
        }
        String[] getTypeOutput = getType(line);
        String varType = getTypeOutput[0];
        line = getTypeOutput[1];
        // Now, we have the line without final or type keyword.
        String[] splitLine = line.split("\\s*=\\s*");
        String part = splitLine[0];
        String[] varNames = part.split(COMMAS_SPLIT);
        if (splitLine.length == 1) {
            ArrayList<Variable> variables = new ArrayList<>(VariableFactory.createVariableWithValue
                    (isFinal, varType, varNames, null, currentScope.getIsGlobal()));
            addVarsToScope(variables, currentScope);
            return;
        }
        for (int i = 1; i < splitLine.length; i++) {
            ArrayList<Variable> allLineVars = new ArrayList<>();
            String valuesPart = splitLine[i];
            String[] splitValuesPart = valuesPart.split(COMMAS_SPLIT);
            String varsValue = splitValuesPart[0].replaceAll("\\s*", "");
            Variable isVariableValue = currentScope.isScopeKnowVar(varsValue);
            // Create variables with new value
            if (isVariableValue == null) {
                allLineVars.addAll(VariableFactory.createVariableWithValue(isFinal, varType, varNames,
                        varsValue, currentScope.getIsGlobal()));
            // Create variables with exist Variable
            } else {
                allLineVars.addAll(VariableFactory.createVariableWithVariable(isFinal, varType, varNames,
                        isVariableValue, currentScope.getIsGlobal()));
            }
            addVarsToScope(allLineVars, currentScope);
            // If this there is no another variables names after this value, so this is the last part.
            if (splitValuesPart.length > 1) {
                varNames = Arrays.copyOfRange(splitValuesPart, 1, splitValuesPart.length);
            // this value is the last part of the line
            } else {
                break;
            }
            // If this is the last part of the line
            if (i == splitLine.length-1) {
                allLineVars = new ArrayList<>(VariableFactory.createVariableWithValue
                        (isFinal, varType, varNames, null, currentScope.getIsGlobal()));
                addVarsToScope(allLineVars, currentScope);
                break;
            }
        }
    }

    /**
     * Return an ArrayList of the type that appears at the line start, and the rest of the line (without
     * the type keyword).
     * @param line String of one line that start with type keyword (optional with spaces)
     * @return Array of String - [variableType, line without the type]
     */
    private static String[] getType(String line) {
        Matcher typeMatcher = TYPE_PATTERN.matcher(line);
        typeMatcher.find();
        int endOfTypeIndex = typeMatcher.end();
        String varType = line.substring(0, endOfTypeIndex).replaceAll("\\s", "");
        line = line.substring(endOfTypeIndex);
        return new String[]{varType, line};
    }

    /**
     * adds all the variables in the given array list to the given scope
     * @param vars ArrayList of Variables
     * @param curScope Scope object
     * @throws ScopeException If the variable is already exist in this scope
     */
    private static void addVarsToScope(ArrayList<Variable> vars, Scope curScope) throws ScopeException {
        for (Variable var : vars) {
            curScope.addVariable(var);
        }
    }
}
