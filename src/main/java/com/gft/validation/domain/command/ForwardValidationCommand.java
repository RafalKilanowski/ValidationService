package com.gft.validation.domain.command;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;


@Value
public final class ForwardValidationCommand implements ValidationCommand {
    LocalDate tradeDate;
    LocalDate valueDate;
    @NonNull String customer;
    String currency;
    @NonNull String currenciesPair;
    @NonNull String trader;
}
