package com.bigdan.exception;

public class lsveaException extends Exception {
    private static final long serialVersionUID = 1L;
    public lsveaException() {
        super();
    }
    public lsveaException(String errors) {
        super(errors);
    }
}
