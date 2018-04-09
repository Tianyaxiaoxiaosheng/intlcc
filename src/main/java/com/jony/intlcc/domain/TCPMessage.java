package com.jony.intlcc.domain;

/**
 * Created by jony on 3/27/18.
 */
public class TCPMessage {

    private TCPMessageType type;
    private String content;


    public TCPMessage() {
    }

    public TCPMessage(TCPMessageType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TCPMessageType getType() {
        return type;
    }

    public void setType(TCPMessageType type) {
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
        return "TCPMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
