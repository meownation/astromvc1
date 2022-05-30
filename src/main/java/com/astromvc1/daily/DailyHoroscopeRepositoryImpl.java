package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import com.astromvc1.paragraph.Paragraph;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class DailyHoroscopeRepositoryImpl implements DailyHoroscopeDao{

    private final JdbcTemplate jdbcTemplate;

    public DailyHoroscopeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DailyHoroscope> getDailyHoroscopeResult(Date date, AstroSign sign) {
        // topic1 2 i 3 su strani kljucevi u dailyHoroscopes, ukazuju na paragraf
        var sql = """
                     SELECT d.prediction_date,
                            d.astrosign, 
                           p1.text AS p1text, 
                           p2.text AS p2text, 
                           p3.text AS p3text, 
                           p1.topic AS p1topic, 
                           p2.topic AS p2topic, 
                           p3.topic AS p3topic
                     FROM dailyHoroscope d
                     JOIN paragraph p1 ON d.topic1=p1.id 
                     JOIN paragraph p2 ON d.topic2=p2.id 
                     JOIN paragraph p3 ON d.topic3=p3.id
                     WHERE prediction_date = ? AND astrosign = ?
                     """;
         return jdbcTemplate.query(sql,this::mapRow,date, sign.toString())
                 .stream()
                 .findFirst();
    }

    private DailyHoroscope mapRow(ResultSet resultSet, int i) throws SQLException {
        List<Paragraph> l = new ArrayList<>();
        l.add(new Paragraph(resultSet.getString("p1topic"), resultSet.getString("p1text")));
        l.add(new Paragraph(resultSet.getString("p2topic"), resultSet.getString("p2text")));
        l.add(new Paragraph(resultSet.getString("p3topic"), resultSet.getString("p3text")));
        return new DailyHoroscope(
                Date.valueOf(LocalDate.parse(resultSet.getString("prediction_date"))), // string to LocalDate to sql.Date
                AstroSign.valueOf(resultSet.getString("astrosign")),
                l

        );
    }
}
