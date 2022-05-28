package com.astromvc1.daily;

import com.astromvc1.model.AstroSign;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DailyRowMapper implements RowMapper<DailyHoroscopeResult> {
    @Override
    public DailyHoroscopeResult mapRow(ResultSet resultSet, int i) throws SQLException {
//        return new DailyHoroscopeResult(
//                resultSet.getInt("id"),
//                resultSet.getString("name"),
//                List.of(),
//                LocalDate.parse(resultSet.getString("release_date"))
//        );
          return new DailyHoroscopeResult(
                  resultSet.getString("payload"),
                  Date.valueOf(LocalDate.parse(resultSet.getString("prediction_date"))), // string to LocalDate to sql.Date
                  AstroSign.valueOf(resultSet.getString("astrosign"))


          );
    }

}
