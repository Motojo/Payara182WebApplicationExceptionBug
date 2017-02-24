package com.example.users.beans;

import com.example.common.utils.Hash;
import com.example.users.models.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Jorge on 23/02/2017.
 */
@Local
@Stateless
public class UserBean
{
    @PersistenceContext
    EntityManager db;

    public List<User> all()
    {

        return db.createNamedQuery("All Users", User.class).getResultList();

    }

    public User byId(Long id)
    {
        return db.find(User.class,id);
    }

    public  User byEmail(String email)
    {
        User user = null;
        try
        {
            user = db.createNamedQuery("User by email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }
        catch (NoResultException ignored){}

        return user;
    }

    public User add(String email, String password)
    {
        User user = new User();
        user.setEmail(email);
        user.setPassword(Hash.createPassword(password));
        user.setCreatedAt(new Date());

        db.persist(user);
        return user;
    }


    public void remove(String email)
    {
        User user = byEmail(email);
        if( user!=null)
        {
            db.remove(user);
        }
    }

    public void remove(Long id)
    {
        User user = byId(id);
        if( user!=null)
        {
            db.remove(user);
        }
    }

    public User updatePassword(String email, String password)
    {
        User user = byEmail(email);
        if( user != null )
        {
            user.setPassword(Hash.createPassword(password));
            db.merge(user);
        }
        return user;
    }
}
