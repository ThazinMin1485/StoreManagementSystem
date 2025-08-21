package com.store.system.validator;

import com.store.system.dto.GoodDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuantityXorKgValidator implements ConstraintValidator<QuantityXorKgRequired, GoodDTO> {
    @Override
    public boolean isValid(GoodDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true;  // if object null, don't validate here

        boolean hasQuantity = dto.getQuantity() != null;
        boolean hasKg = dto.getKg() != null;

        // Return true only if exactly one is true (XOR)
        return hasQuantity ^ hasKg;
    }
}
