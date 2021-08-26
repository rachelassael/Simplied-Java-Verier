package oop.ex6.main.simpleObjects;

import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Class of object from String type.
 */
class StringObject extends GeneralObject {

    /** error message for invalid value */
    private final static String ERROR_MSG = "Not-String value for String variable";

    /** the pattern of valid String object */
    private final static Pattern STRING_PATTERN = Pattern.compile("\".*\"");

    /** all the types that match to this type */
    private final static ArrayList<String> VALID_TYPES_FOR_STRING =
            new ArrayList<>(Arrays.asList(STRING_TYPE));


    /**
     * Checks if the value is in the expected format for String object.
     *
     * @param value the value for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    StringObject(CharSequence value) throws InvalidValueForType {
        super(STRING_TYPE, STRING_PATTERN, ERROR_MSG, VALID_TYPES_FOR_STRING);
        this.isValueValid(value);
    }


    /**
     * Constructor when variable object received
     * @param variable Var object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    StringObject(Variable variable) throws InvalidValueForType {
        super(STRING_TYPE, STRING_PATTERN, ERROR_MSG, VALID_TYPES_FOR_STRING);
        this.isValueVarValid(variable);
    }
}