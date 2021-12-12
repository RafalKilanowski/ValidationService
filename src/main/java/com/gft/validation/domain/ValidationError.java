package com.gft.validation.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ValidationError {

    INVALID_TRADER(("Only UBS AG trader is supported!")),
    VALUE_DATE_BEFORE_TRADE_DATE(("Value date cannot be before trade date!")),
    INVALID_VALUE_DATE_FOR_CURRENCY(("Value date cannot be before trade date!")),
    UNSUPPORTED_COUNTERPARTY("Unsupported counterparty"),
    INVALID_CURRENCY("Invalid currencies - ISO 4217"),
    INVALID_VALUE_DATE_PRODUCT_TYPE("Invalid value date - product type"), //????? lack of info
    INVALID_STYLE("Style can be american or european"),
    INVALID_EXERCISE_START_DAY("Invalid exercise start date - has to be after trade date but before expiry date"),
    INVALID_EXPIRY_OR_PREMIUM_DATE("Expiry date and premium date must be before delivery date");


    @Getter
    private String errorMessage;

}
