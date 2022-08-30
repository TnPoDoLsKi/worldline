package org.example.exception;

import org.example.config.LoggerConfig;
import org.example.controller.ProfileController;

import java.util.logging.Logger;

public class ExceptionInfo {

    private String message;
    private int status;

    public ExceptionInfo(int statusCode, String message) {
        this.message = message;
        this.status = statusCode;
        Logger logger = LoggerConfig.getLogger(ExceptionInfo.class);
        logger.fine("Exception thrown with status code: " + statusCode + " and message: "+message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
