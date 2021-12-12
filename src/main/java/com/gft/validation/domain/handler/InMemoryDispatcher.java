package com.gft.validation.domain.handler;

import com.gft.validation.domain.Dispatcher;
import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.ValidationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class InMemoryDispatcher implements Dispatcher {

    private final List<ValidationCommandHandler> validationCommandHandlers;

    @Override
    public <T extends ValidationCommand> ValidationResult handle(T var1) {
        return validationCommandHandlers.stream()
            .filter(h -> h.getCommandClass().equals(var1.getClass()))
            .findFirst()
            .map(handler -> handler.handle(var1))
            .orElseThrow(() -> new RuntimeException("Unsupported command type !"));
    }
}
