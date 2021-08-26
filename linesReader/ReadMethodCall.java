package oop.ex6.main.linesReader;

import oop.ex6.main.linesReader.lineExceptions.CalledNotExistingMethod;
import oop.ex6.main.linesReader.lineExceptions.LineException;
import oop.ex6.main.linesReader.lineExceptions.WrongMethodCall;
import oop.ex6.main.method.Method;
import oop.ex6.main.method.MethodFactory;
import oop.ex6.main.method.methodExceptions.InvalidMethodName;
import oop.ex6.main.method.methodExceptions.MethodException;
import oop.ex6.main.method.methodExceptions.WrongParameterGiven;
import oop.ex6.main.scope.InnerScope;
import oop.ex6.main.simpleObjects.ObjectFactory;
import oop.ex6.main.simpleObjects.typeException.IllegalTypeException;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.Variable;
import oop.ex6.main.variables.VariableFactory;
import oop.ex6.main.variables.varExceptions.VariableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extends from ReadOneLine - purpose is to read a method call line
 */
class ReadMethodCall extends ReadOneLine {

    /**
     * Patterns constant
     */
    private static final Pattern methodNamePattern = Pattern.compile("\\s*[a-zA-Z]+[\\w_]*\\s*\\("),
            paramsMethodPattern = Pattern.compile("\\(.*\\)");

    /**
     * Represents an empty string array of params given
     */
    private static final String[] EMPTY_PARAM_ARRAY = new String[]{""};

    /**
     * Reads a line of the method called
     * @param line         line given in file
     * @param currentScope - the inner scope curr
     * @return Method obj called
     * @throws MethodException         - thrown in case of error
     * @throws CalledNotExistingMethod thrown in case of error
     */
    private static Method readMethod(String line, InnerScope currentScope) throws MethodException,
            CalledNotExistingMethod {
        // Now checks if the name starts by any letter
        Matcher matcherName = methodNamePattern.matcher(line);
        if (!matcherName.find()) {
            throw new InvalidMethodName();
        }
        String methodName = line.substring(matcherName.start(), matcherName.end());
        methodName = methodName.trim().replaceAll("\\s+|\\(", "");
        // Checks if the method exists
        if (currentScope.isMethodExist(methodName) == null) {
            throw new CalledNotExistingMethod();
        } else {
            return currentScope.isMethodExist(methodName);
        }
    }

    /**
     * Private method that checks if the params inserted in the call are correct to the method called
     * @param paramsLine - the params given
     * @param methodCalled - the method obj called
     * @param currentScope - the current scope it appears
     * @throws WrongParameterGiven thrown in case of error
     */
    private static void checkMethodParams(String paramsLine, Method methodCalled, InnerScope currentScope)
            throws WrongParameterGiven {
        Matcher paramsMatcher = paramsMethodPattern.matcher(paramsLine);
        if (paramsMatcher.find()) {
            paramsLine = paramsLine.substring(paramsMatcher.start() + 1, paramsMatcher.end() - 1);
            String[] params = paramsLine.trim().split(",");
            ArrayList<Variable> methodParams = methodCalled.getMethodParams();
            if (methodParams.size() == 0 && (Arrays.equals(params, EMPTY_PARAM_ARRAY))) {
                return;
            } else if (methodParams.size() != params.length) {
                throw new WrongParameterGiven();
            }
            int i = 0;
            for (Variable param : methodParams) {
                String typeNeeded = param.getValue().getType();
                // check if the given param is a Variable, or a value
                Variable inputParam = currentScope.isScopeKnowVar(params[i]);
                try {
                    // The given parameter is a variable, try to create object in the expected Type with this
                    // variable.
                    if (inputParam != null) {
                        ObjectFactory.createObj(typeNeeded, inputParam);
                        i++;
                        continue;
                        // if the value of the parameter is not a variable
                    } else {
                        ObjectFactory.createObj(typeNeeded, params[i].trim());
                    }
                    i++;
                } catch (TypeException e) {
                    throw new WrongParameterGiven();
                }
            }
        }
    }

    /**
     * Calls all the method of the class, does all the checking process
     * @param line line given of method call
     * @param currentScope curr inner scope
     * @throws LineException thrown exception
     * @throws MethodException thrown exception
     */
    public static void readMethodCall(String line, InnerScope currentScope) throws
            LineException, MethodException {
        Method methodCalled = readMethod(line, currentScope);
        checkMethodParams(line, methodCalled, currentScope);
    }

}

