package oop.ex6.main.simpleObjects;

import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Class of object from char type.
 */
class CharObject extends GeneralObject {

    /** error message for invalid value */
    private final static String ERROR_MSG = "Not-char value for char variable";

    /** the pattern of valid char object */
    private final static Pattern CHAR_PATTERN = Pattern.compile("\'.\'");

    /** all the types that match to this type */
    private final static ArrayList<String> VALID_TYPES_FOR_CHAR =
            new ArrayList<>(Arrays.asList(CHAR_TYPE));


    /**
     * Checks if the value is in the expected format for char object.
     *
     * @param value the value for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    CharObject(CharSequence value) throws InvalidValueForType {
        super(CHAR_TYPE ,CHAR_PATTERN, ERROR_MSG, VALID_TYPES_FOR_CHAR);
        this.isValueValid(value);
    }

    /**
     * Checks if the value is in the expected format for char object.
     *
     * @param variable the variable for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    CharObject(Variable variable) throws InvalidValueForType {
        super(CHAR_TYPE ,CHAR_PATTERN, ERROR_MSG, VALID_TYPES_FOR_CHAR);
        this.isValueVarValid(variable);
    }
}
