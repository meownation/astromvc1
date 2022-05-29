package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
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
        var sql = """
                     SELECT prediction_date, astrosign, topic1, topic2, topic3
                     FROM dailyHoroscopes 
                     JOIN paragraph AS paragraph1 ON topic1=paragraph1.id 
                     JOIN paragraph AS paragraph2 ON topic2=paragraph2.id 
                     JOIN paragraph AS paragraph3 ON topic3=paragraph3.id
                     WHERE prediction_date = ? AND astrosign = ?
                     """;
         return jdbcTemplate.query(sql,this::mapRow,date, sign.toString())
                 .stream()
                 .findFirst();
    }

    private DailyHoroscope mapRow(ResultSet resultSet, int i) throws SQLException {
        List<Paragraph> l = new ArrayList<>();
        l.add(new Paragraph("1", resultSet.getString("topic1")));
        l.add(new Paragraph("2", resultSet.getString("topic2")));
        l.add(new Paragraph("3", resultSet.getString("topic3")));
        return new DailyHoroscope(
                Date.valueOf(LocalDate.parse(resultSet.getString("prediction_date"))), // string to LocalDate to sql.Date
                AstroSign.valueOf(resultSet.getString("astrosign")),
                l

        );
    }

    @Override
    public void insertDailyResult(DailyHoroscope dailyHoroscope) {
        var sql = """
                INSERT INTO dailyHoroscopes(prediction_date, astrosign, payload)
                VALUES ( ? , ? , ? );
                  """;
        jdbcTemplate.update(
                sql,
                dailyHoroscope.getDate(),
                dailyHoroscope.getSign().toString(),
                dailyHoroscope.getPayload());
    }
}
