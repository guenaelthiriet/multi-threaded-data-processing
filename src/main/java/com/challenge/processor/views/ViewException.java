package com.challenge.processor.views;

/**
 * The <code>ProcessingException</code> is used when
 * errors happen while Clip is being used.
 *
 * @author Guenael Thiriet
 */

public class ViewException extends Exception {

    public ViewException() {
        super();
    }

    public ViewException(String message) {
        super(message);
    }

    public ViewException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewException(Throwable cause) {
        super(cause);
    }
}
