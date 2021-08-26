package oop.ex6.main.linesReader;

import oop.ex6.main.condition.Condition;
import oop.ex6.main.condition.conditionExceptions.*;
import oop.ex6.main.scope.GlobalScope;
import oop.ex6.main.scope.InnerScope;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extends from ReadOneLine - the purpose is to read a condition format line
 */
class ReadConditionLine extends ReadOneLine {

    /** Pattern of a conditions part */
    private static final Pattern insideConditionPattern = Pattern.compile("\\(.*\\)");

    /**
     * Check if the given condition (inside the parenthesis) is in valid format
     * @param conditionLine string line
     * @return the condition line after been parsed
     * @throws ConditionException - thrown in case error in format
     */
    private static String isValidConditionLine(String conditionLine) throws ConditionException {
        Matcher matcher = insideConditionPattern.matcher(conditionLine);
        if (matcher.find()) {
            conditionLine = conditionLine.substring(matcher.start(), matcher.end());
            conditionLine = conditionLine.replaceAll("[()]", "");
        } else {
            throw new ConditionException();
        }
        return conditionLine;
    }

    /**
     * check if the condition line is valid, and return the new scope of the condition. If the condition
     * line is wrong, throw an exception.
     * @param line        String, the current line
     * @param curScope    the scope this line is in
     * @param globalScope the global scope of the file
     * @return the new scope that create for this condition
     * @throws ConditionException if the condition line is wrong
     */
    static InnerScope checkCondition(String line, InnerScope curScope, GlobalScope globalScope,
                                     Matcher conditionMatcher)
            throws ConditionException {
        if (conditionMatcher.matches()) {
            String conditionLine = ReadConditionLine.isValidConditionLine(line);
            new Condition(conditionLine, curScope);
            return new InnerScope(curScope, globalScope);
        } else {
            throw new ConditionException();
        }
    }
}
