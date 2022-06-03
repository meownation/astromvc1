package com.astromvc1.paragraph;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ParagraphRepositoryImpl implements ParagraphRepositoryDao {

    private final JdbcTemplate jdbcTemplate;

    public ParagraphRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public void deleteParagraph(Long id) {
        var sql= """
                DELETE FROM paragraph
                WHERE id = ?
                
                """;
        jdbcTemplate.update(sql,id);
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
    public Optional<Paragraph> getRandomParagraphByTopic(String topic) {
        var sql = """
                SELECT id,topic,text FROM paragraph
                WHERE topic = ?
                ORDER BY RANDOM()
                LIMIT 1 
                """;
        return jdbcTemplate.query(sql,this::mapRow,topic).stream().findFirst();

    }

    @Override
    public List<Paragraph> getAllParagraphs() {
        var sql = """
                SELECT * FROM paragraph
                LIMIT 100
              
                """;
        return new ArrayList<>(jdbcTemplate.query(sql, this::mapRow));
    }

    private Paragraph mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Paragraph(
                rs.getLong("id"),
                rs.getString("topic"),
                rs.getString("text")
        );
    }

    @Override
    public void selectParagraphById() {

    }
}
