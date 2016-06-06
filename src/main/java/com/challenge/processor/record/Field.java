package com.challenge.processor.record;

/**
 * Created by Guenael Thiriet on 2016-06-04.
 */
public class Field {

    private final String fieldValue;

    public Field(String value) {
        fieldValue = value;
    }

    @Override
    public String toString() {
        return fieldValue;
    }
}
