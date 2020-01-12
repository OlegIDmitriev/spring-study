package com.apress.prospring5.ch10.annot;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CountrySingerValidatorClass.class)
public @interface CheckCountrySinger {
    String message() default "Country SInger should have gender and last name defined";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
