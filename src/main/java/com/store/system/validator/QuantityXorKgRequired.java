package com.store.system.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityXorKgValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityXorKgRequired {
    String message() default "You must fill exactly one of quantity or kg";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
