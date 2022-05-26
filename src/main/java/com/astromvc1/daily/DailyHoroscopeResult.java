package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;

public class DailyHoroscopeResult {
    private String result;

    public DailyHoroscopeResult(Date date, AstroSign sign) {
            result=date+" "+ sign;
    }
    public String toString(){
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }
}
