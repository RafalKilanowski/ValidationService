package com.gft.validation.domain;

import com.gft.validation.domain.command.ForwardValidationCommand;
import com.gft.validation.domain.command.OptionsSpecificValidationCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DispatcherTest {

    private static final LocalDate TRADE_DATE = LocalDate.of(2020, 10, 10);
    private static final LocalDate VALUE_DATE = LocalDate.of(2020, 11, 10);
    private static final LocalDate EXPIRY_DATE = LocalDate.of(2020, 11, 11);
    private static final LocalDate EXERCISE_START_DATE = LocalDate.of(2020, 11, 12);
    private static final LocalDate DELIVERY_DATE = LocalDate.of(2020, 11, 14);
    private static final LocalDate PREMIUM_DATE = LocalDate.of(2020, 11, 15);
    private static final String CUSTOMER = "customer";
    private static final String TRADER = "trader";
    private static final String CURRENCY = "PLN";
    private static final String CURRENCIES_PAIR = "USDEUR";
    private static final String STYLE = "AMERICAN";

    @Autowired
    private Dispatcher dispatcher;

    @Test
    public void shouldReturnValidationErrorsForOptionsSpecificCommand() {
        //given
        OptionsSpecificValidationCommand command = new OptionsSpecificValidationCommand(
            TRADE_DATE,
            VALUE_DATE,
            CUSTOMER,
            CURRENCY,
            CURRENCIES_PAIR,
            STYLE,
            EXPIRY_DATE,
            EXERCISE_START_DATE,
            DELIVERY_DATE,
            PREMIUM_DATE,
            TRADER
        );

        //when
        ValidationResult results = dispatcher.handle(command);

        //then
        assertThat(results.errors().size()).isEqualTo(3);
        assertThat(results.errors()).containsExactlyInAnyOrder(
            ValidationError.INVALID_CURRENCY,
            ValidationError.INVALID_EXERCISE_START_DAY,
            ValidationError.INVALID_EXPIRY_OR_PREMIUM_DATE
        );
    }

    @Test
    public void shouldReturnEmptyValidationErrorsForValidForwardValidationCommand() {
        //given
        ForwardValidationCommand command = new ForwardValidationCommand(
            TRADE_DATE,
            VALUE_DATE,
            CUSTOMER,
            "PLN",
            CURRENCIES_PAIR,
            TRADER
        );

        //when
        ValidationResult results = dispatcher.handle(command);

        //then
        assertThat(results.errors()).isEmpty();
    }
}