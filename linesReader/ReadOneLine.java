package oop.ex6.main.linesReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class that is in charge to read a general line
 */
abstract class ReadOneLine {

    /**
     * All patterns needed to read the lines correctly
     */
    private final static Pattern METHOD_PATTERN = Pattern.compile("\\s*void\\s+\\w+[^()\\s]*\\s*\\([^" +
            "()]*\\)\\s*\\{\\s*"), RETURN_PATTERN = Pattern.compile("\\s*(?:return\\s*;)"),
            END_OF_SCOPE_PATTERN = Pattern.compile("\\s*}\\s*"), OPEN_SCOPE_PATTERN = Pattern.compile("\\{"),
            COMMENT_PATTERN = Pattern.compile(".*//.*"),
            VARIABLE_DECLARATION_PATTERN = Pattern.compile("(?:\\s*final\\s)?\\s*(?:String|int|double|boolean"
                    + "|char)\\s(?:\\s*[^,\\s]+\\s*(?:,\\s*[^,\\s]+\\s*)*(?:=\\s*(?:(?:[^,\\s]+\\s*)|(?:\"[^,"
                    + "]*\")))?\\s*)(?:\\s*,\\s*[^,\\s]+\\s*(?:,\\s*[^,\\s]+\\s*)*(?:=\\s*(?:(?:[^,\\s]+\\s*)|"
                    + "(?:\"[^,]*\")))?\\s*)*;\\s*"),
            VARIABLE_ASSIGNMENT_PATTERN = Pattern.compile
                    ("\\s*(?:(?:[a-zA-Z]+[\\w_]*)|(?:_[\\w_]+))\\s*=\\s*[^,\\s]+;"),
            BLANK_LINE_PATTERN = Pattern.compile("\\s*"),
            METHOD_CALL_PATTERN = Pattern.compile("\\s*[\\w]+[\\S]*\\s*\\([^)(]*\\)\\s*;"),
            CONDITION_PATTERN = Pattern.compile("\\s*(?:if|while)\\s*\\((?:(?:\\s*[^|&,\")(\\s]+)" +
                    "(?:\\s([&|])\\1\\s*[^|&,\"()\\s]+\\s*)*)?\\s*\\)\\s*\\{\\s*"),
            CONTAINS_IF_WHILE = Pattern.compile("if|while"), CONTAINS_VOID = Pattern.compile("void");


    /**
     * Matchers needed for the read of lines
     */
    Matcher methodMatcher, returnMatcher, endOfScopeMatcher, commentMatcher,
            variableDeclarationMatcher, variableAssignmentMatcher, blankLineMatcher, openScopeMatcher,
            conditionMatcher, methodCallMatcher, optionalConditionMatcher, optionalMethodMatcher;

    /**
     * Method purpose is to reset all the matchers for the next line read
     * @param line line of file been read
     */
    void resetMatchers(String line) {
        this.methodMatcher = METHOD_PATTERN.matcher(line);
        this.returnMatcher = RETURN_PATTERN.matcher(line);
        this.commentMatcher = COMMENT_PATTERN.matcher(line);
        this.variableDeclarationMatcher = VARIABLE_DECLARATION_PATTERN.matcher(line);
        this.variableAssignmentMatcher = VARIABLE_ASSIGNMENT_PATTERN.matcher(line);
        this.blankLineMatcher = BLANK_LINE_PATTERN.matcher(line);
        this.openScopeMatcher = OPEN_SCOPE_PATTERN.matcher(line);
        this.endOfScopeMatcher = END_OF_SCOPE_PATTERN.matcher(line);
        this.conditionMatcher = CONDITION_PATTERN.matcher(line);
        this.methodCallMatcher = METHOD_CALL_PATTERN.matcher(line);
        this.optionalConditionMatcher = CONTAINS_IF_WHILE.matcher(line);
        this.optionalMethodMatcher = CONTAINS_VOID.matcher(line);
    }
}