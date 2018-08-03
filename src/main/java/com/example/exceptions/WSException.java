package com.example.exceptions;

import lombok.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by Jorge on 09/07/2016.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WSException extends WebApplicationException
{
    @NonNull
    private String message;
    private Response.Status statusCode = Response.Status.BAD_REQUEST;

    public WSException as(Response.Status status)
    {
        this.statusCode = status;
        return this;
    }
}













