package com.example.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jorge on 23/02/2017.
 */

@NamedQueries
({
    @NamedQuery(name = "All Users", query = "SELECT user FROM User user"),
    @NamedQuery(name = "User by email", query = "SELECT user FROM User user WHERE user.email =:email")
})


@Data
@Entity
@Table(name = "user")
public class User
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password_hash")
    private String password;

    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
