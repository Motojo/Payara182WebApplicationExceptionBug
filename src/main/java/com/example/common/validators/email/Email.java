package com.example.common.validators.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by Jorge on 01/10/2016.
 */

/* Nested Validators*/

/*Annotation Configuration*/
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, TYPE})

/*Validator Class to Use*/
@Constraint(validatedBy = {EmailValidator.class})
public @interface Email
{
    String message() default "Invalid Email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
