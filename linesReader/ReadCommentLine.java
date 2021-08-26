package oop.ex6.main.linesReader;

import oop.ex6.main.linesReader.lineExceptions.InValidCommentLine;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Extends from ReadOneLine - the purpose is to read a comment line
 */
public class ReadCommentLine extends ReadOneLine{

    /** A pattern of valid comment line */
    private final static Pattern VALID_COMMENT_LINE = Pattern.compile("^//");


    /**
     * Check if the given line is in valid format for comment line. Throw an Exception if it is not.
     *
     * @param line String of one line.
     * @throws InValidCommentLine if the line contain "/", but the format is wrong.
     */
    public static void isValidCommentLine(String line) throws InValidCommentLine {
        Matcher validCommentMatcher = VALID_COMMENT_LINE.matcher(line);
        if (!validCommentMatcher.find()) {
            throw new InValidCommentLine();
        }
    }
}
