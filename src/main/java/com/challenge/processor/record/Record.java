package com.challenge.processor.record;

import com.challenge.processor.DataProcessorApplication;

import java.util.List;

/**
 * Created by Guenael Thiriet on 2016-06-04.
 */
public class Record {

    private final List<Field> fields;

    Record(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        String csvRepresentation = fields.get(0).toString();
        for (Field field: fields.subList(1, fields.size())){
            csvRepresentation = csvRepresentation.concat(DataProcessorApplication.CSV_DELIMITER + field.toString());
        }
        return csvRepresentation;
    }

}
