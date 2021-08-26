package oop.ex6.main.simpleObjects.typeException;

/**
 * Extends from scope exception, if the var already exists in it
 */
public class InvalidValueForType extends TypeException {

    public InvalidValueForType(String msg){
        super(msg);
    }
}
