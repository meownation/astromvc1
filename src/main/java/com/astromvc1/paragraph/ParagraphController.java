package com.astromvc1.paragraph;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paragraph")
public class ParagraphController {
    private final ParagraphService paragraphService;

    public ParagraphController(ParagraphService paragraphService) {
        this.paragraphService = paragraphService;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addParagraph(@RequestBody Paragraph paragraph){
        paragraphService.storeParagraph(paragraph);
    }

    @GetMapping
    public List<Paragraph> getAllParagraphs(){
        return paragraphService.getAllParagraphs();
    }

    @DeleteMapping
    public void deleteParagraph(Long id){

        paragraphService.deleteParagraph(id);
    }
}
