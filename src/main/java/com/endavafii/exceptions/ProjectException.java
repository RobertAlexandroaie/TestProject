package com.endavafii.exceptions;

/**
 * Class which all exceptions from EnQuiz will extend.
 */
public class ProjectException extends Exception {

    private static final long serialVersionUID = -66109643242078913L;

    /**
     * Constructor with String.
     * @param message String
     */
    public ProjectException(String message) {
        super(message);
    }

    /**
     * Constructor with a Throwable object.
     * @param  cause Throwable
     */
    public ProjectException(Throwable cause) {
        super(cause);
    }
}
