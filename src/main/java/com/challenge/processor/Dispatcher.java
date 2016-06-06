package com.challenge.processor;

import com.challenge.processor.processor.ProcessorFactory;
import com.challenge.processor.record.Field;
import com.challenge.processor.record.Record;
import com.challenge.processor.record.RecordBuilder;
import com.challenge.processor.views.ViewInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * @author Guenael Thiriet
 */

public class Dispatcher {

    @Autowired
    private ViewInterface view;

    private InputStream hugeFileIs;

    private final ThreadPool threadPool;

    // Used only for printouts
    static private long currentRecord = 0;

    public Dispatcher(InputStream inputStream, ProcessorFactory processorFactory) {
        hugeFileIs = inputStream;
        // It would probably make sense to create this elsewhere and pass it to the dispatcher
        threadPool = new ThreadPool(processorFactory);
    }

    private synchronized Record read() {
        // It would be wise to move scanner and builder creation outside of this method
        Scanner scanner = new Scanner(hugeFileIs);
        scanner.useDelimiter(DataProcessorApplication.CSV_DELIMITER);

        RecordBuilder builder = new RecordBuilder();
        // Read 10 fields
        int i = DataProcessorApplication.NB_FIELDS_IN_RECORD;
        while (scanner.hasNext() && (i > 0)) {
            builder.addField(new Field(scanner.next()));
            i--;
        }
        scanner.close();

        if(i == DataProcessorApplication.NB_FIELDS_IN_RECORD) {
            // No data was read from stream
            return null;
        }
        return builder.build();
    }

    /**
     * The method starts the interactive console.
     * Main thread is responsible to read data and dispatch work
     * Dummy strategy is to read 1 record and spawn a thread each time
     */
    void startProcessing() throws InterruptedException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Record record;
        // While there is more data to read, process it
        while ((record = read()) != null) {
            Thread processingThread = threadPool.getAvailableProcessingThreadFromPool(record);
            processingThread.start();
            // The thread will be recycled when inactive.
        }
    }
}
