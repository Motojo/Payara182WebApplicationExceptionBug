package com.example;

import com.example.exceptions.WSException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jorge on 23/02/2017.
 */
@Path("/")
public class Root
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@QueryParam("fail") boolean fail)
    {

        if(fail)
        {
            throw new WSException("Error");
        }

        return "Hello World!";
    }

}
