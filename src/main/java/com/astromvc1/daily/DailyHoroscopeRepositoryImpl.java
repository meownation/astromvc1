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
@Repository("postgres")
public class DailyHoroscopeRepositoryImpl implements DailyHoroscopeDao{

    private final JdbcTemplate jdbcTemplate;

    public DailyHoroscopeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DailyHoroscope> readDailyHoroscope(Date date, AstroSign sign) {
        // topic1 2 i 3 su strani kljucevi u dailyHoroscopes, ukazuju na paragraf
        var sql = """
                     SELECT d.prediction_date,
                            d.astrosign,
                           p1.text AS p1text, 
                           p2.text AS p2text,
                           p3.text AS p3text,
                           p1.topic AS p1topic,
                           p2.topic AS p2topic,
                           p3.topic AS p3topic,
                           p1.id AS p1id,
                           p2.id AS p2id,
                           p3.id AS p3id
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
        l.add(new Paragraph(resultSet.getLong("p1id"), resultSet.getString("p1topic"), resultSet.getString("p1text")));
        l.add(new Paragraph(resultSet.getLong("p2id"), resultSet.getString("p2topic"), resultSet.getString("p2text")));
        l.add(new Paragraph(resultSet.getLong("p3id"), resultSet.getString("p3topic"), resultSet.getString("p3text")));
        return new DailyHoroscope(
                Date.valueOf(LocalDate.parse(resultSet.getString("prediction_date"))), // string to LocalDate to sql.Date
                AstroSign.valueOf(resultSet.getString("astrosign")),
                l

        );
    }


    @Override
    public DailyHoroscope save(DailyHoroscope dailyHoroscope) {
        var sql = """
                INSERT INTO dailyHoroscope(prediction_date, astrosign, topic1, topic2, topic3)
                VALUES ( ? , ? , ? , ? , ? )
                  """;
        jdbcTemplate.update(
                sql,
                dailyHoroscope.getDate(),//prediction_date
                dailyHoroscope.getSign().toString(),//astrosign
                dailyHoroscope.getParagraphs().get(0).getId(),//topic1
                dailyHoroscope.getParagraphs().get(1).getId(),//topic2
                dailyHoroscope.getParagraphs().get(2).getId()//topic3
        );
        return dailyHoroscope;
    }

    public void delete(LocalDate date, AstroSign astroSign){
        Date sqldate =Date.valueOf(date);
        var sql = """
                DELETE FROM dailyHoroscope 
                WHERE prediction_date = ? AND astrosign = ?        
                """;
        jdbcTemplate.update(sql, sqldate, astroSign.toString());
    }
}
