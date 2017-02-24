package com.example.common.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jorge on 09/07/2016.
 */
@Data
public class WSResponse
{
    @JsonProperty
    private boolean success;

    @JsonProperty
    private String message;

    @JsonProperty
    private Object data;
}
