package com.astromvc1.daily;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParagraphService {
    private ParagraphRepositoryImpl paragraphRepository;

    public ParagraphService(ParagraphRepositoryImpl paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    List<Optional<Paragraph>> generateParagraphs(){
        List<Optional<Paragraph>> lp = new ArrayList<>();
        lp.add(paragraphRepository.randomParagraph("Posao"));
        lp.add(paragraphRepository.randomParagraph("Zdravlje"));
        lp.add(paragraphRepository.randomParagraph("Ljubav"));
        return lp;
    }
}
