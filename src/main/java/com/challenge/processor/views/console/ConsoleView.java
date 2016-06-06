package com.challenge.processor.views.console;

import com.challenge.processor.views.ViewException;
import com.challenge.processor.views.ViewInterface;

import java.io.Console;

// Problems when injecting the Bean
//@Component
final public class ConsoleView implements ViewInterface {

    /**
     * The new line separator
     */
    private static final String NEW_LINE = System.getProperty("line.separator");

    private final Console console ;

    public ConsoleView() throws ViewException {
        console = System.console();
        if (console == null) {
            throw new ViewException("Unable to create console");
        }
    }

    @Override
    public void printMessage(String message) throws ViewException {
        console.printf(message);
        console.printf(NEW_LINE);
    }

    @Override
    public void printError(String message) throws ViewException {
        console.printf("Error: %s%s", message, NEW_LINE);
    }
}
