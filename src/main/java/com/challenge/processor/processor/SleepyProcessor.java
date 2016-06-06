package com.challenge.processor.processor;

import com.challenge.processor.ProcessingException;
import com.challenge.processor.record.Record;

import org.apache.commons.math3.random.RandomDataImpl;

/**
 * Created by Guenael Thiriet on 2016-06-04.
 */
public class SleepyProcessor implements ProcessorInterface {

    private final int sleepTime;

    private final Record record;

    public SleepyProcessor(Record record) {
        this.record = record;
        RandomDataImpl randomData = new RandomDataImpl();
        sleepTime = randomData.nextInt(3,10);
    }

    @Override
    public void process() throws ProcessingException {
        try {
            Thread.sleep(sleepTime*1000);
        } catch (InterruptedException e) {
            throw new ProcessingException(e);
        }
    }
}
