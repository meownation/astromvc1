package com.astromvc1.daily;


import com.astromvc1.model.AstroSign;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(value="/daily")
public class DailyHoroscopeController {
    private final DailyHoroscopeService dailyHoroscopeService;
    public DailyHoroscopeController(DailyHoroscopeService dailyHoroscopeService) {
        this.dailyHoroscopeService = dailyHoroscopeService;
    }
    //example: localhost:8080/daily/taurus
    @GetMapping("/{astroSign}")
    public DailyHoroscope getDailyHoroscope(@Parameter(name =  "astroSign", example = "LEO", required = true) @PathVariable AstroSign astroSign,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date
    ){
        // AstroSign astroSign = AstroSign.valueOf(sign.toUpperCase());//enum valueOf requires uppercase

        return dailyHoroscopeService.getDailyHoroscope(date.orElseGet(LocalDate::now),astroSign);
    }
    //example: localhost:8080/daily/
    //ARIES, TAURUS, GEMINI, CANCER, LEO, VIRGO, LIBRA, SCORPIO, SAGITTARIUS, CAPRICORN, AQUARIUS, PISCES, UNKNOWN
    @GetMapping
    public AstroSign[] getAllSigns(){
        return AstroSign.values();
    }

    @DeleteMapping("/{astroSign}")
    public void deleteDailyHoroscope(@Parameter(name = "astroSign", example = "LEO" , required = true)
                                     @PathVariable AstroSign astroSign,
                                     @Parameter(name = "date" , example = "2022-06-06")
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date){

        dailyHoroscopeService.deleteHoroscope(date.orElseGet(()->LocalDate.now()),astroSign);
    }

//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void postDailyHoroscope(@RequestBody DailyHoroscope dailyHoroscope){
//            dailyHoroscopeService.addDailyResult(dailyHoroscope);
//    }
}
