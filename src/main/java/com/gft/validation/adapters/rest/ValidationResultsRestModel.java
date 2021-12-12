package com.gft.validation.adapters.rest;

import lombok.Value;

import java.util.Map;

@Value
public class ValidationResultsRestModel {
    Map<TradeRestModel, ValidationResultRestModel> results;
}
