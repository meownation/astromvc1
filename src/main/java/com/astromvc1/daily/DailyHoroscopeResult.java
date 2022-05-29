package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DailyHoroscopeResult {
    //private int id;
    private String payload; // mozda description
    private Date date;
    private AstroSign sign;

    public DailyHoroscopeResult(Date date, AstroSign sign, List<Optional<Paragraph>> lp) {
            List<Paragraph> l= new ArrayList<>();
            StringBuffer buff = new StringBuffer();
               l= lp.
                   stream().
                        filter(Optional::isPresent).
                        map(Optional::get).collect(Collectors.toList());
               lp.stream().
                       filter(Optional::isPresent).
                       map(Optional::get).forEach(p->buff.append(p.getTopic()).append(": ").append(p.getText()).append(" "));
            this.payload= buff.toString();
            this.date=date;
            this.sign=sign;
    }
    public DailyHoroscopeResult(String payload, Date date, AstroSign sign){
        this.payload=payload;
        this.date=date;
        this.sign=sign;
    }

    public DailyHoroscopeResult() {
    }// potreban za deserijalizaciju pri postu

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
