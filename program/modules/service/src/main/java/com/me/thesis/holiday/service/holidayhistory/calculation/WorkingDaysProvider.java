package com.me.thesis.holiday.service.holidayhistory.calculation;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

/**
 * The type Working days provider.
 */
@Service
public class WorkingDaysProvider {

    private static final DayOfWeek SATURDAY = DayOfWeek.SATURDAY;
    private static final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;
    private static final int DAYS_TO_ADD = 1;

    /**
     * Provide big decimal.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param holidays  the holidays
     *
     * @return the big decimal
     */
    public BigDecimal provide(LocalDate startDate, LocalDate endDate, List<LocalDate> holidays) {
        return BigDecimal.valueOf(Stream.iterate(startDate, date -> date.plusDays(DAYS_TO_ADD))
            .limit(getPeriod(startDate, endDate))
            .filter(isHoliday(holidays).or(isWeekend())
                .negate())
            .count());
    }

    private long getPeriod(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    private Predicate<LocalDate> isHoliday(List<LocalDate> holidays) {
        return date -> !holidays.isEmpty() && holidays.contains(date);
    }

    private Predicate<LocalDate> isWeekend() {
        return date -> date.getDayOfWeek() == SATURDAY || date.getDayOfWeek() == SUNDAY;
    }

}
