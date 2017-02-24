package com.example.common.config.json;

import com.example.common.exceptions.ws.WSException;
import com.example.common.utils.WSResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;

/**
 * Created by Jorge on 09/07/2016.
 */
@Provider
public class JsonWrapper implements WriterInterceptor
{

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException
    {
        MediaType mediaType = context.getMediaType();

        if(mediaType!=null && mediaType.toString().contains(MediaType.APPLICATION_JSON))
        {
            WSResponse response = new WSResponse();
            Object entity = context.getEntity();

            if(entity instanceof WSException)
            {
                WSException ex = (WSException)entity;
                response.setSuccess(false);
                response.setMessage(ex.getMessage());
                response.setData(ex.getErrors());
            }
            else
            {
                response.setSuccess(true);
                response.setMessage("");
                response.setData(entity);
            }

            context.setEntity(response);
            context.setType(WSResponse.class);
            context.setGenericType(WSResponse.class);

       }
        context.proceed();
    }
}










