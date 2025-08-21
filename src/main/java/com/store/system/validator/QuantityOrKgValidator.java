package com.store.system.validator;

import com.store.system.dto.GoodArrivalDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuantityOrKgValidator implements ConstraintValidator<QuantityOrKgRequired, GoodArrivalDTO> {
    @Override
    public boolean isValid(GoodArrivalDTO value, ConstraintValidatorContext context) {
        if (value == null) return true;  // if object null, don't validate here

        boolean hasQuantity = value.getQuantity() != null && value.getQuantity() > 0;
        boolean hasKg = value.getKg() != null && value.getKg() > 0;

        // Return true only if exactly one is true (XOR)
        return hasQuantity ^ hasKg;
    }
}
