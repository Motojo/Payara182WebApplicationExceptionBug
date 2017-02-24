package com.example.common.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Hash
{

    public static String createPassword(String clearString)
    {
        String response = clearString;
        if (clearString != null)
        {
            response =  BCrypt.hashpw(clearString, BCrypt.gensalt());
        }
        return response;
    }

    public static boolean checkPassword(String candidate, String encryptedPassword)
    {
        if (candidate == null)
        {
            return false;
        }
        return encryptedPassword != null && BCrypt.checkpw(candidate, encryptedPassword);
    }
}
