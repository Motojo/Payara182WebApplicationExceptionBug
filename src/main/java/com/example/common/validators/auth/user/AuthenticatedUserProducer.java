package com.example.common.validators.auth.user;


import com.example.users.beans.UserBean;
import com.example.users.models.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

/**
 * Created by Jorge on 13/06/2016.
 */
@RequestScoped
public class AuthenticatedUserProducer
{
    @EJB
    UserBean userBean;

    @Produces
    @RequestScoped
    @AuthenticatedUser
    private User user = null;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUser long id)
    {
        this.user = userBean.byId(id);
    }

}
