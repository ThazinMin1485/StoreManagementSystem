package com.store.system.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityOrKgValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityOrKgRequired {
    String message() default "Either quantity or kg must be set, but not both";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
