package com.astromvc1.daily;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return jdbcTemplate.query(sql,new ParagraphRowMapper(),topic).stream().findFirst();

    }

    @Override
    public void selectParagraphById() {

    }
}
