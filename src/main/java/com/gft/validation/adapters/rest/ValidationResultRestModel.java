package com.gft.validation.adapters.rest;

import com.gft.validation.domain.ValidationResult;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ValidationResultRestModel {

    List<String> errors;

    boolean hasErrors;

    private ValidationResultRestModel(@NonNull List<String> errors) {
        this.errors = errors;
        hasErrors = !errors.isEmpty();
    }

    public static ValidationResultRestModel of(ValidationResult result) {
        if (result.errors().isEmpty()) {
            return new ValidationResultRestModel(List.of());
        }
        List<String> errors = result.errors().stream()
            .map(e -> e.getErrorMessage())
            .collect(Collectors.toList());

        return new ValidationResultRestModel(errors);
    }
}
