package com.astromvc1.daily;

import org.springframework.jdbc.core.JdbcTemplate;

public class ParagraphRepositoryImpl implements ParagraphRepositoryDao {

    private final JdbcTemplate jdbcTemplate;

    public ParagraphRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public void deleteParagraph() {

    }

    @Override
    public void createParagraph() {

    }

    @Override
    public void updateParagraph() {

    }

    @Override
    public void readParagraph() {

    }

    @Override
    public void selectParagraphById() {

    }
}