package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;
import java.time.LocalDate;

public interface DailyHoroscopeService {
   DailyHoroscope getDailyHoroscope(Date date, AstroSign sign);
   DailyHoroscope save(DailyHoroscope dailyHoroscope);

   void deleteHoroscope(LocalDate date,AstroSign astroSign);
}
