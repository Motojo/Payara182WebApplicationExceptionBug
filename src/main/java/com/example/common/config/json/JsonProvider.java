package com.example.common.config.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by Jorge on 23/02/2017.
 */
@Provider
public class JsonProvider implements ContextResolver<ObjectMapper>
{
    final ObjectMapper mapper;

    public JsonProvider()
    {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    @Override
    public ObjectMapper getContext(final Class<?> type)
    {
        return mapper;
    }


}
