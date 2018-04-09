package com.jony.intlcc.domain;

/**
 * Created by jony on 3/27/18.
 */
public class ControlMessage {


    private String clientId;
    private String rcuIp;
    private int rcuPort;
    private String content;

    public ControlMessage() {
    }

    public ControlMessage(String clientId, String rcuIp, int rcuPort, String content) {
        this.clientId = clientId;
        this.rcuIp = rcuIp;
        this.rcuPort = rcuPort;
        this.content = content;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRcuIp() {
        return rcuIp;
    }

    public void setRcuIp(String rcuIp) {
        this.rcuIp = rcuIp;
    }

    public int getRcuPort() {
        return rcuPort;
    }

    public void setRcuPort(int rcuPort) {
        this.rcuPort = rcuPort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ControlMessage{" +
                "clientId='" + clientId + '\'' +
                ", rcuIp='" + rcuIp + '\'' +
                ", rcuPort=" + rcuPort +
                ", content='" + content + '\'' +
                '}';
    }
}
