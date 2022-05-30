package com.astromvc1.paragraph;

import java.util.Optional;

public interface ParagraphRepositoryDao {

    void deleteParagraph();
    void createParagraph(Paragraph paragraph);
    void updateParagraph();
    Optional<Paragraph> getRandomParagraphByTopic(String topic);
    void selectParagraphById();



}
