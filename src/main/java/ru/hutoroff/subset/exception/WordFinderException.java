package ru.hutoroff.subset.exception;

/**
 * Created by hutoroff on 16.02.17.
 */
public class WordFinderException extends Exception {

    public WordFinderException() {
    }

    public WordFinderException(String message) {
        super(message);
    }

    public WordFinderException(String message, Throwable cause) {
        super(message, cause);
    }

    public WordFinderException(Throwable cause) {
        super(cause);
    }
}
