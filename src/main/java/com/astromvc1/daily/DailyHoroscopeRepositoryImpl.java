package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;
@Repository
public class DailyHoroscopeRepositoryImpl implements DailyHoroscopeDao{

    private final JdbcTemplate jdbcTemplate;

    public DailyHoroscopeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DailyHoroscopeResult> getDailyHoroscopeResult(Date date, AstroSign sign) {
        var sql = """
                     SELECT prediction_date, astrosign, payload
                     FROM daily
                     WHERE prediction_date = ? AND astrosign = ?
                     """;
         return jdbcTemplate.query(sql,new DailyRowMapper(),date, sign.toString())
                 .stream()
                 .findFirst();
    }
}
