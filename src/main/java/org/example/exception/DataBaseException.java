package org.example.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DataBaseException extends WebApplicationException {
    public DataBaseException(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
                new ExceptionInfo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message)
        ).type("application/json").build());
    }
}
