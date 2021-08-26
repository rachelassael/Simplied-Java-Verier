package oop.ex6.main.method;

import oop.ex6.main.method.methodExceptions.InvalidMethodName;
import oop.ex6.main.method.methodExceptions.MethodException;
import oop.ex6.main.method.methodExceptions.WrongParameterGiven;
import oop.ex6.main.scope.GlobalScope;
import oop.ex6.main.simpleObjects.typeException.IllegalTypeException;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.varExceptions.NameException;
import oop.ex6.main.variables.varExceptions.NotInitializedFinal;
import oop.ex6.main.variables.varExceptions.VariableException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Factory of methods
 */
public class MethodFactory {

    /** Needed patterns to recognize good methods declaration*/
    private static final Pattern METHOD_NAME_PATTERN = Pattern.compile("^\\s*[a-zA-Z]+[\\w_]*\\s*\\("),
    METHOD_PARAMS_PATTERN = Pattern.compile("\\(.*\\)");


    /**
     * Checks the length, containing letters, uppercase or lowercase, must start with a letter
     *
     * @throws InvalidMethodName - exception thrown
     */
    private static String checkMethodName(String methodLine) throws InvalidMethodName {
        Matcher matcherName = METHOD_NAME_PATTERN.matcher(methodLine);
        if (!matcherName.find()) {
            throw new InvalidMethodName();
        }
        String methodName = methodLine.substring(matcherName.start(), matcherName.end()-1);
        methodName = methodName.replaceAll("\\s*|\\)|void", "");
        return methodName;
    }


    /**
     * This method verifies the param lines into the parentheses of the method matches the pattern.
     * @param methodLine - the method line read by the parser
     * @return - the params line to insert to create a method object
     * @throws WrongParameterGiven - Exception thrown if does not match the pattern.
     */
    public static String insertToMethodParams(String methodLine) throws WrongParameterGiven {
        String paramsLine;
        Matcher matcher = METHOD_PARAMS_PATTERN.matcher(methodLine);
        if (matcher.find()) {
            paramsLine = methodLine.substring(matcher.start(), matcher.end());
            paramsLine = paramsLine.replaceAll("[()]", "");
        } else {
            throw new WrongParameterGiven();
        }
        return paramsLine;
    }

    /**
     * After checking name and validity of parameter line, creates a new method object
     * @param methodLine - the line received from the reading file parser
     * @param lines - the method lines scope
     * @return the object
     */
    public static Method createMethodObj(String methodLine, ArrayList<String> lines, GlobalScope globalScope)
            throws MethodException, TypeException, VariableException {
        methodLine = methodLine.replaceAll("\\s*void\\s*", "");
        String methodName = checkMethodName(methodLine);
        String paramsLine = insertToMethodParams(methodLine);
        return new Method(methodName,paramsLine,lines,globalScope);

    }
}
