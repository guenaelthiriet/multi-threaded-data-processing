package com.challenge.processor;

import com.challenge.processor.processor.ProcessorFactory;
import com.challenge.processor.processor.RunnableProcessor;
import com.challenge.processor.record.Record;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guenael Thiriet on 2016-06-05.
 */
public class ThreadPool {

    private final List<Thread> processingThreadList;

    private final ProcessorFactory processorFactory;

    public ThreadPool(ProcessorFactory processorFactory) {
        this.processingThreadList = new ArrayList<Thread>(DataProcessorApplication.MAX_PROCESSORS);
        this.processorFactory = processorFactory;
    }

    /**
     * This method block until a free thread is available for processing
     *
     * @param record
     * @return
     * @throws InterruptedException
     */
    public synchronized Thread getAvailableProcessingThreadFromPool(Record record)
            throws InterruptedException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // Wait for a processing slot
        while (!hasMore()) {

            Thread threadReturnedToPool = null;
            // Check if the threads are active
            for (Thread t : processingThreadList) {
                if (!t.isAlive()) {
                    threadReturnedToPool = t;
                    // Found a thread which can be used!
                    break;
                }
            }

            if (threadReturnedToPool != null) {
                returnToPool(threadReturnedToPool);
                break;
            }
            // If not found, wait a bit before retrying to find a free processor
            // TODO: It would be smarter to notify the main thread that processing is done
            Thread.sleep(1);
        }

        Thread t = new Thread(new RunnableProcessor(processorFactory.createProcessor(record)));
        t.setName(String.format("Thread %d", processingThreadList.size()));
        processingThreadList.add(t);
        return t;
    }

    /**
     * Return a thread to the the thread pool
     *
     * @param thread
     */
    private void returnToPool(Thread thread) throws InterruptedException {
        thread.join();
        processingThreadList.remove(thread);
    }

    /**
     * Check if they are more processor available
     *
     * @return
     */
    private boolean hasMore() {
        return processingThreadList.size() < DataProcessorApplication.MAX_PROCESSORS;
    }
}
