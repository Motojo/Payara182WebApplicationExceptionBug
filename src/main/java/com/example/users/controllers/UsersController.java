package com.example.users.controllers;

import com.example.common.exceptions.ws.WSException;
import com.example.common.utils.Hash;
import com.example.common.utils.properties.PropertiesFromFile;
import com.example.common.validators.auth.user.AuthenticatedUser;
import com.example.common.validators.auth.user.Secured;
import com.example.users.beans.UserBean;
import com.example.users.controllers.request.LoginRequest;
import com.example.users.controllers.request.RegisterRequest;
import com.example.users.controllers.response.LoginResponse;
import com.example.users.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by Jorge on 23/02/2017.
 */

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersController
{
    @Inject
    @PropertiesFromFile
    Properties properties;

    @EJB
    UserBean userBean;

    @Inject
    @AuthenticatedUser
    User user;

    @GET
    @Secured
    public List<User> allUsers()
    {
        return userBean.all();
    }

    @POST
    @Path("/login")
    public LoginResponse login(@Valid LoginRequest request)
    {
        User user = userBean.byEmail(request.getEmail());
        if(user == null )
        {
            throw new WSException("incorrect User/Password").as(Response.Status.UNAUTHORIZED);
        }

        if(!Hash.checkPassword(request.getPassword(), user.getPassword()))
        {
            throw new WSException("incorrect User/Password").as(Response.Status.UNAUTHORIZED);
        }

        String key = properties.getProperty("application.key");
        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setLoginTime(new Date());

        return response;
    }

    @POST
    @Path("/register")
    public User register(@Valid RegisterRequest request)
    {
        User user = userBean.byEmail(request.getEmail());
        if( user != null )
        {
            throw new WSException("A registered user already exists with this email");
        }
        user = userBean.add(request.getEmail(), request.getPassword());
        return user;
    }


    @DELETE
    @Path("/{id}")
    public String deleteUser(@PathParam("id") Long id)
    {
        User user = userBean.byId(id);
        if(user == null)
        {
            throw new WSException("Username does not exist\n").as(Response.Status.NOT_FOUND);
        }
        userBean.remove(id);
        return "User successfully deleted";
    }

}
