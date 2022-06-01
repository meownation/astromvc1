package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import com.astromvc1.paragraph.Paragraph;

import java.sql.Date;
import java.util.List;

public class DailyHoroscope {
    //private int id;
    private List<Paragraph> paragraphs; // mozda description
    private Date date;
    private AstroSign sign;

    public DailyHoroscope(Date date, AstroSign sign, List<Paragraph> lp) {
            this.paragraphs=lp;
            this.date=date;
            this.sign=sign;
    }

    public DailyHoroscope() {
    }// potreban za deserijalizaciju pri postu

    @Override
    public String toString() {
        return "DailyHoroscope{" +
                "paragraphs size=" + paragraphs.toString() +
                ", date=" + date +
                ", sign=" + sign +
                '}';
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setDailyHoroscope(List<Paragraph> list) {
        this.paragraphs=list;
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
