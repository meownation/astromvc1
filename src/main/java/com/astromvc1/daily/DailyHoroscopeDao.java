package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface DailyHoroscopeDao {
    Optional<DailyHoroscope> readDailyHoroscope(LocalDate date, AstroSign sign);
    DailyHoroscope save(DailyHoroscope dailyHoroscope);
    void delete(LocalDate date, AstroSign astroSign);


}
