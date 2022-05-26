package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;

public interface DailyHoroscopeService {
   DailyHoroscopeResult getDailyResult(Date date, AstroSign sign);
}
