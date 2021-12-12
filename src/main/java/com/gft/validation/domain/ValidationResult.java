package com.gft.validation.domain;

import lombok.NonNull;

import java.util.List;

public record ValidationResult(
    @NonNull List<ValidationError> errors
) {

    public static ValidationResult empty() {
        return new ValidationResult(List.of());
    }

    public static ValidationResult of(List<ValidationError> errors) {
        return new ValidationResult(errors);
    }
}
