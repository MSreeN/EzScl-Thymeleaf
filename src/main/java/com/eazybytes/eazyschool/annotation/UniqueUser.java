package com.eazybytes.eazyschool.annotation;

import com.eazybytes.eazyschool.validations.PersistUnqUser;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Constraint(validatedBy = PersistUnqUser.class)
public @interface UniqueUser {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";
}
