package ru.aston.ian_ev.task2;

public enum ErrorCode {
    INVALID_UNIT_PRICE(1),
    INVALID_AMOUNT(2);
    private final int code;
    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
