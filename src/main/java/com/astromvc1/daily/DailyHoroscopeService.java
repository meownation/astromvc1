package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.time.LocalDate;

public interface DailyHoroscopeService {
   DailyHoroscope getDailyHoroscope(LocalDate date, AstroSign sign);
   DailyHoroscope save(DailyHoroscope dailyHoroscope);

   void deleteHoroscope(LocalDate date,AstroSign astroSign);
}
