package com.zm.hospital.common.exception;

/**
 * 控制层通用异常
 * Created by ange on 2016/11/13.
 */
public class ControllerException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ControllerException(String message) {
        super(message);
    }
}
