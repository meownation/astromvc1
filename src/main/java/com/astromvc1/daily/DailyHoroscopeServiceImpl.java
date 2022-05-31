package com.astromvc1.daily;

import com.astromvc1.cache.RedisRepository;
import com.astromvc1.model.AstroSign;
import com.astromvc1.paragraph.ParagraphService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class DailyHoroscopeServiceImpl implements DailyHoroscopeService{
    private final DailyHoroscopeDao dailyHoroscopeDao;
    private final ParagraphService paragraphService;
    private final RedisRepository redisRepository;

    public DailyHoroscopeServiceImpl(DailyHoroscopeDao dailyHoroscopeDao,
                                     ParagraphService paragraphService,
                                     RedisRepository redisRepository
                                     ){
        this.dailyHoroscopeDao = dailyHoroscopeDao;
        this.paragraphService= paragraphService;
        this.redisRepository= redisRepository;
    }
    @Override
    public DailyHoroscope getDailyResult(Date date, AstroSign sign) {
        Optional<DailyHoroscope> resultOpt= dailyHoroscopeDao.getDailyHoroscopeResult(date,sign);
       // return resultOpt.orElse(save(new DailyHoroscope(date, sign, paragraphService.generateParagraphs()))); orElse se uvek izvrsava
        return resultOpt.orElseGet(()->save(new DailyHoroscope(date, sign, paragraphService.generateParagraphs())));
    }

       @Override
       public DailyHoroscope save(DailyHoroscope dailyHoroscope) {
       dailyHoroscopeDao.saveDailyResult(dailyHoroscope);
       redisRepository.saveDailyResult(dailyHoroscope);
       return dailyHoroscope;
    }
}
