package com.astromvc1.daily;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParagraphService {
    private ParagraphRepositoryImpl paragraphRepository;

    public ParagraphService(ParagraphRepositoryImpl paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    List<Paragraph> generateParagraphs(){
        List<Paragraph> lp = new ArrayList<>();
        lp.add(paragraphRepository.randomParagraph("Posao").get());
        lp.add(paragraphRepository.randomParagraph("Zdravlje").get());
        lp.add(paragraphRepository.randomParagraph("Ljubav").get());
        return lp;
    }
}
