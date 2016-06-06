package com.challenge.processor;

import com.challenge.processor.processor.ProcessorFactory;
import com.challenge.processor.processor.SleepyProcessor;
import com.challenge.processor.views.ViewException;
import com.challenge.processor.views.ViewInterface;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;

/**
 * The <code>DataProcessorApplication</code> is the DataProcessorApplication program record point.
 *
 * @author Guenael Thiriet
 */

@SpringBootApplication
public class DataProcessorApplication {

    /**
     * Number of processors
     */
    public static final int MAX_PROCESSORS = 8;

    // How can this be used?
    // Maybe if we were doing some actual processing, we could read chunks of Records instead of a single Record
    public static final int MAX_RAM = 16; // In gigs

    public static final int NB_FIELDS_IN_RECORD = 10;

    public static final String CSV_DELIMITER = ",";

    @Autowired
    ViewInterface view;

    /**
     * @param args Arguments for the DataProcessorApplication program.
     */
    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }

    String getFilePath(String[] args) throws ParseException, ViewException {
        Options options = new Options();
        // add t option
        options.addOption("f", true, "display current time");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        if(!cmd.hasOption("f")) {
            view.printError("File option has not been provided, exiting");
            System.exit(0);
        }
        return cmd.getOptionValue("f");
    }

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            String filePath = getFilePath(args);

            view.printMessage("Running data processor");

            FileInputStream fis = new FileInputStream(filePath);
            // Start the console
            Dispatcher dispatcher = new Dispatcher(fis, new ProcessorFactory(SleepyProcessor.class));
            dispatcher.startProcessing();
        };
    }
}
