package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;
import java.util.Optional;

public interface DailyHoroscopeDao {
    Optional<DailyHoroscope> getDailyHoroscopeResult(Date date, AstroSign sign);
    DailyHoroscope saveDailyResult(DailyHoroscope dailyHoroscope);

}
