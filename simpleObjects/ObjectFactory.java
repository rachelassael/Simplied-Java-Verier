package oop.ex6.main.simpleObjects;

import oop.ex6.main.simpleObjects.typeException.IllegalTypeException;
import oop.ex6.main.simpleObjects.typeException.InvalidValueForType;
import oop.ex6.main.simpleObjects.typeException.NullValue;
import oop.ex6.main.variables.Variable;

/**
 * class with one static method that creates GeneralObject according to given typeName and value. If the type
 */
public class ObjectFactory {

    /**
     * Constants representing type of variable
     */
    private static final String STRING_TYPE = "String", INT_TYPE = "int", DOUBLE_TYPE = "double",
            BOOLEAN_TYPE = "boolean", CHAR_TYPE = "char";

    /**
     * Creates object of General object
     *
     * @param typeName name of type
     * @param varValue value of object
     * @return General Object
     * @throws IllegalTypeException
     * @throws InvalidValueForType
     */
    public static GeneralObject createObj(String typeName, String varValue) throws IllegalTypeException,
            InvalidValueForType {
        GeneralObject newObj;
        switch (typeName) {
            case STRING_TYPE:
                newObj = new StringObject(varValue);
                break;
            case INT_TYPE:
                newObj = new IntObject(varValue);
                break;
            case DOUBLE_TYPE:
                newObj = new DoubleObject(varValue);
                break;
            case BOOLEAN_TYPE:
                newObj = new BooleanObject(varValue);
                break;
            case CHAR_TYPE:
                newObj = new CharObject(varValue);
                break;
            default:
                throw new IllegalTypeException();
        }
        return newObj;
    }

    /**
     * Creates object of General object
     *
     * @param typeName name of type
     * @param newValueVar Variable we assign to object
     * @return General Object
     * @throws IllegalTypeException
     * @throws InvalidValueForType
     */
    public static GeneralObject createObj(String typeName, Variable newValueVar) throws IllegalTypeException,
            InvalidValueForType, NullValue {
        if (newValueVar.getValue().isNull()) {
            throw new NullValue();
        }
        GeneralObject newObj;
        switch (typeName) {
            case STRING_TYPE:
                newObj = new StringObject(newValueVar);
                break;
            case INT_TYPE:
                newObj = new IntObject(newValueVar);
                break;
            case DOUBLE_TYPE:
                newObj = new DoubleObject(newValueVar);
                break;
            case BOOLEAN_TYPE:
                newObj = new BooleanObject(newValueVar);
                break;
            case CHAR_TYPE:
                newObj = new CharObject(newValueVar);
                break;
            default:
                throw new IllegalTypeException();
        }
        return newObj;
    }
}

