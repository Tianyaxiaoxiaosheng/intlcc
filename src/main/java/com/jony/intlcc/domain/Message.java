package com.jony.intlcc.domain;

/**
 * Created by jony on 3/27/18.
 */
public class Message {

    private MessageType type;
    private String content;


    public Message() {
    }

    public Message(MessageType type, String content) {
        this.type = type;
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
