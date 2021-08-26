package oop.ex6.main.linesReader;

import oop.ex6.main.condition.Condition;
import oop.ex6.main.condition.conditionExceptions.*;
import oop.ex6.main.linesReader.lineExceptions.*;
import oop.ex6.main.method.Method;
import oop.ex6.main.method.methodExceptions.InvalidMethodName;
import oop.ex6.main.method.methodExceptions.MethodException;
import oop.ex6.main.method.methodExceptions.MissingReturnStatement;
import oop.ex6.main.scope.GlobalScope;
import oop.ex6.main.scope.InnerScope;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.Variable;
import oop.ex6.main.variables.varExceptions.VariableException;

import java.util.ArrayList;

/**
 * Extends from ReadOneLine - purpose is to read all the lines inside the methods scopes created
 */
public class ReadInnerScopes extends ReadOneLine {

    /**
     * Constructor of the class
     */
    public ReadInnerScopes() {
    }

    /**
     * Read all the line the method object array lines
     * @param globalScope - the global scope
     * @throws LineException exception line thrown
     * @throws TypeException exception type thrown
     * @throws MethodException exception method thrown
     * @throws ConditionException exception condition thrown
     * @throws VariableException exception variable thrown
     * @throws ScopeException exception scope thrown
     */
    public void readMethodsScope(GlobalScope globalScope) throws
            LineException, TypeException, MethodException, ConditionException, VariableException,
            ScopeException {
        ArrayList<Method> knownMethods = globalScope.getKnownMethods();

        for (Method method : knownMethods) {
            ArrayList<String> methodLines = method.getArrayLines();
            this.checkMethodEnd(method, methodLines);
            InnerScope curScope = new InnerScope(null, globalScope);
            for (Variable methodParam : method.getMethodParams()) {
                curScope.addVariable(methodParam);
            }
            // Run in a loop for every line of the method
            int linesCounter = 0;
            ArrayList<Variable> globalVarChanged = new ArrayList<>();
            for (String line : methodLines) {
                linesCounter++;
                this.resetMatchers(line);
                if (optionalConditionMatcher.find()) {
                    curScope = ReadConditionLine.checkCondition(line, curScope, globalScope, this.conditionMatcher);
                } else if (this.methodCallMatcher.matches()) {
                    ReadMethodCall.readMethodCall(line, curScope);
                } else if (this.commentMatcher.matches()) {
                    ReadCommentLine.isValidCommentLine(line);
                } else if (this.variableDeclarationMatcher.matches()) {
                    ReadVariableDeclarationLine.createVariable(line, curScope);
                } else if (this.variableAssignmentMatcher.matches()) {
                    ReadVariableAssignmentLine.readLine(line, curScope, globalVarChanged);
                } else if (this.returnMatcher.matches()) {
                    continue;
                    // It means it is the last line
                } else if (this.endOfScopeMatcher.matches()) {
                    // If this is the last line
                    if (linesCounter == method.getArrayLines().size()) {
                        // If the last line is in inner scope and not in the method scope
                        if (curScope.getParentScope() != null) {
                            throw new UnclosedScope();
                        } else {
                            this.resetGlobalVars(globalVarChanged);
                        }
                    } else {
                        curScope = curScope.getParentScope();
                    }
                } else if (this.blankLineMatcher.matches()) {
                    continue;
                } else {
                    throw new InValidLine();
                }
            }
        }
    }

    /**
     * This method is in charge to reset the global variables after reading a method.
     */
    private void resetGlobalVars(ArrayList<Variable> globalVarChanged) {
        for (Variable variable : globalVarChanged) {
            variable.resetGlobalVar(variable);
        }
    }

    /**
     * This method checks if the method has end correctly
     * @param method method object
     * @param methodLines the method array lines
     * @throws MethodException - exception method thrown
     * @throws LineException exception line thrown
     */
    private void checkMethodEnd(Method method, ArrayList<String> methodLines) throws MethodException,
            LineException {
        if (methodLines.size() < 1) {
            throw new InvalidMethodName();
        } else if (!method.checkEnfOfMethodScope()) {
            throw new UnclosedScope();
        } else if (!method.checkReturnStatement())
            throw new MissingReturnStatement();
    }
}

