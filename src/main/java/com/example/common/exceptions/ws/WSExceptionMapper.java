package com.example.common.exceptions.ws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Jorge on 09/07/2016.
 */

@Provider
public class WSExceptionMapper implements ExceptionMapper<WSException>
{
    @Override
    public Response toResponse(WSException exception)
    {
        Response.ResponseBuilder builder = Response.status( exception.getStatusCode() )
                .entity(exception)
                .type(MediaType.APPLICATION_JSON);

        return builder.build();
    }
}
