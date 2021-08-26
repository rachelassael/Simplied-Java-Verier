package oop.ex6.main.linesReader;

import oop.ex6.main.linesReader.lineExceptions.InValidLine;
import oop.ex6.main.linesReader.lineExceptions.LineException;
import oop.ex6.main.method.Method;
import oop.ex6.main.method.MethodFactory;
import oop.ex6.main.method.methodExceptions.InvalidMethodName;
import oop.ex6.main.method.methodExceptions.MethodException;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.scope.GlobalScope;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.varExceptions.VariableException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the first parsing of the file
 */
public class ReadGlobal extends ReadOneLine {

    /** Global scope obj created */
    private GlobalScope globalScope;

    /** File path of the file read*/
    private String filePath;

    /**
     * Constructor of the class
     * @param filePath - filepath of sjava file
     */
    public ReadGlobal(String filePath)
    {
        this.filePath = filePath;
        this.globalScope = new GlobalScope();
    }

    /**
     * This method does the first parsing of the file- the global parsing and creates methods object, and
     *  global variable objects that will be used for the reading of inner scopes.
     * @throws IOException exception thrown
     * @throws VariableException variable exception thrown
     * @throws TypeException type exception thrown
     * @throws ScopeException scope exception thrown
     * @throws MethodException method exception thrown
     * @throws LineException line read exception thrown
     */
    public void readGlobalScope() throws IOException, VariableException, TypeException,
            ScopeException, MethodException, LineException
             {
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line = reader.readLine();
        while (line != null) {
            resetMatchers(line);
            if (blankLineMatcher.matches()) {
                line = reader.readLine();
                continue;
            } else if (commentMatcher.matches()) {
                ReadCommentLine.isValidCommentLine(line);
            } else if (variableDeclarationMatcher.matches()) {
                ReadVariableDeclarationLine.createVariable(line,this.globalScope);
            } else if (variableAssignmentMatcher.matches()) {
                ReadVariableAssignmentLine.readLine(line, this.globalScope, null);
            } else if (optionalMethodMatcher.find()) {
                if (methodMatcher.matches()) {
                    ArrayList<String> methodsLines = this.createMethodLines(reader);
                    Method newMethod = MethodFactory.createMethodObj(line, methodsLines, this.globalScope);
                    this.globalScope.addMethod(newMethod);
                } else {
                    throw new InvalidMethodName();
                }
            } else {
                throw new InValidLine();
            }
            line = reader.readLine();
        }
    }

    /**
     * This method insert lines into the method object according to found parentheses that define a method
     * scope.
     *
     * @param reader - buffered reader object of the file given
     * @return - an array list of string representing the lines of each method object
     * @throws IOException exception thrown in case of problem with reading the file
     */
    private ArrayList<String> createMethodLines(BufferedReader reader) throws IOException {
        ArrayList<String> methodLines = new ArrayList<>();
        String line = reader.readLine();
        resetMatchers(line);
        int innerScopes = 1;
        // We haven't reached the last closing inner scope
        while (line != null) {
            resetMatchers(line);
            if (openScopeMatcher.find()) {
                innerScopes++;
            } else if (endOfScopeMatcher.matches()) {
                innerScopes--;
            }
            methodLines.add(line);
            if (innerScopes == 0) {
                break;
            }
            line = reader.readLine();
        }
        return methodLines;
    }

    /**
     * Returns the global scope created during the first parsing of the file
     * @return - Global scope obj
     */
    public GlobalScope getGlobalScope(){
        return this.globalScope;
    }
}


