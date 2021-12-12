package com.gft.validation.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationClock {

    public LocalDate getCurrentDate() {
        return LocalDate.of(2020, 10, 9);
    }
}
