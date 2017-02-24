package com.example.users.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Jorge on 23/02/2017.
 */
@Data
public class LoginResponse
{
    @JsonProperty
    private String token;

    @JsonProperty
    private Date loginTime;

}
