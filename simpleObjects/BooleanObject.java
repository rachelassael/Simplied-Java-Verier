package oop.ex6.main.simpleObjects;

import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Class of object from char type.
 */
class BooleanObject extends GeneralObject {

    /** error message for invalid value */
    private final static String ERROR_MSG = "Not-boolean value for boolean variable";

    /** the pattern of valid boolean object */
    private final static Pattern BOOLEAN_PATTERN = Pattern.compile("(?:true|false)|(?:-?\\d+(?:\\.\\d*)?)");

    /** all the types that match to this type */
    private final static ArrayList<String> VALID_TYPES_FOR_BOOLEAN =
            new ArrayList<>(Arrays.asList(BOOLEAN_TYPE, INT_TYPE, DOUBLE_TYPE));


    /**
     * Checks if the value is in the expected format for boolean object.
     *
     * @param value the value for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    BooleanObject(CharSequence value) throws InvalidValueForType {
        super(BOOLEAN_TYPE ,BOOLEAN_PATTERN, ERROR_MSG, VALID_TYPES_FOR_BOOLEAN);
        this.isValueValid(value);
    }

    /**
     * Checks if the value is in the expected format for boolean object.
     *
     * @param variable the variable for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    BooleanObject(Variable variable) throws InvalidValueForType {
        super(BOOLEAN_TYPE ,BOOLEAN_PATTERN, ERROR_MSG, VALID_TYPES_FOR_BOOLEAN);
        this.isValueVarValid(variable);
    }
}
