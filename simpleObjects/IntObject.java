package oop.ex6.main.simpleObjects;

import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class of object from int type.
 */
class IntObject extends GeneralObject {

    /** error message for invalid value */
    private final static String ERROR_MSG = "Not-int value for int variable";

    /** the pattern of valid int object */
    private final static Pattern INT_PATTERN = Pattern.compile("-?\\d+");

    /** all the types that match to this type */
    private final static ArrayList<String> VALID_TYPES_FOR_INT = new ArrayList<>(Arrays.asList(INT_TYPE));

    /**
     * Checks if the value is in the expected format for int object.
     *
     * @param value the value for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    IntObject(CharSequence value) throws InvalidValueForType {
        super(INT_TYPE ,INT_PATTERN, ERROR_MSG, VALID_TYPES_FOR_INT);
        this.isValueValid(value);
    }

    /**
     * Checks if the value is in the expected format for int object.
     *
     * @param variable the variable for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    IntObject(Variable variable) throws InvalidValueForType {
        super(INT_TYPE ,INT_PATTERN, ERROR_MSG, VALID_TYPES_FOR_INT);
        this.isValueVarValid(variable);
    }
}
