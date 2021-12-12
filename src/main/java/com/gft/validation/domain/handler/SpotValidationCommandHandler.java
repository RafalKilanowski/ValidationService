package com.gft.validation.domain.handler;

import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.SpotValidationCommand;
import org.springframework.stereotype.Component;

@Component
class SpotValidationCommandHandler extends BasicValidationCommandHandler<SpotValidationCommand> {

    public SpotValidationCommandHandler(NonWorkingDayValidator nonWorkingDayValidator) {
        super(nonWorkingDayValidator);
    }

    @Override
    public ValidationResult handleCommand(SpotValidationCommand command) {
        //validate the value date against the product type - i dont understand...
        return ValidationResult.empty();
    }

    @Override
    public Class<SpotValidationCommand> getCommandClass() {
        return SpotValidationCommand.class;
    }
}
