package com.example.utils;

import lombok.Data;

/**
 * Created by Jorge on 09/07/2016.
 */
@Data
public class WSResponse
{
    private boolean success;
    private String  message;
    private Object  data;
}
