package com.astromvc1.daily;


import com.astromvc1.model.AstroSign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping(value="/daily")
public class DailyHoroscopeController {

    private final DailyHoroscopeService dailyHoroscopeService;

    public DailyHoroscopeController(DailyHoroscopeService dailyHoroscopeService) {
        this.dailyHoroscopeService = dailyHoroscopeService;
    }
    //AstroSign enum: ["ARIES","TAURUS","GEMINI","CANCER","LEO","VIRGO","LIBRA","SCORPIO","SAGITTARIUS","CAPRICORN","AQUARIUS","PISCES"]
    //example : localhost:8080/daily/taurus
    @GetMapping("/{sign}")
    public DailyHoroscopeResult getDailyHoroscope(@PathVariable String sign){


        AstroSign astroSign = AstroSign.valueOf(sign.toUpperCase());//enum valueOf requires uppercase
        Date date=Date.valueOf(LocalDate.now()); // sql date = valueOf(LocalDate.now())
        return dailyHoroscopeService.getDailyResult(date,astroSign);

    }
    //example: localhost:8080/daily/
    @GetMapping
    public AstroSign[] getAllSigns(){
        return AstroSign.values();

    }
}
