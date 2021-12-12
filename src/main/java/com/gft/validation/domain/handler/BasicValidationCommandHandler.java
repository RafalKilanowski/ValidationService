package com.gft.validation.domain.handler;

import com.gft.validation.domain.ValidationError;
import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.ValidationCommand;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@RequiredArgsConstructor
abstract class BasicValidationCommandHandler<T extends ValidationCommand> implements ValidationCommandHandler<T> {

    private static final List<String> supportedCustomers = Arrays.asList("YODA1", "YODA2");
    private static final String ALLOWED_TRADER = "UBS AG";
    private static final int CURRENCIES_SIZE = 6;

    private final NonWorkingDayValidator nonWorkingDayValidator;

    public abstract ValidationResult handleCommand(T command);


    @Override
    public ValidationResult handle(T command) {
        List<ValidationError> basicErrors = basicValidation(command);

        ValidationResult validationResult = handleCommand(command);
        List<ValidationError> additionalErrors = validationResult.errors();
        basicErrors.addAll(additionalErrors);

        return new ValidationResult(basicErrors);
    }

    private List<ValidationError> basicValidation(T command) {
        List<ValidationError> errors = new ArrayList<>();

        if (command.getTrader().equalsIgnoreCase(ALLOWED_TRADER)) {
            errors.add(ValidationError.INVALID_TRADER);
        }

        LocalDate valueDate = command.getValueDate();

        if (valueDate != null) {
            if (command.getTradeDate() != null && valueDate.isBefore(command.getTradeDate())) {
                errors.add(ValidationError.VALUE_DATE_BEFORE_TRADE_DATE);
            }
            if (command.getCurrency() != null && nonWorkingDayValidator.isNonWorkingDay(valueDate)) {
                errors.add(ValidationError.INVALID_VALUE_DATE_FOR_CURRENCY);
            }
        }

        if (supportedCustomers.contains(command.getCustomer().toUpperCase())) {
            errors.add(ValidationError.UNSUPPORTED_COUNTERPARTY);
        }
        String currencies = command.getCurrenciesPair();
        if (currencies.length() != CURRENCIES_SIZE) {
            errors.add(ValidationError.INVALID_CURRENCY);
        }
        try {
            Currency c1 = Currency.getInstance(currencies.substring(0, 3));
            Currency c2 = Currency.getInstance(currencies.substring(3, 6));
            if (c1 == null || c2 == null) {
                errors.add(ValidationError.INVALID_CURRENCY);
            }
        } catch (IllegalArgumentException ex) {
            errors.add(ValidationError.INVALID_CURRENCY);
        }
        return errors;
    }
}
