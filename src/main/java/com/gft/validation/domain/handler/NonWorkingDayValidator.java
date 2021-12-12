package com.gft.validation.domain.handler;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Component
class NonWorkingDayValidator {

    private final Map<Month, List<Integer>> holidays;

    public NonWorkingDayValidator() {
        holidays = Map.of(
            Month.JANUARY, List.of(1, 6),
            Month.MAY, List.of(1),
            Month.DECEMBER, List.of(25, 26)
        );
    }

    public boolean isNonWorkingDay(LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.SUNDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            return true;
        }

        Month month = localDate.getMonth();
        int dayOfMonth = localDate.getDayOfMonth();
        List<Integer> monthHolidays = holidays.get(month);

        return monthHolidays != null && monthHolidays.contains(dayOfMonth);
    }
}
