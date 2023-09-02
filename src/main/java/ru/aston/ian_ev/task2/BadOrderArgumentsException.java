package ru.aston.ian_ev.task2;

public class BadOrderArgumentsException extends Exception {
    private final ErrorCode code;
    public BadOrderArgumentsException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
