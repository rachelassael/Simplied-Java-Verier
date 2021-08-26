package oop.ex6.main;

import oop.ex6.main.condition.conditionExceptions.ConditionException;
import oop.ex6.main.linesReader.ReadGlobal;
import oop.ex6.main.linesReader.ReadInnerScopes;
import oop.ex6.main.linesReader.lineExceptions.*;
import oop.ex6.main.method.methodExceptions.MethodException;
import oop.ex6.main.scope.scopeException.ScopeException;
import oop.ex6.main.simpleObjects.typeException.TypeException;
import oop.ex6.main.variables.varExceptions.VariableException;

import java.io.IOException;

/**
 * Main class calls all the parsers and checks if the file is good
 */
public class Sjavac {
    /**
     * Main method
     * @param args args in command line
     */
    public static void main(String[] args) {
        String filePath = args[0];
        ReadGlobal firstParser = new ReadGlobal(filePath);
        try {
            firstParser.readGlobalScope();
            ReadInnerScopes secondParser = new ReadInnerScopes();
            secondParser.readMethodsScope(firstParser.getGlobalScope());
            System.out.println(0);
        } catch (IOException e) {
            System.out.println(2);
            System.err.println(e.getMessage());
        } catch (VariableException | ScopeException | MethodException | LineException | TypeException |
                ConditionException e) {
            System.out.println(1);
            System.err.println(e.getMessage());
        }
    }
}


