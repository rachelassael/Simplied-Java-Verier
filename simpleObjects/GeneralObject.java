package oop.ex6.main.simpleObjects;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.variables.Variable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class of general Object.
 */
public abstract class GeneralObject {

    /** Constants represent types*/
    static final String STRING_TYPE = "String", INT_TYPE = "int", DOUBLE_TYPE = "double",
            BOOLEAN_TYPE = "boolean", CHAR_TYPE = "char";

    /** The pattern of values of this object type */
    protected Pattern pattern;

    /** error message for invalid value */
    protected String error_msg;

    /** The type of the object */
    protected String type;

    /**
     * Array list of string of allowed type
     */
    protected ArrayList<String> validTypes;


    /** The CharSequence of the object's value */
    private CharSequence objValue;

    /**
     * Constructor of class
     * @param type - type of new object
     * @param pattern - its pattern regular expression
     * @param error_msg - the error to send
     * @param validTypes - array of valid type allowed
     */
    GeneralObject(String type, Pattern pattern, String error_msg, ArrayList<String> validTypes) {
        this.type = type;
        this.pattern = pattern;
        this.error_msg = error_msg;
        this.validTypes = validTypes;
    }


    /**
     * Checks if the value is in the expected format for this type objects.
     *
     * @param value the value for this object
     * @throws InvalidValueForType when the value is not in the expected format.
     */
    void isValueValid(CharSequence value) throws InvalidValueForType {
        if (value == null) {
            this.objValue = null;
        } else {
            Matcher matcher = this.pattern.matcher(value);
            if (!matcher.matches()) throw new InvalidValueForType(this.error_msg);
            this.objValue = value;
        }
    }

    /**
     * Check if the given variable is valid to be the value of new variable
     * @param var the variable for the new Variable's value
     * @throws InvalidValueForType if the given variable is not in valid type for
     * the current variable type, or if its
     *  value is null.
     */
    void isValueVarValid(Variable var) throws InvalidValueForType {
        if (!this.validTypes.contains(var.getValue().getType())) {
            throw new InvalidValueForType(this.error_msg);
        } else if (var.getValue().isNull()) {
            throw  new InvalidValueForType(this.error_msg);
        } else {
            this.objValue = var.getValue().getValueOfObject();
        }
    }

    /**
     * returns the type of this object.
     * @return String of type name
     */
    public String getType() {
        return this.type;
    }

    /**
     * returns true if the object is null, false otherwise.
     * @return String of type name
     */
    public boolean isNull() {
        return this.objValue == null;
    }

    /**
     * returns true if the object is null, false otherwise.
     * @return String of type name
     */
    public CharSequence getValueOfObject() {
        return this.objValue;
    }

    /**
     * Sets the value to the object
     * @param value value to set
     */
    public void setValue(CharSequence value) {
        this.objValue = value;
    }
}
