package com.example;

import com.example.common.exceptions.ValidationExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Created by Jorge on 09/07/2016.
 */

//@ApplicationPath("/api")
public class Application extends ResourceConfig
{
    public Application()
    {
        register(JacksonFeature.class);
        register(MultiPartFeature.class);

        register(ValidationExceptionMapper.class);

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        packages("com.example");
    }
}











