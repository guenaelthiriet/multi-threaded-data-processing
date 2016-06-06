package com.challenge.processor.views;

/**
 * Created by Guenael Thiriet on 2016-06-04.
 */
public interface ViewInterface {

    void printMessage(String message) throws ViewException;
    void printError(String message) throws ViewException;
}
