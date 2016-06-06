package com.challenge.processor.views.console;

import com.challenge.processor.views.ViewException;
import com.challenge.processor.views.ViewInterface;
import org.springframework.stereotype.Component;

@Component
final public class StdoutView implements ViewInterface {

    public StdoutView() throws ViewException {
    }

    @Override
    public void printMessage(String message) throws ViewException {
        System.out.println(message);
    }

    @Override
    public void printError(String message) throws ViewException {
        System.out.println(String.format("Error: %s", message));
    }
}
