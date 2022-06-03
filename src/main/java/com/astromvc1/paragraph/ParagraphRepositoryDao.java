package com.astromvc1.paragraph;

import java.util.List;
import java.util.Optional;

public interface ParagraphRepositoryDao {

    void deleteParagraph(Long id);
    void createParagraph(Paragraph paragraph);
    void updateParagraph();
    Optional<Paragraph> getRandomParagraphByTopic(String topic);
    void selectParagraphById();

    List<Paragraph> getAllParagraphs();



}
