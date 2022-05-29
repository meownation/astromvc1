package com.astromvc1.daily;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParagraphRowMapper implements RowMapper<Paragraph> {

    @Override
    public Paragraph mapRow(ResultSet rs, int rowNum) throws SQLException {

       return new Paragraph(
               rs.getString("topic"),
                rs.getString("text")
        );
    }
}
