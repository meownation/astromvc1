package com.astromvc1.daily;


import com.astromvc1.model.AstroSign;
import io.swagger.v3.oas.annotations.Parameter;
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
    //example: localhost:8080/daily/taurus
    @GetMapping("/{astroSign}")
    public DailyHoroscope getDailyHoroscope(@Parameter(
            name =  "astroSign",
            example = "LEO",
            required = true) @PathVariable AstroSign astroSign){
        // AstroSign astroSign = AstroSign.valueOf(sign.toUpperCase());//enum valueOf requires uppercase
        Date date=Date.valueOf(LocalDate.now()); // sql date = valueOf(LocalDate.now())
        return dailyHoroscopeService.getDailyHoroscope(date,astroSign);
    }
    //example: localhost:8080/daily/
    //ARIES, TAURUS, GEMINI, CANCER, LEO, VIRGO, LIBRA, SCORPIO, SAGITTARIUS, CAPRICORN, AQUARIUS, PISCES, UNKNOWN
    @GetMapping
    public AstroSign[] getAllSigns(){
        return AstroSign.values();
    }

//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void postDailyHoroscope(@RequestBody DailyHoroscope dailyHoroscope){
//            dailyHoroscopeService.addDailyResult(dailyHoroscope);
//    }
}
