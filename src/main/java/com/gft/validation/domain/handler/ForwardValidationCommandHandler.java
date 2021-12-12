package com.gft.validation.domain.handler;

import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.ForwardValidationCommand;
import org.springframework.stereotype.Component;

@Component
class ForwardValidationCommandHandler extends BasicValidationCommandHandler<ForwardValidationCommand> {

    public ForwardValidationCommandHandler(NonWorkingDayValidator nonWorkingDayValidator) {
        super(nonWorkingDayValidator);
    }

    @Override
    public ValidationResult handleCommand(ForwardValidationCommand command) {
        //validate the value date against the product type - i dont understand...
        return ValidationResult.empty();
    }

    @Override
    public Class<ForwardValidationCommand> getCommandClass() {
        return ForwardValidationCommand.class;
    }
}
