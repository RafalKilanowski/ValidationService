package com.gft.validation.domain.command;


import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

// - validate the value date against the product type ???? I don't understand based on the information I received...
@Value
public class SpotValidationCommand implements ValidationCommand {
    LocalDate tradeDate;
    LocalDate valueDate;
    @NonNull String customer;
    String currency;
    @NonNull String currenciesPair;
    @NonNull String trader;
}
