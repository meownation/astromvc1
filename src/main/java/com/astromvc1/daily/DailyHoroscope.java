package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;
import java.util.List;

public class DailyHoroscope {
    //private int id;
    private List<Paragraph> payload; // mozda description
    private Date date;
    private AstroSign sign;

    public DailyHoroscope(Date date, AstroSign sign, List<Paragraph> lp) {
            this.payload=lp;
            this.date=date;
            this.sign=sign;
    }

    public DailyHoroscope() {
    }// potreban za deserijalizaciju pri postu

    @Override
    public String toString() {
        return "DailyHoroscopeResult{" +
                "payload='" + payload + '\'' +
                ", date=" + date +
                ", sign=" + sign +
                '}';
    }

    public List<Paragraph> getPayload() {
        return payload;
    }

    public void setPayload(List<Paragraph> list) {
        this.payload=list;
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

}
