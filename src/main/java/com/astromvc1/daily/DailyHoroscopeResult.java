package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;

public class DailyHoroscopeResult {
    //private int id;
    private String payload; // mozda description
    private Date date;
    private AstroSign sign;

    public DailyHoroscopeResult(Date date, AstroSign sign) {
            this.payload="Ja cu ti policiju poslat";
            this.date=date;
            this.sign=sign;
    }
    public DailyHoroscopeResult(Date date, AstroSign sign, String payload){
        this.payload=payload;
        this.date=date;
        this.sign=sign;
    }

    @Override
    public String toString() {
        return "DailyHoroscopeResult{" +
                "payload='" + payload + '\'' +
                ", date=" + date +
                ", sign=" + sign +
                '}';
    }

    public String getPayload() {
        return payload + " kroz getPayload()";
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AstroSign getSign() {
        return sign;
    }

    public void setSign(AstroSign sign) {
        this.sign = sign;
    }

//    public void setResult(String result) {
//        this.result = result;
//    }
//    public String getResult() {
//        return result;
//    }
}
