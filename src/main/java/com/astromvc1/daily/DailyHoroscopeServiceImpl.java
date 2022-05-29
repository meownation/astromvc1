package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class DailyHoroscopeServiceImpl implements DailyHoroscopeService{
    private final DailyHoroscopeDao dailyHoroscopeDao;
    private final ParagraphService paragraphService;

    public DailyHoroscopeServiceImpl(DailyHoroscopeDao dailyHoroscopeDao, ParagraphService paragraphService){
        this.dailyHoroscopeDao = dailyHoroscopeDao;
        this.paragraphService= paragraphService;
    }
    @Override
    public DailyHoroscope getDailyResult(Date date, AstroSign sign) {
        //TODO
        //return new DailyHoroscopeResult (date,sign);
        Optional<DailyHoroscope> resultOpt= dailyHoroscopeDao.getDailyHoroscopeResult(date,sign);
        return resultOpt.orElse(new DailyHoroscope(date, sign, paragraphService.generateParagraphs()));
    }

    @Override
    public void addDailyResult(DailyHoroscope dailyHoroscope) {
        dailyHoroscopeDao.insertDailyResult(dailyHoroscope);
    }
}
