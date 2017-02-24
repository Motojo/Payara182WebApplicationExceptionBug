package com.example.common.validators.auth.user;

import com.example.common.exceptions.ws.WSException;
import com.example.common.utils.properties.PropertiesFromFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jorge on 27/01/2017.
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthInterceptor implements ContainerRequestFilter
{
    @Inject
    @AuthenticatedUser
    Event<Long> authEvent;

    @Inject
    @PropertiesFromFile
    Properties properties;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        String key = properties.getProperty("application.key");
        String header = requestContext.getHeaderString("Authorization");

        if (header!=null && header.startsWith("Bearer "))
        {
            String token = header.replace("Bearer ", "").trim();
            try
            {
                Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
                final long userID = Long.parseLong(claims.getSubject());
                authEvent.fire(userID);
            }
            catch (Exception e)
            {
                throw  new WSException("Invalid Token").as(Response.Status.UNAUTHORIZED);
            }
        }
        else
        {
            throw  new WSException("Invalid Token").as(Response.Status.UNAUTHORIZED);
        }
    }
}

