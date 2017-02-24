package com.example.users.controllers.request;

import com.example.common.validators.email.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Jorge on 23/02/2017.
 */

@Data
public class LoginRequest
{
    @Email
    @NotNull
    @JsonProperty
    private String email;

    @NotNull
    @JsonProperty
    private String password;

}
