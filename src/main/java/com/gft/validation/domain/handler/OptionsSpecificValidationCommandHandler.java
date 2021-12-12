package com.gft.validation.domain.handler;

import com.gft.validation.domain.ValidationError;
import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.OptionsSpecificValidationCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class OptionsSpecificValidationCommandHandler extends BasicValidationCommandHandler<OptionsSpecificValidationCommand> {

    private static String AMERICAN_STYLE = "AMERICAN";

    private static List<String> ALLOWED_STYLES = List.of(AMERICAN_STYLE, "EUROPEAN");

    public OptionsSpecificValidationCommandHandler(NonWorkingDayValidator nonWorkingDayValidator) {
        super(nonWorkingDayValidator);
    }

    @Override
    public ValidationResult handleCommand(OptionsSpecificValidationCommand command) {
        List<ValidationError> errors = new ArrayList<>();
        String style = command.getStyle();
        if (style != null && !ALLOWED_STYLES.contains(style.toUpperCase())) {
            errors.add(ValidationError.INVALID_STYLE);
        }
        if (AMERICAN_STYLE.equalsIgnoreCase(style)) {
            validateForAmericanStyle(errors, command);
        }

        validateForExpiryDate(errors, command);
        return ValidationResult.of(errors);
    }

    private void validateForAmericanStyle(List<ValidationError> errors, OptionsSpecificValidationCommand command) {
        if (command.getExerciseStartDate() == null || command.getTradeDate() == null || command.getExpiryDate() == null) {
            errors.add(ValidationError.INVALID_EXERCISE_START_DAY);
            return;
        }
        if (command.getExerciseStartDate().isBefore(command.getTradeDate()) ||
            command.getExerciseStartDate().isAfter(command.getExpiryDate())) {
            errors.add(ValidationError.INVALID_EXERCISE_START_DAY);
        }
    }

    private void validateForExpiryDate(List<ValidationError> errors, OptionsSpecificValidationCommand command) {
        if (command.getExpiryDate() == null || command.getDeliveryDate() == null || command.getPremiumDate() == null) {
            errors.add(ValidationError.INVALID_EXPIRY_OR_PREMIUM_DATE);
            return;
        }
        if (command.getExpiryDate().isAfter(command.getDeliveryDate()) || command.getPremiumDate().isAfter(command.getDeliveryDate())) {
            errors.add(ValidationError.INVALID_EXPIRY_OR_PREMIUM_DATE);
        }
    }

    @Override
    public Class<OptionsSpecificValidationCommand> getCommandClass() {
        return OptionsSpecificValidationCommand.class;
    }
}
