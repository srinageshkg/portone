package com.dcp.portone.exceptions;

public class ObjNotFoundException extends RuntimeException{
    public ObjNotFoundException(String msg) {
        super(msg);
    }

    public ObjNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjNotFoundException(Throwable cause) {
        super(cause);
    }
}
