package com.example.common.validators.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jorge on 8/2/2016.
 */
public class EmailValidator implements ConstraintValidator<Email,String>
{

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    public void initialize(Email constraintAnnotation){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        boolean response = false;
        if(value!=null)
        {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(value);
            response = matcher.matches();
        }

        return response;
    }
}
