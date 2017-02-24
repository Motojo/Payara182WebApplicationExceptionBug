package com.example.common.exceptions;

import com.example.common.exceptions.ws.WSException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jorge on 14/05/2016.
 */

public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException>
{
    @Override
    public Response toResponse(ConstraintViolationException exception)
    {
        //<editor-fold desc="Magic Here">
        List<String> paths = exception.getConstraintViolations().stream().map(v -> v.getPropertyPath().toString() ).collect(Collectors.toList());
        String[] errors = new String[paths.size()];
        exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).toArray(errors);
        HashMap<String, String> violations = new HashMap<>();

        for(int i = 0; i< paths.size(); i++)
        {
            String[] path = paths.get(i).split("\\.");
            String field = path[path.length-1];
            violations.put(field, errors[i]);
        }
        //</editor-fold>

        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST)
                .entity(new WSException("Error en los datos transmitidos",violations))
                .type(MediaType.APPLICATION_JSON);

        return builder.build();
    }

















































}
