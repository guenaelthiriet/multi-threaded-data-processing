package com.challenge.processor;

import com.challenge.processor.processor.ProcessorFactory;
import com.challenge.processor.processor.ProcessorInterface;
import com.challenge.processor.processor.SleepyProcessor;
import com.challenge.processor.record.Field;
import com.challenge.processor.record.Record;
import com.challenge.processor.record.RecordBuilder;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * Created by Guenael Thiriet on 2016-06-05.
 */
public class DispatcherTest {

    Record createRecord() {
        RecordBuilder builder = new RecordBuilder();
        for (int i =0; i<10; i++) {
            builder.addField( new Field("Field value"));
        }
        return builder.build();
    }

    String createRecords(int nbRecords) {
        StringBuilder records = new StringBuilder();
        for(int i=0; i<nbRecords; i++) {
            records.append(createRecord().toString() + DataProcessorApplication.CSV_DELIMITER);
        }
        // Remove last delimiter
        return records.substring(0,records.length()-1);
    }

    @Test
    public void testProcessing() throws Exception {
        // Provide some test data
        String record = createRecord().toString();
        ByteArrayInputStream bais = new ByteArrayInputStream(record.getBytes());

        // Create the processor factory with a dummy processor
        ProcessorInterface processor = mock(SleepyProcessor.class);
        doNothing().when(processor).process();
        ProcessorFactory processorFactory = new ProcessorFactory(processor.getClass());

        Dispatcher dispatcher = new Dispatcher(bais, processorFactory);
        dispatcher.startProcessing();
    }

    @Test
    public void testThousandRecordProcessing() throws Exception {
        // Provide some test data
        String records = createRecords(1000);
        ByteArrayInputStream bais = new ByteArrayInputStream(records.getBytes());

        // Create the processor factory with a dummy processor
        ProcessorInterface processor = mock(SleepyProcessor.class);
        doNothing().when(processor).process();
        ProcessorFactory processorFactory = new ProcessorFactory(processor.getClass());

        Dispatcher dispatcher = new Dispatcher(bais, processorFactory);
        dispatcher.startProcessing();
        // Since the processors are doing nothing, it's not a very interesting test...
        // At least it doesn't crash :)
    }

}