package com.gft.validation.adapters.rest;

import com.gft.validation.adapters.exception.UnsupportedTransactionTypeException;
import com.gft.validation.domain.command.ForwardValidationCommand;
import com.gft.validation.domain.command.OptionsSpecificValidationCommand;
import com.gft.validation.domain.command.SpotValidationCommand;
import com.gft.validation.domain.command.ValidationCommand;
import org.springframework.stereotype.Component;

@Component
public class ValidationCommandFactory {

    //it may be an enum but not big amount info about transactions types... if I should support other ones I will need more details
    private static final String SPOT_TRANSACTION_TYPE_NAME = "Spot";
    private static final String FORWARD_TRANSACTION_TYPE_NAME = "Forward";
    private static final String VANILLA_OPTION_TRANSACTION_TYPE_NAME = "VanillaOption";

    public ValidationCommand createCommand(TradeRestModel restModel) {
        String transactionType = restModel.getType();
        if (transactionType.equalsIgnoreCase(SPOT_TRANSACTION_TYPE_NAME)) {
            return new SpotValidationCommand(
                restModel.getTradeDate(),
                restModel.getValueDate(),
                restModel.getCustomer(),
                restModel.getCurrency(),
                restModel.getCcyPair(),
                restModel.getTrader()
            );
        } else if (transactionType.equalsIgnoreCase(FORWARD_TRANSACTION_TYPE_NAME)) {
            return new ForwardValidationCommand(
                restModel.getTradeDate(),
                restModel.getValueDate(),
                restModel.getCustomer(),
                restModel.getCurrency(),
                restModel.getCcyPair(),
                restModel.getTrader()
            );
        } else if (transactionType.equalsIgnoreCase(VANILLA_OPTION_TRANSACTION_TYPE_NAME)) {
            return new OptionsSpecificValidationCommand(
                restModel.getTradeDate(),
                restModel.getValueDate(),
                restModel.getCustomer(),
                restModel.getCurrency(),
                restModel.getCcyPair(),
                restModel.getStyle(),
                restModel.getExpiryDate(),
                restModel.getExerciseStartDate(),
                restModel.getDeliveryDate(),
                restModel.getPremiumDate(),
                restModel.getTrader()
            );
        }
        throw new UnsupportedTransactionTypeException();
    }
}

