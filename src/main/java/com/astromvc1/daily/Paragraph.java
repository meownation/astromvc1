package com.astromvc1.daily;

public class Paragraph {
    private String topic;
    private String text;

    public String getText() {

        return text;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Paragraph(String topic,String text) {
        this.topic= topic;
        this.text = text;
    }

    public Paragraph() {
    }
}
