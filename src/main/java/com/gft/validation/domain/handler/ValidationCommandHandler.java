package com.gft.validation.domain.handler;

import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.ValidationCommand;

interface ValidationCommandHandler<T extends ValidationCommand> {
    ValidationResult handle(T var1);

    Class<T> getCommandClass();
}
