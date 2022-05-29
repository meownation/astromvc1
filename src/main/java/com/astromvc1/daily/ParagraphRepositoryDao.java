package com.astromvc1.daily;

import java.util.Optional;

public interface ParagraphRepositoryDao {

    void deleteParagraph();
    void createParagraph(Paragraph paragraph);
    void updateParagraph();
    Optional<Paragraph> randomParagraph(String topic);
    void selectParagraphById();



}
