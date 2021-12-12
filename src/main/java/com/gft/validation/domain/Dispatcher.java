package com.gft.validation.domain;

import com.gft.validation.domain.command.ValidationCommand;

public interface Dispatcher {

    <T extends ValidationCommand> ValidationResult handle(T var1);
}
