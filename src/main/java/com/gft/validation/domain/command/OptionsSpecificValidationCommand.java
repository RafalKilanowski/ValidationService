package com.gft.validation.domain.command;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class OptionsSpecificValidationCommand implements ValidationCommand {
    LocalDate tradeDate;
    LocalDate valueDate;
    @NonNull String customer;
    String currency;
    @NonNull String currenciesPair;
    String style;
    LocalDate expiryDate;
    LocalDate exerciseStartDate;
    LocalDate deliveryDate;
    LocalDate premiumDate;
    @NonNull String trader;

}
