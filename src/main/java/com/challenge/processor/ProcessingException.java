package com.challenge.processor;

/**
 * The <code>ProcessingException</code> is used when
 * errors happen while Clip is being used.
 *
 * @author Guenael Thiriet
 */

public class ProcessingException extends Exception {

    public ProcessingException() {
        super();
    }

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessingException(Throwable cause) {
        super(cause);
    }
}
