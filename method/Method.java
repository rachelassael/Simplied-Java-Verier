package oop.ex6.main.method;

import oop.ex6.main.method.methodExceptions.MethodException;
import oop.ex6.main.method.methodExceptions.WrongParameterGiven;
import oop.ex6.main.scope.GlobalScope;
import oop.ex6.main.scope.Scope;
import oop.ex6.main.simpleObjects.GeneralObject;
import oop.ex6.main.simpleObjects.typeException.IllegalTypeException;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.Variable;
import oop.ex6.main.variables.VariableFactory;
import oop.ex6.main.variables.varExceptions.NameException;
import oop.ex6.main.variables.varExceptions.NotInitializedFinal;
import oop.ex6.main.variables.varExceptions.VariableException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents Method object
 */
public class Method {

    /**
     * method's name
     */
    private String methodName;

    /**
     * the global scope the method belongs to
     */
    private GlobalScope globalScope;

    private static final String FINAL = "final";

    /**
     * ArrayList of params variables of the method in the scope
     */
    private ArrayList<Variable> methodParams;

    /**
     * Lines in the method scope
     */
    private ArrayList<String> arrayLines;

    /**
     * Some patterns for methods use
     */
    private static final Pattern EMPTY_PARAMS = Pattern.compile("\\s*"), RETURN_PATTERN =
            Pattern.compile("\\s*return\\s*;"),
            END = Pattern.compile("\\s*}\\s*");


    /**
     * Constructor
     * @param methodName the name given
     * @param paramsString params string the method()
     * @param arrayLines lines in method scope
     * @param globalScope - the global scope it belongs
     * @throws TypeException thrown exception
     * @throws MethodException thrown exception
     * @throws VariableException thrown exception
     */
    public Method(String methodName, String paramsString, ArrayList<String> arrayLines,
                  GlobalScope globalScope) throws TypeException, MethodException, VariableException {
        this.methodParams = new ArrayList<>();
        this.globalScope = globalScope;
        this.methodName = methodName;
        this.createLocalVariables(paramsString);
        this.arrayLines = arrayLines;

    }

    /**
     * Returns the lines of the method scope
     *
     * @return string array of lines
     */
    public ArrayList<String> getArrayLines() {
        return this.arrayLines;
    }

    /**
     * Returns the name of the method
     *
     * @return name
     */
    public String getName() {
        return this.methodName;
    }


    /**
     * This method creates the array of the parameters of the method created
     *
     * @param paramsString - the string of the method(type1 param, type2 param2,...)
     * @throws IllegalTypeException - if type is wrong
     * @throws InvalidValueForType  - will be never be thrown
     * @throws NameException        - if the name of the given parameter is not valid
     */
    private void createLocalVariables(String paramsString) throws MethodException, VariableException,
            TypeException {
        Matcher isParamEmpty = EMPTY_PARAMS.matcher(paramsString);
        if (isParamEmpty.matches()) {
            return;
        }
        String[] params = paramsString.split(",");
        for (String param : params) {
            String[] selfParam = param.trim().split("\\s+");
            Variable newParamVar;
            if (selfParam.length == 2) {
                newParamVar = VariableFactory.createParamsVariable(false, selfParam[0], selfParam[1]);
            } else if (selfParam[0].equals(FINAL)) {
                newParamVar = VariableFactory.createParamsVariable
                        (true, selfParam[1], selfParam[2]);
            } else {
                throw new WrongParameterGiven();
            }
            this.methodParams.add(newParamVar);
        }
    }

    /**
     * Returns parameters of the method
     */
    public ArrayList<Variable> getMethodParams() {
        return this.methodParams;
    }

    /**
     * Checks the return statement
     */
    public boolean checkReturnStatement() {
        String lastLine = this.arrayLines.get(this.arrayLines.size() - 2);
        Matcher returnMatcher = RETURN_PATTERN.matcher(lastLine);
        return returnMatcher.matches();
    }

    /**
     * Checks if the scope was ended
     */
    public boolean checkEnfOfMethodScope() {
        String lastLine = this.arrayLines.get(this.arrayLines.size() - 1);
        Matcher endMatcher = END.matcher(lastLine);
        return endMatcher.matches();
    }


}



