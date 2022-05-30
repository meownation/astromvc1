package com.astromvc1.paragraph;

public class Paragraph {
    private long id;
    private String topic;
    private String text;

    public String getText() {

        return text;
    }
    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Paragraph(Long id,String topic,String text) {
        this.id=id;
        this.topic= topic;
        this.text = text;
    }

    public Paragraph() {
    }
}
