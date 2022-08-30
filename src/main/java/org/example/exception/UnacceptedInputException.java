package org.example.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class UnacceptedInputException extends WebApplicationException {
    public UnacceptedInputException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(
                new ExceptionInfo(Response.Status.BAD_REQUEST.getStatusCode(), message)
        ).type("application/json").build());
    }
}
