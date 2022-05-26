package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class DailyHoroscopeServiceImpl implements DailyHoroscopeService{

    @Override
    public DailyHoroscopeResult getDailyResult(Date date, AstroSign sign) {
        //TODO
        return new DailyHoroscopeResult (date,sign);

    }
}
