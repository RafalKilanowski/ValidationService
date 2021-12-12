package com.gft.validation.adapters.rest;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
@EqualsAndHashCode
public class TradeRestModel {
    @NotNull String customer;
    @NotNull String ccyPair;
    @NotNull String type;
    String style;
    LocalDate tradeDate;
    LocalDate valueDate;
    LocalDate deliveryDate;
    LocalDate expiryDate;
    LocalDate exerciseStartDate;
    String currency;
    LocalDate premiumDate;
    String legalEntity;
    @NotNull String trader;

}
