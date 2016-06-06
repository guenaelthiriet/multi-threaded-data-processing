package com.challenge.processor.record;

import com.challenge.processor.DataProcessorApplication;

import java.util.ArrayList;
import java.util.List;

public class RecordBuilder {

    private final List<Field> fields;

    public RecordBuilder() {
        fields = new ArrayList<Field>(DataProcessorApplication.NB_FIELDS_IN_RECORD);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public Record build() {
        return new Record(fields);
    }

}
