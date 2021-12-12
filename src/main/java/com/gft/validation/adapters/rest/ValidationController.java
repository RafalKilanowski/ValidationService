package com.gft.validation.adapters.rest;

import com.gft.validation.domain.Dispatcher;
import com.gft.validation.domain.ValidationResult;
import com.gft.validation.domain.command.ValidationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationCommandFactory factory;

    private final Dispatcher dispatcher;

    @PostMapping("/validate-single")
    public ResponseEntity<ValidationResultRestModel> validate(@Valid @RequestBody TradeRestModel trade) {
        ValidationCommand command = factory.createCommand(trade);
        ValidationResult result = dispatcher.handle(command);
        return ResponseEntity.ok(ValidationResultRestModel.of(result));
    }

    @PostMapping("/validate-batch")
    public ResponseEntity<ValidationResultsRestModel> validate(@Valid @RequestBody List<TradeRestModel> trades) {
        Map<TradeRestModel, ValidationResultRestModel> results = new HashMap<>();
        trades.forEach(trade -> {
                ValidationCommand command = factory.createCommand(trade);
                ValidationResult result = dispatcher.handle(command);
                results.put(trade, ValidationResultRestModel.of(result));
            }
        );
        return ResponseEntity.ok(new ValidationResultsRestModel(results));
    }
}
