package com.gft.validation.domain.command;

import java.time.LocalDate;

public interface ValidationCommand {
    LocalDate getTradeDate();

    LocalDate getValueDate();

    String getCustomer();

    String getCurrenciesPair();

    String getCurrency();

    String getTrader();
}
