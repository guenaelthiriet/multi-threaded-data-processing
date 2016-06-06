package com.challenge.processor.processor;

import com.challenge.processor.record.Record;
import org.apache.commons.lang.reflect.ConstructorUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProcessorFactory {

    private Constructor constructor;

    /**
     *
     * @param cls Class of the desired instances returned by the factory.
     */
    public ProcessorFactory(Class cls) {
        constructor = ConstructorUtils.getAccessibleConstructor(cls, Record.class);
    }

    public ProcessorInterface createProcessor(Record record) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (ProcessorInterface) constructor.newInstance(record);
    }
}
