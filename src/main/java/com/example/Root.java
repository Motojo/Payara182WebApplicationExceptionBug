package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Jorge on 23/02/2017.
 */
@Path("/")
public class Root
{
    @GET
    public String hello()
    {
        return "Hello World!";
    }

}
