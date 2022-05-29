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
    public DailyHoroscopeResult getDailyResult(Date date, AstroSign sign) {
        //TODO
        //return new DailyHoroscopeResult (date,sign);
        Optional<DailyHoroscopeResult> resultOpt= dailyHoroscopeDao.getDailyHoroscopeResult(date,sign);
        return resultOpt.orElse(new DailyHoroscopeResult(date, sign, paragraphService.generateParagraphs()));
    }

    @Override
    public void addDailyResult(DailyHoroscopeResult dailyHoroscopeResult) {
        dailyHoroscopeDao.insertDailyResult(dailyHoroscopeResult);
    }
}
