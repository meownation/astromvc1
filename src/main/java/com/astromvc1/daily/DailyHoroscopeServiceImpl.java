package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import com.astromvc1.paragraph.ParagraphService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyHoroscopeServiceImpl implements DailyHoroscopeService{

    private final DailyHoroscopeDao primaryRepository;

    private final ParagraphService paragraphService;

    private final DailyHoroscopeDao cacheRepository;

    public DailyHoroscopeServiceImpl(@Qualifier("postgres") DailyHoroscopeDao dailyHoroscopeDao,
                                     ParagraphService paragraphService,
                                     @Qualifier("redis") DailyHoroscopeDao cacheRepository
                                     ){
        this.primaryRepository = dailyHoroscopeDao;
        this.paragraphService= paragraphService;
        this.cacheRepository = cacheRepository;
    }
    @Override
    public DailyHoroscope getDailyHoroscope(Date date, AstroSign sign) {
        boolean cacheMiss=false;
        Optional<DailyHoroscope> result= cacheRepository.readDailyHoroscope(date,sign);
        if(result.isEmpty()) {cacheMiss=true; result=primaryRepository.readDailyHoroscope(date,sign);}
        if(result.isPresent() && cacheMiss==true) cacheRepository.save(result.get());
        //orElse would always evaluate and save as side effect
        return result.orElseGet(()->save(new DailyHoroscope(date, sign, paragraphService.generateParagraphs())));
    }

       @Override
       public DailyHoroscope save(DailyHoroscope dailyHoroscope) {
       primaryRepository.save(dailyHoroscope);
       cacheRepository.save(dailyHoroscope);
       return dailyHoroscope;
    }

    @Override
    public void deleteHoroscope(LocalDate date, AstroSign astroSign) {
        primaryRepository.delete(date ,astroSign);
        cacheRepository.delete(date ,astroSign);
    }
}
