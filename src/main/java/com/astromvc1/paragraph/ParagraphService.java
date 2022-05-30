package com.astromvc1.paragraph;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParagraphService {
    private final ParagraphRepositoryImpl paragraphRepository;

    public ParagraphService(ParagraphRepositoryImpl paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    public void storeParagraph(Paragraph paragraph){
        paragraphRepository.createParagraph(paragraph);
    }

    public List<Paragraph> generateParagraphs(){
        List<Paragraph> lp = new ArrayList<>();
        lp.add(paragraphRepository.getRandomParagraphByTopic("Posao").get());
        lp.add(paragraphRepository.getRandomParagraphByTopic("Zdravlje").get());
        lp.add(paragraphRepository.getRandomParagraphByTopic("Ljubav").get());
        return lp;
    }
}
