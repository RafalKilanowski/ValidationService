package com.gft.validation.adapters.rest;

import com.gft.validation.domain.command.ForwardValidationCommand;
import com.gft.validation.domain.command.OptionsSpecificValidationCommand;
import com.gft.validation.domain.command.SpotValidationCommand;
import com.gft.validation.domain.command.ValidationCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationCommandFactoryTest {

    private static final LocalDate TRADE_DATE = LocalDate.of(2020, 10, 10);
    private static final LocalDate VALUE_DATE = LocalDate.of(2020, 11, 10);
    private static final LocalDate EXPIRY_DATE = LocalDate.of(2020, 11, 11);
    private static final LocalDate EXERCISE_START_DATE = LocalDate.of(2020, 11, 12);
    private static final LocalDate DELIVERY_DATE = LocalDate.of(2020, 11, 14);
    private static final LocalDate PREMIUM_DATE = LocalDate.of(2020, 11, 15);
    private static final String CUSTOMER = "customer";
    private static final String TRADER = "trader";
    private static final String CURRENCY = "USD";
    private static final String CURRENCIES_PAIR = "USDEUR";
    private static final String STYLE = "AMERICAN";

    private final ValidationCommandFactory factory = new ValidationCommandFactory();

    @Test
    public void shouldCreateForwardValidationCommand() {
        //given
        TradeRestModel tradeRestModel = aTradeRestModel("Forward");
        //when
        ValidationCommand command = factory.createCommand(tradeRestModel);

        //then
        assertThat(command.getClass()).isEqualTo(ForwardValidationCommand.class);

        ForwardValidationCommand fvc = (ForwardValidationCommand) command;

        assertThat(fvc.getCurrency()).isEqualTo(CURRENCY);
        assertThat(fvc.getCustomer()).isEqualTo(CUSTOMER);
        assertThat(fvc.getTradeDate()).isEqualTo(TRADE_DATE);
        assertThat(fvc.getValueDate()).isEqualTo(VALUE_DATE);
        assertThat(fvc.getCurrenciesPair()).isEqualTo(CURRENCIES_PAIR);
    }

    @Test
    public void shouldCreateOptionsSpecificValidationCommand() {
        //given
        TradeRestModel tradeRestModel = aTradeRestModel("VanillaOption");
        //when
        ValidationCommand command = factory.createCommand(tradeRestModel);

        //then
        assertThat(command.getClass()).isEqualTo(OptionsSpecificValidationCommand.class);

        OptionsSpecificValidationCommand osvc = (OptionsSpecificValidationCommand) command;

        assertThat(osvc.getCurrency()).isEqualTo(CURRENCY);
        assertThat(osvc.getCustomer()).isEqualTo(CUSTOMER);
        assertThat(osvc.getTradeDate()).isEqualTo(TRADE_DATE);
        assertThat(osvc.getValueDate()).isEqualTo(VALUE_DATE);
        assertThat(osvc.getCurrenciesPair()).isEqualTo(CURRENCIES_PAIR);
        assertThat(osvc.getStyle()).isEqualTo(STYLE);
        assertThat(osvc.getExpiryDate()).isEqualTo(EXPIRY_DATE);
        assertThat(osvc.getPremiumDate()).isEqualTo(PREMIUM_DATE);
        assertThat(osvc.getDeliveryDate()).isEqualTo(DELIVERY_DATE);
        assertThat(osvc.getExerciseStartDate()).isEqualTo(EXERCISE_START_DATE);
    }

    @Test
    public void shouldCreateSpotValidationCommand() {
        //given
        TradeRestModel tradeRestModel = aTradeRestModel("Spot");
        //when
        ValidationCommand command = factory.createCommand(tradeRestModel);

        //then
        assertThat(command.getClass()).isEqualTo(SpotValidationCommand.class);

        SpotValidationCommand svc = (SpotValidationCommand) command;

        assertThat(svc.getCurrency()).isEqualTo(CURRENCY);
        assertThat(svc.getCustomer()).isEqualTo(CUSTOMER);
        assertThat(svc.getTradeDate()).isEqualTo(TRADE_DATE);
        assertThat(svc.getValueDate()).isEqualTo(VALUE_DATE);
        assertThat(svc.getCurrenciesPair()).isEqualTo(CURRENCIES_PAIR);
        assertThat(svc.getCurrenciesPair()).isEqualTo(CURRENCIES_PAIR);
    }


    private TradeRestModel aTradeRestModel(String type) {
        return TradeRestModel.builder()
            .tradeDate(TRADE_DATE)
            .valueDate(VALUE_DATE)
            .expiryDate(EXPIRY_DATE)
            .exerciseStartDate(EXERCISE_START_DATE)
            .premiumDate(PREMIUM_DATE)
            .deliveryDate(DELIVERY_DATE)
            .type(type)
            .currency(CURRENCY)
            .customer(CUSTOMER)
            .ccyPair(CURRENCIES_PAIR)
            .trader(TRADER)
            .style(STYLE)
            .build();
    }

}