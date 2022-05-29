package com.astromvc1.daily;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ParagraphRepositoryImpl implements ParagraphRepositoryDao {

    private final JdbcTemplate jdbcTemplate;

    public ParagraphRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public void deleteParagraph() {

    }

    @Override
    public void createParagraph(Paragraph paragraph) {
        var sql = """
                INSERT INTO paragraph(topic, text)
                VALUES ( ? , ? );
                """;
        jdbcTemplate.update(sql,paragraph.getTopic(), paragraph.getText());
    }

    @Override
    public void updateParagraph() {

    }

    @Override
    public Optional<Paragraph> randomParagraph(String topic) {
        var sql = """
                SELECT topic,text FROM paragraph
                WHERE topic = ?
                ORDER BY RANDOM()
                LIMIT 1 
                """;
        return jdbcTemplate.query(sql,this::mapRow,topic).stream().findFirst();

    }
    private Paragraph mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Paragraph(
                rs.getString("topic"),
                rs.getString("text")
        );
    }

    @Override
    public void selectParagraphById() {

    }
}
