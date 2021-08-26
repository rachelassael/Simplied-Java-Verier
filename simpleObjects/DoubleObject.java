package oop.ex6.main.simpleObjects;

import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Class of object from double type.
 */
class DoubleObject extends GeneralObject {

    /** error message for invalid value */
    private final static String ERROR_MSG = "Not-double value for double variable";

    /** the pattern of valid double object */
    private final static Pattern DOUBLE_PATTERN = Pattern.compile("-?\\d+(?:\\.\\d*)?");

    /** all the types that match to this type */
    private final static ArrayList<String> VALID_TYPES_FOR_DOUBLE =
            new ArrayList<>(Arrays.asList(INT_TYPE, DOUBLE_TYPE));


    /**
     * Checks if the value is in the expected format for double object.
     *
     * @param value the value for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    DoubleObject(CharSequence value) throws InvalidValueForType {
        super(DOUBLE_TYPE ,DOUBLE_PATTERN, ERROR_MSG, VALID_TYPES_FOR_DOUBLE);
        this.isValueValid(value);
    }

    /**
     * Checks if the value is in the expected format for double object.
     *
     * @param variable the variable for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    DoubleObject(Variable variable) throws InvalidValueForType {
        super(DOUBLE_TYPE ,DOUBLE_PATTERN, ERROR_MSG, VALID_TYPES_FOR_DOUBLE);
        this.isValueVarValid(variable);
    }
}
