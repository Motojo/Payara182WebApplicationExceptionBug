package com.example.common.exceptions.ws;

import lombok.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Created by Jorge on 09/07/2016.
 */

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class WSException extends WebApplicationException
{
    @NonNull
    private String message;

    private HashMap<String,String> errors;

    private Response.Status statusCode = Response.Status.BAD_REQUEST;

    public WSException(String message, HashMap<String, String> errors)
    {
        this.message = message;
        this.errors = errors;
    }

    public WSException as(Response.Status status)
    {
        this.statusCode = status;
        return this;
    }
}












